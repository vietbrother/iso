/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.service;

import com.iso.dashboard.dao.DocumentDAO;
import com.iso.dashboard.dao.FileDAO;
import com.iso.dashboard.dao.UserDAO;
import com.iso.dashboard.dto.DocumentDTO;
import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.dto.Users;
import java.util.List;

/**
 *
 * @author 
 */
public class DocumentMngService {

    private static DocumentMngService service;

    public static DocumentMngService getInstance() {
        if (service == null) {
            service = new DocumentMngService();
        }
        return service;
    }

    public ResultDTO addUser(DocumentDTO p) {
        return DocumentDAO.getInstance().addUsers(p);
    }

    public ResultDTO updateUser(DocumentDTO p) {
        return DocumentDAO.getInstance().updateUsers(p);
    }

    public List<DocumentDTO> listUsers(String fileName) {
        return DocumentDAO.getInstance().listUsers(fileName);
    }

    public DocumentDTO getUserById(String id) {
        return DocumentDAO.getInstance().getUsersById(String.valueOf(id));
    }

    public ResultDTO removeUser(String id) {
        return DocumentDAO.getInstance().removeUsers(id);
    }

}
