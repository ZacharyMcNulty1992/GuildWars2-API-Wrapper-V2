package GW2APIV2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GW2TradingPost {

	private String apiKey;
	private InternetConnection ic;
	
	
	/*
	 * Default constructor 
	 * NOTE: prevents the use of current transactions and transaction history ties to your account
	 */
	
	public GW2TradingPost(){
		ic = new InternetConnection();
		apiKey = null;
	}
	
	/*
	 * Params: the String representation of your APIKey
	 * NOTE: only need to use if you want to access current transactions or transaction history that
	 * 		 are ties to your account
	 */
	
	public GW2TradingPost(String APIKey){
		apiKey = APIKey;
		ic = new InternetConnection(apiKey);
	}
	
	protected GW2Listings makeListing(int id){
		return null; //stub
	}
	
	/*******************
	 *  Public Methods *
	 *******************/
	
	
	/*
	 * GoldToInteger
	 * Params: int: Gold amt, int: Silver amt, int: Copper amt
	 * Returns: an int that represents the currency amt supplied as an integer
	 */
	public int GoldToInteger(int g, int s, int c){
		
		int val = 0;
		
		for(int x = 0; x < g; x++){
			val += 10000;
		}
		
		for(int x = 0; x < s; x++){
			val += 100;
		}
		
		for(int x = 0; x < c; x++){
			val += 1;
		}

		return val;
		
	}
	
	/*
	 * getBuyInfo
	 * Params: an item id
	 * Returns: a Listing object with the highest buying price and the amount of the item on the tp
	 */
	
	public GW2Listing getBuyInfo(Long id){
		
		try{
			String URLString = "https://api.guildwars2.com/v2/commerce/prices/";
			
			URL u = new URL(URLString + id);
			
			JSONObject a = ic.getJsonObj(u);
			JSONObject b = (JSONObject) a.get("buys");
			
			return new GW2Listing(b);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * getSellInfo
	 * Params: an item id
	 * Returns: a Listing object with the lowest selling price and the amount of the item on the tp
	 */
	
	public GW2Listing getSellInfo(Long id){
		
		try{
			
			String URLString = "https://api.guildwars2.com/v2/commerce/prices/";
			
			URL u = new URL(URLString + id);
			
			JSONObject a = ic.getJsonObj(u);
			JSONObject b = (JSONObject) a.get("sells");
	
			return new GW2Listing(b);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * gteLowestSellPrice
	 * Params: an item id
	 * Returns: the lowest selling price of that item on the tp
	 */
	public Long getLowestSellPrice(Long id){
		
		String URLString = "https://api.guildwars2.com/v2/commerce/prices/";
		
		try {
			
			URL u = new URL(URLString + id.intValue());
			
			JSONObject a = ic.getJsonObj(u);
			JSONObject b = (JSONObject) a.get("sells");
			
			Long c = (Long) b.get("unit_price");
			
			return c;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return (long) 0;
	}
	
	/*
	 * getHighestBuyPrice
	 * Params: an item id
	 * Returns: the highest buying price on the tp
	 */
	
	public Long getHighestBuyPrice(Long id){
		
		String URLString = "https://api.guildwars2.com/v2/commerce/prices/";
		try {
			URL u = new URL(URLString + id);
			
			JSONObject a = ic.getJsonObj(u);
			JSONObject b = (JSONObject) a.get("buys");
			
			Long c = (Long) b.get("unit_price");
			
			return c;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return (long) 0; 
	}
	
	/*
	 * getBuyQuantity
	 * Params: takes the int value of an item id
	 * Returns: the amount of that item being purchaced on the TP
	 */
	
	public Long getBuyQuantity(Long id){
		String URLString = "https://api.guildwars2.com/v2/commerce/prices/";
		try {
			URL u = new URL(URLString + id);
			
			JSONObject a = ic.getJsonObj(u);
			JSONObject b = (JSONObject) a.get("buys");
			
			Long c = (Long) b.get("quantity");
			
			return c;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return (long) 0;
	}
	
	/*
	 * getSellQuantity
	 * Params: takes the int value of an item id
	 * Returns the amount of that item currently being sold on the TP
	 */
	
	public Long getSellQuantity(Long id){
		String URLString = "https://api.guildwars2.com/v2/commerce/prices/";
		try {
			URL u = new URL(URLString + id);
			
			JSONObject a = ic.getJsonObj(u);
			JSONObject b = (JSONObject) a.get("sells");
			
			Long c = (Long) b.get("quantity");
			
			return c;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return (long) 0;
	}
	
	/********************
	 * Listings Methods *
	 ********************/
	
	/*
	 * getAllCurrentListingsOfItem
	 * Params: Long: the item id
	 * Returns: a GW2Listings Object containing all of the listings for an item
	 * 
	 * NOTE: the object returned contains both the listings for buy and sell
	 * 			in order to access the information at each listing you must get the lists
	 * 			of listing objects for both buy and sell
	 */
	
	public GW2Listings getAllCurrentListingsOfItem(Long id){
		
		try{
			
			String ListingString = "https://api.guildwars2.com/v2/commerce/listings/";

			URL ListingURL = new URL(ListingString + id.intValue());
			
			JSONObject a = ic.getJsonObj(ListingURL);
			
			return new GW2Listings((JSONArray) a.get("buys") , (JSONArray) a.get("sells"));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * getBuyListings
	 * Params: Long: an item id
	 * Returns: a List of Listing objects that contain the approate information
	 */
	public List<GW2Listing> getBuyListings(Long id){
		
		List<GW2Listing> buyListings = new ArrayList<GW2Listing>();
		
		try{
			
			String ListingString = "https://api.guildwars2.com/v2/commerce/listings/";

			URL ListingURL = new URL(ListingString + id.intValue());
			
			JSONObject listingObject = ic.getJsonObj(ListingURL);
			
			List<JSONObject> a = (List<JSONObject>) listingObject.get("buys");
			
			for(JSONObject c : a){
				buyListings.add(new GW2Listing(c));
			}
			
			return buyListings;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/*
	 * getSellListings
	 * Params: Long: an item id
	 * Returns: a list of Listing objects that contain the approaite information
	 */
	public List<GW2Listing> getSellListing(Long id){
		List<GW2Listing> sellListings = new ArrayList<GW2Listing>();
		
		try{
			
			String ListingString = "https://api.guildwars2.com/v2/commerce/listings/";

			URL ListingURL = new URL(ListingString + id.intValue());
			
			JSONObject listingObject = ic.getJsonObj(ListingURL);
			
			List<JSONObject> a = (List<JSONObject>) listingObject.get("sells");
			
			for(JSONObject c : a){
				sellListings.add(new GW2Listing(c));
			}
			
			return sellListings;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/*************************
	 * Account Transactions  *
	 * Requires API Key      *
	 *************************/
	
	/*
	 *getSellingHistory
	 *Params: None
	 *Returns: a list of selling transactions that have been fulfilled in the past 90 days  
	 */
	public List<GW2Transaction> getSellingHistory(){
		if(apiKey == null)
			return null;
		
		List<GW2Transaction> a = new ArrayList<GW2Transaction>();
		
		String TString = "https://api.guildwars2.com/v2/commerce/transactions";
		try {
			URL t = new URL(TString + "/history/sells");
			
			List<JSONObject> p = (List)ic.getJsonArray(t);
			
			for(JSONObject b : p){
				a.add(new GW2Transaction(b));
			}

			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	/*
	 * getBuyingHistory
	 * Params: None
	 * Returns: a list of buying transactions that have been fulfilled in the past 90 days
	 */
	public List<GW2Transaction> getBuyingHistory(){
		
		if(apiKey == null)
			return null;
		
		List<GW2Transaction> a = new ArrayList<GW2Transaction>();
		
		String TString = "https://api.guildwars2.com/v2/commerce/transactions";
		try {
			URL t = new URL(TString + "/history/buys");
			
			List<JSONObject> p = (List)ic.getJsonArray(t);
			
			for(JSONObject b : p){
				a.add(new GW2Transaction(b));
			}

			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * getCurrentlySelling
	 * Params: None
	 * Returns: a list of unfulfilled selling orders
	 */
	
	public List<GW2Transaction> getCurrentlySelling(){
		if(apiKey == null)
			return null;
		
		List<GW2Transaction> a = new ArrayList<GW2Transaction>();
		
		String TString = "https://api.guildwars2.com/v2/commerce/transactions";
		try {
			URL t = new URL(TString + "/current/sells");
			
			List<JSONObject> p = (List)ic.getJsonArray(t);
			
			for(JSONObject b : p){
				a.add(new GW2Transaction(b));
			}

			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	/*
	 * getCurrentlyBuying
	 * Params: None
	 * Returns: a list of unfulfilled buying orders
	 */
	
	public List<GW2Transaction> getCurrentlyBuying(){
		if(apiKey == null)
			return null;
		
		List<GW2Transaction> a = new ArrayList<GW2Transaction>();
		
		String TString = "https://api.guildwars2.com/v2/commerce/transactions";
		try {
			URL t = new URL(TString + "/current/buys");
			
			List<JSONObject> p = (List)ic.getJsonArray(t);
			
			for(JSONObject b : p){
				a.add(new GW2Transaction(b));
			}

			return a;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
