package org.ol3jsf.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ol3jsf.util.ComponentUtils;

@FacesComponent(value = "org.ol3jsf.components.InputVectorLayer", createTag = true, namespace = "http://ol3jsf.org/core", tagName = "inputVectorLayer")
public class InputVectorLayer extends UIInput {

	@Override
	public void encodeAll(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String features = ComponentUtils.getValueToRender(context, this);
		UIComponent parent = this;

		while (!(parent instanceof Map)) {
			parent = parent.getParent();
		}

		Map mapComponent = (Map) parent;
		String mapVar = mapComponent.getJsVariable();

		if (features == null || features.length() == 0) {
			features = "[]";
		}

		writer.write("var vector = new ol.layer.Vector({" + "  source: new ol.source.Vector(), " + "  style: new ol.style.Style({" + "	   fill: new ol.style.Fill({"
				+ "	     color: 'rgba(50,30,230, 0.3)'" + "	   })," + "	   stroke: new ol.style.Stroke({" + "	     color: 'rgba(50,25,180, 1)'," + "		 width: 2" + "	   })" + "  })" + "});\n");

		writer.write(mapVar + ".addLayer(vector);\n");
		writer.write("vector.getSource().addFeatures(" + features + ");\n");
	}

	@Override
	public String getFamily() {
		return "components.InputVectorLayer";
	}

}
