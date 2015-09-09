package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Radius extends GW2TraitedFacts {
	
	public Long distance;
	
	public GW2Radius(JSONObject o){
		super(o);
		
		distance = (Long) o.get("distance");
	}

}
