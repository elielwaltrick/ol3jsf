package org.ol3jsf.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "org.ol3jsf.components.OSMLayer", 
				createTag = true, 
				namespace = "http://ol3jsf.org/core", 
				tagName = "osmLayer")
public class OSMLayer extends UIComponentBase {

	@Override
	public void encodeAll(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UIComponent parent = this;

		while (!(parent instanceof Map)) {
			parent = parent.getParent();
		}

		Map mapComponent = (Map) parent;
		String mapVar = mapComponent.getJsVariable();

		String isBaseLayer = getIsBaseLayer();
		String newLayer = "var newOsmLayer = new ol.layer.Tile({  source: new ol.source.OSM()});\n";
		String addLayer;

		if (isBaseLayer != null && isBaseLayer.equals("true")) {
			addLayer = "var layersCollection = " + mapVar + ".getLayers(); layersCollection.insertAt(0, newOsmLayer);\n";
		} else {
			addLayer = mapVar + ".addLayer(newOsmLayer);\n";
		}

		writer.write(newLayer + addLayer);
	}

	@Override
	public String getFamily() {
		return "components.Layer";
	}

	public String getIsBaseLayer() {
		return (String) getStateHelper().eval("isBaseLayer");
	}

	public void setIsBaseLayer(String isBaseLayer) {
		getStateHelper().put("isBaseLayer", isBaseLayer);
	}

}