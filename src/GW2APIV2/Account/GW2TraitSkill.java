package GW2APIV2.Account;

import java.util.List;

import org.json.simple.JSONObject;

public class GW2TraitSkill {
	
	public Long id;
	public String name;
	public String description;
	public String iconURL;
	
	private List<Long> traitedFacts;
	public Long reqiresTrait;
	public Long overides;
	
	private List<String> facts;
	public String factText; //may be null not in all fact objects
	public String factIconURL; //may be null not in all fact objects
	public String factType;
	
	public GW2TraitSkill(JSONObject o){
		
	}

}
