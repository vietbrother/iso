package com.iso.dashboard.component.render;


import com.vaadin.ui.Notification;
import com.vaadin.ui.renderers.ClickableRenderer;
import java.util.Map;

/**
 * Add view, edit and delete buttons next to the value (value is rendered as HTML)
 *
 * @author Marten Prieß (http://www.non-rocket-science.com)
 * @version 1.0
 */
public class CustomButtonValueRenderer extends ClickableRenderer<String> {

	/**
	 * "injects" view, edit and delete buttons in the cell
	 * 
	 * @param listener
	 *            that get triggered on click on both buttons
	 */
	public CustomButtonValueRenderer(final Map<Integer, HandlerButtonCustomRenderer> listener) {
		super(String.class);

		addClickListener(new RendererClickListener() {

			@Override
			public void click(final com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent event) {
                                if(listener.get(event.getRelativeX()) != null){
                                    listener.get(event.getRelativeX()).action(event);
                                } else {
                                    Notification.show("Nothing implement handler action!");
                                }
			}
		});
	}

}
