package GW2APIV2.Account.Trait;

import org.json.simple.JSONObject;

public class GW2ComboField extends GW2TraitedFacts {

	private String fieldType; //a type like air, water, fire, ice, dark, etc....
	
	public GW2ComboField(JSONObject o){
		super(o);
		
		fieldType = (String) o.get("field_type");
	}
	
	public String getFieldType(){
		return fieldType;
	}
	
}
