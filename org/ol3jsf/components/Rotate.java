package org.ol3jsf.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "org.ol3jsf.components.Rotate", 
				createTag = true, 
				namespace = "http://ol3jsf.org/core", 
				tagName = "rotate")
public class Rotate extends UIComponentBase {

	@Override
	public void encodeAll(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UIComponent parent = this;

		while (!(parent instanceof Map)) {
			parent = parent.getParent();
		}

		Map mapComponent = (Map) parent;
		String mapVar = mapComponent.getJsVariable();

		writer.write(mapVar + ".addInteraction(new ol.interaction.DragRotateAndZoom());\n");
	}

	@Override
	public String getFamily() {
		return "components.Rotate";
	}
}
