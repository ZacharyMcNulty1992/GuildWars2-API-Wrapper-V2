package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2AttributeAdjust extends GW2TraitedFacts {

	private Long value; //the amount the target gets adjusted
	private String target; //target of the adjustment
	
	public GW2AttributeAdjust(JSONObject o){
		super(o);
		
		value = (Long) o.get("value");
		target = (String) o.get("target");
	}
	
	public Long getAdjustmentAmount(){
		return value;
	}
	
	public String getTarget(){
		return target;
	}
	
}
