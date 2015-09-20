package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Buff extends GW2TraitedFacts {
	
	private String status; //the boon condition or effect 
	private String description; //the description of the effect
	private Long applyCount; //the number of stacks of this effect
	private Long duration; //how long the effect lasts
	
	public GW2Buff(JSONObject o){
		super(o);
		
		status = (String) o.get("status");
		description = (String) o.get("description");
		applyCount = (Long) o.get("apply_count");
		duration = (Long) o.get("duration");
	}

	/***********
	 * Getters *
	 ***********/
	
	public String getStatus(){
		return status;
	}
	
	public String getDescription(){
		return description;
	}
	
	public Long getNumberOfStacks(){
		return applyCount;
	}
	
	public Long getDuration(){
		return duration;
	}
	
}
