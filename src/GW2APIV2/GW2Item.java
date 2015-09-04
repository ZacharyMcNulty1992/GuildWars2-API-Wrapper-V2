package GW2APIV2;

import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

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
    private String secondaryType;
    private String rarity;
    private Long id;
    private Long level;
    private Long vendor_Value;
    private Long default_skin;
    private List<String> flags;
    private List<String> game_types;
    private List<String> restrictions;
    private Map details; //specific item types and other properties show up in this map
    private Map infusion_slots;
    private Map infix_upgrade;
    private Long suffix_item_id;
    private String secondary_suffix_item_id;
    private String secondaryDescription;
    
    /*
     * Armor and Back properties
     * NOTE: Back items only have the last 4 properties
     */
    private String weight_class;
    private Long defense;
    
    
    
    /*
     * Bag properties
     */
    private Long size; //number of bag slots
    private boolean no_sell_or_sort; //true if the bag hides items from merchants when selling items
    
    /*
     * Consumable properties
     */
    private Long duration; //measured in miliseconds
    private String unlock_type; //if the consumable unlocks something it is listed here
    private Long color_id; //used for dyes, this represents the color that is unlocked by the dye
    private Long recipe_id;
    
    
    /*
     * Salvage Kits variables
     */
    private Long charges;
    
    /*
     * Upgrade Component variables
     */
    private List<String> componentOf; // a list of item types that this upgrade component would be useable for
    private List<String> infusion_upgrade_flags;//applicable infusion slot for infuaion upgrades
    private String suffix; //what would be appended to the name of an item if it is applied
    private Map secondary_infix_upgrade;//bonuses the upgrade component grants
    private List<String> attributes; //not yet implemented in the game // therefore this is only a place holder for a later update
    private Map buff; //in the secondary infix upgrade ////// this holes information about the upgrades effects
    private String skill_id; //in the buff map ///// id of the skill
    private List<String> bonuses;
    
    
    /*
     * Weapon variables
     */
    private String damage_type;
    private Long min_power;
    private Long max_power;
    private Long shield_defense; //used for shields

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
            
            //used to get the other variables for specific item types
            //there is no get for this variable
            details = (Map) o.get("details"); 
            
            
            /*
             * decission structure to fill the proper variables with the proper data depending on the item type
             */
            
            if(type == "Armor"){
            	secondaryType = (String) details.get("type");
            	weight_class = (String) details.get("weight_class");
            	defense = (Long) details.get("defense");
            	infusion_slots = (Map) details.get("infusion_slots");
            	infix_upgrade = (Map) details.get("infix_upgrade");
            	suffix_item_id = (Long) details.get("suffix_item_id");
            	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
            }
            else if(type == "Back"){
            	infusion_slots = (Map) details.get("infusion_slots");
            	infix_upgrade = (Map) details.get("infix_upgrade");
            	suffix_item_id = (Long) details.get("suffix_item_id");
            	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
            }
            else if(type == "Bag"){
            	size = (Long) details.get("size");
            	no_sell_or_sort = (boolean) details.get("no_sell_or_sort");
            }
            else if(type == "Consumable"){
            	secondaryType = (String) details.get("type");
            	secondaryDescription = (String) details.get("description");
            	duration = (Long) details.get("duration_ms");
            	unlock_type = (String) details.get("unlock_type");
            	color_id = (Long) details.get("color_id");
            	recipe_id = (Long) details.get("recipe_id");
            }
            else if(type == "Container"){
            	secondaryType = (String) details.get("type");
            }
            else if(type == "Gathering"){
            	secondaryType = (String) details.get("type");
            }
            else if(type == "Gizmo"){
            	secondaryType = (String) details.get("type");
            }
            else if(type == "Tool"){ //these are for salvage kits
            	secondaryType = (String) details.get("type");
            	charges = (Long) details.get("charges");
            }
            else if(type == "Trinket"){
            	secondaryType = (String) details.get("type");
            	infusion_slots = (Map) details.get("infusion_slots");
            	infix_upgrade = (Map) details.get("infix_upgrade");
            	suffix_item_id = (Long) details.get("suffix_item_id");
            	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
            }
            else if(type == "UpgradeComponent"){
            	secondaryType = (String) details.get("type");
            	componentOf = (List) details.get("flags");
            	infusion_upgrade_flags = (List) details.get("infusion_upgrade_flags");
            	suffix = (String) details.get("suffix");
            	secondary_infix_upgrade = (Map) details.get("infix_upgrade");
            	
            	//runes do not have a buff property
            	if(secondaryType != "rune")
            		buff = (Map) secondary_infix_upgrade.get("buff");
            	
            	skill_id = (String) buff.get("skill_id");
            	secondaryDescription = (String) buff.get("description");
            	
            	//only runes have this property
            	if(secondaryType == "Rune")
            		bonuses = (List) details.get("bonuses");
            }
            else if(type == "Weapon"){
            	secondaryType = (String) details.get("type");
            	damage_type = (String) details.get("damage_type");
            	min_power = (Long) details.get("min_power");
            	max_power = (Long) details.get("max_power");
            	if(secondaryType == "Shield")
            		shield_defense = (Long) details.get("defense");
            	infusion_slots = (Map) details.get("infusion_slots");
            	infix_upgrade = (Map) details.get("infix_upgrade");
            	suffix_item_id = (Long) details.get("suffix_item_id");
            	secondary_suffix_item_id = (String) details.get("secondary_suffix_item_id");
            }
            
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

    
    /*
     * specific getters for various items types
     * the type is checked 
     * for example if a call to get a property that does not exist on an item of a given type then
     * the method will return null
     */
    
    /*
     * Common to most but not all items
     */
    public String getSecondaryType(){
    	if(type == "Bag" || type == "Back" || type == "CraftingMaterial" || type == "MiniPet" || type == "Trophy")
    		return null;
    	else
    		return secondaryType;
    }
   
    public Map getInfusionSlots(){
    	if(type == "Weapon" || type == "Trinket" || type == "Armor" || type == "Back")
    		return infusion_slots;
    	else
    		return null;	
    }
    
    public Long getSuffixItemId(){
    	if(type == "Armor" || type == "Back" || type == "Trinket" || type == "Weapon")
    		return suffix_item_id;
    	else
    		return null;
    }
    
    public String getSecondarySuffixItemId(){
    	if(type == "Armor" || type == "Back" || type == "Trinket" ||  type == "Weapon")
    		return secondary_suffix_item_id; // an empty string for all currently found items
    	else 
    		return null;
    }
    
    public Map getInfixUpgrade(){
    	if(type == "Armor" || type == "Back" || type == "Trinket" || type == "UpgradeComponent" || type == "Weapon")
    		return infix_upgrade;
    	else
    		return null;
    }
    
    /*
     * Armor specific
     * NOTE: getDefense is also used for shields
     */
    public String getWeightClass(){
    	if(type == "Armor")
    		return weight_class;
    	else
    		return null;
    }
    
    public Long getDefense(){
    	if(type == "Armor" || secondaryType == "shield")
    		return defense;
    	else
    		return null;
    }
    
    /*
     * Bag specific
     */
    
    public Long getSize(){
    	if(type == "Bag")
    		return size;
    	else 
    		return null;
    }

    public boolean getBagSafeOrInvisable(){
    	if(type == "Bag")
    		return no_sell_or_sort;
    	else 
    		return false;
    }

    /*
     * Consumable specific 
     */

    public Long getDuration(){ //returns the duration in milliseconds
    	if(type == "Consumable")
    		return duration;
    	else 
    		return null;
    }

    public String getSecondaryDescription(){
    	if(type == "Consumable")
    		return secondaryDescription;
    	else
    		return null;
    }
    
    public String getUnlockType(){
    	if(type == "Consumable")
    		return unlock_type;
    	else
    		return null;
    }

    public Long getColorId(){
    	if(type == "Consumable")
    		return color_id;
    	else
    		return null;
    }
    
    public Long getRecipeId(){
    	if(type == "Consumable")
    		return recipe_id;
    	else
    		return null;
    }
    
    /*
     * Salvage kit specific
     */

    public Long getCharges(){
    	if(type == "Tool")
    		return charges;
    	else
    		return null;
    }
    
    /*
     * Upgrade Component specific
     */
    
    public List getSecondaryFlags(){
    	if(type == "UpgradeComponent")
    		return componentOf;
    	else
    		return null;
    }
    
    public List getInfusionUpgradeFlags(){
    	if(type == "UpgradeComponent")
    		return infusion_upgrade_flags;
    	else
    		return null;
    }
    
    public String getSuffix(){
    	if(type == "UpgradeComponent")
    		return suffix;
    	else
    		return null;
    }
    
    /*
     * this method is used to get the effect of the upgrade component
     */
    public List getEffect(){
    	if(type == "UpgradeComponent"){
    		if(secondaryType == "Rune")
    			return getBonuses();
    		else 
    			return (List) buff;
    	}
    	else 
    		return null;
    }

    private List getBonuses(){
    	return bonuses;
    }
    
    public List getAttributes(){
    	if(type == "UpgradeComponent")
    		return attributes;
    	else
    		return null;
    }
    
    /*
     * weapon specific
     */
    
    public String getDamageType(){
    	if(type == "Weapon")
    		return damage_type;
    	else
    		return null;
    }  
}