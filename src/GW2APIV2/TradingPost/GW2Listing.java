package GW2APIV2.TradingPost;

import org.json.simple.JSONObject;

public class GW2Listing {

	private Long id;
	private Long numOfListings;
	private Long unitPrice;
	private Long quantity;
	
	public GW2Listing(JSONObject a){
		
		id = (Long) a.get("id");
		numOfListings = (Long) a.get("listings");
		unitPrice = (Long) a.get("unit_price");
		quantity = (Long) a.get("quantity");
		
	}
	
	/*
	 * Getters
	 */
	public Long getId(){
		return id;
	}
	
	public Long getNumberOfListings(){
		return numOfListings;
	}
	
	public Long getunitPrice(){
		return unitPrice;
	}
	
	public Long getQuantity(){
		return quantity;
	}
}
