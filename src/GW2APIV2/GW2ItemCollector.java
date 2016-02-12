package GW2APIV2;

import java.io.IOException;
import java.net.MalformedURLException;
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
		Parser = new GW2Parser(max);
		p = new Thread(Parser);
		p.start();
	}

	public void run() {
		parse();
	}

	public void parse() {

		JSONArray a;
			try{
				int x = 0;
			for (x = minNum; x <= maxNum; x++) {
				
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
			
			//when we are finished we can concatinate the maps from each thread together
			concatMap();
			
			} catch(MalformedURLException e){
				System.out.println("An error has occured in format of URL");
				System.out.println(e.toString());
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println("Error in fetching JSONObjects");
				System.out.println(e.toString());
			}
		
	}

	protected void finished(){
		finished = true;
		Parser.terminateThread();
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
