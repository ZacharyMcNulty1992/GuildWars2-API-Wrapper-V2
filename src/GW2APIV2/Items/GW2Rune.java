package GW2APIV2.Items;

import java.util.List;

import org.json.simple.JSONObject;

public class GW2Rune extends GW2UpgradeComponent{
	
	private List<String> bonuses;
	
	@SuppressWarnings("unchecked")
	public GW2Rune(JSONObject o){
		super(o);
		bonuses = (List<String>) super.details.get("bonuses");
	}
	
	public List<String> getBonuses(){
		return bonuses;
	}
}
