/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.service;

import com.iso.dashboard.dao.TaskOrgDAO;
import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.dto.TaskOrg;
import java.util.List;

/**
 *
 * @author VIET_BROTHER
 */
public class TaskOrgMngService {

    private static TaskOrgMngService service;

    public static TaskOrgMngService getInstance() {
        if (service == null) {
            service = new TaskOrgMngService();
        }
        return service;
    }

    public ResultDTO addTaskOrg(TaskOrg p) {
        return TaskOrgDAO.getInstance().addTaskOrg(p);
    }

    public ResultDTO updateTaskOrg(TaskOrg p) {
        return TaskOrgDAO.getInstance().updateTaskOrg(p);
    }

    public List<TaskOrg> listTaskOrg(String id) {
        return TaskOrgDAO.getInstance().listTaskOrg(id);
    }

    public TaskOrg getTaskOrgById(String id) {
        return TaskOrgDAO.getInstance().getTaskOrgById(String.valueOf(id));
    }

    public ResultDTO removeTaskOrg(String id) {
        return TaskOrgDAO.getInstance().removeTaskOrg(id);
    }

}
