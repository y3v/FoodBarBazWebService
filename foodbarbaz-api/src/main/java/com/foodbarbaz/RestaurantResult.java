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
@JsonPropertyOrder({ "geometry", "icon", "id", "name", "opening_hours", "photos", "place_id", "price_level", "rating",
		"reference", "scope", "types", "vicinity" })
public class RestaurantResult {

	@JsonProperty("icon")
	private String icon;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("photos")
	private List<Photo> photos = null;
	
	@JsonProperty("place_id")
	private String placeId;
	
	@JsonProperty("price_level")
	private Integer priceLevel;
	
	@JsonProperty("rating")
	private Double rating;
	
	@JsonProperty("vicinity")
	private String vicinity;

	@JsonProperty("icon")
	public String getIcon() {
		return icon;
	}

	@JsonProperty("icon")
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("photos")
	public List<Photo> getPhotos() {
		return photos;
	}

	@JsonProperty("photos")
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	@JsonProperty("place_id")
	public String getPlaceId() {
		return placeId;
	}

	@JsonProperty("place_id")
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@JsonProperty("price_level")
	public Integer getPriceLevel() {
		return priceLevel;
	}

	@JsonProperty("price_level")
	public void setPriceLevel(Integer priceLevel) {
		this.priceLevel = priceLevel;
	}

	@JsonProperty("rating")
	public Double getRating() {
		return rating;
	}

	@JsonProperty("rating")
	public void setRating(Double rating) {
		this.rating = rating;
	}

	@JsonProperty("vicinity")
	public String getVicinity() {
		return vicinity;
	}

	@JsonProperty("vicinity")
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}


}