package GW2APIV2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GW2Character {
	
	public String name;
	public String race;
	public String gender;
	public String profession;
	public Long level;
	public String guild;//guilds this character is apart of
	public String creation_time;// date and time of creation of character
	public Long age; // number of seconds a character has been played
	public Long deaths;
	public List<GW2Item> bags; //items in characters bags
	public List<GW2Item> equips; //items currently equipped on character
	
	protected InternetConnection ic;
	protected String standardURL;
	protected boolean bp;
	
	
	public GW2Character(String name, String apiKey, boolean bagPermission){
		this.name = name;
		standardURL = "https://api.guildwars2.com/v2/characters";
		bp = bagPermission;
		
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
	
	protected void parseObject(JSONObject a){
		
		race = (String) a.get("race");
		gender = (String) a.get("gender");
		profession = (String) a.get("profession");
		level = (Long) a.get("level");
		guild = (String) a.get("guild");
		age = (Long) a.get("age");
		creation_time = (String) a.get("created");
		deaths = (Long) a.get("deaths");
		
		if(bp){
			invInfo((JSONArray)a.get("equipment"), (JSONArray)a.get("bags"));
		}	
	}
	
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
	
}
