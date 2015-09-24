package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Trinket extends GW2Item {
	
	private Map details; 
    private String secondaryType;
    private List<GW2InfusionSlot> infusion_slots;
    private GW2InfixUpgrade infix_upgrade;
    private Long suffix_item_id;
    private String secondary_suffix_item_id;
    
	public GW2Trinket(JSONObject o){
		super(o);
	
		//type specific variables are located here
		details = (Map) o.get("details"); 
		
		secondaryType = (String) details.get("type");
		
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
	public String getSecondaryType(){
		return secondaryType;
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
	public String getSecondarySuffixItemId(){
		return secondary_suffix_item_id;
	}
	
	@Override
	public Long getSuffixItemId(){
		return suffix_item_id;
	}
}
