package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Back extends GW2Item{
	
	private Map details;
	private List<GW2InfusionSlot> infusion_slots;
    private GW2InfixUpgrade infix_upgrade;
    private Long suffix_item_id;
    private String secondary_suffix_item_id;
	
	public GW2Back(JSONObject o){
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details");
		
		List<JSONObject> is = (List) details.get("infusion_slots");
    	for(JSONObject a : is){
    		infusion_slots.add(new GW2InfusionSlot(a));
    	}
    	
		JSONObject iu = (JSONObject) details.get("infix_upgrade");
    	infix_upgrade = new GW2InfixUpgrade(iu);
    	suffix_item_id = (Long) details.get("suffix_item_id");
    	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
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
	public Long getSuffixItemId(){
		return suffix_item_id;			
	}
	
	@Override
	public String getSecondarySuffixItemId(){
		return secondary_suffix_item_id;
	}
}
