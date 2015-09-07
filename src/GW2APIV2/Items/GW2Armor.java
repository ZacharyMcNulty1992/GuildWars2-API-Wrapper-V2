package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Armor extends GW2Item {
	
		
		private Map details;
	 	private List<JSONObject> infusion_slots;
	    private List<JSONObject> infix_upgrade;
	    private Long suffix_item_id;
	    private Long defense;
	    private String secondaryType;
	    private String secondary_suffix_item_id;
	    private String secondaryDescription;
	    private String weight_class;
	    
	    
	public GW2Armor(JSONObject o){
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details"); 
		
		secondaryType = (String) details.get("type");
    	weight_class = (String) details.get("weight_class");
    	defense = (Long) details.get("defense");
    	infusion_slots = (List) details.get("infusion_slots");
    	infix_upgrade = (List) details.get("infix_upgrade");
    	suffix_item_id = (Long) details.get("suffix_item_id");
    	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
        
	}
	
	/***********
	 * Getters *
	 ***********/
	public List<JSONObject> getInfusionSlots(){
		return infusion_slots;
	}
	
	public List<JSONObject> getInfixUpgrade(){
		return infix_upgrade;
	}
	
	public Long getSuffixItemId(){
		return suffix_item_id;
	}
	
	public Long getDefense(){
		return defense;
	}
	
	public String getSecondaryType(){
		return secondaryType;
	}
	
	public String getSecondaryDescription(){
		return secondaryDescription;
	}
	
	public String getSecondarySuffixItemId(){
		return secondary_suffix_item_id;
	}
	
	public String getWeightClass(){
		return weight_class;
	}
}
