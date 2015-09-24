package GW2APIV2.Items;

import java.util.Map;

import org.json.simple.JSONObject;

public class GW2Bag extends GW2Item{
	
	private Map details;
	private Long size; //number of bag slots
	private boolean no_sell_or_sort; //true if the bag hides items from merchants when selling items
	   
	
	
	public GW2Bag(JSONObject o){
		super(o);
		
		//type specific variables are located here
		details = (Map) o.get("details"); 
		
		size = (Long) details.get("size");
    	no_sell_or_sort = (boolean) details.get("no_sell_or_sort");
	}

	@Override
	public Long getSize(){
		return size;
	}
	
	@Override
	public boolean getSellOrSort(){
		//returns true if the bag hides items from merchants
		return no_sell_or_sort;
	}
	
}
