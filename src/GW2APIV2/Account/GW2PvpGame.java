package GW2APIV2.Account;

import org.json.simple.JSONObject;

public class GW2PvpGame {
	
	private Long id;
	private Long map_id;
	private String started;
	private String ended;
	private String result;
	private String team;
	private String profession;
	private JSONObject scores;
	private Long red_score;
	private Long blue_score;
	
	public GW2PvpGame(JSONObject o){
		
		id = (long) o.get("id");
		map_id = (long) o.get("map_id");
		started = (String) o.get("started");
		ended = (String) o.get("ended");
		result = (String) o.get("result");
		team = (String) o.get("team");
		profession = (String) o.get("profession");
		
		scores = (JSONObject) o.get("scores");
		red_score = (Long) scores.get("red");
		blue_score = (Long) scores.get("blue");
	}
	
	/************
	 *  Getters *
	 ************/

	public Long getId(){
		return id;
	}
	
	public Long getMapId(){
		return map_id;
	}
	
	public String getStartTime(){
		return started;
	}
	
	public String getEndTime(){
		return ended;
	}
	
	public String getResult(){
		return result;
	}
	
	public String getTeam(){
		return team;
	}
	
	public String getProfession(){
		return profession;
	}
	
	public Long getRedScore(){
		return red_score;
	}
	
	public Long getBlueScore(){
		return blue_score;
	}
}
