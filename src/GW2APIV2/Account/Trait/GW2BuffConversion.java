package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2BuffConversion extends GW2TraitedFacts {
	
	public String source;
	public String percent;
	public String target;
	
	public GW2BuffConversion(JSONObject o){
		super(o);
		
		source = (String) o.get("source");
		percent = (String) o.get("percent");
		target = (String) o.get("target");
		
	}

}
