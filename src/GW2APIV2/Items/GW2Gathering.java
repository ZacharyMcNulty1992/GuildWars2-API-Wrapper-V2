package GW2APIV2.Items;

import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Gathering extends GW2Item {

	private Map details;
	private String secondaryType;
	
	public GW2Gathering(JSONObject o){
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details"); 
		secondaryType = (String) details.get("type");
	}
	
	@Override
	public String getSecondaryType(){
		return secondaryType;
	}
}
