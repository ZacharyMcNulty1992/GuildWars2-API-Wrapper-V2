package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Recharge extends GW2TraitedFacts {

	private Long value; //recharge time in seconds
	
	public GW2Recharge(JSONObject o){
		super(o);
		
		value = (Long) o.get("value");
	}
	
	public Long getRechargeTime(){
		return value;
	}
}
