package GW2APIV2.Items;

import java.util.Map;

import org.json.simple.JSONObject;

public class GW2SalvageKit extends GW2Item {
	
	private Map details; //specific item types and other properties show up in this map
    
	private String secondaryType;
	private Long charges;
	
	public GW2SalvageKit(JSONObject o){
		super(o);

		//type specific variables are located here
		details = (Map) o.get("details"); 
		
		secondaryType = (String) details.get("type");
    	charges = (Long) details.get("charges");
    
	}

	public String getSecondaryType(){
		return secondaryType;
	}
	
	public Long getCharges(){
		return charges;
	}
}
