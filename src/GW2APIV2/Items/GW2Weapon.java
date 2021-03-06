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
	    private List<GW2InfusionSlot> infusion_slots;
	    private GW2InfixUpgrade infix_upgrade;
	    
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
    	
    	List<JSONObject> is = (List) details.get("infusion_slots");
    	for(JSONObject a : is){
    		infusion_slots.add(new GW2InfusionSlot(a));
    	}
    	JSONObject iu = (JSONObject) details.get("infix_upgrade");
    	
    	if(iu != null)
    		infix_upgrade = new GW2InfixUpgrade(iu);
    	else
    		infix_upgrade = null;
    	
    	suffix_item_id = (Long) details.get("suffix_item_id");
    	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
	}
	
	/***********
	 * Getters *
	 ***********/
	@Override
	public Long getMinPower(){
		return min_power;
	}
	
	@Override
	public Long getMaxPower(){
		return max_power;
	}
	
	@Override
	public Long getShieldDefense(){
		if(secondaryType != "Shield")
			return null;
		else
			return shield_defense;
	}
	
	@Override
	public Long getSuffixItemId(){
		return suffix_item_id;
	}
	
	@Override
	public List<GW2InfusionSlot> getInfusionSlots(){
		return infusion_slots;
	}
	
	@Override
	public GW2InfixUpgrade getInfixUpgrade(){
		return infix_upgrade;
	}
	
	@Override
	public String getDamageType(){
		return damage_type;
	}
	
	@Override
	public String getSecondarySuffixItemId(){
		return secondary_suffix_item_id;
	}
	
	@Override
	public String getSecondaryDescription(){
		return secondaryDescription;
	}
	
	@Override
	public String getSecondaryType(){
		return secondaryType;
	}
}