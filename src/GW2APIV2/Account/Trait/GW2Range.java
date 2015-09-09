package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Range extends GW2TraitedFacts{
	
	public Long value; //range of the trait/skill
	
	public GW2Range(JSONObject o){
		super(o);
		
		value = (Long) o.get("value");
	}

}
