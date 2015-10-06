package GW2APIV2.Account;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import GW2APIV2.InternetConnection;
import GW2APIV2.Items.GW2Item;

public class GW2Character {
	
	private String name; //name of the character
	private String race; //the race of the character
	private String gender; //male or female
	private String profession; //the class of the character ie: Elementalist
	private Long level; //level of the character
	private String guild;//guilds this character is apart of
	private String creation_time;// date and time of creation of character
	private Long age; // number of seconds a character has been played
	private Long deaths; //number of deaths on this character
	private List<GW2Item> bags; //items in characters bags
	private List<GW2Item> equips; //items currently equipped on character
	
	protected InternetConnection ic; //internet connection class for obtaining character information from character endpoints
	protected String standardURL; //url for character endpoints
	protected boolean bp; //bag permissions
	protected boolean buildPermission;
	/*
	 * Constructor
	 * 
	 * Parameters:
	 * 		String name: the name of the character
	 * 		String apiKey: the apiKey associated with the account
	 * 		boolean bagPermission: bag permission of the supplied api-key
	 * 		boolean build: the build permission of the supplied api-key
	 */
	public GW2Character(String name, String apiKey, boolean bagPermission, boolean build){
		this.name = name;
		standardURL = "https://api.guildwars2.com/v2/characters";
		bp = bagPermission;
		buildPermission = build;
		
		try{
			//gets the JSONObject of this character
			ic = new InternetConnection(apiKey);
			
			URL u = new URL(standardURL + "/" + name);
			
			JSONObject chara = ic.getJsonObj(u);
			
			parseObject(chara);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/*
	 * parseObject
	 * 
	 * Parameters:
	 * 		JSONObject a: a JSONObject from the v2/characters/{character name} endpoint
	 * 
	 * Function:
	 * 		parses all the information within the JSONObject 
	 */
	protected void parseObject(JSONObject a){
		
		race = (String) a.get("race");
		gender = (String) a.get("gender");
		profession = (String) a.get("profession");
		level = (Long) a.get("level");
		guild = (String) a.get("guild");
		age = (Long) a.get("age");
		creation_time = (String) a.get("created");
		deaths = (Long) a.get("deaths");
		
		//check to see what permissions are available
		if(bp){
			invInfo((JSONArray)a.get("equipment"), (JSONArray)a.get("bags"));
		}	
		if(buildPermission){
			
		}
		
	}
	
	/*
	 * invInfo
	 * 
	 * Parameters:
	 * 		JSONArray eq: an array of JSONObjects that represent items currently equipted to the character
	 * 		JSONArray bags: an array of JSONObjects that represent items currently in a characters bags or inventory
	 * 
	 * Function:
	 * 		adds items to lists that will represent the items currently equipted, and items currently in a characters inventory or bags 
	 */
	
	protected void invInfo(JSONArray eq, JSONArray bags){
		
		this.bags = new ArrayList<GW2Item>();
		equips = new ArrayList<GW2Item>();
		
		List<JSONObject> equ = (List) eq;
		List<JSONObject> bag = (List) bags;	
		
		GW2Item test;
		
		for(JSONObject a : equ){
			if(a != null){
				test = new GW2Item(a);
				equips.add(test);
			}
			else
				equips.add(null);
		}
		
		
		for(JSONObject a : bag){
			if(a != null){
				test = new GW2Item(a);
				this.bags.add(test);
			}
			else
				this.bags.add(null);
		}
		
	}
	
	/***********
	 * Getters *
	 ***********/
	public String getName(){
		return name;
	}
	
	public String getRace(){
		return race;
	}
	
	public String getGender(){
		return gender;
	}
	
	public String getProfession(){
		return profession;
	}
	
	public Long getLevel(){
		return level;
	}
	
	public String getGuild(){
		return guild;
	}
	
	public String getCreationTime(){
		return creation_time;
	}
	
	public Long getAge(){
		return age;
	}
	
	public Long getDeaths(){
		return deaths;
	}
	
	public List<GW2Item> getBags(){
		return bags;
	}
	
	public List<GW2Item> getEquips(){
		return equips;
	}
}


