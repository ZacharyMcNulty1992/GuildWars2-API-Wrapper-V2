package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Prefix {
	
	private String text; //the type of prefix
	private String icon; //url of the icon
	private String status; // ie) fire attunement
	private String description; //description of the prefix
	
	public GW2Prefix(JSONObject o){
		text = (String) o.get("text");
		icon = (String) o.get("icon");
		status = (String) o.get("status");
		description = (String) o.get("description");
	}

	
	/***********
	 * Getters *
	 ***********/
	
	public String getText(){
		return text;
	}
	
	public String getIcon(){
		return icon;
	}
	
	public String getStatus(){
		return status;
	}
	
	public String getDescription(){
		return description;
	}
	
}
