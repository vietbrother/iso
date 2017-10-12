/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.view;

import com.iso.dashboard.component.CustomPageTable;
import com.iso.dashboard.controller.TaskOrgMngtController;
import com.iso.dashboard.dto.Organization;
import com.iso.dashboard.ui.OrgTreeSearchUI;
import com.iso.dashboard.utils.BundleUtils;
import com.iso.dashboard.utils.Constants;
import com.iso.dashboard.utils.ISOIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author VIET_BROTHER
 */
public class TaskOrgMngtView extends Panel implements View {

    public List<Organization> lstData = new ArrayList<>();
    public Map<String, Organization> map = new HashMap<>();

    private TextField txtTaskName;
    private Button btnHideTree;
    private Button btnSearch;
    private Button btnAdd;

    private Tree orgTree;
    boolean isViewTree;
    private CustomPageTable pagedTable;

    public TaskOrgMngtView() {
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();
        VerticalLayout root = new VerticalLayout();
        root.setCaption(BundleUtils.getStringCas("menu.organizationMngt"));
        root.setSizeFull();
        root.setSpacing(true);
        root.addStyleName("dashboard-view");
        Responsive.makeResponsive(root);

        Component content = buildContent();
        TabSheet tabSheet = new TabSheet();
        tabSheet.setStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
        setContent(tabSheet);
//        addComponent(new Trees());
        tabSheet.addComponent(root);

        root.addComponent(new OrgTreeSearchUI("don vi"));
//        root.addComponent(buildPagedTableTab());
        root.addComponent(buildHeader());
        root.addComponent(content);
//        root.setExpandRatio(content, 1);

//        addComponent(buildToolbar());
        new TaskOrgMngtController(this);
    }

    private Component buildHeader() {
        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setWidth(100.0f, Unit.PERCENTAGE);
        headerLayout.addStyleName("profile-form");

        // btnHide
        btnHideTree = new Button();
        btnHideTree.setWidth(Constants.STYLE_CONF.AUTO_VALUE);
        btnHideTree.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        btnHideTree.setIcon(FontAwesome.ARROW_CIRCLE_O_RIGHT);
        btnHideTree.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnHideTree.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                isViewTree = !isViewTree;
                if (isViewTree) {
                    btnHideTree.setIcon(FontAwesome.ARROW_CIRCLE_O_RIGHT);
                } else {
                    btnHideTree.setIcon(FontAwesome.ARROW_CIRCLE_O_LEFT);
                }
                orgTree.setVisible(isViewTree);
            }
        });

        CssLayout headerPanel = new CssLayout();
        headerPanel.addStyleName("dashboard-panels");
        Responsive.makeResponsive(headerPanel);

        txtTaskName = new TextField();
        txtTaskName.setImmediate(true);
        txtTaskName.setInputPrompt(BundleUtils.getString("userMngt.list.username"));
        txtTaskName.setHeight(Constants.STYLE_CONF.AUTO_VALUE);

        btnSearch = new Button();
        btnSearch.setDescription(BundleUtils.getString("common.button.search"));
        btnSearch.setIcon(ISOIcons.SEARCH);
        btnSearch.setWidth(Constants.STYLE_CONF.AUTO_VALUE);
        btnSearch.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        btnSearch.setStyleName(ValoTheme.BUTTON_PRIMARY);

        // btnAdd
        btnAdd = new Button();
        btnAdd.setDescription(BundleUtils.getString("common.button.add"));
        btnAdd.setWidth(Constants.STYLE_CONF.AUTO_VALUE);
        btnAdd.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        btnAdd.setIcon(ISOIcons.ADD);

        headerPanel.addComponent(txtTaskName);
        headerPanel.addComponent(btnSearch);
        headerPanel.addComponent(btnAdd);

        headerLayout.addComponent(btnHideTree);
        headerLayout.setExpandRatio(btnHideTree, 5.0f);
        headerLayout.setComponentAlignment(btnHideTree, Alignment.TOP_LEFT);
        headerLayout.addComponent(headerPanel);
        headerLayout.setComponentAlignment(headerPanel, Alignment.TOP_RIGHT);
        return headerLayout;
    }

    private Component buildContent() {

        CssLayout contenPanel = new CssLayout();
        contenPanel.addStyleName("dashboard-panels");
        Responsive.makeResponsive(contenPanel);

        HorizontalLayout contentLayout = new HorizontalLayout();
        contentLayout.setWidth(100.0f, Unit.PERCENTAGE);
        contentLayout.addStyleName("profile-form");

//        loadTreeData();
        orgTree = new Tree();
        orgTree.setCaption(BundleUtils.getString("orgMngt.tree"));
        contentLayout.addComponent(orgTree);
        contentLayout.setComponentAlignment(orgTree, Alignment.MIDDLE_LEFT);
        contentLayout.setExpandRatio(orgTree, 3.0f);

        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setSizeFull();
        pagedTable = new CustomPageTable(
                BundleUtils.getString("task.list"));
        tableLayout.addComponent(pagedTable);
        contentLayout.addComponent(tableLayout);
        contentLayout.setComponentAlignment(tableLayout, Alignment.MIDDLE_RIGHT);
        contentLayout.setExpandRatio(tableLayout, 7.0f);


        contenPanel.addComponent(contentLayout);
        return contenPanel;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    public List<Organization> getLstData() {
        return lstData;
    }

    public void setLstData(List<Organization> lstData) {
        this.lstData = lstData;
    }

    public Map<String, Organization> getMap() {
        return map;
    }

    public void setMap(Map<String, Organization> map) {
        this.map = map;
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(Button btnAdd) {
        this.btnAdd = btnAdd;
    }

    public Tree getOrgTree() {
        return orgTree;
    }

    public void setOrgTree(Tree orgTree) {
        this.orgTree = orgTree;
    }

    public Button getBtnHideTree() {
        return btnHideTree;
    }

    public void setBtnHideTree(Button btnHideTree) {
        this.btnHideTree = btnHideTree;
    }

    public boolean isIsViewTree() {
        return isViewTree;
    }

    public void setIsViewTree(boolean isViewTree) {
        this.isViewTree = isViewTree;
    }

    public CustomPageTable getPagedTable() {
        return pagedTable;
    }

    public void setPagedTable(CustomPageTable pagedTable) {
        this.pagedTable = pagedTable;
    }

    public TextField getTxtTaskName() {
        return txtTaskName;
    }

    public void setTxtTaskName(TextField txtTaskName) {
        this.txtTaskName = txtTaskName;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

}
