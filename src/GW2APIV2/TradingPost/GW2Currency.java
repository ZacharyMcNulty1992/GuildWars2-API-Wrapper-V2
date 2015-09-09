package GW2APIV2.TradingPost;

import org.json.simple.JSONObject;

public class GW2Currency {
	
	private Long id;
	private String name;
	private String description;
	private String icon_url; //used with the render service to get an icon
	private Long order; //used when sorting the list of currencies
	private Long amount; //amount of this currency if given
	
	
	public GW2Currency(JSONObject o){
		
		id = (Long) o.get("id");
		name = (String) o.get("name");
		description = (String) o.get("description");
		icon_url = (String) o.get("icon");
		order = (Long) o.get("order");
		amount = null;
	}
	
	public GW2Currency(JSONObject o, Long amt){
		
		id = (Long) o.get("id");
		name = (String) o.get("name");
		description = (String) o.get("description");
		icon_url = (String) o.get("icon");
		order = (Long) o.get("order");
		amount = amt;
	}

	/***********
	 * Getters *
	 ***********/
	public Long getId(){
		return id;
	}
	
	public Long getOrder(){
		return order;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getIconURL(){
		return icon_url;
	}
	
	public Long getAmount(){
		return amount;
	}
}
