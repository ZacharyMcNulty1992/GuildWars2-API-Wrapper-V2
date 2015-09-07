package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Weapon extends GW2Item{
		
	    private Long min_power;
	    private Long max_power;
	    private Long shield_defense; //used for shields
	    private Long suffix_item_id;
	    
	    private Map details;
	    private List<JSONObject> infusion_slots;
	    private List<JSONObject> infix_upgrade;
	    
	    private String damage_type;
	    private String secondary_suffix_item_id;
	    private String secondaryDescription;
	    private String secondaryType;
	    
	public GW2Weapon(JSONObject o) {
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details"); 
		
		//set all of the variables for weapons
		secondaryType = (String) details.get("type");
    	damage_type = (String) details.get("damage_type");
    	min_power = (Long) details.get("min_power");
    	max_power = (Long) details.get("max_power");
    	if(secondaryType == "Shield")
    		shield_defense = (Long) details.get("defense");
    	infusion_slots = (List) details.get("infusion_slots");
    	infix_upgrade = (List) details.get("infix_upgrade");
    	suffix_item_id = (Long) details.get("suffix_item_id");
    	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
	}
	
	/***********
	 * Getters *
	 ***********/
	
	public Long getMinPower(){
		return min_power;
	}
	
	public Long getMaxPower(){
		return max_power;
	}
	
	public Long getShieldDefense(){
		if(secondaryType != "Shield")
			return null;
		else
			return shield_defense;
	}
	
	public Long getSuffixItemId(){
		return suffix_item_id;
	}
	
	public List<JSONObject> getInfusionSlots(){
		return infusion_slots;
	}
	
	public List<JSONObject> getInfixUpgrade(){
		return infix_upgrade;
	}
	
	public String getDamageType(){
		return damage_type;
	}
	
	public String getSecondarySuffixItemId(){
		return secondary_suffix_item_id;
	}
	
	public String getSecondaryDescription(){
		return secondaryDescription;
	}
	
	public String getSecondaryType(){
		return secondaryType;
	}
}