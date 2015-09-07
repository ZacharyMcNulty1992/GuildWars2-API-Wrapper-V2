package GW2APIV2.Items;

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
     */
    private String weight_class;
    private Long defense;
    
    /*
     * Consumable properties
     */
    
    
    
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
            
            //details = (Map) o.get("details"); 
            
            
            /*
             * decission structure to fill the proper variables with the proper data depending on the item type
             */

            
           
           
            if(type == "Container"){
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
}