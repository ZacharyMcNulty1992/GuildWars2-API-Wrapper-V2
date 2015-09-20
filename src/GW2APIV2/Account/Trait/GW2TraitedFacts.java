package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2TraitedFacts {
	
	public String text;
	public String iconURL;
	public String type;
	
	//only have values if the object is a Traited Facts object, else these will be null
	public Long requiresTrait;
	public Long overrides;
	
	public GW2TraitedFacts(JSONObject o){
		
		text = (String) o.get("text");
		iconURL = (String) o.get("icon");
		type = (String) o.get("type");
		
		requiresTrait = (Long) o.get("requires_trait");
		overrides = (Long) o.get("overrides");
	}
	
	/***********
	 * Getters *
	 ***********/
	
	public String getText(){
		return text;
	}
	
	public String getIcon(){
		return iconURL;
	}
	
	public String getType(){
		return type;
	}
	
	public Long getRequiredTrait(){
		return requiresTrait;
	}
	
	public Long getOverrides(){
		return overrides;
	}
	
}