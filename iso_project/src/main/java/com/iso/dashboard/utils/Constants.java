/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso.dashboard.utils;

import java.io.File;

/**
 *
 * @author VIET_BROTHER
 */
public class Constants {

    public static final String SECURE_SECRET_KEY = "OH_SUONG";
    public static final String SECURE_SALT = BundleUtils.getStringCas("salt");
    // Decreasing this speeds down startup time and can be useful during testing, but it also makes it easier for brute force attackers
    public static final int SECURE_ITERATION_COUNT = 1;
    public static final int SECURE_KEY_LENGTH = 128;

    public static final String LANGUAGE = "language";
    public static final String MENU = "menu";
    public static final String CAS = "cas";

    public static final String EMPTY_CHARACTER = "";
    public static final String SPACE_CHARACTER = " ";
    public static final String DEFAULT_VALUE = "-1";
    public static final String DEFAULT_PAGING = "10";

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public static final String DEACTIVE = "0";
    public static final String DEACTIVE_STR = "DEACTIVE";
    public static final String ACTIVE = "1";
    public static final String ACTIVE_STR = "ACTIVE";

    public static final String ITEM_ID = "itemId";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_CODE = "itemCode";

    public static final Integer ATTACHMENT_TYPE_TASK = 1;
    public static final Integer ATTACHMENT_TYPE_REPORT = 2;

    public static final Integer HIST_TYPE_REPORT = 1;
    public static final Integer HIST_TYPE_CHECK = 2;

    public interface STYLE_CONF {

        public static final String AUTO_VALUE = "-1px";
        public static final String MAX_VALUE = "100%";
        public static final String BUTTON_LINK_LARGE = "v-button-link-large";
        public static final String CUSTOM_FEILDSET = "custom-feildset";
    }

    public interface DATE {

        public final static String ddMMyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
    }

    public final static String COMMA = ",";
    public static final String XLS_FILE_EXTENTION = ".xls";
    public static final String XLSX_FILE_EXTENTION = ".xlsx";
    public static final String PDF_FILE_EXTENTION = ".pdf";
    public static final String REPORT_OUT = "report_out";
    public static final String PATH_TEMPLATE = File.separator + ".." + File.separator + ".." + File.separator + ".." + File.separator + "report_out" + File.separator;
    public static final String PATH_OUT = File.separator + ".." + File.separator + ".." + File.separator + ".." + File.separator + "report_out" + File.separator;

    public interface FILE_CONF {

        public static final String PATH_EXPORT_TEMPLATE = File.separator + "WEB-INF"
                + File.separator + "templates"
                + File.separator + "TEMPLATE_EXPORT.xls";
        public static final String PATH_EXPORT_TEMPLATE_XLSX = File.separator + "WEB-INF"
                + File.separator + "templates"
                + File.separator + "TEMPLATE_EXPORT.xlsx";

        public final static String TEMPLATE_EXPORT_MULTI_SHEET_KHPS = "TEMPLATE_EXPORT_MULTI_SHEET_KHPS.xlsx";

        public final static int MAX_ROW_IMPORT_USER = 500;
        public final static int MAX_ROW_IMPORT_SPR = 1000;
        public static final long MAX_FILE_SIZE_UPLOAD = 40960000;
        public final static String REQUIRE = "<font color='red'> *</font>";
    }

    public interface BUTTON_RENDERER {

        public static final int VIEW_BITM = 4;
        public static final int EDIT_BITM = 16;
        public static final int DELETE_BITM = 32;
        public static final int ASIGN_BITM = 64;
    }
}
