package GW2APIV2.Account;

import java.util.List;

import org.json.simple.JSONObject;

import GW2APIV2.Account.Trait.GW2TraitSkill;
import GW2APIV2.Account.Trait.GW2TraitedFacts;

public class GW2Trait {
	
	public Long id; //id of the trait
	public String name; //name of the trait
	public String description; //description of the trait
	public Long spec_id; //id of the specialization this trait belongs to
	public Long tier; //tier of the trait (ie: Adept, Master, Grandmaster) as a value between 1-3
	public String slot; //Either major or minor, minor traits are the ones given immediately upon chooseing a specialization
	
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
		
		//change these to the appropriate sub class type
		for(JSONObject a : TF){
			traitedFacts.add(new GW2TraitedFacts(a));
		}
		
		for(JSONObject a : F){
			facts.add(new GW2TraitedFacts(a));
		}
	}
}
