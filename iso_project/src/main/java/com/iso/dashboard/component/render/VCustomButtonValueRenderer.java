package com.iso.dashboard.component.render;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.iso.dashboard.utils.Constants;
import com.vaadin.client.renderers.ClickableRenderer;
import com.vaadin.client.widget.grid.RendererCellReference;
import com.vaadin.server.FontAwesome;
import java.util.HashMap;
import java.util.Map;

/**
 * UpperClass for all ButtonValueVariations: draws a FlowPanel, adds a HTML
 * widget that could handle clicks and add a label for value
 *
 * @author Marten Prieß (http://www.non-rocket-science.com)
 * @version 1.0
 */
public class VCustomButtonValueRenderer extends ClickableRenderer<String, FlowPanel> {

    private static String STYLE_NAME = "v-button-value-cell";

    public static final int VIEW_BITM = 4;
    public static final int EDIT_BITM = 16;
    public static final int DELETE_BITM = 32;

    private int clickedBITM = 0;
//    private final int buttonBITM;
    private Map<Button, Integer> mapAction;

//    public VCustomButtonValueRenderer(Map<Button, Integer> mapAction) {
    public VCustomButtonValueRenderer() {
        super();
        this.mapAction = new HashMap<>();
        Button btnEdit = GWT.create(Button.class);
        btnEdit.setStylePrimaryName("v-nativebutton");
        btnEdit.setTitle("abs");
        btnEdit.addStyleName("v-other");
        btnEdit.setHTML("<span></span>");
        mapAction.put(btnEdit, Constants.BUTTON_RENDERER.EDIT_BITM);
    }

    private Button genButton(final int bitm) {
        Button btn = GWT.create(Button.class);
        btn.setStylePrimaryName("v-nativebutton");
        btn.setTitle("abs");
        switch (bitm) {
            case VIEW_BITM:
                btn.addStyleName("v-view");
                break;
            case EDIT_BITM:
                btn.addStyleName("v-edit");
                break;
            case DELETE_BITM:
                btn.addStyleName("v-delete");
                break;

        }
        btn.setHTML("<span></span>");
        btn.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                VCustomButtonValueRenderer.this.clickedBITM = bitm;
                VCustomButtonValueRenderer.super.onClick(event);
            }
        });
        return btn;
    }

    /**
     * dirty hack - before we fire onClick we keep last clicked button because
     * of the lost of RelativeElement during converting and the issue of
     * different layouts
     */
    @Override
    public FlowPanel createWidget() {
        FlowPanel buttonBar = GWT.create(FlowPanel.class);
        buttonBar.setStylePrimaryName("v-button-bar");

        for (Map.Entry<Button, Integer> entry : mapAction.entrySet()) {
            Button btn = entry.getKey();
            btn.setStylePrimaryName("v-nativebutton");
            btn.setHTML("<span></span>");
            Integer bitm = entry.getValue();
            btn.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {
                    VCustomButtonValueRenderer.this.clickedBITM = bitm;
                    VCustomButtonValueRenderer.super.onClick(event);
                }
            });
        }

        FlowPanel panel = GWT.create(FlowPanel.class);
        panel.setStylePrimaryName(STYLE_NAME);
        panel.addStyleName("v-customr-buttons");
        panel.add(buttonBar);

        HTML valueLabel = GWT.create(HTML.class);
        valueLabel.setStylePrimaryName("v-cell-value");
        panel.add(valueLabel);
        return panel;
    }

    public int getClickedBITM() {
        return this.clickedBITM;
    }

    @Override
    public void render(final RendererCellReference cell, final String text, final FlowPanel panel) {
        ((HTML) panel.getWidget(1)).setHTML(text);
    }

}
