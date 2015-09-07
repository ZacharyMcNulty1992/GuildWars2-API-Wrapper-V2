package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2UpgradeComponent extends GW2Item{
	
	//not returned
	//secondary_infix_upgrade will need to be changed when data is added to its attributes field
    private Map secondary_infix_upgrade;//bonuses the upgrade component grants
    private Map buff; //in the secondary infix upgrade ////// this holes information about the upgrades effects
    protected Map details; //protected here to give access to this variable to the child class
	
	
	//returned variables
	private List<String> componentOf; // a list of item types that this upgrade component would be useable for
    private List<String> infusion_upgrade_flags;//applicable infusion slot for infusion upgrades
    private List<String> attributes; //not yet implemented in the game // therefore this is only a place holder for a later update
    
    private String skill_id; //in the buff map ///// id of the skill
    private String suffix; //what would be appended to the name of an item if it is applied
    private String secondaryDescription;
    private String secondaryType;
    
	
	public GW2UpgradeComponent(JSONObject o){
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details"); 
		
		secondaryType = (String) details.get("type");
    	componentOf = (List) details.get("flags");
    	infusion_upgrade_flags = (List) details.get("infusion_upgrade_flags");
    	suffix = (String) details.get("suffix");
    	secondary_infix_upgrade = (Map) details.get("infix_upgrade");
    	
    	//runes do not have a buff property
    	if(secondaryType != "rune"){
    		buff = (Map) secondary_infix_upgrade.get("buff");
    		
    		//get varibales form the buff object
    		skill_id = (String) buff.get("skill_id");
    		secondaryDescription = (String) buff.get("description");
    	}
    	else{
    		buff = null;
    		skill_id = null;
    		secondaryDescription = null;
    	}
	}

	public String getSecondaryType(){
		return secondaryType;
	}
	
	public List<String> getComponentOf(){
		return componentOf;
	}
	
	public List<String> getInfusionUpgradeFlags(){
		return infusion_upgrade_flags;
	}
	
	/*
	 * getAttributes()
	 *  this is currently null until heart of thorns XPac
	 */
	public List<String> getAttributes(){
		return attributes;
	}
	
	public String getSkillId(){
		return skill_id;
	}
	
	public String getSuffic(){
		return suffix;
	}
	
	public String getSecondaryDescription(){
		return secondaryDescription;
	}
	

}
