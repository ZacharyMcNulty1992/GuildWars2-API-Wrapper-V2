package GW2APIV2.Account;

import org.json.simple.JSONObject;

public class GW2BankedMaterial {

	public Long id;
	public Long category;
	public Long count;
	
	public GW2BankedMaterial(JSONObject a){
		
		id = (Long) a.get("id");
		category = (Long) a.get("category");
		count = (Long) a.get("count");
	}
	
	/*
	 * Getters
	 */
	
	public Long getID(){
		return id;
	}
	
	public Long getCategory(){
		return category;
	}
	
	public Long getCount(){
		return count;
	}
}
