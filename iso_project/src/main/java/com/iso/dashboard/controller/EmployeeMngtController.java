/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.controller;

import com.iso.dashboard.component.CustomGrid;
import com.iso.dashboard.dto.Employee;
import com.iso.dashboard.dto.Organization;
import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.service.EmployeeMngtService;
import com.iso.dashboard.service.JobMngtService;
import com.iso.dashboard.ui.EmployeeMngtUI;
import com.iso.dashboard.ui.OrgTreeSearchUI;
import com.iso.dashboard.utils.BundleUtils;
import com.iso.dashboard.utils.CommonExport;
import com.iso.dashboard.utils.ComponentUtils;
import com.iso.dashboard.utils.Constants;
import com.iso.dashboard.utils.DataUtil;
import com.iso.dashboard.utils.ISOIcons;
import com.iso.dashboard.view.EmployeeMngtView;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author Thuclt-VNPTTech
 */
public class EmployeeMngtController {

    EmployeeMngtView view;
    EmployeeMngtService service;

    CustomGrid pagedTable;
    Tree orgTree;

//    String[] headerName = new String[]{"Id", "Username", "Email", "Phone", ""};
    String prefix = "employeeMngt.list";//tien to trong file language
    // header.jobMngt=id#employeeCode#employeeName#birthday#email#mobile#jobTitle#department#action
    String headerKey = "header.employeeMngt";//lay trong file cas 
    String[] headerName = BundleUtils.getHeaderColumnName(prefix, headerKey);
    String[] headerColumn = BundleUtils.getHeaderColumn(headerKey);
    String employeeListLabel = "employeeMngt.list";
    Resource resource;

    public EmployeeMngtController(EmployeeMngtView view) {
        this.view = view;
        this.pagedTable = view.getPagedTable();
        this.orgTree = view.getOrgTree();
        initTable(EmployeeMngtService.getInstance().listEmployees(null));
        OrgTreeSearchUI.initTree(orgTree);
        doAction();
    }

    public void initTable(List<Employee> lstEmployees) {
//        pagedTable.addGeneratedColumn("btnAction", new Table.ColumnGenerator() {
//
//            public Component generateCell(Table source, Object itemId, Object columnId) {
//                Item item = source.getItem(itemId);
//                
//                // Edit Action
//                Button btnEdit = new Button();
//                btnEdit.setIcon(FontAwesome.EDIT);
//                btnEdit.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
//                btnEdit.setDescription(BundleUtils.getString("common.button.edit"));
//                btnEdit.addClickListener(new Button.ClickListener() {
//                    
//                    @Override
//                    public void buttonClick(Button.ClickEvent event) {
//                        
//                        String employeeId = (String) item.getItemProperty("id").getValue();
//                        Notification.show("Edit " + employeeId);
//                        Employee dto = EmployeeMngtService.getInstance().getEmployeeById(employeeId);
//                        onUpdate(dto);
//                        view.getBtnSearch().click();
//                    }
//                });
//                
//                // Delete Action
//                Button btnDelete = new Button();
//                btnDelete.setIcon(ISOIcons.DELETE);
//                btnDelete.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
//                btnDelete.setDescription(BundleUtils.getString("common.button.delete"));
//                btnDelete.addClickListener(new Button.ClickListener() {
//                    
//                    @Override
//                    public void buttonClick(Button.ClickEvent event) {
//                        ConfirmDialog d = ConfirmDialog.show(
//                                UI.getCurrent(),
//                                BundleUtils.getString("message.warning.title"),
//                                BundleUtils.getString("message.warning.content"),
//                                BundleUtils.getString("common.confirmDelete.yes"),
//                                BundleUtils.getString("common.confirmDelete.no"),
//                                new ConfirmDialog.Listener() {
//                            
//                            public void onClose(ConfirmDialog dialog) {
//                                if (dialog.isConfirmed()) {
//                                    // Confirmed to continue
//                                    String employeeId = (String) item.getItemProperty("id").getValue();
//                                    ResultDTO res = JobMngtService.getInstance().removeJob(employeeId);
//                                    ComponentUtils.showNotification("Delete id : " + employeeId + " " + res.getKey() + " " + res.getMessage());
//                                    view.getBtnSearch().click();
//                                }
//                            }
//                        });
//                        d.setStyleName(Reindeer.WINDOW_LIGHT);
//                        d.setContentMode(ConfirmDialog.ContentMode.HTML);
//                        d.getOkButton().setIcon(ISOIcons.SAVE);
//                        d.getCancelButton().setIcon(ISOIcons.CANCEL);
//                    }
//                });
//                
//                HorizontalLayout hori = new HorizontalLayout();
//                hori.addComponent(btnEdit);
//                hori.addComponent(btnDelete);
//                return hori;
//            }
//        });
//        reloadData(lstEmployees);
//        pagedTable.setSizeFull();
//        //pagedTable.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
////        pagedTable.setWidth("1000px");
//        pagedTable.setPageLength(10);
//        pagedTable.setImmediate(true);
//        pagedTable.setSelectable(true);
//        pagedTable.setAlwaysRecalculateColumnWidths(true);
//        pagedTable.setResponsive(true);
//        pagedTable.setColumnHeaders(headerName);
        IndexedContainer container = createContainer(lstEmployees);
        pagedTable.genGrid(container, prefix, headerColumn, null, new HandlerButtonActionGrid() {

            @Override
            public void actionEdit(Object obj) {
                Employee item = (Employee) obj;
                String employeeId = String.valueOf(item.getId());
                Notification.show("Edit " + employeeId);
                Employee dto = EmployeeMngtService.getInstance().getEmployeeById(employeeId);
                onUpdate(dto);
                view.getBtnSearch().click();
            }

            @Override
            public void actionDelete(Object obj) {
                ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(),
                        BundleUtils.getString("message.warning.title"),
                        BundleUtils.getString("message.warning.content"),
                        BundleUtils.getString("common.confirmDelete.yes"),
                        BundleUtils.getString("common.confirmDelete.no"),
                        new ConfirmDialog.Listener() {

                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    // Confirmed to continue
                                    Employee item = (Employee) obj;
                                    String employeeId = String.valueOf(item.getId());
                                    ResultDTO res = JobMngtService.getInstance().removeJob(employeeId);
                                    ComponentUtils.showNotification("Delete id : " + employeeId + " " + res.getKey() + " " + res.getMessage());
                                    view.getBtnSearch().click();
                                }
                            }
                        });
                d.setStyleName(Reindeer.WINDOW_LIGHT);
                d.setContentMode(ConfirmDialog.ContentMode.HTML);
                d.getOkButton().setIcon(ISOIcons.SAVE);
                d.getCancelButton().setIcon(ISOIcons.CANCEL);
            }

            @Override
            public void actionSelect(Object obj) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public void reloadData(List<Employee> lstEmployees) {
        pagedTable.setContainerDataSource(pagedTable.createWrapContainer(createContainer(lstEmployees)));
    }

    public IndexedContainer createContainer(List<Employee> lstEmployees) {
        IndexedContainer container = new IndexedContainer();
        // Table title
        container.addContainerProperty("btnAction", String.class, null);
        container.addContainerProperty("id", String.class, null);
        container.addContainerProperty("employeeCode", String.class, null);
        container.addContainerProperty("employeeName", String.class, null);
        container.addContainerProperty("birthday", String.class, null);
        container.addContainerProperty("email", String.class, null);
        container.addContainerProperty("mobile", String.class, null);
        container.addContainerProperty("jobTitle", String.class, null);
        container.addContainerProperty("department", String.class, null);
        // Table data grid
        for (Employee j : lstEmployees) {
            Item item = container.addItem(j);
            item.getItemProperty("id").setValue(String.valueOf(j.getId()));
            item.getItemProperty("employeeCode").setValue(j.getEmployeeCode());
            item.getItemProperty("employeeName").setValue((j.getFirstName() + " " + j.getLastName()));
            item.getItemProperty("birthday").setValue(j.getBirthday().toString());
            item.getItemProperty("email").setValue(j.getEmail());
            item.getItemProperty("mobile").setValue(j.getMobile());
            item.getItemProperty("jobTitle").setValue(j.getJob().getJobTitle());
            item.getItemProperty("department").setValue(j.getDepartment().getName());
        }
        container.sort(new Object[]{"id"}, new boolean[]{true});
        return container;
    }

    private void doAction() {
        view.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                onSearch();
            }
        });

        view.getBtnAdd().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                onInsert();
            }
        });

        view.getBtnExport().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                onExport();
            }
        });

        orgTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                Organization org = (Organization) event.getItemId();
                Integer orgId = org.getId();
                List<Employee> lstEmployees = EmployeeMngtService.getInstance().getEmployeeByOrganizationId(String.valueOf(orgId));
                Notification.show("lstEmployees : " + lstEmployees.size());
                reloadData(lstEmployees);
            }
        });

    }

    public boolean validateData(EmployeeMngtUI ui) {
        if (DataUtil.isNullOrEmpty(ui.getTxtEmployeeCode().getValue())) {
            Notification.show(BundleUtils.getString("employeeMngt.list.employee") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.notnull"));
            return false;
        }
        if (ui.getTxtEmployeeCode().getValue().length() > 20) {
            Notification.show(BundleUtils.getString("employeeMngt.list.employee") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.maxlength.20"));
            return false;
        }
        return true;
    }

    private void onInsert() {
        createDialog(true, new Employee());
    }

    private void onUpdate(Employee dto) {
        createDialog(false, dto);
    }

    private void onSearch() {
        List<Employee> lstEmployees = EmployeeMngtService.getInstance().listEmployees(view.getTxtEmployeeType().getValue());
        Notification.show("lstEmployees : " + lstEmployees.size());
        reloadData(lstEmployees);
    }

    private void onExport() {

        try {
            List<Employee> lstEmployeeTypes = EmployeeMngtService.getInstance().listEmployees(view.getTxtEmployeeType().getValue());
            String[] header = new String[]{"export_01", "export_02", "export_03"};
            String[] align = new String[]{"LEFT", "LEFT", "LEFT"};
            List<AbstractMap.SimpleEntry<String, String>> headerAlign = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
            for (int i = 0; i < header.length; i++) {
                headerAlign.add(new AbstractMap.SimpleEntry(header[i], align[i]));
            }
            String fileTemplate = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()
                    //+ File.separator + "WEB-INF"
                    //+ File.separator + "templates"
                    //+ File.separator + "incident"
                    //+ File.separator + "TEMPLATE_EXPORT.xls"
                    + Constants.FILE_CONF.PATH_EXPORT_TEMPLATE_XLSX;

            String subTitle = Constants.EMPTY_CHARACTER;

            File fileExport = CommonExport.exportFile(lstEmployeeTypes,//list du lieu
                    headerAlign,//header
                    //"userMngt.list",//header prefix
                    employeeListLabel,//header prefix
                    fileTemplate,//path template
                    BundleUtils.getString("userMngt.fileName.export"),//fileName out
                    7,//start row
                    subTitle,//sub title
                    4,//cell title Index
                    BundleUtils.getString("userMngt.report")//title
            );
            resource = new FileResource(fileExport);

            Page.getCurrent().open(resource, null, false);
        } catch (Exception e) {
        }
    }

    private void initDataDialog(EmployeeMngtUI ui, boolean isInsert, Employee dto) {

        if (isInsert) {

        } else {
            ui.getTxtEmployeeCode().setValue(dto.getEmployeeCode() == null ? "" : dto.getEmployeeCode());
            ui.getTxtEmployeeName().setValue(dto.getEmployeeCode() == null ? "" : (dto.getFirstName() + " " + dto.getLastName()));
            ui.getTxtBirthday().setValue(dto.getBirthday() == null ? "" : dto.getBirthday().toString());
            ui.getTxtEmail().setValue(dto.getEmail() == null ? "" : dto.getEmail());
            ui.getTxtMobile().setValue(dto.getMobile() == null ? "" : dto.getMobile());
            ui.getTxtDepartment().setValue(dto.getDepartment().getName() == null ? "" : dto.getDepartment().getName());
            ui.getTxtJobTitle().setValue(dto.getJob().getJobTitle() == null ? "" : dto.getJob().getJobTitle());
        }
    }

    public void createDialog(boolean isInsert, Employee dto) {
        EmployeeMngtUI ui = new EmployeeMngtUI(isInsert ? BundleUtils.getString("common.button.add") : BundleUtils.getString("common.button.edit"));
        Window window = new Window(
                "",
                ui);
        //window.setWidth("700px");
        float height = UI.getCurrent().getWidth() * 3 / 4;
        window.setWidth(String.valueOf(height) + "%");
//        window.setIcon(VaadinIcons.CALENDAR_USER);
        initDataDialog(ui, isInsert, dto);
        ui.getBtnSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                boolean validate = validateData(ui);
                if (validate) {
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
                                        ResultDTO res = null;
                                        getDataFromUI(ui, dto);
                                        if (isInsert) {
                                            res = EmployeeMngtService.getInstance().addEmployee(dto);
                                            ComponentUtils.showNotification(BundleUtils.getString("common.button.add") + " " + res.getKey() + " " + res.getMessage());
                                        } else {
                                            res = EmployeeMngtService.getInstance().updateEmployee(dto);
                                            ComponentUtils.showNotification(BundleUtils.getString("common.button.update") + " "
                                                    + res.getKey() + " " + res.getMessage());
                                        }
                                        window.close();
                                        view.getBtnSearch().click();
                                    } else {
                                        // User did not confirm
                                        Notification.show("nok");
                                        window.close();
                                    }
                                }
                            });
                    d.setStyleName(Reindeer.LAYOUT_BLUE);
                    d.setContentMode(ConfirmDialog.ContentMode.HTML);
                    d.getOkButton().setIcon(ISOIcons.SAVE);
                    d.getOkButton().focus();
                    d.getCancelButton().setIcon(ISOIcons.CANCEL);
                }
            }

        });
        ui.getBtnCancel().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                window.close();
            }
        });

        ui.setWidth("100%");
        ui.setHeight(Constants.STYLE_CONF.AUTO_VALUE);

        window.setModal(true);
        DataUtil.reloadWindow(window);
        UI.getCurrent().addWindow(window);
        ui.getTxtEmployeeCode().focus();
    }

    private void getDataFromUI(EmployeeMngtUI ui, Employee dto) {
        dto.setEmployeeCode(ui.getTxtEmployeeCode().getValue().trim());
    }

}
