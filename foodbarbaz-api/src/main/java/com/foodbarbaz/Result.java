package com.foodbarbaz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "address_components", "formatted_address", "geometry", "place_id", "types" })
public class Result {

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("geometry")
	public Geometry getGeometry() {
		return geometry;
	}

	@JsonProperty("geometry")
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

}