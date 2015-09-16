package GW2APIV2.Account;

import java.util.List;

import org.json.simple.JSONObject;

import GW2APIV2.InternetConnection;

public class GW2Specialization {

	private Long id;
	private String name;
	private String profession; //the profession that this spec belongs to
	private boolean elite; //if this spec is an elite spec this will be true, it will be false otherwise
	private String icon; //url to the icon of the specialization
	private String background; // url for the background image of the specialization
	private List<GW2Trait> minor_trait; //list of ids for minor traits
	private List<GW2Trait> major_trait; //list of major traits
	
	public GW2Specialization(JSONObject o){
		
		//fill veriables
		id = (Long) o.get("id");
		name = (String) o.get("name");
		profession = (String) o.get("profession");
		elite = (boolean) o.get("elite");
		icon = (String) o.get("icon");
		background = (String) o.get("background");
		
		List<JSONObject> a = (List) o.get("minor_traits");
		List<JSONObject> b = (List) o.get("major_traits");

		
		for(JSONObject c : a){
			minor_trait.add(new GW2Trait(c));
		}
		
		for(JSONObject c : b){
			major_trait.add(new GW2Trait(c));
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
	
	public String getProfession(){
		return profession;
	}
	
	public boolean getElite(){
		return elite;
	}
	
	public String getIconURL(){
		return icon;
	}
	
	public String getBackgroundImageURL(){
		return background;
	}
	
	public List<GW2Trait> getMinorTraits(){
		return minor_trait;
	}
	
	public List<GW2Trait> getMajorTraits(){
		return major_trait;
	}
}
