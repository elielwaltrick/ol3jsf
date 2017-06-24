package org.ol3jsf.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "org.ol3jsf.components.WMSLayer", 
				createTag = true, 
				namespace = "http://ol3jsf.org/core", 
				tagName = "wmsLayer")
public class WMSLayer extends UIComponentBase {

	@Override
	public void encodeAll(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UIComponent parent = this;

		while (!(parent instanceof Map)) {
			parent = parent.getParent();
		}

		Map mapComponent = (Map) parent;
		String mapVar = mapComponent.getJsVariable();

		String url = getUrl();
		String layer = getLayer();
		String name = getName();

		String newLayer = "var newLayer = new ol.layer.Image({"
				+ "		     title: '" + name + "', "
				+ "		     source: new ol.source.ImageWMS({url: '" + url + "',  "
				+ "            params: {'LAYERS': '" + layer
				+ "                  ', 'VERSION': '1.1.1'}," 
				+ "            serverType: 'geoserver' "
			    + "          }) "
				+ "        });\n";

		writer.write(newLayer + mapVar + ".addLayer(newLayer);\n");
	}

	@Override
	public String getFamily() {
		return "components.Layer";
	}

	public String getLayer() {
		return (String) getStateHelper().eval("layer");
	}

	public String getName() {
		return (String) getStateHelper().eval("name");
	}

	public String getUrl() {
		return (String) getStateHelper().eval("url");
	}

	public void setLayer(String layer) {
		getStateHelper().put("layer", layer);
	}

	public void setName(String name) {
		getStateHelper().put("name", name);
	}

	public void setUrl(String url) {
		getStateHelper().put("url", url);
	}
}
