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

            if(type == "UpgradeComponent"){
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