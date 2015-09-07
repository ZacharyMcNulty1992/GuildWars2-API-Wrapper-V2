package GW2APIV2.Items;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

public class GW2InfixUpgrade {
	
	private HashMap<String, Long> attributes;
	
	//for boon duration and condition duration
	private Long skillId;
	private String effectDescription;
	
	public GW2InfixUpgrade(JSONObject o){
		
		List<JSONObject> att = (List) o.get("attributes");
		
		//populate the attributes map
		for(JSONObject a : att){
			attributes.put((String) a.get("attribute"), (Long) a.get("modifier"));
		}
		
		JSONObject buff = (JSONObject) o.get("buff");
		
		skillId = (Long) buff.get("akill_id");
		effectDescription = (String) buff.get("description");
		
	}
	

}
