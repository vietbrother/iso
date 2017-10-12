/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.service;

import com.iso.dashboard.dao.SecurityLevelDAO;
import com.iso.dashboard.dao.UserDAO;
import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.dto.SecurityLevel;
import com.iso.dashboard.dto.Users;
import java.util.List;

/**
 *
 * @author 
 */
public class SecurityLevelMngService {

    private static SecurityLevelMngService service;

    public static SecurityLevelMngService getInstance() {
        if (service == null) {
            service = new SecurityLevelMngService();
        }
        return service;
    }

    public ResultDTO addUser(Users p) {
        return UserDAO.getInstance().addUsers(p);
    }

    public ResultDTO updateUser(Users p) {
        return UserDAO.getInstance().updateUsers(p);
    }

    public List<SecurityLevel> listUsers(String username) {
        return SecurityLevelDAO.getInstance().listUsers(username);
    }

    public Users getUserById(String id) {
        return UserDAO.getInstance().getUsersById(String.valueOf(id));
    }

    public ResultDTO removeUser(String id) {
        return UserDAO.getInstance().removeUsers(id);
    }

}
