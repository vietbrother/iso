/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.dao;

import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.dto.SecurityLevel;
import com.iso.dashboard.dto.Users;
import com.iso.dashboard.utils.Constants;
import com.iso.dashboard.utils.DataUtil;
import com.iso.dashboard.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 
 */
public class SecurityLevelDAO extends BaseDAO {

    private static SecurityLevelDAO dao;

    public static SecurityLevelDAO getInstance() {
        if (dao == null) {
            dao = new SecurityLevelDAO();
        }
        return dao;
    }

    public List<SecurityLevel> listUsers(String username) {
        List<SecurityLevel> listUsers = new ArrayList<>();
        Session session = null;
        try {
            session = getSession();
            String sql = "FROM SecurityLevel u ";
//                    + (DataUtil.isNullOrEmpty(username) ? "" : ("where LOWER(u.username) like ? "))
//                    + "ORDER BY u.username ASC";
            Query query = session.createQuery(sql);            
//            if (!DataUtil.isNullOrEmpty(username)) {
//                query.setParameter(0, "%" + username.toLowerCase() + "%");
//            }
            listUsers = (List<SecurityLevel>) query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listUsers;
    }

    public ResultDTO addUsers(Users p) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            int id = (Integer) session.save(p);
            //session.flush();
//            getTransaction().commit();
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

    public ResultDTO updateUsers(Users newData) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            Users u = (Users) session.get(Users.class, Integer.valueOf(newData.getId()));
            u.setUsername(newData.getUsername());
            u.setFirstName(newData.getFirstName());
            u.setLastName(newData.getLastName());
            u.setEmail(newData.getEmail());
            u.setBirthDay(newData.getBirthDay());
            u.setPhone(newData.getPhone());
            u.setRole(newData.getRole());
            session.update(u);
            //session.flush();
//            getTransaction().commit();
            session.getTransaction().commit();
            session.close();
            res.setKey(Constants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public ResultDTO removeUsers(String id) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        try {
            session = getSession();
            session.beginTransaction();
            Users u = (Users) session.get(Users.class, Integer.valueOf(id));
            session.delete(u);
            //session.flush();
//            getTransaction().commit();
            session.getTransaction().commit();
            session.close();
            res.setKey(Constants.SUCCESS);
        } catch (Exception e) {
            res.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    public Users getUsersById(String id) {
        ResultDTO res = new ResultDTO(Constants.FAIL, "");
        Session session = null;
        Users users = null;
        try {
            session = getSession();
            session.beginTransaction();
            users = (Users) session.get(Users.class, Integer.valueOf(id));
            session.getTransaction().commit();
            session.close();
            res.setKey(Constants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(e.getMessage());
        }
        return users;
    }

}
