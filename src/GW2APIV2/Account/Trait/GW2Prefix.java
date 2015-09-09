package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Prefix {
	
	public String text;
	public String icon;
	public String status;
	public String description;
	
	public GW2Prefix(JSONObject o){
		text = (String) o.get("text");
		icon = (String) o.get("icon");
		status = (String) o.get("status");
		description = (String) o.get("description");
	}

}
