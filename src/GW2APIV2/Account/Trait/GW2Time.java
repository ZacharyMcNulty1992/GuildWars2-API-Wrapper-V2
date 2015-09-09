package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Time extends GW2TraitedFacts {

	public Long duration; //measured in seconds
	
	public GW2Time(JSONObject o) {
		super(o);
		
		duration = (Long) o.get("duration");
	}
	
	

}
