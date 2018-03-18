package com.foodbarbaz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import examples.topic.Topic;

@RestController
public class RestaurantController {
	
	final private static String API_KEY = "AIzaSyD_fCoMhkGpVHTWgRxn3BWw95yDjOLKm-8";
	final private static String GEOCODE_API_KEY = "AIzaSyDr6r5KdOVFsxussTsOkElvahvers4SDdo";
	final private static String URL_START = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
	final private static String URL_AFTER = "&rankby=distance&type=restaurant&key=";
	final private static String URL_GEOCODE = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	final private static String URL_GOOGLE_PHOTO = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
	private String nextPageToken;
	private Double lat;
	private Double lng;
	
	@RequestMapping("/myRestaurants/{postalCode}")
	public List<Restaurant> displayRestaurants(@PathVariable String postalCode){
		RestTemplate restTemplate = new RestTemplate();
		
		getGeo(postalCode); // gets the lat and lng;
		
		if (lat == null && lng == null) { // if no results, return a restaurant with N/A values
			System.out.println("BAD VALUE");
			return Arrays.asList(new Restaurant("N/A","N/A","N/A","N/A","N/A","N/A"));
		}
		
		System.out.println(URL_START + lat + "," + lng + URL_AFTER + API_KEY);
		RestaurantsFullJSON restFullList = restTemplate.getForObject(URL_START + lat + "," + lng + URL_AFTER + API_KEY, RestaurantsFullJSON.class);
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		nextPageToken = restFullList.getNextPageToken(); // to use to get next page of results; not sure how yet
		
		System.out.println("LIST SIZE: " + restFullList.getResults().size() + "!");
		
		for (int i = 0; i < restFullList.getResults().size(); i++) {
			String photoRef;
			String photoURL;
			String address;
			String name;
			String rating;
			String priceLevel;
			String placeID;
			
			//System.out.println("-------------------------------");
			if(restFullList.getResults().get(i).getPhotos() != null) { // not every result has a photo
				photoRef = restFullList.getResults().get(i).getPhotos().get(0).getPhotoReference();
				photoURL = URL_GOOGLE_PHOTO + photoRef + "&key=" + API_KEY;
				//System.out.println(photoURL);
			}
			else {
				photoURL = "N/A";
			}
			
			/*System.out.println(restFullList.getResults().get(i).getVicinity());
			System.out.println(restFullList.getResults().get(i).getName());
			System.out.println(restFullList.getResults().get(i).getPriceLevel());
			System.out.println(restFullList.getResults().get(i).getRating());
			System.out.println(restFullList.getResults().get(i).getPlaceId());*/
			
			
			address = restFullList.getResults().get(i).getVicinity();
			name = restFullList.getResults().get(i).getName();
			
			if (restFullList.getResults().get(i).getRating() != null) {
				rating = restFullList.getResults().get(i).getRating().toString();
			}
			else {
				rating = "N/A";
			}
			
			if (restFullList.getResults().get(i).getPriceLevel() != null) {
				priceLevel = restFullList.getResults().get(i).getPriceLevel().toString();
			}
			else {
				priceLevel = "N/A";
			}
			
			placeID = restFullList.getResults().get(i).getPlaceId();
			
			restaurants.add(new Restaurant(name,placeID,address,photoURL,priceLevel,rating));
			
		}
		
		return restaurants;
	}
	
	@RequestMapping("/myGeoCode")
	public String displayGeoCode(){
		RestTemplate restTemplate = new RestTemplate();
		GeoResult geoResult = restTemplate.getForObject("https://maps.googleapis.com/maps/api/geocode/json?address=H3C+0e9&key=AIzaSyDr6r5KdOVFsxussTsOkElvahvers4SDdo", GeoResult.class);
		
		System.out.println("Latitude: " + geoResult.getResults().get(0).getGeometry().getLocation().getLat());
		System.out.println("Longitude: " + geoResult.getResults().get(0).getGeometry().getLocation().getLng());

		return geoResult.toString();
	}
	
	private void getGeo(String postalCode) {
		RestTemplate restTemplate = new RestTemplate();
		GeoResult geoResult = restTemplate.getForObject(URL_GEOCODE + postalCode + "&key=" + GEOCODE_API_KEY, GeoResult.class);
		
		System.out.println(geoResult.getStatus());
		
		if (!geoResult.getStatus().equals("ZERO_RESULTS")) {
			lat = geoResult.getResults().get(0).getGeometry().getLocation().getLat();
			lng = geoResult.getResults().get(0).getGeometry().getLocation().getLng();	
		}
	}
	
	
}
