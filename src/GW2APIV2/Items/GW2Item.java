package GW2APIV2.Items;

import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by zach on 6/17/2015.
 */
public class GW2Item {
	
	/*
	 * Generic properties
	 */
	
    private String name;
    private String iconURL;
    private String description;
    private String type;
    private String rarity;
    private Long id;
    private Long level;
    private Long vendor_Value;
    private Long default_skin;
    private List<String> flags;
    private List<String> game_types;
    private List<String> restrictions;

    //takes an object supplied to the child and populates common variables
    public GW2Item(JSONObject o){
        try {
            //general variable initializations
            name = (String) o.get("name");
            id = (Long) o.get("id");
            iconURL = (String) o.get("icon");
            description = (String) o.get("description");
            type = (String) o.get("type");
            rarity = (String) o.get("rarity");
            level = (Long) o.get("level");
            vendor_Value = (Long) o.get("vendor_value");
            default_skin = (Long) o.get("default_skin");
            flags = (List)  o.get("flags");
            game_types = (List)  o.get("game_types");
            restrictions = (List) o.get("restrictions");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     *  Getters for generic item traits
     *  therefore there is no checking for errors
     */

    public String getName(){
        return name;
    }

    public String getIconUrl(){
        return iconURL;
    }

    public String getDescription(){
        return description;
    }

    public String getType(){
        return type;
    }

    public String getRarity(){
        return rarity;
    }
    
    public Long getLevel(){
        return level;
    }

    public Long getVendorValue(){
        return vendor_Value;
    }

    public Long getId(){
    	return id;
    }
    
    public Long getDefaultSkinId(){
    	return default_skin;
    }
    
    public List<String> getFlags(){
        return flags;
    }

    public List<String> getGame_types(){
        return game_types;
    }

    public List<String> getRestrictions(){
        return restrictions;
    }

    /*********************
     * Overriden methods *
     *********************/
    
    /*
     * these methods are overriden and only return null if the child class does not override them
     */
    
	public GW2InfixUpgrade getInfixUpgrade() {
		return null;
	}

	public List<GW2InfusionSlot> getInfusionSlots() {
		return null;
	}

	public Long getSuffixItemId() {
		return null;
	}

	public Long getDefense() {
		return null;
	}

	public String getSecondaryType() {
		return null;
	}

	public String getSecondaryDescription() {
		return null;
	}

	public String getSecondarySuffixItemId() {
		return null;
	}

	public String getWeightClass() {
		return null;
	}

	public Long getSize() {
		return null;
	}

	public boolean getSellOrSort() {
		return false;
	}

	public String getUnlockType() {
		return null;
	}

	public Long getColorId() {
		return null;
	}

	public Long getRecipeId() {
		return null;
	}

	public Long getDuration() {
		return null;
	}

	public List<String> getBonuses() {
		return null;
	}

	public Long getCharges() {
		return null;
	}

	public List<String> getComponentOf() {
		return null;
	}

	public List<String> getInfusionUpgradeFlags() {
		return null;
	}

	public List<String> getAttributes() {
		return null;
	}

	public String getSkillId() {
		return null;
	}

	public String getSuffix() {
		return null;
	}

	public Long getMinPower() {
		return null;
	}

	public Long getMaxPower() {
		return null;
	}

	public Long getShieldDefense() {
		return null;
	}

	public String getDamageType() {
		return null;
	}
}