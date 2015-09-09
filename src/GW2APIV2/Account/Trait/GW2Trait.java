package GW2APIV2.Account.Trait;

import java.util.List;

import org.json.simple.JSONObject;

public class GW2Trait {
	
	public Long id;
	public String name;
	public String description;
	public Long spec_id; //id of the specialization this trait belongs to
	public Long tier;
	public String slot;
	
	private List<GW2TraitedFacts> traitedFacts;
	private List<GW2TraitedFacts> facts;
	
	public List<GW2TraitSkill> skill; // list of skills triggered by this trait / may be null
	
	public GW2Trait(JSONObject o){
		
		id = (Long) o.get("id");
		name = (String) o.get("name");
		description = (String) o.get("description");
		spec_id = (Long) o.get("specialization");
		tier = (Long) o.get("tier");
		slot = (String) o.get("slot");
		
		List<JSONObject> TF = (List) o.get("traited_fects");
		List<JSONObject> F = (List) o.get("facts");
		
		
		for(JSONObject a : TF){
			traitedFacts.add(new GW2TraitedFacts(a));
		}
		
		for(JSONObject a : F){
			facts.add(new GW2TraitedFacts(a));
		}
	}
}
