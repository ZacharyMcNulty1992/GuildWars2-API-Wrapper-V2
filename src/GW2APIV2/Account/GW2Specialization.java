package GW2APIV2.Account;

import java.util.List;

import org.json.simple.JSONObject;

import GW2APIV2.InternetConnection;

public class GW2Specialization {

	private Long id;
	private String name;
	private String profession;
	private boolean elite; //if this spec is an elite spec this will be true, it will be false otherwise
	private String icon; //url to the icon of the specialization
	private String background; // url for the background image of the specialization
	private List<GW2Trait> minor_trait; //list of ids for minor traits
	private List<GW2Trait> major_trait; //list of major traits
	
	public GW2Specialization(JSONObject o, JSONObject minor, JSONObject major){
		id = (Long) o.get("id");
	}
	
}
