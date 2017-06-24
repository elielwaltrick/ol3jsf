package org.ol3jsf.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ol3jsf.util.ComponentUtils;

@FacesComponent(value = "org.ol3jsf.components.Popup", 
				createTag = true, 
				namespace = "http://ol3jsf.org/core", 
				tagName = "popup")
public class Popup extends UIInput {

	@Override
	public void encodeAll(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UIComponent parent = this;

		while (!(parent instanceof Map)) {
			parent = parent.getParent();
		}

		Map mapComponent = (Map) parent;
		String mapVar = mapComponent.getJsVariable();

		String id = (String) this.getAttributes().get("id");
		String styleClass = (String) this.getAttributes().get("styleClass");

		// popup
		writer.write("var popupContainer = document.createElement('div');\n");
		writer.write("popupContainer.id = '" + id + "';\n");
		writer.write("popupContainer.className = '" + styleClass + "';\n");

		// popup-closer
		writer.write("var popupCloser = document.createElement('a');\n");
		writer.write("popupCloser.href = '#';\n");
		writer.write("popupCloser.id = 'popup-closer';\n");
		writer.write("popupCloser.className = 'ol-popup-closer';\n");

		// popup-content
		writer.write("var popupContent = document.createElement('div');\n");
		writer.write("popupContent.id = 'popup-content';\n");

		writer.write("var overlay = new ol.Overlay(({"
				+ "     element: popupContainer, "
				+ "     autoPan: true, "
				+ "     autoPanAnimation: {duration: 250}"
				+ "   }));\n");
		
		writer.write("popupCloser.onclick = function() {"
				+ "     overlay.setPosition(undefined); "
				+ "     popupCloser.blur(); "
				+ "     return false; "
				+ "   };\n");
		
		writer.write(mapVar + ".addOverlay(overlay);\n");
		writer.write(mapVar + ".on('singleclick', function(evt) { "
				+ "              var coordinate = evt.coordinate; "
				+ "              overlay.setPosition(coordinate); "
				+ "            });\n");

		writer.write("popupContainer.appendChild(popupCloser);\n");
		writer.write("popupContainer.appendChild(popupContent);\n");

	}

	@Override
	public String getFamily() {
		return "components.Popup";
	}

}
