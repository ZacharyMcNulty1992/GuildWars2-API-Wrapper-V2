package GW2APIV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.json.simple.JSONObject;

public class GW2Parser extends Thread {
	
	private final Object GIM = new Object();
	private List<String> LON;
	private HashMap<String, Long> MON;
	private List<JSONObject> o; //supplied by sending this class a new list
	private int maxIndex;//the total number of pages that need to be parsed
	private int currentIndex;//number of pages currently parsed
	private int count; //where to look for new items in the updated list
	private int maxNum; //the max index that can be parsed
	private boolean finished;
	private boolean wait;
	private boolean terminate;
	
	public GW2Parser(int maximum){
		LON = new ArrayList<String>();
		MON = new HashMap<String, Long>();
		o = new ArrayList<JSONObject>();
		currentIndex = 0;
		maxIndex = 0;
		count = 0;
		finished = false;
		wait = true;
		terminate = false;
		maxNum = maximum;
	}
	
	public void run(){
		
		//each iteration will look for if it should wait
		try{
			while (!finished){
				checkForWait();
				parseMap();
				if(terminate)
					break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Thread has finished");
		
	}
	
	protected void checkForWait(){
		synchronized(GIM){
			while(wait){
				try{
					GIM.wait();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Called only from within this class
	 * it is only called when an updated list is supplied to the class
	 */	
	protected void parseMap() throws InterruptedException{
		
		if(currentIndex <= maxIndex && o != null){
			
			Long id;
			int size = o.size();
			
			//System.out.println("adding names of page " + currentIndex);
			for(int i = count; i < size; i++){
				
				id = (Long) (o.get(i)).get("id");
				
				MON.put( (String) o.get(i).get("name") , id);
				
				if((i + 1) == size)
					count = o.size();
				
			}
			//current index has been parsed now moving to the next one
			currentIndex++;
		}
		
		pauseThread();
	}
	
	
	/*
	 * pause and wait methods
	 */
	public void pauseThread() throws InterruptedException{
		wait = true;
		
		if(currentIndex >= maxNum){
			finished = true;
		}
	}
	
	public void resumeThread(){
		synchronized(GIM){
			wait = false;
			GIM.notify();
		}
	}

	
	/*
	 * public methods
	 */
	
	public void supplyNewList(List<JSONObject> i){
		o.addAll(i);
		maxIndex++;
		resumeThread();
	}
	
	public void terminateThread(){
		terminate = true;
	}
	
	/*
	 * Getters
	 */
	
	public boolean finishedProcessing(){
		return finished;
	}
	
	public List<String> getList(){
		return LON;
	}
	
	public HashMap<String, Long> getMap(){
		return MON;
	}
}
