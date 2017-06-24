package org.ol3jsf.api;

import org.ol3jsf.util.Properties;

public class Feature {
	private String geometry;

	public Feature(String wkt) {
		setGeometry(
				"wktFormatter.readFeature('" + wkt + "',{dataProjection:" + "'" + Properties.getTransformationSource() + "',featureProjection:" + "'" + Properties.getTransformationTarget() + "'})");
	}

	public String getGeometry() {
		return geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

}
