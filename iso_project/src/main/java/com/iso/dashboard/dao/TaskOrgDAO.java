/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.dao;

import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.dto.TaskOrg;
import com.iso.dashboard.utils.Constants;
import com.iso.dashboard.utils.DataUtil;
import com.iso.dashboard.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author VIET_BROTHER
 */
public class TaskOrgDAO extends BaseDAO {

    private static TaskOrgDAO dao;

    public static TaskOrgDAO getInstance() {
        if (dao == null) {
            dao = new TaskOrgDAO();
        }
        return dao;
    }

    public List<TaskOrg> listTaskOrg(String id) {
        List<TaskOrg> listTaskOrg = new ArrayList<>();
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            String sql = "FROM TaskOrg org where 1 = 1 "
                    + (DataUtil.isNullOrEmpty(id) ? "" : ("and org.taskId = ? "))
                    + "ORDER BY org.id ASC";
            Query query = session.createQuery(sql);
            int i = 0;
            if (!DataUtil.isNullOrEmpty(id)) {
                query.setParameter(i, Integer.valueOf(id));
                i++;
            }
            listTaskOrg = (List<TaskOrg>) query.list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTaskOrg;
    }

    public ResultDTO addTaskOrg(TaskOrg addObj) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            int id = (Integer) session.save(addObj);
            //session.flush();
            session.getTransaction().commit();
            session.close();
            res.setId(String.valueOf(id));
            res.setKey(Constants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public ResultDTO updateTaskOrg(TaskOrg newData) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            TaskOrg updateObj = (TaskOrg) session.get(TaskOrg.class, Integer.valueOf(newData.getTaskId()));
            updateObj.setAttachment(newData.getAttachment());
            updateObj.setBudget(newData.getBudget());
            updateObj.setCurrency(newData.getCurrency());
            updateObj.setDepartment_id(newData.getDepartment_id());
            updateObj.setEnd_time(newData.getEnd_time());
            updateObj.setProcess_user_id(newData.getProcess_user_id());
            updateObj.setProgress(newData.getProgress());
            updateObj.setStatus(newData.getStatus());
            updateObj.setTaskName(newData.getTaskName());
            updateObj.setTask_content(newData.getTask_content());

            session.update(updateObj);
            //session.flush();
            session.getTransaction().commit();
            session.close();
            res.setKey(Constants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(e.getMessage());
            HibernateUtil.getSessionFactory().openSession();

        }
        return res;
    }

    public ResultDTO removeTaskOrg(String id) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            TaskOrg org = (TaskOrg) session.get(TaskOrg.class, Integer.valueOf(id));
            session.delete(org);
            session.getTransaction().commit();
            session.close();
//            session.flush();
            res.setKey(Constants.SUCCESS);
        } catch (Exception e) {
            res.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    public TaskOrg getTaskOrgById(String id) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        TaskOrg org = null;
        try {
            session = getSession();
            session.beginTransaction();
            org = (TaskOrg) session.get(TaskOrg.class, Integer.valueOf(id));
            session.getTransaction().commit();
            session.close();
            res.setKey(Constants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(e.getMessage());
        }
        return org;
    }

}
