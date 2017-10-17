/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.controller;

import com.iso.dashboard.component.CustomGrid;
import com.iso.dashboard.component.CustomPageTable;
import com.iso.dashboard.dto.CTask;
import com.iso.dashboard.dto.CTaskAssignee;
import com.iso.dashboard.dto.CTaskAttachment;
import com.iso.dashboard.dto.CTaskAttachmentPK;
import com.iso.dashboard.dto.CTaskGroup;
import com.iso.dashboard.dto.CTaskPriority;
import com.iso.dashboard.dto.CatItemDTO;
import com.iso.dashboard.dto.Organization;
import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.service.OrganizationMngService;
import com.iso.dashboard.service.TaskAttachmentService;
import com.iso.dashboard.service.TaskGroupMgntService;
import com.iso.dashboard.service.TaskOrgMngService;
import com.iso.dashboard.service.TaskPriorityMgntService;
import com.iso.dashboard.ui.OrgTreeSearchUI;
import com.iso.dashboard.ui.TaskAddUI;
import com.iso.dashboard.utils.BundleUtils;
import com.iso.dashboard.utils.ComponentUtils;
import com.iso.dashboard.utils.Constants;
import com.iso.dashboard.utils.DataUtil;
import com.iso.dashboard.utils.ISOIcons;
import com.iso.dashboard.view.TaskAttachmentView;
import com.iso.dashboard.view.TaskOrgMngtView;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.ProgressBarRenderer;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.gridutil.converter.SimpleStringConverter;

/**
 *
 * @author VIET_BROTHER
 */
public class TaskOrgMngtController {

    TaskOrgMngtView view;
    CustomGrid pagedTable;
    Tree orgTree;

    String prefix = "taskOrgMngt.list";//tien to trong file language
    String headerKey = "header.taskOrgMngt";//lay trong file cas
    String[] headerName = BundleUtils.getHeaderColumnName(prefix, headerKey);
    String[] headerColumn = BundleUtils.getHeaderColumn(headerKey);
    String[] headerVisible = BundleUtils.getHeaderColumnVisible(headerKey);
    String orgTaskListLabel = "taskOrgMngt.list";

    public TaskOrgMngtController(TaskOrgMngtView view) {
        this.view = view;
        this.pagedTable = view.getPagedTable();
        this.orgTree = view.getOrgTree();
        initTable(new ArrayList<>());
        OrgTreeSearchUI.initTree(orgTree);
        doAction();
    }

    public void initTable(List<CTask> cTasks) {
//        pagedTable.addGeneratedColumn("index", (final Table source, final Object itemId, final Object columnId) -> {
//            Container.Indexed container = (Container.Indexed) source.getContainerDataSource();
//            return Integer.toString(container.indexOfId(itemId) + 1);
//        });
//
//        pagedTable.addGeneratedColumn("progress", (Table source, Object itemId, Object columnId) -> {
//            Float value = source.getItem(itemId).getItemProperty("progress").getValue() != null ? (Float) source.getItem(itemId).getItemProperty("progress").getValue() : 0f;
//            ProgressBar bar = new ProgressBar();
//            bar.setIndeterminate(true);
//            bar.setDescription(Float.floatToIntBits(value) + "%");
//            bar.setValue(value / 100);
//            return bar;
//        });
//        pagedTable.addGeneratedColumn("action", new Table.ColumnGenerator() {
////            private static final long serialVersionUID = -5042109683675242407L;
//
//            public Component generateCell(Table source, Object itemId, Object columnId) {
//                Item item = source.getItem(itemId);
//
//                Button btnEdit = new Button();
//                btnEdit.setIcon(FontAwesome.EDIT);
//                btnEdit.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
//                btnEdit.setDescription(BundleUtils.getString("common.button.edit"));
//                btnEdit.addClickListener(new Button.ClickListener() {
//
//                    @Override
//                    public void buttonClick(Button.ClickEvent event) {
//                        CTask cTask = (CTask) itemId;
//                        createDialog(false, cTask, cTask.getDepartmentId());
//                    }
//                });
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
//                                    public void onClose(ConfirmDialog dialog) {
//                                        if (dialog.isConfirmed()) {
//                                            // Confirmed to continue
////                                            String userId = (String) item.getItemProperty("id").getValue();
////                                            ResultDTO res = UserMngService.getInstance().removeUser(userId);
////                                            ComponentUtils.showNotification("Delete id : " + userId + " " + res.getKey() + " " + res.getMessage());
//                                            view.getBtnSearch().click();
//                                        }
//                                    }
//                                });
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
//        reloadData(cTasks);
//        pagedTable.setSizeFull();
//        //pagedTable.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
////        pagedTable.setWidth("1000px");
//        pagedTable.setPageLength(10);
//        pagedTable.setImmediate(true);
//        pagedTable.setSelectable(true);
//        pagedTable.setAlwaysRecalculateColumnWidths(true);
//        pagedTable.setResponsive(true);
//        pagedTable.setColumnHeaders(headerName);
////		pagedTable.setVisibleColumns("index", "action", "name", "startTime", "endTime", "progress", "status");
//        pagedTable.setVisibleColumns((Object[]) headerVisible);

        IndexedContainer container = createContainer(cTasks);
        pagedTable.genGrid(container, prefix, headerColumn, null, new HandlerButtonActionGrid() {

            @Override
            public void actionEdit(Object obj) {
                CTask cTask = (CTask) obj;
                createDialog(false, cTask, cTask.getDepartmentId());
            }

            @Override
            public void actionDelete(Object obj) {
                ConfirmDialog d = ConfirmDialog.show(
                        UI.getCurrent(),
                        BundleUtils.getString("message.warning.title"),
                        BundleUtils.getString("message.warning.content"),
                        BundleUtils.getString("common.confirmDelete.yes"),
                        BundleUtils.getString("common.confirmDelete.no"),
                        new ConfirmDialog.Listener() {

                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
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
//        pagedTable.getColumn("progress").setRenderer(new ProgressBarRenderer());
        pagedTable.getColumn("progress").setRenderer(new HtmlRenderer(), new Converter<String, String>() {

            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "not implemented";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "<div class=\"progress\" "
//                        + "style=\"background-color: #D5D5C9;border-radius: 8px;width: 100%;height: 5px;vertical-align: middle;\""
                        + ">\n"
                        + "    <div class=\"progress-bar\" "
//                        + "style=\"background-color: #29D110; border-radius: 8px; height: 100%;\" "
                        + "role=\"progressbar\" "
                                + "aria-valuenow=\"" + value + "\" "
                                + "aria-valuemin=\"0\" "
                                + "aria-valuemax=\"100\" "
                                + "style=\"width:" + value+ "%\">\n"
//                        + "      <p style=\"padding-left: 16px; margin-top: 0px;\">" + value + "%</p>\n"
                        + "      <p style=\"margin-top: 0px;\">" + value + "%</p>\n"
                        + "    </div>\n"
                        + "  </div>";
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public IndexedContainer createContainer(List<CTask> cTasks) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("name", String.class, null);
        container.addContainerProperty("startTime", String.class, null);
        container.addContainerProperty("endTime", String.class, null);
//        container.addContainerProperty("progress", Double.class, null);
        container.addContainerProperty("progress", String.class, null);
        container.addContainerProperty("status", String.class, null);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (CTask u : cTasks) {
            Item item = container.addItem(u);
            item.getItemProperty("name").setValue(u.getTaskName() != null ? u.getTaskName() : "");
            item.getItemProperty("startTime").setValue(u.getStartTime() != null ? sdf.format(u.getStartTime()) : "");
            item.getItemProperty("endTime").setValue(u.getEndTime() != null ? sdf.format(u.getEndTime()) : "");
//            item.getItemProperty("progress").setValue(u.getRate() != null ? Float.intBitsToFloat(u.getRate()) : 0f);
            item.getItemProperty("progress").setValue(u.getRate() != null ? String.valueOf(u.getRate()) : "0");
//            item.getItemProperty("progress").setValue(u.getRate() != null ? Double.valueOf((double) u.getRate() / 100.00) : 0f);
            if (u.getStatus() != null) {
                if (null != u.getStatus()) switch (u.getStatus()) {
                    case 4:
                        item.getItemProperty("status").setValue("Đã hủy");
                        break;
                    case 3:
                        item.getItemProperty("status").setValue("Hoàn thành");
                        break;
                    case 2:
                        item.getItemProperty("status").setValue("Thực hiện");
                        break;
                    case 1:
                        item.getItemProperty("status").setValue("Mới");
                        break;
                    default:
                        item.getItemProperty("status").setValue("Mới");
                        break;
                }
            } else {
                item.getItemProperty("status").setValue("Mới");
            }
        }
        return container;
    }

    public void reloadData(List<CTask> cTasks) {
        pagedTable.setContainerDataSource(pagedTable.createWrapContainer(createContainer(cTasks)));
    }

    public void doAction() {
        orgTree.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                Organization org = (Organization) event.getItemId();
                Integer orgId = org.getId();
                Map<Object, Object> map = new HashMap<>();
                map.put("departmentId", orgId);
                List<CTask> cTasks = TaskOrgMngService.getInstance().listTaskByCondition(map);
                reloadData(cTasks);
            }
        });

        view.getBtnAdd().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Organization org = (Organization) orgTree.getValue();
                if (org == null) {
                    ComponentUtils.showNotification(BundleUtils.getString("orgMngt.validate.nodeIsEmpty"));
                    return;
                }
                createDialog(true, new CTask(), org.getId());
            }
        });
    }

    public void createDialog(boolean isInsert, CTask dto, Integer departmentId) {
        TaskAddUI ui = new TaskAddUI(isInsert ? BundleUtils.getString("common.button.add") : BundleUtils.getString("common.button.edit"), dto.getTaskId());
        Window window = new Window("", ui);
        float height = UI.getCurrent().getWidth() * 3 / 4;
        window.setWidth(String.valueOf(height) + "%");
        window.setHeight(92.0f, Sizeable.Unit.PERCENTAGE);
//        window.setIcon(VaadinIcons.CALENDAR_USER);
        initDataDialog(ui, isInsert, dto);
        ui.getBtnSave().addClickListener((Button.ClickEvent event) -> {
            boolean validate = validateData(ui);
            if (validate) {
                ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(),
                        BundleUtils.getString("message.warning.title"),
                        BundleUtils.getString("message.warning.content"),
                        BundleUtils.getString("common.confirmDelete.yes"),
                        BundleUtils.getString("common.confirmDelete.no"), (ConfirmDialog dialog) -> {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                        ResultDTO res = null;
                        List<CTaskAttachment> attachments = new ArrayList<>();
                        getDataFromUI(ui, dto, departmentId, attachments);
                        if (isInsert) {
                            res = TaskOrgMngService.getInstance().addTaskOrg(dto);
                            String taskId = res.getId();
                            if (taskId != null && !"".equals(taskId)) {
                                attachments.forEach((attachment) -> {
                                    CTaskAttachmentPK attachmentPK = new CTaskAttachmentPK();
                                    attachmentPK.setTaskId(Integer.valueOf(taskId));
                                    attachmentPK.setAttachmentType(Constants.ATTACHMENT_TYPE_TASK);
                                    attachment.setId(attachmentPK);
                                    TaskAttachmentService.getInstance().addTaskAttachment(attachment);
                                });
                            }
                            ComponentUtils.showNotification(BundleUtils.getString("common.button.add") + " " + res.getKey() + " " + res.getMessage());
                        } else {
                            dto.setUpdateTime(new Date());
                            res = TaskOrgMngService.getInstance().updateTaskOrg(dto);
                            TaskAttachmentService.getInstance().deleteAttachmentsByTask(String.valueOf(dto.getTaskId()), String.valueOf(Constants.ATTACHMENT_TYPE_TASK), null);
                            attachments.forEach((attachment) -> {
                                CTaskAttachmentPK attachmentPK = new CTaskAttachmentPK();
                                attachmentPK.setTaskId(dto.getTaskId());
                                attachmentPK.setAttachmentType(Constants.ATTACHMENT_TYPE_TASK);
                                attachment.setId(attachmentPK);
                                TaskAttachmentService.getInstance().addTaskAttachment(attachment);
                            });
                            ComponentUtils.showNotification(BundleUtils.getString("common.button.update") + " "
                                    + res.getKey() + " " + res.getMessage());
                        }
                        window.close();
                        view.getBtnSearch().click();
                    } else {
                        Notification.show("nok");
                        window.close();
                    }
                });
                d.setStyleName(Reindeer.LAYOUT_BLUE);
                d.setContentMode(ConfirmDialog.ContentMode.HTML);
                d.getOkButton().setIcon(ISOIcons.SAVE);
                d.getOkButton().focus();
                d.getCancelButton().setIcon(ISOIcons.CANCEL);
            }
        });
        ui.getBtnCancel().addClickListener((Button.ClickEvent event) -> {
            window.close();
        });

        ui.setWidth("100%");
        ui.setHeight(Constants.STYLE_CONF.AUTO_VALUE);

        window.setModal(true);
        DataUtil.reloadWindow(window);
        UI.getCurrent().addWindow(window);
        ui.getTxtTaskName().focus();
    }

    private void initDataDialog(TaskAddUI ui, boolean isInsert, CTask dto) {
        List<CTaskGroup> cTaskGroups = TaskGroupMgntService.getInstance().getTaskGroups();
        List<CTaskPriority> cTaskPioritys = TaskPriorityMgntService.getInstance().getTaskPrioritys();
        List<CatItemDTO> listTaskGroups = new ArrayList<>();
        listTaskGroups.add(0, new CatItemDTO("-1", BundleUtils.getString("common.select")));
        List<CatItemDTO> listTaskPiorities = new ArrayList<>();
        listTaskPiorities.add(0, new CatItemDTO("-1", BundleUtils.getString("common.select")));
        cTaskGroups.stream().forEach((cTaskGroup) -> {
            listTaskGroups.add(new CatItemDTO(String.valueOf(cTaskGroup.getTaskGroupId()), cTaskGroup.getTaskGroupName()));
        });
        cTaskPioritys.stream().forEach((cTaskPiority) -> {
            listTaskPiorities.add(new CatItemDTO(String.valueOf(cTaskPiority.getTaskPiorityId()), cTaskPiority.getTaskPiorityName()));
        });
        ui.getCmbTashGroup().setNullSelectionAllowed(false);
        ui.getCmbTashPriority().setNullSelectionAllowed(false);
        if (isInsert) {
            ComponentUtils.fillDataComboNoDefault(ui.getCmbTashGroup(), Constants.EMPTY_CHARACTER, listTaskGroups);
            ComponentUtils.fillDataComboNoDefault(ui.getCmbTashPriority(), Constants.EMPTY_CHARACTER, listTaskPiorities);
        } else {
            ui.getTxtTaskName().setValue(dto.getTaskName());
//            ui.getTxtTaskReference().setValue(TaskOrgMngService.getInstance().getTaskOrgById(String.valueOf(dto.getTaskId())).getTaskName());
            if (dto.getTaskParentId() != null) {
                CTask taskParent = TaskOrgMngService.getInstance().getTaskOrgById(String.valueOf(dto.getTaskParentId()));
                ui.getTxtTaskReference().setTreeTaskSelected(taskParent);
                ui.getTxtTaskReference().getTxtTaskName().setValue(taskParent != null ? taskParent.getTaskName() : "");
            }
            ui.getTxtDepartment().setValue(OrganizationMngService.getInstance().getOrganizationById(String.valueOf(dto.getDepartmentId())).getName());
            ui.getPbTimeStart().setValue(dto.getStartTime());
            ui.getPbTimeEnd().setValue(dto.getEndTime());
            ui.getTxtBudget().setValue(String.valueOf(dto.getBudget()));
            ComponentUtils.fillDataCombo(ui.getCmbTashGroup(), Constants.EMPTY_CHARACTER, String.valueOf(dto.getTaskGroupId()), listTaskGroups);
            ComponentUtils.fillDataCombo(ui.getCmbTashPriority(), Constants.EMPTY_CHARACTER, String.valueOf(dto.getTaskPiorityId()), listTaskPiorities);
            ui.getTxaTaskContent().setValue(dto.getTaskContent());
            List<CTaskAttachment> attachments
                    = TaskAttachmentService.getInstance().listTaskAttachment(
                            String.valueOf(dto.getTaskId()), String.valueOf(Constants.ATTACHMENT_TYPE_TASK));
            if (attachments != null) {
                attachments.stream().forEach((attachment) -> {
                    ui.getAttachFile().addFileAttack(attachment.getAttachmentUrl(), attachment.getFileName());
                });
            }
        }
    }

    public boolean validateData(TaskAddUI ui) {
        if (DataUtil.isNullOrEmpty(ui.getTxtTaskName().getValue())) {
            ComponentUtils.showNotification(BundleUtils.getString("taskOrgMngt.list.name") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.notnull"));
            return false;
        }
        if (DataUtil.isNullOrEmpty(ui.getTxaTaskContent().getValue())) {
            ComponentUtils.showNotification(BundleUtils.getString("taskOrgMngt.list.content") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.notnull"));
            return false;
        }

        if (ui.getCmbTashGroup().getValue() == null || ((CatItemDTO) ui.getCmbTashGroup().getValue()).getItemId().equals(("-1"))) {
            ComponentUtils.showNotification(BundleUtils.getString("taskOrgMngt.list.group") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.notnull"));
            return false;
        }
        if (ui.getCmbTashPriority().getValue() == null || ((CatItemDTO) ui.getCmbTashPriority().getValue()).getItemId().equals(("-1"))) {
            ComponentUtils.showNotification(BundleUtils.getString("taskOrgMngt.list.priority") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.notnull"));
            return false;
        }
        if (DataUtil.isNullOrEmpty(ui.getTxtBudget().getValue())) {
            ComponentUtils.showNotification(BundleUtils.getString("taskOrgMngt.list.budget") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.notnull"));
            return false;
        }
        if (ui.getPbTimeStart().getValue() == null) {
            ComponentUtils.showNotification(BundleUtils.getString("taskOrgMngt.list.startTime") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.notnull"));
            return false;
        }
        if (ui.getPbTimeEnd().getValue() == null) {
            ComponentUtils.showNotification(BundleUtils.getString("taskOrgMngt.list.endTime") + Constants.SPACE_CHARACTER + BundleUtils.getString("common.notnull"));
            return false;
        }
        if (ui.getPbTimeEnd().getValue().getTime() < ui.getPbTimeStart().getValue().getTime()) {
            ComponentUtils.showNotification(BundleUtils.getString("taskOrgMngt.list.endTime") + Constants.SPACE_CHARACTER
                    + BundleUtils.getString("common.less")
                    + BundleUtils.getString("taskOrgMngt.list.startTime"));
            return false;
        }

        return true;
    }

    private void getDataFromUI(TaskAddUI ui, CTask dto, Integer depId, List<CTaskAttachment> attachments) {
        dto.setTaskParentId(ui.getTxtTaskReference().getTreeTaskSelected().getTaskId());
        dto.setDepartmentId(depId);
        dto.setTaskName(ui.getTxtTaskName().getValue());
        dto.setTaskContent(ui.getTxaTaskContent().getValue());
        dto.setTaskGroupId(Integer.valueOf(((CatItemDTO) ui.getCmbTashGroup().getValue()).getItemId()));
        dto.setTaskPiorityId(Integer.valueOf(((CatItemDTO) ui.getCmbTashPriority().getValue()).getItemId()));
        dto.setStartTime(ui.getPbTimeStart().getValue());
        dto.setEndTime(ui.getPbTimeEnd().getValue());
        dto.setBudget(Float.parseFloat(ui.getTxtBudget().getValue()));
        List<String> urls = ui.getAttachFile().getUrl();
        String path = ui.getAttachFile().getPath();
        urls.forEach((url) -> {
            CTaskAttachment attachment = new CTaskAttachment();
            attachment.setAttachmentUrl(path);
            attachment.setFileName(url);
            attachments.add(attachment);
        });
    }
}
