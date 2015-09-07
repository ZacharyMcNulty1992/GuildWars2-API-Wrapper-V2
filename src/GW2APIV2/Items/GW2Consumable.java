package GW2APIV2.Items;

import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Consumable extends GW2Item{
	
	private Map details;
	
	private String secondaryType;
	private String secondaryDescription;
    private String unlock_type; //if the consumable unlocks something it is listed here
    private Long color_id; //used for dyes, this represents the color that is unlocked by the dye
    private Long recipe_id;
    private Long duration; //measured in miliseconds
	
	public GW2Consumable(JSONObject o){
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details");
		
		secondaryType = (String) details.get("type");
    	secondaryDescription = (String) details.get("description");
    	duration = (Long) details.get("duration_ms");
    	unlock_type = (String) details.get("unlock_type");
    	color_id = (Long) details.get("color_id");
    	recipe_id = (Long) details.get("recipe_id");
	}

	/***********
	 * Getters *
	 ***********/
	
	public String getSecondaryType(){
		return secondaryType;
	}
	
	public String getSecondaryDescription(){
		return secondaryDescription;
	}
	
	/*
	 * getUnlockType()
	 * 
	 * returns null unless the secondary type of the consumable is Unlock
	 * possible Values for this are
	 * BagSlot - for Bag Expansions
	 * BankTab - for Bank Expansions
	 * CollectableCapacity - for Storage Extender
	 * Content - for Finishers, Collections unlocks, and Commander's Compendium
	 * CraftingRecipe - for Crafting recipes
	 * Dye - for Dyes 
	 * Unknown - Outfits
	 */
	public String getUnlockType(){
		return unlock_type;
	}
	
	/*
	 * getColorId
	 * 
	 * returns null unless the secondary type is a Dye
	 */
	public Long getColorId(){
		return color_id;
	}
	
	/*
	 * getRecipeId
	 * 
	 * returns null unless the secondary type is CraftingRecipe
	 */
	public Long getRecipeId(){
		return recipe_id;
	}
	
	public Long getDuration(){
		return duration;
	}
}
