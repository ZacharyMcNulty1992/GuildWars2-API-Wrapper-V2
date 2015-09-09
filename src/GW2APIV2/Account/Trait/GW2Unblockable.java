package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2Unblockable extends GW2TraitedFacts {
	
	public boolean value;
	
	public GW2Unblockable(JSONObject o) {
		super(o);
		
		value = (boolean) o.get("value");
	}
}
