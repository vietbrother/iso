/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.ui;

import com.iso.dashboard.utils.BundleUtils;
import com.iso.dashboard.utils.Constants;
import com.iso.dashboard.utils.ISOIcons;
import com.iso.dashboard.view.EmployeeEducationMngtView;
import com.iso.dashboard.view.EmployeeProcessMngtView;
import com.iso.dashboard.view.EmployeeRewardMngtView;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author Thuclt-VNPTTech
 */
public class EmployeeMngtUI extends VerticalLayout {

    private VerticalLayout mainLayout;

//    private HorizontalLayout horizontalLayout;
//    private TabSheet tabSheet;
    private TextField txtEmployeeCode;

    private TextField txtEmployeeName;
    private TextField txtBirthday;
    private TextField txtEmail;
    private TextField txtMobile;
    private TextField txtJobTitle;
    private TextField txtDepartment;

    private CheckBox cbxActive;
    private Button btnSave;
    private Button btnCancel;

    private String caption;

    public EmployeeMngtUI(String caption) {
        this.caption = caption;
        this.setIcon(FontAwesome.CALENDAR);
        addComponent(buildFieldsInfo());

        EmployeeProcessMngtView employeeProcessMngtView = new EmployeeProcessMngtView();
        employeeProcessMngtView.getBtnAdd().setVisible(false);
        employeeProcessMngtView.getBtnExport().setVisible(false);
        employeeProcessMngtView.getBtnSearch().setVisible(false);
        employeeProcessMngtView.getTxtEmployeeProcess().setVisible(false);
        employeeProcessMngtView.getHeader().setVisible(false);
        employeeProcessMngtView.setCaption("Quá trình công tác");

        TabSheet detailsWrapper = new TabSheet(employeeProcessMngtView);
        detailsWrapper.setSizeFull();
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        addComponent(detailsWrapper);
        addComponent(buildButton());

        // add new tab
        EmployeeEducationMngtView employeeEducationMngtView = new EmployeeEducationMngtView();
        employeeEducationMngtView.getBtnAdd().setVisible(false);
        employeeEducationMngtView.getBtnExport().setVisible(false);
        employeeEducationMngtView.getBtnSearch().setVisible(false);
        employeeEducationMngtView.getTxtEmployeeEducation().setVisible(false);
        employeeEducationMngtView.setCaption("Văn bằng/ Chứng chỉ");
        employeeEducationMngtView.getHeader().setVisible(false);
        detailsWrapper.addTab(employeeEducationMngtView);

        // add new tab
        EmployeeRewardMngtView rewardMngtView = new EmployeeRewardMngtView();
        rewardMngtView.getBtnAdd().setVisible(false);
        rewardMngtView.getBtnExport().setVisible(false);
        rewardMngtView.getBtnSearch().setVisible(false);
        rewardMngtView.getTxtEmployeeReward().setVisible(false);
        rewardMngtView.setCaption("Khen thưởng");
        rewardMngtView.getHeader().setVisible(false);
        detailsWrapper.addTab(rewardMngtView);

    }

    private void buildMainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setCaption(this.caption);
        mainLayout.setIcon(FontAwesome.CALENDAR);
        mainLayout.setSizeUndefined();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        Responsive.makeResponsive(mainLayout);

        Component mainContent = buildContent();
        mainLayout.addComponent(mainContent);
        mainLayout.setExpandRatio(mainContent, 1.0f);
        mainLayout.addComponent(buildButton());

    }

    public Component buildContent() {
        CssLayout contenPanel = new CssLayout();
        contenPanel.addStyleName("dashboard-panels");
        Responsive.makeResponsive(contenPanel);

        contenPanel.addComponent(buildFieldsInfo());
//        contenPanel.addComponent(buildButton());
        return contenPanel;
    }

    public Component buildFieldsInfo() {
        /**
         * create fields infomation
         */
        txtEmployeeCode = new TextField();
        txtEmployeeCode.setImmediate(true);
        txtEmployeeCode.setRequired(true);
        txtEmployeeCode.setWidth("100.0%");
        txtEmployeeCode.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        txtEmployeeCode.setRequired(true);
        txtEmployeeCode.setDescription(BundleUtils.getString("employeeMngt.list.employeeCode"));
        txtEmployeeCode.setCaption(BundleUtils.getString("employeeMngt.list.employeeCode"));
        txtEmployeeCode.setInputPrompt(BundleUtils.getString("employeeMngt.list.employeeCode"));

        txtEmployeeName = new TextField();
        txtEmployeeName.setImmediate(true);
        txtEmployeeName.setRequired(true);
        txtEmployeeName.setWidth("100.0%");
        txtEmployeeName.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        txtEmployeeName.setRequired(true);
        txtEmployeeName.setDescription(BundleUtils.getString("employeeMngt.list.employeeName"));
        txtEmployeeName.setCaption(BundleUtils.getString("employeeMngt.list.employeeName"));
        txtEmployeeName.setInputPrompt(BundleUtils.getString("employeeMngt.list.employeeName"));

        txtBirthday = new TextField();
        txtBirthday.setImmediate(true);
        txtBirthday.setRequired(true);
        txtBirthday.setWidth("100.0%");
        txtBirthday.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        txtBirthday.setRequired(true);
        txtBirthday.setDescription(BundleUtils.getString("employeeMngt.list.birthday"));
        txtBirthday.setCaption(BundleUtils.getString("employeeMngt.list.birthday"));
        txtBirthday.setInputPrompt(BundleUtils.getString("employeeMngt.list.birthday"));

        txtEmail = new TextField();
        txtEmail.setImmediate(true);
        txtEmail.setRequired(true);
        txtEmail.setWidth("100.0%");
        txtEmail.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        txtEmail.setRequired(true);
        txtEmail.setDescription(BundleUtils.getString("employeeMngt.list.email"));
        txtEmail.setCaption(BundleUtils.getString("employeeMngt.list.email"));
        txtEmail.setInputPrompt(BundleUtils.getString("employeeMngt.list.email"));

        txtMobile = new TextField();
        txtMobile.setImmediate(true);
        txtMobile.setRequired(true);
        txtMobile.setWidth("100.0%");
        txtMobile.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        txtMobile.setRequired(true);
        txtMobile.setDescription(BundleUtils.getString("employeeMngt.list.mobile"));
        txtMobile.setCaption(BundleUtils.getString("employeeMngt.list.mobile"));
        txtMobile.setInputPrompt(BundleUtils.getString("employeeMngt.list.mobile"));

        txtJobTitle = new TextField();
        txtJobTitle.setImmediate(true);
        txtJobTitle.setRequired(true);
        txtJobTitle.setWidth("100.0%");
        txtJobTitle.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        txtJobTitle.setRequired(true);
        txtJobTitle.setDescription(BundleUtils.getString("employeeMngt.list.jobTitle"));
        txtJobTitle.setCaption(BundleUtils.getString("employeeMngt.list.jobTitle"));
        txtJobTitle.setInputPrompt(BundleUtils.getString("employeeMngt.list.jobTitle"));

        txtDepartment = new TextField();
        txtDepartment.setImmediate(true);
        txtDepartment.setRequired(true);
        txtDepartment.setWidth("100.0%");
        txtDepartment.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        txtDepartment.setRequired(true);
        txtDepartment.setDescription(BundleUtils.getString("employeeMngt.list.department"));
        txtDepartment.setCaption(BundleUtils.getString("employeeMngt.list.department"));
        txtDepartment.setInputPrompt(BundleUtils.getString("employeeMngt.list.department"));

        cbxActive = new CheckBox();
        cbxActive.setImmediate(true);
        cbxActive.setWidth("100.0%");
        cbxActive.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        cbxActive.setDescription(BundleUtils.getString("employeeMngt.list.active"));
        cbxActive.setCaption(BundleUtils.getString("employeeMngt.list.active"));

        HorizontalLayout grid = new HorizontalLayout();
        grid.setCaption("Thông tin chung");
        grid.setSizeFull();
        grid.setSpacing(true);
        FormLayout subFrm1 = new FormLayout();
        subFrm1.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        subFrm1.addComponent(txtEmployeeCode);
        subFrm1.addComponent(txtJobTitle);
        subFrm1.addComponent(txtBirthday);
        subFrm1.addComponent(txtMobile);

        grid.addComponent(subFrm1);
//        grid.setComponentAlignment(subFl1, Alignment.MIDDLE_LEFT);
//        grid.setExpandRatio(subFl1, 4.0f);

        FormLayout subFrm2 = new FormLayout();
        subFrm2.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        subFrm2.addComponent(txtEmployeeName);
        subFrm2.addComponent(txtDepartment);
        subFrm2.addComponent(txtEmail);
        grid.addComponent(subFrm2);

        return grid;
    }

    public Component buildButton() {
        /**
         * Create button action
         */
        // btnSave
        btnSave = new Button();
        btnSave.setCaption(BundleUtils.getString("common.button.save"));
        btnSave.setImmediate(true);
        btnSave.setStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.setWidth(Constants.STYLE_CONF.AUTO_VALUE);
        btnSave.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        btnSave.setIcon(ISOIcons.SAVE);
        // btnCancel
        btnCancel = new Button();
        btnCancel.setCaption(BundleUtils.getString("common.button.cancel"));
        btnCancel.setImmediate(true);
        btnCancel.setWidth(Constants.STYLE_CONF.AUTO_VALUE);
        btnCancel.setHeight(Constants.STYLE_CONF.AUTO_VALUE);
        btnCancel.setIcon(ISOIcons.CANCEL);

        HorizontalLayout temp = new HorizontalLayout();
        temp.setSpacing(true);
        temp.addStyleName("fields");
        temp.addComponents(btnSave,
                btnCancel
        );
        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Sizeable.Unit.PERCENTAGE);
        footer.setSpacing(false);
        footer.addComponent(temp);
        footer.setComponentAlignment(temp, Alignment.BOTTOM_RIGHT);

        return footer;
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(VerticalLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public TextField getTxtEmployeeCode() {
        return txtEmployeeCode;
    }

    public void setTxtEmployeeCode(TextField txtEmployeeCode) {
        this.txtEmployeeCode = txtEmployeeCode;
    }

    public TextField getTxtEmployeeName() {
        return txtEmployeeName;
    }

    public void setTxtEmployeeName(TextField txtEmployeeName) {
        this.txtEmployeeName = txtEmployeeName;
    }

    public TextField getTxtBirthday() {
        return txtBirthday;
    }

    public void setTxtBirthday(TextField txtBirthday) {
        this.txtBirthday = txtBirthday;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public TextField getTxtMobile() {
        return txtMobile;
    }

    public void setTxtMobile(TextField txtMobile) {
        this.txtMobile = txtMobile;
    }

    public TextField getTxtJobTitle() {
        return txtJobTitle;
    }

    public void setTxtJobTitle(TextField txtJobTitle) {
        this.txtJobTitle = txtJobTitle;
    }

    public TextField getTxtDepartment() {
        return txtDepartment;
    }

    public void setTxtDepartment(TextField txtDepartment) {
        this.txtDepartment = txtDepartment;
    }

    public CheckBox getCbxActive() {
        return cbxActive;
    }

    public void setCbxActive(CheckBox cbxActive) {
        this.cbxActive = cbxActive;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
