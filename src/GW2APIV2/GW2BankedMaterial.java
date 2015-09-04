package GW2APIV2;

import org.json.simple.JSONObject;

public class GW2BankedMaterial {

	public Long id;
	public Long category;
	public Long count;
	
	public GW2BankedMaterial(JSONObject a){
		
		id = (Long) a.get("id");
		category = (Long) a.get("category");
		count = (Long) a.get("count");
	}
	
}
