package GW2APIV2.Account;

import org.json.simple.JSONObject;

public class GW2PvpCharacterStats {

	private Long wins;
	private Long losses;
	private Long byes;
	private Long desertions;
	private Long forfeits;
	private String professionName;
	
	public GW2PvpCharacterStats(JSONObject o, String Profession){
		professionName = Profession;
	}
	
	/***********
	 * Getters *
	 ***********/
	
	public String getProfession(){
		return professionName;
	}
	
	public Long getWins(){
		return wins;
	}
	
	public Long getLosses(){
		return losses;
	}
	
	public Long getByes(){
		return byes;
	}
	
	public Long getForfeits(){
		return forfeits;
	}
	
	public Long getDesertions(){
		return desertions;
	}
}
