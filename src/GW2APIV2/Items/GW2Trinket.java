package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Trinket extends GW2Item {
	
	private Map details; 
    private String secondaryType;
    private List<JSONObject> infusion_slots;
    private List<JSONObject> infix_upgrade;
    private Long suffix_item_id;
    private String secondary_suffix_item_id;
    
	public GW2Trinket(JSONObject o){
		super(o);
	
		//type specific variables are located here
		details = (Map) o.get("details"); 
		
		secondaryType = (String) details.get("type");
    	infusion_slots = (List) details.get("infusion_slots");
    	infix_upgrade = (List) details.get("infix_upgrade");
    	suffix_item_id = (Long) details.get("suffix_item_id");
    	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
	}
	
	public String getSecondaryType(){
		return secondaryType;
	}
	
	public List<JSONObject> getInfusionSlots(){
		return infusion_slots;
	}
	
	public List<JSONObject> getInfixUpgrade(){
		return infix_upgrade;
	}
	
	public String getSecondarySuffixItemId(){
		return secondary_suffix_item_id;
	}
	
	public Long getSuffixItemId(){
		return suffix_item_id;
	}
}
