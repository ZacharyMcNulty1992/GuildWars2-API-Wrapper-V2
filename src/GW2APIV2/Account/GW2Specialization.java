package GW2APIV2.Account;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import GW2APIV2.InternetConnection;

public class GW2Specialization {

	private Long id;
	private String name;
	private String profession; // the profession that this spec belongs to
	private boolean elite; // if this spec is an elite spec this will be true,
							// it will be false otherwise
	private String icon; // url to the icon of the specialization
	private String background; // url for the background image of the
								// specialization
	private InternetConnection ic;
	private List<GW2Trait> minor_trait; // list of ids for minor traits
	private List<GW2Trait> major_trait; // list of major traits

	public GW2Specialization(JSONObject o) {

		ic = new InternetConnection();

		// fill veriables
		id = (Long) o.get("id");
		name = (String) o.get("name");
		profession = (String) o.get("profession");
		elite = (boolean) o.get("elite");
		icon = (String) o.get("icon");
		background = (String) o.get("background");

		minor_trait = new ArrayList();
		major_trait = new ArrayList();
		
		List<Long> a = (List) o.get("minor_traits");
		List<Long> b = (List) o.get("major_traits");
		
		try {
			
			URL Joe = new URL("https://api.guildwars2.com/v2");
			
			for (Long c : a) {
				Joe = new URL("https://api.guildwars2.com/v2/traits/" + c);
				JSONObject bob = ic.getJsonObj(Joe);
				minor_trait.add(new GW2Trait(bob));
			}

			for (Long c : b) {
				Joe = new URL("https://api.guildwars2.com/v2/traits/" + c);
				JSONObject bob = ic.getJsonObj(Joe);
				major_trait.add(new GW2Trait(bob));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***********
	 * Getters *
	 ***********/

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getProfession() {
		return profession;
	}

	public boolean getElite() {
		return elite;
	}

	public String getIconURL() {
		return icon;
	}

	public String getBackgroundImageURL() {
		return background;
	}

	public List<GW2Trait> getMinorTraits() {
		return minor_trait;
	}

	public List<GW2Trait> getMajorTraits() {
		return major_trait;
	}
}
