package GW2APIV2.Account.Trait;

import java.util.List;

import org.json.simple.JSONObject;

public class GW2TraitSkill {
	
	public Long id;
	public String name;
	public String description;
	public String iconURL;
	
	private List<Long> traitedFacts;
	private List<String> facts;

	public GW2TraitSkill(JSONObject o){
		
	}

}
