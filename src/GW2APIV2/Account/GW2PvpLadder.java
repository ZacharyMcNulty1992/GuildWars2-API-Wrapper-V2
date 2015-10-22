package GW2APIV2.Account;

import org.json.simple.JSONObject;

public class GW2PvpLadder {
	
	private String type;
	private Long wins;
	private Long losses;
	private Long desertions;
	private Long byes;
	private Long forfeits;
	
	
	public GW2PvpLadder(JSONObject o, String type){
		
		//this is what type of ladder object this is ie) ranked or unranked
		this.type = type;
		
		//init variables from the supplied JSONObject
		wins = (long) o.get("wins");
		losses = (long) o.get("losses");
		desertions = (long) o.get("desertions");
		byes = (long) o.get("byes");
		forfeits = (long) o.get("forfeits");
		
	}

	/*
	 * getters
	 */
	
	public Long getWins(){
		return wins;
	}
	
	public Long getLosses(){
		return losses;
	}
	
	public Long getDesertions(){
		return desertions;
	}
	
	public Long getByes(){
		return byes;
	}
	
	public Long getForfeits(){
		return forfeits;
	}
}
