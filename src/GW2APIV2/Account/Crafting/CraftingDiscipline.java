package GW2APIV2.Account.Crafting;

import org.json.simple.JSONObject;

public class CraftingDiscipline {
	
	private String discipline; //name of the type of crafting
	private Long rating; //level of the discipline
	private boolean active; //if it is one of the two currently active crafting disciplines

	/*
	 * Constructor
	 */
	
	public CraftingDiscipline(JSONObject a){
		
		discipline = (String) a.get("discipline");
		rating = (Long) a.get("rating");
		active = (boolean) a.get(active);
	}
	
	/***********
	 * Getters *
	 ***********/
	
	public String getDisciplineName(){
		return discipline;
	}
	
	public Long getLevel(){
		return rating;
	}
	
	public boolean getActive(){
		return active;
	}
}
