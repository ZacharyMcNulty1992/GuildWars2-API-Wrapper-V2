package GW2APIV2;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GW2BankItem {

	/********************************************************
	 *  NOTE: this class is a smaller version of the normal *
	 *   	  GW2Item class and is only used for effeciency *
	 ********************************************************/
	
	
	
	public Long id; //item id
	public Long count; //amount of an item there is
	public Long skin; //skin id
	public List upgrades; //id for each rune in an item if there is one
	public List infusions;//id for each infusion if an item has one
	
	public GW2BankItem(JSONObject a){
		id = (Long) a.get("id");
		count = (Long) a.get("count");
		skin = (Long) a.get("skin");
		upgrades = (List) a.get("upgrades");
		infusions = (List) a.get("infusions");
	}
	
	public List getUpgrades(){
		return upgrades;
	}
	
	public List getInfusions(){
		return infusions;
	}
}
