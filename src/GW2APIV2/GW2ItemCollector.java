package GW2APIV2;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GW2ItemCollector implements Runnable {

	private int minNum;
	private int maxNum;
	private String itemURL;
	private URL newItemURL;
	private HashMap<String, Long> map;
	private InternetConnection ic;
	private boolean finished;
	private GW2Parser Parser;
	private List<JSONObject> g;
	private Thread p;

	public GW2ItemCollector(int min, int max) {
		minNum = min;
		maxNum = max;
		map = new HashMap<String, Long>();
		ic = new InternetConnection();
		itemURL = "https://api.guildwars2.com/v2/items";
		finished = false;
		Parser = new GW2Parser();
		p = new Thread(Parser);
		p.start();
	}

	public void run() {
		parse();
	}

	public void parse() {

		JSONArray a;
		try {
			for (int x = minNum; x <= maxNum; x++) {
				//used for debugging
				//System.out.println("getting item list at page : " + x + "      on thread : " + Thread.currentThread());
				
				// create the new url
				itemURL = itemURL + "?page=" + x + "&page_size=200";
				newItemURL = new URL(itemURL);

				// get the JSONObject
				a = ic.getJsonArray(newItemURL);

				// get the list of JSONObjects
				g = (List<JSONObject>) a;
				
				//supply a list to the parser
				Parser.supplyNewList(g);
				
				//reset the item url
				itemURL = "https://api.guildwars2.com/v2/items";
				
				
			}
			
			//check to see if the parser is finished
			while(true){
				Thread.sleep(2000);
				if(Parser.finishedProcessing()){
					finished();
					break;
				}
			}
			
			concatMap();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void finished(){
		finished = true;
	}
	
	public boolean getFinished(){
		return finished;
	}
	
	public void concatMap(){
		
  		HashMap<String, Long> mappy = Parser.getMap();
   		Set<String> strings = mappy.keySet();
  
   		for(String a : strings){
    			map.put(a, mappy.get(a));
    		}
		
	}
	
	public HashMap<String, Long> getMap(){
		return map;
	}
}