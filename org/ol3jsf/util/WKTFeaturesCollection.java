package org.ol3jsf.util;

import java.util.ArrayList;
import java.util.List;

import org.ol3jsf.api.Feature;

public class WKTFeaturesCollection {
	List<Feature> features = new ArrayList<Feature>();

	public void addFeature(Feature feature) {
		features.add(feature);
	}

	public String toMap() {
		if (features.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder("[");

		boolean first = true;
		for (Feature feature : features) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}

			sb.append(feature.getGeometry());
		}

		sb.append("]");

		return sb.toString();
	}
}
