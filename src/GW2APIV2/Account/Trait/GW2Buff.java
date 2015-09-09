package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Buff extends GW2TraitedFacts {
	
	public String status;
	public String description;
	public Long applyCount;
	public Long duration;
	
	public GW2Buff(JSONObject o){
		super(o);
		
		status = (String) o.get("status");
		description = (String) o.get("description");
		applyCount = (Long) o.get("apply_count");
		duration = (Long) o.get("duration");
	}

}
