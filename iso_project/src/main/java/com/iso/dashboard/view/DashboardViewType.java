package com.iso.dashboard.view;

import com.iso.dashboard.view.dashboard.DashboardView;
import com.iso.view.reports.ReportsView;
import com.iso.dashboard.view.sales.SalesView;
import com.iso.dashboard.view.schedule.ScheduleView;
//import com.iso.dashboard.view.transactions.TransactionsView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DashboardViewType {

//<<<<<<< HEAD
//    DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true),
//    USER_MNGT("userMngt", UserMngtView.class, FontAwesome.USERS, true),
//    FILE_MNGT("fileMngt", FileMngtView.class, FontAwesome.FILE, true),
////    SECURITY_LEVEL_MNGT("selMngt", SecurityLevelMngtView.class, FontAwesome.FILE, true),
//    DOCUMENT_MNGT("documentMngt", DocumentMngtView.class, FontAwesome.FILE, true),
//    ORGANIZATION_MNGT("organizationMngt", OrganizationMngtView.class, FontAwesome.SITEMAP, true),
//    SALES("sales", SalesView.class, FontAwesome.BAR_CHART_O, false),
//    REPORTS("reports", ReportsView.class, FontAwesome.FILE_TEXT_O, true),
//    SCHEDULE("schedule", ScheduleView.class, FontAwesome.CALENDAR_O, false);
//=======
    DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true, true, false),
    USER_MNGT("userMngt", UserMngtView.class, FontAwesome.USERS, true, true, false),
    ORGANIZATION_MNGT("organizationMngt", OrganizationMngtView.class, FontAwesome.SITEMAP, true, true, false),
    
    WORK_MNGT("taskMngt", DashboardView.class, FontAwesome.TASKS, true, true, true),
    WORK_ORG_MNGT("taskOrgMngt", TaskOrgMngtView.class, FontAwesome.TASKS, true, false, false),
    WORK_PERSONAL_MNGT("taskPersonalMngt", TaskPersonalMngtView.class, FontAwesome.TASKS, true, false, false),
    //WORK_PERSONAL_MNGT("taskPersonalMngt", Test.class, FontAwesome.TASKS, true, false, false),
    WORK_ASIGN_MNGT("taskAsignMngt", TaskAsignMngtView.class, FontAwesome.TASKS, true, false, false),
    
    FILE_MNGT("fileMngt", FileMngtView.class, FontAwesome.FILE, true, true, false),
    DOCUMENT_MNGT("documentMngt", DocumentMngtView.class, FontAwesome.FILE, true, true, false),
    
    SALES("sales", SalesView.class, FontAwesome.BAR_CHART_O, false, true, false),
    REPORTS("reports", ReportsView.class, FontAwesome.FILE_TEXT_O, true, true, false),
    SCHEDULE("schedule", ScheduleView.class, FontAwesome.CALENDAR_O, false, true, false);
    

    public static Map<String, List<DashboardViewType>> mapSubMenu = new HashMap<>();
    static {
        List<DashboardViewType> lstWorkMngtSubMenu = new ArrayList<>();
        lstWorkMngtSubMenu.add(WORK_ORG_MNGT);
        lstWorkMngtSubMenu.add(WORK_PERSONAL_MNGT);
        lstWorkMngtSubMenu.add(WORK_ASIGN_MNGT);
        mapSubMenu.put(WORK_MNGT.getViewName(), lstWorkMngtSubMenu);
        
    }
    
    
    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;
    private final boolean isParentView;
    private final boolean isContainChild;

    private DashboardViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful,
            boolean isParentView, boolean isContainChild) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
        this.isParentView = isParentView;
        this.isContainChild = isContainChild;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public boolean isParentView() {
        return isParentView;
    }

    public boolean isContainChild() {
        return isContainChild;
    }

    
    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static DashboardViewType getByViewName(final String viewName) {
        DashboardViewType result = null;
        for (DashboardViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
