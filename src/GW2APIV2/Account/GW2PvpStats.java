package GW2APIV2.Account;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class GW2PvpStats {
		private Long pvp_rank;
		private JSONObject winloss;
		private JSONObject professions;
		private JSONObject Ladders;
		
		private List<GW2PvpCharacterStats> professionList;
		private List<GW2PvpLadder> ladderList;
		
		private Long wins;
		private Long losses;
		private Long desertions;
		private Long byes;
		private Long forfiets;
		
		public GW2PvpStats(JSONObject o){
			
			//initialize variables in the base JSONObject
			pvp_rank = (Long) o.get("pvp_rank");
			winloss = (JSONObject) o.get("aggrate");
			professions = (JSONObject) o.get("professions");
			Ladders = (JSONObject) o.get("ladders");
			
			//init an arrayList for holding the profession specific stats
			professionList = new ArrayList();
			
			//init the ladderList arrayList
			ladderList = new ArrayList();
			
			//get all the different professions and add them to a list 
			if(professions != null){
				
				JSONObject ele = (JSONObject) professions.get("elementalist");
				if(ele != null)
					professionList.add(new GW2PvpCharacterStats(ele, "Elementalist"));
				
				JSONObject guard = (JSONObject) professions.get("guardian");
				if(guard != null)
					professionList.add(new GW2PvpCharacterStats(guard, "Guardian"));
				
				JSONObject mes = (JSONObject) professions.get("mesmer");
				if(mes != null)
					professionList.add(new GW2PvpCharacterStats(mes, "Mesmer"));
				
				JSONObject necro = (JSONObject) professions.get("necromancer");
				if(necro != null)
					professionList.add(new GW2PvpCharacterStats(necro, "Necromancer"));
				
				JSONObject rang = (JSONObject) professions.get("ranger");
				if(rang != null)
					professionList.add(new GW2PvpCharacterStats(rang, "Ranger"));
				
				JSONObject war = (JSONObject) professions.get("warrior");
				if(war != null)
					professionList.add(new GW2PvpCharacterStats(war, "Warrior"));
				
				JSONObject thief = (JSONObject) professions.get("thief");
				if(thief != null)
					professionList.add(new GW2PvpCharacterStats(thief, "Thief"));
				
				JSONObject eng = (JSONObject) professions.get("engineer");
				if(eng != null)
					professionList.add(new GW2PvpCharacterStats(eng, "Engineer"));
			}
			else{
				professionList = null;
			}
			
			//get what is in the Ladders object
			if(Ladders != null){
				
				//get ranked information
				JSONObject ranked = (JSONObject) Ladders.get("ranked");
				
				if(ranked != null)
					ladderList.add(new GW2PvpLadder(ranked, "Ranked"));
				
				
				//get the unranked information
				JSONObject unranked = (JSONObject) Ladders.get("unranked");
			}
		}
		
		/*
		 * getters
		 */
		
		public Long getPvpRank(){
			return pvp_rank;
		}
		
		public List<GW2PvpCharacterStats> getProfessionList(){
			return professionList;
		}
		
		public List<GW2PvpLadder> getLadderList(){
			return ladderList;
		}
		
		public Long getNumOfWins(){
			return wins;
		}
		
		public Long getNumOfLosses(){
			return losses;
		}
		
		public Long getNumOfDesertions(){
			return desertions;
		}
		
		public Long getNumOfByes(){
			return byes;
		}
		
		public Long getNumOfForfiets(){
			return forfiets;
		}
}