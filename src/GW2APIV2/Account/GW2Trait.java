package GW2APIV2.Account;

import java.util.List;

import org.json.simple.JSONObject;

import GW2APIV2.Account.Trait.*;

public class GW2Trait {
	
	private Long id; //id of the trait
	private String name; //name of the trait
	private String description; //description of the trait
	private Long spec_id; //id of the specialization this trait belongs to
	private Long tier; //tier of the trait (ie: Adept, Master, Grandmaster) as a value between 1-3
	private String slot; //Either major or minor, minor traits are the ones given immediately upon chooseing a specialization
	
	private List<GW2TraitedFacts> traitedFacts; //a list of additions or changes to tooltip facts
	private List<GW2TraitedFacts> facts; //a lits of tooltips that belong to the skill
	
	private List<GW2TraitSkill> skill; // list of skills triggered by this trait / may be null
	
	
	/*
	 * Constructor
	 * Params: a JSONObject that contains a trait
	 * Throws: a Runtime Exception if the Facts or the TraitedFacts sub-objects contain invalid types
	 */
	
	public GW2Trait(JSONObject o){
		
		//Initialize variables
		id = (Long) o.get("id");
		name = (String) o.get("name");
		description = (String) o.get("description");
		spec_id = (Long) o.get("specialization");
		tier = (Long) o.get("tier");
		slot = (String) o.get("slot");
		
		List<JSONObject> TF = (List) o.get("traited_fects");
		List<JSONObject> F = (List) o.get("facts");
		
		String type = "";
		
		//change these to the appropriate sub class type
		for(JSONObject a : TF){
			
			type = (String) a.get("type");
			
			
			//this switch is for each different subclass of the traitedFacts object
			//if a type is not found this will throw a runtime exception
			switch(type){
				case "AttributeAdjust" : traitedFacts.add(new GW2AttributeAdjust(a));
										 break;
				case "Buff" : traitedFacts.add(new GW2Buff(a));
							  break;
				case "BuffConversion" : traitedFacts.add(new GW2BuffConversion(a));
										break;
				case "ComboField" : traitedFacts.add(new GW2ComboField(a));
									break;
				case "ComboFinisher" : traitedFacts.add(new GW2ComboFinisher(a));
									   break;
				case "Damage" : traitedFacts.add(new GW2Damage(a));
								break;
				case "Distance" : traitedFacts.add(new GW2Distance(a));
								  break;
				case "noData" : traitedFacts.add(new GW2TraitedFacts(a));
								break;
				case "Number" : traitedFacts.add(new GW2Number(a));
								break;
				case "Percent" : traitedFacts.add(new GW2Percent(a));
								 break;
				case "PrefixedBuff" : traitedFacts.add(new GW2PrefixedBuff(a));
									  break;
				case "Radius" : traitedFacts.add(new GW2Radius(a));
								break;
				case "Range" : traitedFacts.add(new GW2Range(a));
								break;
				case "Recharge" : traitedFacts.add(new GW2Recharge(a));
								  break;
				case "Time" : traitedFacts.add(new GW2Time(a));
							  break;
				case "Unblockable" : traitedFacts.add(new GW2Unblockable(a));
									 break;
				default : throw new RuntimeException();
			}
		}
		
		for(JSONObject a : F){
			
			type = (String) a.get("type");
			
			switch(type){
				case "AttributeAdjust" : facts.add(new GW2AttributeAdjust(a));
										  break;
				case "Buff" : facts.add(new GW2Buff(a));
						      break;
				case "BuffConversion" : facts.add(new GW2BuffConversion(a));
									    break;
				case "ComboField" : facts.add(new GW2ComboField(a));
								    break;
				case "ComboFinisher" : facts.add(new GW2ComboFinisher(a));
								       break;
				case "Damage" : facts.add(new GW2Damage(a));
							    break;
				case "Distance" : facts.add(new GW2Distance(a));
							      break;
				case "noData" : facts.add(new GW2TraitedFacts(a));
							    break;
				case "Number" : facts.add(new GW2Number(a));
							    break;
				case "Percent" : facts.add(new GW2Percent(a));
							     break;
				case "PrefixedBuff" : facts.add(new GW2PrefixedBuff(a));
								      break;
				case "Radius" : facts.add(new GW2Radius(a));
							    break;
				case "Range" : facts.add(new GW2Range(a));
							   break;
				case "Recharge" : facts.add(new GW2Recharge(a));
							      break;
				case "Time" : facts.add(new GW2Time(a));
							  break;
				case "Unblockable" : facts.add(new GW2Unblockable(a));
								     break;
				default : throw new RuntimeException();
			}
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
	
	public String getDescription(){
		return description;
	}
	
	
	//this getter returns the specialization id of the specialization that this trait belongs to
	public Long getSpecializationId(){ 
		return spec_id;
	}
	
	public Long getTier(){
		return tier;
	}
	
	public String getSlot(){
		return slot;
	}
	
	public List<GW2TraitedFacts> getTraitedFacts(){
		return traitedFacts;
	}
	
	public List<GW2TraitedFacts> getFacts(){
		return facts;
	}
	
	public List<GW2TraitSkill> getSkills(){
		return skill;
	}
	
}