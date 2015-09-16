package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2ComboFinisher extends GW2TraitedFacts{
	
	private String finisherType; //type of the finisher
	private Long percent; //percent chance it will trigger
	
	public GW2ComboFinisher(JSONObject o){
		super(o);
		
		finisherType = (String) o.get("finisher_type");
		percent = (Long) o.get("percent");
		
	}
	
	
	/***********
	 * Getters *
	 ***********/
	
	public String getFinisherType(){
		return finisherType;
	}
	
	public Long getPercentChanceToProc(){
		return percent;
	}
}
