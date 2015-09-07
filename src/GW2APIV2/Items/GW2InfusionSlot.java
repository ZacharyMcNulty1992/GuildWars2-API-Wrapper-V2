package GW2APIV2.Items;

import java.util.List;

import org.json.simple.JSONObject;

public class GW2InfusionSlot {
	
	private String flag; //if this is null this is an agony infuaion slot
	private Long itemId;
	
	public GW2InfusionSlot(JSONObject o){
		List<String> flags = (List) o.get("flags");
		
		for(String f : flags){
			flag = f;
		}
		
		itemId = (Long) o.get("item_id");
	}

}
