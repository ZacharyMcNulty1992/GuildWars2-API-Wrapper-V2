package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2PrefixedBuff extends GW2TraitedFacts {
	
	private GW2Prefix prefix;
	private Long duration;
	private String description;
	private Long applyCount;
	
	public GW2PrefixedBuff(JSONObject o){
		super(o);
		
		prefix = new GW2Prefix((JSONObject)o.get("prefix"));
		duration = (Long) o.get("duration");
		description = (String) o.get("description");
		applyCount = (Long) o.get("applly_count");
		
	}
	
	public GW2Prefix getPrefix(){
		return prefix;
	}
	
	public Long getDuration(){
		return duration;
	}
	
	public Long getApplyCount(){
		return applyCount;
	}
	
	public String getDescription(){
		return description;
	}
	
}
