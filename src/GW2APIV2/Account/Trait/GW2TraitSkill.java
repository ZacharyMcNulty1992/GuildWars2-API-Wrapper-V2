package GW2APIV2.Account.Trait;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class GW2TraitSkill {
	
	public Long id;
	public String name;
	public String description;
	public String iconURL;
	
	private List<GW2TraitedFacts> traitedFacts;
	private List<GW2TraitedFacts> facts;

	public GW2TraitSkill(JSONObject o){
		
		id = (Long) o.get("id");
		name = (String) o.get("name");
		description = (String) o.get("description");
		iconURL = (String) o.get("icon");
		
		traitedFacts = new ArrayList();
		facts = new ArrayList();
		
		//temporary variables to hold JSONObjects of the Facts and Traited Facts objects 
		List<JSONObject> TF = (List) o.get("traited_fects");
		List<JSONObject> F = (List) o.get("facts");
		
		String type = "";
		
		if(TF != null)
		for(JSONObject a : TF){
			
			type = (String) a.get("type");
			
			
			//this switch is for each different subclass of the traitedFacts object
			//it adds the correct type of Traited fact or fact to the appropriate List
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
		else
			traitedFacts = null;
		
		if(F != null){
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
				case "NoData" : facts.add(new GW2TraitedFacts(a));
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
		else
			facts = null;
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
	
	public String getIconURL(){
		return iconURL;
	}
	
	public List<GW2TraitedFacts> getFacts(){
		return facts;
	}
	
	public List<GW2TraitedFacts> getTraitedFacts(){
		return traitedFacts;
	}
	
}
