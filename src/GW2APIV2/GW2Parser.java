package GW2APIV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.json.simple.JSONObject;

public class GW2Parser extends Thread {
	
	private final Object GIM = new Object();
	private List<String> LON;
	private HashMap<String, Long> MON;
	private List<JSONObject> o; //supplied by sending this class a new list
	private int maxIndex;//the total number of pages that need to be parsed
	private int currentIndex;//number of pages currently parsed
	private int count; //where to look for new items in the updated list
	private boolean finished;
	private boolean wait;
	
	public GW2Parser(){
		LON = new ArrayList<String>();
		MON = new HashMap<String, Long>();
		o = new ArrayList<JSONObject>();
		currentIndex = 0;
		maxIndex = 0;
		count = 0;
		finished = false;
		wait = true;
	}
	
	public void run(){
		
		//each iteration will look for if it should wait
		try{
			while (true){
				checkForWait();
				parseMap();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void checkForWait(){
		synchronized(GIM){
			while(wait){
				try{
					GIM.wait();
					//System.out.println("Parser is waiting");
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
			
			//System.out.println("adding names of page " + currentIndex);
			for(int i = count; i < o.size(); i++){
				
				id = (Long) (o.get(i)).get("id");
				
				MON.put( (String) o.get(i).get("name") , id);
				
				if((i + 1) == o.size())
					count = o.size();
				
			}
			System.out.println("Names added from page " + currentIndex);
			//current index has been parsed now moving to the next one
			currentIndex++;
		}
		
		System.out.println("Pausing Parser");
		pauseThread();
	}
	
	
	/*
	 * pause and wait methods
	 */
	public void pauseThread() throws InterruptedException{
		wait = true;
		
		if(currentIndex == 207){
			System.out.println("finished parsing");
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
		System.out.println("Resuming Parser");
		resumeThread();
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
	
	public HashMap getMap(){
		return MON;
	}
}
