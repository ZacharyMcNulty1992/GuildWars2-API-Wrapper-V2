package GW2APIV2.Items;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class GW2UpgradeComponent extends GW2Item{
	
	//not returned
    private GW2InfixUpgrade infix_upgrade;//bonuses the upgrade component grant
    protected Map details; //protected here to give access to this variable to the child class
	
	//returned variables
	private List<String> componentOf; // a list of item types that this upgrade component would be useable for
    private List<String> infusion_upgrade_flags;//applicable infusion slot for infusion upgrades
    
    private String suffix; //what would be appended to the name of an item if it is applied
    private String secondaryType;
    
	
	public GW2UpgradeComponent(JSONObject o){
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details"); 
		
		secondaryType = (String) details.get("type");
    	componentOf = (List) details.get("flags");
    	infusion_upgrade_flags = (List) details.get("infusion_upgrade_flags");
    	suffix = (String) details.get("suffix");
    	JSONObject iu = (JSONObject) details.get("infix_upgrade");
    	
    	if(iu != null)
    		infix_upgrade = new GW2InfixUpgrade(iu);
    	else
    		infix_upgrade = null;
    	
	}

	/***********
	 * Getters *
	 ***********/
	
	@Override
	public String getSecondaryType(){
		return secondaryType;
	}
	
	@Override
	public List<String> getComponentOf(){
		return componentOf;
	}
	
	@Override
	public List<String> getInfusionUpgradeFlags(){
		return infusion_upgrade_flags;
	}
	
	@Override
	public String getSuffix(){
		return suffix;
	}
	
	@Override
	public GW2InfixUpgrade getInfixUpgrade(){
		return infix_upgrade;
	}
	

}
