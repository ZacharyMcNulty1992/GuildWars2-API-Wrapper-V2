package GW2APIV2.Account;

import java.util.List;

import org.json.simple.JSONObject;

public class GW2Skin {
	
	private Long id;			//the skin id
	private String name;		//name of the skin
	private String type;    	//possible values Armor, Weapon, or Back 
	private List<String> flags;	// additional details are stored in this list
								//possible flags: ShowInWardrobe, NoCost, HideIfLocked
	
	private List<String> restrictions;	//a list of restrictions
	private String icon; 		//the icon url
	private String description; //the tool tip description
	private JSONObject details; //the details object contains additional information like
								//the secondary type, and weight class, or damage type
	
	//these variables will only be non-null if the type is not back
	private String secondaryType;		//the secondary type if the primary type is weapon or armor
	private String weight_class; 		//armor type this skin will work with. ie) heavy, light
	private String damage_type;			//damage ype of the skin fire, ice , physical, etc...
	
	public GW2Skin(JSONObject o){
		
		//fill variables from the JSONObject
		id = (Long) o.get("id");
		name = (String) o.get("name");
		type = (String) o.get("type");
		flags = (List) o.get("flags");
		restrictions = (List) o.get("restrictions");
		icon = (String) o.get("icon");
		description = (String) o.get("description");
		details = (JSONObject) o.get("details");
		
		//now fill secondary variables based on the type of skin 
		if(type == "Armor"){
			secondaryType = (String) details.get("type");
			weight_class = (String) details.get("weight_class");
			damage_type = null;
		}
		else if(type == "Weapon"){
			secondaryType = (String) details.get("type");
			weight_class = null;
			damage_type = (String) details.get("damage_type");
		}
		else if(type == "Back"){
			secondaryType = null;
			weight_class = null;
			damage_type = null;
		}
	}
	
	/***********
	 * Getters *
	 ***********/
	public Long getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public List<String> getFlags(){
		return flags;
	}
	
	public List<String> getRestrictions(){
		return restrictions;
	}
	
	public String getIconURL(){
		return icon;
	}
	
	public String getDescription(){
		return description;
	}
	
	/*
	 * these next methods will return null if the type is not either weapon or armor
	 * they will also return null if for example getWeightClass is called on a weapon skin
	 */
	
	public String getSecondaryType(){
		return secondaryType;
	}
	
	public String getWeightClass(){
		return weight_class;
	}
	
	public String getDamageType(){
		return damage_type;
	}
}
