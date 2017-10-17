/*
 * Copyright 2013 gergo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iso.dashboard.utils;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamVariable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadFinishedHandler;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStatePanel;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStateWindow;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo application. Run with mvn jetty:run.
 */

public class UploadUI extends VerticalLayout{

    private static final int POLLING_INTERVAL = 100;
    private static final int FILE_COUNT = 5;
    private List<MultiFileUpload> uploads = new ArrayList<>();
    private VerticalLayout root = new VerticalLayout();
    private FormLayout form = new FormLayout();
    private UploadStateWindow uploadStateWindow = new UploadStateWindow();
    private UploadFinishedHandler uploadFinishedHandler;
    private double uploadSpeed = 500;
    private boolean uploadFieldsEnabled = true;
    private Label lblFileName;

//    @Override
//    protected void init(VaadinRequest request) {
//        /*
//         * ProgressIndicator is deprecated from 7.1, so use UI#setPushMode(PushMode) or UI#setPollInterval(int) 
//         * to refresh the ProgressBar.
//         */
//        setPollInterval(POLLING_INTERVAL);
//
//        setContent(root);
//        root.setMargin(true);
//
//        addLabels();
//
//        root.addComponent(form);
//
//        createForm();
//    }

    public UploadUI() {
        UI.getCurrent().setPollInterval(POLLING_INTERVAL);

        addComponent(root);
        root.setMargin(true);

        addLabels();

        root.addComponent(form);

        createForm();
    }
    
    

    private void createForm() {
        createUploadFinishedHandler();
        uploads.clear();
//        getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
        //setPollInterval(100);
        //getPushConfiguration().setPushMode(PushMode.DISABLED);

        addSlowUploadExample("Single upload", false);
        addSlowUploadExample("Multiple upload", true);

        uploadStateWindow.setWindowPosition(UploadStateWindow.WindowPosition.BOTTOM_RIGHT);
//        addUploadSpeedSlider();
//        addOverallProgressSwitcher();
//        addAllUploadFinishedHandlerSwitcher();
//        addIndeterminateSwitcher();
//        addPollSwitcher();
//        addMaxFileCountSlider();
    }

    private void addLabels() {
    }

    private void createUploadFinishedHandler() {
        uploadFinishedHandler = (InputStream stream, String fileName, String mimeType, long length, int filesLeftInQueue) -> {
            Notification.show(fileName + " uploaded (" + length + " bytes). " + filesLeftInQueue + " files left.");
            lblFileName.setValue(fileName);
        };
    }

    private void addSlowUploadExample(String caption, boolean multiple) {
        final SlowUpload slowUpload = new SlowUpload(uploadFinishedHandler, uploadStateWindow, multiple);
        int maxFileSize = 5242880; //5 MB
        slowUpload.setMaxFileSize(maxFileSize);
        String errorMsgPattern = "File is too big (max = {0}): {2} ({1})";
        slowUpload.setSizeErrorMsgPattern(errorMsgPattern);
        slowUpload.setCaption(caption);
        slowUpload.setPanelCaption(caption);
        slowUpload.setMaxFileCount(FILE_COUNT);
        slowUpload.getSmartUpload().setUploadButtonCaptions("Upload File", "Upload Files");
        slowUpload.getSmartUpload().setUploadButtonIcon(FontAwesome.UPLOAD);

        form.addComponent(slowUpload, 0);
        lblFileName = new Label("abc");
        form.addComponent(lblFileName, 1);
        uploads.add(slowUpload);

//        addFocusBtn(slowUpload);
        if (multiple) {
            addDropArea(slowUpload);
        }
    }

    private void addDropArea(SlowUpload slowUpload) {
        Label dropLabel = new Label("Drop files here...");
        dropLabel.addStyleName(ValoTheme.LABEL_HUGE);
        Panel dropArea = new Panel(dropLabel);
        dropArea.setWidth(300, Unit.PIXELS);
        dropArea.setHeight(150, Unit.PIXELS);

        DragAndDropWrapper dragAndDropWrapper = slowUpload.createDropComponent(dropArea);
        dragAndDropWrapper.setSizeUndefined();
        form.addComponent(dragAndDropWrapper, 2);
    }


    private class SlowUpload extends MultiFileUpload {

        public SlowUpload(UploadFinishedHandler handler, UploadStateWindow uploadStateWindow) {
            super(handler, uploadStateWindow, true);
        }

        public SlowUpload(UploadFinishedHandler handler, UploadStateWindow uploadStateWindow, boolean multiple) {
            super(handler, uploadStateWindow, multiple);
        }

        @Override
        protected UploadStatePanel createStatePanel(UploadStateWindow uploadStateWindow) {
            return new SlowUploadStatePanel(uploadStateWindow);
        }
    }

    private class SlowUploadStatePanel extends UploadStatePanel {

        public SlowUploadStatePanel(UploadStateWindow window) {
            super(window);
        }

        @Override
        public void onProgress(StreamVariable.StreamingProgressEvent event) {
//            try {
//                Thread.sleep((int) uploadSpeed);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(WidgetTestApplication.class.getName()).log(Level.SEVERE, null, ex);
//            }
            super.onProgress(event);
        }
    }
}
