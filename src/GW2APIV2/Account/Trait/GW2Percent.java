package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Percent extends GW2TraitedFacts {

	public Long percent;
	
	public GW2Percent(JSONObject o){
		super(o);
		
		percent = (Long) o.get("percent");
	}
}
