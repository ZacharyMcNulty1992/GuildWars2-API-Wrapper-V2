package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Distance extends GW2TraitedFacts {
	
	private Long distance;
	
	public GW2Distance(JSONObject o){
		super(o);
		
		distance = (Long) o.get("distance");
	}

	public Long getDistance(){
		return distance;
	}
}
