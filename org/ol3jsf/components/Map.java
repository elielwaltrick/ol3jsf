package org.ol3jsf.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ol3jsf.util.Properties;

@FacesComponent(value = "org.ol3jsf.components.Map", 
				createTag = true, 
				namespace = "http://ol3jsf.org/core", 
				tagName = "map")
public class Map extends UIInput {

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		String mapVar = getJsVariable();
		String id = this.getClientId();
		String center = getCenterLonLat();
		String zoom = getZoom();
		String transformationSource = getTransformationSource();
		String transformationTarget = getTransformationTarget();
		Properties.setTransformationSource(transformationSource);
		Properties.setTransformationTarget(transformationTarget);

		writer.startElement("div", this);
		writer.writeAttribute("id", id, "id");
		writer.writeAttribute("class", "map", null);
		writer.endElement("div");

		writer.startElement("script", this);
		writer.writeAttribute("type", "text/javascript", null);

		writer.write("var " + mapVar + " = new ol.Map({" 
							+ "target: '" + id + "'," 
							+ "view: new ol.View({" 
								+ "center: ol.proj.fromLonLat([" + center + "])," 
								+ "zoom: " + zoom 
							+ "})" 
					  + "});\n");
		writer.write("var wktFormatter = new ol.format.WKT();\n");
		writer.write("var layerSwitcher = new ol.control.LayerSwitcher();\n");
		writer.write(mapVar + ".addControl(layerSwitcher);\n");
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.endElement("script");
	}

	public String getCenterLonLat() {
		return (String) getStateHelper().eval("centerLonLat");
	}

	@Override
	public String getFamily() {
		return "components.Map";
	}

	public String getJsVariable() {
		return (String) getStateHelper().eval("jsVariable");
	}

	public String getTransformationSource() {
		return (String) getStateHelper().eval("transformationSource");
	}

	public String getTransformationTarget() {
		return (String) getStateHelper().eval("transformationTarget");
	}

	public String getZoom() {
		return (String) getStateHelper().eval("zoom");
	}

	public void setCenterLonLat(String centerLonLat) {
		getStateHelper().put("centerLonLat", centerLonLat);

	}

	public void setJsVariable(String jsVariable) {
		getStateHelper().put("jsVariable", jsVariable);
	}

	public void setTransformationSource(String transformationSource) {
		getStateHelper().put("transformationSource", transformationSource);
	}

	public void setTransformationTarget(String transformationTarget) {
		getStateHelper().put("transformationTarget", transformationTarget);
	}

	public void setZoom(String zoom) {
		getStateHelper().put("zoom", zoom);
	}
}
