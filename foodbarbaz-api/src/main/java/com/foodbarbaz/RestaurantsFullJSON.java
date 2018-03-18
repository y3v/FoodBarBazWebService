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
@JsonPropertyOrder({ "html_attributions", "next_page_token", "results", "status" })
public class RestaurantsFullJSON {

	@JsonProperty("html_attributions")
	private List<Object> htmlAttributions = null;
	@JsonProperty("next_page_token")
	private String nextPageToken;
	@JsonProperty("results")
	private List<RestaurantResult> results = null;
	@JsonProperty("status")
	private String status;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("html_attributions")
	public List<Object> getHtmlAttributions() {
		return htmlAttributions;
	}

	@JsonProperty("html_attributions")
	public void setHtmlAttributions(List<Object> htmlAttributions) {
		this.htmlAttributions = htmlAttributions;
	}

	@JsonProperty("next_page_token")
	public String getNextPageToken() {
		return nextPageToken;
	}

	@JsonProperty("next_page_token")
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	@JsonProperty("results")
	public List<RestaurantResult> getResults() {
		return results;
	}

	@JsonProperty("results")
	public void setResults(List<RestaurantResult> results) {
		this.results = results;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}