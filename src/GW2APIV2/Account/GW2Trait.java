package GW2APIV2.Account;

import java.util.List;

import org.json.simple.JSONObject;

public class GW2Trait {
	
	public Long id;
	public String name;
	public String description;
	public Long spec_id; //id of the specialization this trait belongs to
	public Long tier;
	public String slot;
	
	private List<Long> traitedFacts;
	public Long reqiresTrait;
	public Long overides;
	
	private List<String> facts;
	public String factText; //may be null not in all fact objects
	public String factIconURL; //may be null not in all fact objects
	public String factType;
	
	public List<GW2TraitSkill> skill; // list of skills triggered by this trait / may be null
	
	public GW2Trait(JSONObject o){
		
		id = (Long) o.get("id");
		name = (String) o.get("name");
		description = (String) o.get("description");
		spec_id = (Long) o.get("specialization");
		tier = (Long) o.get("tier");
		slot = (String) o.get("slot");
		
		
	}
}
