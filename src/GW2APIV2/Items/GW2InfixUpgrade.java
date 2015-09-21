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
		
		attributes = new HashMap<String, Long>();
		
		//populate the attributes map
		for(JSONObject a : att){
			attributes.put((String) a.get("attribute"), (Long) a.get("modifier"));
		}
		
		JSONObject buff = (JSONObject) o.get("buff");
		
		if(buff != null){
			skillId = (Long) buff.get("akill_id");
			effectDescription = (String) buff.get("description");
		}else{
			skillId = null;
			effectDescription = null;
		}
			
	}
	
	
	/***********
	 * Getters *
	 ***********/
	
	public HashMap<String, Long> getAttributes(){
		return attributes;
	}
	
	public Long getSkillId(){
		return skillId;
	}
	
	public String getEffectDescription(){
		return effectDescription;
	}
}
