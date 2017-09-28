/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.controller;

import com.iso.dashboard.component.CustomPageTable;
import com.iso.dashboard.dto.Organization;
import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.dto.Users;
import com.iso.dashboard.service.UserMngService;
import com.iso.dashboard.ui.OrgTreeSearchUI;
import com.iso.dashboard.utils.BundleUtils;
import com.iso.dashboard.utils.ComponentUtils;
import com.iso.dashboard.utils.ISOIcons;
import com.iso.dashboard.view.TaskOrgMngtView;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author VIET_BROTHER
 */
public class TaskOrgMngtController {

    TaskOrgMngtView view;
    CustomPageTable pagedTable;
    Tree orgTree;

    String prefix = "taskOrgMngt.list";//tien to trong file language
    String headerKey = "header.taskOrgMngt";//lay trong file cas
    String[] headerName = BundleUtils.getHeaderColumnName(prefix, headerKey);
    String[] headerVisible = BundleUtils.getHeaderColumnVisible(headerKey);
    String orgTaskListLabel = "taskOrgMngt.list";

    public TaskOrgMngtController(TaskOrgMngtView view) {
        this.view = view;
        this.pagedTable = view.getPagedTable();
        this.orgTree = view.getOrgTree();
        initTable(UserMngService.getInstance().listUsers(null));
        OrgTreeSearchUI.initTree(orgTree);
        doAction();
    }

    public void initTable(List<Users> lstUsers) {

        pagedTable.addGeneratedColumn("action", new Table.ColumnGenerator() {
//            private static final long serialVersionUID = -5042109683675242407L;

            public Component generateCell(Table source, Object itemId, Object columnId) {
                Item item = source.getItem(itemId);

                Button btnEdit = new Button();
                btnEdit.setIcon(FontAwesome.EDIT);
                btnEdit.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
                btnEdit.setDescription(BundleUtils.getString("common.button.edit"));
                btnEdit.addClickListener(new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        String userId = (String) item.getItemProperty("id").getValue();
                        Notification.show("Edit " + userId);
//                        Users dto = UserMngService.getInstance().getUserById(userId);
//                        onUpdate(dto);
//                        view.getBtnSearch().click();
                    }
                });
                Button btnDelete = new Button();
                btnDelete.setIcon(ISOIcons.DELETE);
                btnDelete.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
                btnDelete.setDescription(BundleUtils.getString("common.button.delete"));
                btnDelete.addClickListener(new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        ConfirmDialog d = ConfirmDialog.show(
                                UI.getCurrent(),
                                BundleUtils.getString("message.warning.title"),
                                BundleUtils.getString("message.warning.content"),
                                BundleUtils.getString("common.confirmDelete.yes"),
                                BundleUtils.getString("common.confirmDelete.no"),
                                new ConfirmDialog.Listener() {

                                    public void onClose(ConfirmDialog dialog) {
                                        if (dialog.isConfirmed()) {
                                            // Confirmed to continue
//                                            String userId = (String) item.getItemProperty("id").getValue();
//                                            ResultDTO res = UserMngService.getInstance().removeUser(userId);
//                                            ComponentUtils.showNotification("Delete id : " + userId + " " + res.getKey() + " " + res.getMessage());
                                            view.getBtnSearch().click();
                                        }
                                    }
                                });
                        d.setStyleName(Reindeer.WINDOW_LIGHT);
                        d.setContentMode(ConfirmDialog.ContentMode.HTML);
                        d.getOkButton().setIcon(ISOIcons.SAVE);
                        d.getCancelButton().setIcon(ISOIcons.CANCEL);
                    }
                });

                HorizontalLayout hori = new HorizontalLayout();
                hori.addComponent(btnEdit);
                hori.addComponent(btnDelete);
                return hori;
            }
        });
        reloadData(lstUsers);
        pagedTable.setSizeFull();
        //pagedTable.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
//        pagedTable.setWidth("1000px");
        pagedTable.setPageLength(10);
        pagedTable.setImmediate(true);
        pagedTable.setSelectable(true);
        pagedTable.setAlwaysRecalculateColumnWidths(true);
        pagedTable.setResponsive(true);
        pagedTable.setColumnHeaders(headerName);
        pagedTable.setVisibleColumns((Object[]) headerVisible);
    }

    public IndexedContainer createContainer(List<Users> lstUser) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("stt", Integer.class, null);
        container.addContainerProperty("id", String.class, null);
        container.addContainerProperty("name", String.class, null);
//        container.addContainerProperty("email", String.class, null);
//        container.addContainerProperty("phone", String.class, null);

        int i = 0;
        for (Users u : lstUser) {
            Item item = container.addItem(u);
            item.getItemProperty("stt").setValue(i);
            i++;
            item.getItemProperty("id").setValue(String.valueOf(u.getId()));
            item.getItemProperty("name").setValue(u.getUsername());
//            item.getItemProperty("email").setValue(u.getEmail());
//            item.getItemProperty("phone").setValue(u.getPhone());
        }
        container.sort(new Object[]{"id"}, new boolean[]{true});
        return container;
    }

    public void reloadData(List<Users> lstUsers) {
        pagedTable.setContainerDataSource(createContainer(lstUsers));
    }

    public void doAction() {
        orgTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                Organization org = (Organization) event.getItemId();
                Notification.show(org.toString());
            }
        });
    }
}
