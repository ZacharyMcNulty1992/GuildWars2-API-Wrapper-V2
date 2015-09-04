package GW2APIV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GW2Listings {
	
	private List<GW2Listing> buyListings; //First: buy price, Second: amount of items
	private List<GW2Listing> sellListings; //First: sell price, Second: amount of items
	
	public GW2Listings(JSONArray buy, JSONArray sell){
		 
		buyListings = new ArrayList<GW2Listing>();
		sellListings = new ArrayList<GW2Listing>();
		
		List<JSONObject> buying = (List) buy;
		List<JSONObject> selling = (List) sell;
		 
		Parse(buying, selling);
	}
	
	protected void Parse(List<JSONObject> a, List<JSONObject> b){
		
		for(JSONObject c : a){
			buyListings.add(new GW2Listing(c));
		}
		
		for(JSONObject c : b){
			sellListings.add(new GW2Listing(c));
		}
	}
	
	
	/***********
	 * Getters * 
	 ***********/
	
	public List<GW2Listing> getBuyListings(){
		return buyListings;
	}
	public List<GW2Listing> getSellListings(){
		return sellListings;
	}
}
