package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2BuffConversion extends GW2TraitedFacts {
	
	private String source; //attribute used to calculate the gain
	private Long percent; //amount of the source attribute is added to the target
	private String target; //the attribute that is added to
	
	public GW2BuffConversion(JSONObject o){
		super(o);
		
		source = (String) o.get("source");
		percent = (Long) o.get("percent");
		target = (String) o.get("target");
		
	}

	
	/***********
	 * Getters *
	 ***********/
	
	public String getSource(){
		return source;
	}
	
	public Long getPercent(){
		return percent;
	}
	
	public String getTarget(){
		return target;
	}
	
}
