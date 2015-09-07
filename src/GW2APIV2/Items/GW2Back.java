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
	}

}
