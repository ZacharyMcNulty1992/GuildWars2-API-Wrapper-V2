package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2PrefixedBuff extends GW2TraitedFacts {
	
	public GW2Prefix prefix;
	
	public GW2PrefixedBuff(JSONObject o){
		super(o);
		
		prefix = new GW2Prefix((JSONObject)o.get("prefix"));
	}

}
