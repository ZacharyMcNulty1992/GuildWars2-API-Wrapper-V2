package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Back extends GW2Item{
	
	private Map details;
	private List<JSONObject> infusion_slots;
    private List<JSONObject> infix_upgrade;
    private Long suffix_item_id;
    private String secondary_suffix_item_id;
	
	public GW2Back(JSONObject o){
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details");
		
		infusion_slots = (List<JSONObject>) details.get("infusion_slots");
    	infix_upgrade = (List<JSONObject>) details.get("infix_upgrade");
    	suffix_item_id = (Long) details.get("suffix_item_id");
    	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
	}
	
	public List<JSONObject> getInfusionSlots(){
		return infusion_slots;
	}
	public List<JSONObject> getInfixUpgrade(){
		return infix_upgrade;
	}
	public Long getSuffixItemId(){
		return suffix_item_id;			
	}
	public String getSecondarySuffixItemId(){
		return secondary_suffix_item_id;
	}
}
