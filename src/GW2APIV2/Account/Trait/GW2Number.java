package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Number extends GW2TraitedFacts {
	
	public Long value;
	
	public GW2Number(JSONObject o){
		super(o);
		
		value = (Long) o.get("value");
	}

}
