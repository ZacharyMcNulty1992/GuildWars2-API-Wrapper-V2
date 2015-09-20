package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Damage extends GW2TraitedFacts {
	
	private Long hitCount; //amout of time the damage hits
	
	public GW2Damage(JSONObject o){
		super(o);
		
		hitCount = (Long) o.get("hit_count");
	}
	
	public Long getHitCount(){
		return hitCount;
	}
	
}
