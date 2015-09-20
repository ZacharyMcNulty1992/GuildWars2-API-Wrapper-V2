package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Unblockable extends GW2TraitedFacts {
	
	private boolean value; //always true
	
	public GW2Unblockable(JSONObject o) {
		super(o);
		
		value = (boolean) o.get("value");
	}
	
	public boolean getValue(){
		return value; //always true
	}
}
