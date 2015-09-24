package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Armor extends GW2Item {
	
		
		private Map details;
	 	private List<GW2InfusionSlot> infusion_slots;
	    private GW2InfixUpgrade infix_upgrade;
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
    	List<JSONObject> is = (List) details.get("infusion_slots");
    	for(JSONObject a : is){
    		infusion_slots.add(new GW2InfusionSlot(a));
    	}
    	
    	JSONObject iu = (JSONObject) details.get("infix_upgrade");
    	
    	//test for null here
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
	public List<GW2InfusionSlot> getInfusionSlots(){
		return infusion_slots;
	}
	
	@Override
	public GW2InfixUpgrade getInfixUpgrade(){
		return infix_upgrade;
	}
	
	@Override
	public Long getSuffixItemId(){
		return suffix_item_id;
	}
	
	@Override
	public Long getDefense(){
		return defense;
	}
	
	@Override
	public String getSecondaryType(){
		return secondaryType;
	}
	
	@Override
	public String getSecondaryDescription(){
		return secondaryDescription;
	}
	
	@Override
	public String getSecondarySuffixItemId(){
		return secondary_suffix_item_id;
	}
	
	@Override
	public String getWeightClass(){
		return weight_class;
	}
}
