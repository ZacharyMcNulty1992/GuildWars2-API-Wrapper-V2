package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Percent extends GW2TraitedFacts {

	private Number percent; //percent value as referenced by the text field
	
	public GW2Percent(JSONObject o){
		super(o);
		
		percent = (Number) o.get("percent");
	}
	
	public Number getPercent(){
		return percent;
	}
}
