package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Percent extends GW2TraitedFacts {

	private Long percent; //percent value as referenced by the text field
	
	public GW2Percent(JSONObject o){
		super(o);
		
		percent = (Long) o.get("percent");
	}
	
	public Long getPercent(){
		return percent;
	}
}
