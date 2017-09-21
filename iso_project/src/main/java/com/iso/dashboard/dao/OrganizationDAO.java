/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.dao;

import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.dto.Organization;
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
public class OrganizationDAO extends BaseDAO {

    private static OrganizationDAO dao;

    public static OrganizationDAO getInstance() {
        if (dao == null) {
            dao = new OrganizationDAO();
        }
        return dao;
    }

    public List<Organization> listOrganization(String id, String parentId) {
        List<Organization> listOrganization = new ArrayList<>();
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            String sql = "FROM Organization org where 1 = 1 "
                    + (DataUtil.isNullOrEmpty(id) ? "" : ("and org.id = ? "))
                    + (DataUtil.isNullOrEmpty(parentId) ? "" : ("and org.parentId = ? "))
                    + "ORDER BY org.id ASC";
            Query query = session.createQuery(sql);
            int i = 0;
            if (!DataUtil.isNullOrEmpty(id)) {
                query.setParameter(i, Integer.valueOf(id));
                i++;
            }
            if (!DataUtil.isNullOrEmpty(parentId)) {
                query.setParameter(i, Integer.valueOf(parentId));
            }
            listOrganization = (List<Organization>) query.list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOrganization;
    }

    public ResultDTO addOrganization(Organization addObj) {
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

    public ResultDTO updateOrganization(Organization newData) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            Organization updateObj = (Organization) session.get(Organization.class, Integer.valueOf(newData.getId()));
            updateObj.setName(newData.getName());
            updateObj.setCode(newData.getCode());
            updateObj.setDescription(newData.getDescription());
            updateObj.setValue(newData.getValue());

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

    public ResultDTO removeOrganization(String id) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            Organization org = (Organization) session.get(Organization.class, Integer.valueOf(id));
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

    public Organization getOrganizationById(String id) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        Organization org = null;
        try {
            session = getSession();
            session.beginTransaction();
            org = (Organization) session.get(Organization.class, Integer.valueOf(id));
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
