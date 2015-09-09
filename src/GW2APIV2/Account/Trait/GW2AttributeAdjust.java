package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2AttributeAdjust extends GW2TraitedFacts {

	public Long number;
	public String target;
	
	public GW2AttributeAdjust(JSONObject o){
		super(o);
		
		number = (Long) o.get("number");
		target = (String) o.get("target");
	}
}
