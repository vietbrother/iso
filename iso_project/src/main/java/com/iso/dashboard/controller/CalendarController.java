/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.controller;

import com.iso.dashboard.component.CustomPageTable;
import com.iso.dashboard.dto.CCalendar;
import com.iso.dashboard.dto.ResultDTO;
import com.iso.dashboard.service.CalendarMgntService;
import com.iso.dashboard.service.ColorService;
import com.iso.dashboard.service.UserMngService;
import com.iso.dashboard.utils.BundleUtils;
import com.iso.dashboard.utils.ComponentUtils;
import com.iso.dashboard.utils.ISOIcons;
import com.iso.dashboard.view.CalendarMngtView;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author VIET_BROTHER
 */
public class CalendarController {

    CalendarMngtView view;
    UserMngService service;

    CustomPageTable pagedTable;
    String prefix = "calmgnt.list";//tien to trong file language
    String headerKey = "header.calMngt";//lay trong file cas
    String[] headerName = BundleUtils.getHeaderColumnName(prefix, headerKey);
    Resource resource;
    public static Object propertyID = null;
    public static Object itemID = null;

    String styleSelect = "";
    public CalendarController(CalendarMngtView view) {
        this.view = view;
        this.pagedTable = view.getPagedTable();
        initTable(CalendarMgntService.getInstance().getCalendars());
        doAction();
    }

    public void initTable(List<CCalendar> calendars) {
        pagedTable.addGeneratedColumn("index", (final Table source, final Object itemId, final Object columnId) -> {
            Container.Indexed container = (Container.Indexed) source.getContainerDataSource();
            return Integer.toString(container.indexOfId(itemId) + 1);
        });
        pagedTable.addGeneratedColumn("action", (Table source, Object itemId, Object columnId) -> {
            Button btnSave = new Button();
            btnSave.setIcon(FontAwesome.SAVE);
            btnSave.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
            btnSave.setDescription(BundleUtils.getString("common.save"));
            btnSave.addClickListener((Button.ClickEvent event) -> {
                //luu thong tin cap nhat
                Item item = source.getItem(itemId);
                String calName = item.getItemProperty("calName").getValue().toString();
                String color = item.getItemProperty("color").getValue().toString();
                String workday = item.getItemProperty("workday").getValue().toString();
                Object obj = item.getItemProperty("id").getValue();
                String id = obj != null && !"".equals(obj.toString()) && !"0".equals(obj.toString()) ? item.getItemProperty("id").getValue().toString() : null;
                String desc = item.getItemProperty("desc").getValue().toString();
                CCalendar calendar = new CCalendar();
                calendar.setCalendarName(calName);
                calendar.setColor(color);
                calendar.setDescription(desc);
                calendar.setWorkingDate(workday.equals("false") ? 1 : 0);
                if (id != null) {
                    calendar.setCalendarId(Integer.parseInt(id));
                    ResultDTO res = CalendarMgntService.getInstance().updateCalendar(calendar);
                    ComponentUtils.showNotification(BundleUtils.getString("common.button.update") + " " + res.getKey() + " " + res.getMessage());
                } else {
                    ResultDTO res = CalendarMgntService.getInstance().addCalendar(calendar);
                    ComponentUtils.showNotification(BundleUtils.getString("common.button.add") + " " + res.getKey() + " " + res.getMessage());
                }
            });
            Button btnDelete = new Button();
            btnDelete.setIcon(ISOIcons.DELETE);
            btnDelete.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
            btnDelete.setDescription(BundleUtils.getString("common.button.delete"));
            btnDelete.addClickListener((Button.ClickEvent event) -> {
                Item item = source.getItem(itemId);
                String id = item.getItemProperty("id").getValue() != null ? item.getItemProperty("id").getValue().toString() : null;
                if (id != null) {
                    ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(),
                            BundleUtils.getString("message.warning.title"),
                            BundleUtils.getString("message.warning.content"),
                            BundleUtils.getString("common.confirmDelete.yes"),
                            BundleUtils.getString("common.confirmDelete.no"), (ConfirmDialog dialog) -> {
                                if (dialog.isConfirmed()) {
                                    ResultDTO res = CalendarMgntService.getInstance().removeCalendar(id);
                                    ComponentUtils.showNotification("Delete id : " + id + " " + res.getKey() + " " + res.getMessage());
                                    pagedTable.removeItem(itemId);
                                }
                            });
                    d.setStyleName(Reindeer.WINDOW_LIGHT);
                    d.setContentMode(ConfirmDialog.ContentMode.HTML);
                    d.getOkButton().setIcon(ISOIcons.SAVE);
                    d.getCancelButton().setIcon(ISOIcons.CANCEL);
                } else {
                    pagedTable.removeItem(itemId);
                }
            });

            HorizontalLayout hori = new HorizontalLayout();
            hori.addComponent(btnSave);
            hori.addComponent(btnDelete);
            return hori;
        });

        pagedTable.setTableFieldFactory(new DefaultFieldFactory() {
            private static final long serialVersionUID = 8585461394836108250L;

            @Override
            public Field<?> createField(Container container, Object itemId,
                    Object propertyId, Component uiContext) {
                if ("calName".equals(propertyId)) {
                    if (pagedTable.isEditable() && propertyId.equals(CalendarController.propertyID) && itemId.equals(CalendarController.itemID)) {
                        TextField tf = new TextField();
                        tf.focus();
                        tf.addBlurListener((FieldEvents.BlurEvent event) -> {
                            CalendarController.propertyID = null;
                            CalendarController.itemID = null;
                            if (pagedTable.isEditable()) {
                                pagedTable.setEditable(false);
                            }
                        });
                        tf.setWidth("100%");
                        return tf;
                    } else {
                        return null;
                    }
                }
                if ("color".equals(propertyId)) {
                    if (pagedTable.isEditable() && propertyId.equals(CalendarController.propertyID) && itemId.equals(CalendarController.itemID)) {
                        ComboBox cb = new ComboBox();
                        cb.setImmediate(true);
                        cb.focus();
                        cb.addBlurListener((FieldEvents.BlurEvent event) -> {
                            CalendarController.propertyID = null;
                            CalendarController.itemID = null;
                            if (pagedTable.isEditable()) {
                                pagedTable.setEditable(false);
                            }
//                            String style = ((CCalendar) itemId).getColor().toLowerCase();
                            //cb.setStyleName(styleSelect);
                        });
                        cb.setWidth("100%");
//                        cb.setItemStyleGenerator((ComboBox source, Object itemId1) -> 
//                                itemId1 == null ? "white" : itemId1.toString().toLowerCase());
                        cb.setItemStyleGenerator(new ComboBox.ItemStyleGenerator() {

                            @Override
                            public String getStyle(ComboBox source, Object itemId) {
                                String style = (itemId == null) ? "white" : itemId.toString().toLowerCase();
                                cb.setStyleName(style);
                                styleSelect = style;
                                return style;
                            }
                        });

                        cb.addValueChangeListener(new Property.ValueChangeListener() {

                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {
                                String style = (event.getProperty().getValue() == null) ? "white" : ((String) event.getProperty().getValue()).toLowerCase();
                                cb.setStyleName(style);
                            }
                        });
                        cb.addItems(ColorService.getInstance().listColor());
                        cb.setNullSelectionAllowed(false);

                        return cb;
                    } else {
                        return null;
                    }
                }
                if ("desc".equals(propertyId)) {
                    if (pagedTable.isEditable() && propertyId.equals(CalendarController.propertyID) && itemId.equals(CalendarController.itemID)) {
                        TextField tf = new TextField();
                        tf.focus();
                        tf.addBlurListener((FieldEvents.BlurEvent event) -> {
                            CalendarController.propertyID = null;
                            CalendarController.itemID = null;
                            if (pagedTable.isEditable()) {
                                pagedTable.setEditable(false);
                            }
                        });
                        tf.setWidth("100%");
                        return tf;
                    } else {
                        return null;
                    }
                }
                // Otherwise use the default field factory 
                return super.createField(container, itemId, propertyId, uiContext);
            }
        });

        pagedTable.addGeneratedColumn("workday", (Table source, Object itemId, Object columnId) -> {
            Property prop = source.getItem(itemId).getItemProperty(columnId);
            CheckBox checkBox = new CheckBox(null, prop);
            // don't allow user to change value
            if (CalendarController.propertyID != null && CalendarController.itemID != null && CalendarController.propertyID.equals("workday") && CalendarController.itemID.equals(itemId)) {
                checkBox.setEnabled(source.isEditable());
            }
            checkBox.addBlurListener((FieldEvents.BlurEvent event) -> {
                CalendarController.propertyID = null;
                CalendarController.itemID = null;
                if (pagedTable.isEditable()) {
                    pagedTable.setEditable(false);
                }
            });
            return checkBox;
        });
//        pagedTable.setCellStyleGenerator(new Table.CellStyleGenerator() {
//
//            @Override
//            public String getStyle(Table source, Object itemId, Object propertyId) {
//                Item item = source.getItem(itemId);
//                String color = item.getItemProperty("color").getValue().toString();
//                if (color != null) {
//                    if (color.equalsIgnoreCase("yellow")) {
//                        return "yellow";
//                    }
//                }
//                return "";
//            }
//        });

//        pagedTable.addGeneratedColumn("color", new Table.ColumnGenerator() {
//
//            @Override
//            public Object generateCell(Table source, Object itemId, Object columnId) {
//                Item item = source.getItem(itemId);
//                String color = item.getItemProperty("color").getValue().toString();
//                Label lb = new Label(color);
//                lb.setStyleName(color.toLowerCase());
//                return lb;
//            }
//        });
        reloadData(calendars);
        pagedTable.setSizeFull();
        pagedTable.setPageLength(10);
        pagedTable.setImmediate(true);
        pagedTable.setSelectable(true);
        pagedTable.setResponsive(true);
        pagedTable.setColumnHeaders(headerName);
        pagedTable.setVisibleColumns("index", "action", "calName", "color", "workday", "desc");
        pagedTable.addItemClickListener((ItemClickEvent event) -> {
            if (event.isDoubleClick()) {
                propertyID = event.getPropertyId();
                itemID = event.getItemId();
                if (!pagedTable.isEditable()) {
                    pagedTable.setEditable(true);
                }
            }
        });
    }

    public void reloadData(List<CCalendar> calendars) {
        pagedTable.setContainerDataSource(createContainer(calendars));
    }

    public IndexedContainer createContainer(List<CCalendar> calendars) {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("index", String.class, null);
        container.addContainerProperty("action", String.class, null);
        container.addContainerProperty("calName", String.class, null);
        container.addContainerProperty("color", String.class, null);
        container.addContainerProperty("workday", Boolean.class, null);
        container.addContainerProperty("desc", String.class, null);
//        container.addContainerProperty("id", Integer.class, null);
        for (CCalendar cal : calendars) {
            Item item = container.addItem(cal);
            item.getItemProperty("id").setValue(cal.getCalendarId());
            item.getItemProperty("calName").setValue(cal.getCalendarName() != null ? cal.getCalendarName() : "");
            item.getItemProperty("color").setValue(cal.getColor() != null ? cal.getColor() : "WHITE");
            item.getItemProperty("workday").setValue(cal.getWorkingDate() != null && cal.getWorkingDate() == 0);
            item.getItemProperty("desc").setValue(cal.getDescription() != null ? cal.getDescription() : "");
        }
        return container;
    }

    private void doAction() {
        view.getBtnAdd().addClickListener((Button.ClickEvent event) -> {
            List<CCalendar> calendars = CalendarMgntService.getInstance().getCalendars();
            calendars.add(new CCalendar());
            reloadData(calendars);
            pagedTable.setVisibleColumns("index", "action", "calName", "color", "workday", "desc");
        });
    }

}
