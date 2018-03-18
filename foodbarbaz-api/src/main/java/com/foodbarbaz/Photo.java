package com.foodbarbaz;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "height", "html_attributions", "photo_reference", "width" })
public class Photo {

	@JsonProperty("photo_reference")
	private String photoReference;


	@JsonProperty("photo_reference")
	public String getPhotoReference() {
		return photoReference;
	}

	@JsonProperty("photo_reference")
	public void setPhotoReference(String photoReference) {
		this.photoReference = photoReference;
	}
	
}