package GW2APIV2.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Number;
import org.json.simple.JSONObject;

public class GW2Dye {

	private Long id;
	private String name;
	private List<Long> BaseRGBValues;
	
	//when applied to cloth
	private HashMap<String, Number> Cloth;
	private List<Long> clothRGB;
	
	//when applied to leather
	private HashMap<String, Number> Leather;
	private List<Long> leatherRGB;
	
	//when applied to metal
	private HashMap<String, Number> Metal;
	private List<Long> metalRGB;
	
	public GW2Dye(JSONObject a){
		
		id = (Long) a.get("id");
		name = (String) a.get("name");
		BaseRGBValues = (List) a.get("base_rgb");
		
		Cloth = new HashMap<String, Number>();
		Leather = new HashMap<String, Number>();
		Metal = new HashMap<String, Number>();
		
		//get cloth values
		JSONObject obj = (JSONObject) a.get("cloth");
		
		
		Cloth.put("Brightness", (Number) obj.get("brightness"));
		Cloth.put("Contrast", (Number)obj.get("contrast"));
		Cloth.put("Hue", (Number)obj.get("hue"));
		Cloth.put("Saturation", (Number) obj.get("saturation"));
		Cloth.put("Lightness", (Number) obj.get("lightness"));
		clothRGB = (List) obj.get("rgb");
		
		//leather values
		obj = (JSONObject) a.get("leather");
		
		Leather.put("Brightness", (Number) obj.get("brightness"));
		Leather.put("Contrast", (Number) obj.get("contrast"));
		Leather.put("Hue", (Number) obj.get("hue"));
		Leather.put("Saturation", (Number) obj.get("saturation"));
		Leather.put("Lightness", (Number) obj.get("lightness"));
		leatherRGB = (List) obj.get("rgb");
		
		//metal values
		obj  = (JSONObject) a.get("metal");
		
		Metal.put("Brightness", (Number) obj.get("brightness"));
		Metal.put("Contrast", (Number) obj.get("contrast"));
		Metal.put("Hue", (Number) obj.get("hue"));
		Metal.put("Saturation", (Number) obj.get("saturation"));
		Metal.put("Lightness", (Number) obj.get("lightness"));
		metalRGB = (List) obj.get("rgb");
	}
	
	private Long turnThisDamnObjIntoLong(JSONObject o, String key){
		
		Number x = 0;
		Long v = null;
		
		try{
			x = (Number) o.get(key);
			v = new Long((int)x);
		}
		catch(ClassCastException f){
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/***********
	 * Getters *
	 ***********/
	
	
	public Long getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public List<Long> getBaseRGB(){
		return BaseRGBValues;
	}
	
	public HashMap<String, Number> getWhenAppliedToClothMap(){
		return Cloth;
	}
	
	public List<Long> getClothRGB(){
		return clothRGB;
	}
	
	public HashMap<String, Number>getWhenAppliedToLeatherMap(){
		return Leather;
	}
	
	public List<Long> getLeatherRGB(){
		return leatherRGB;
	}
	
	public HashMap<String, Number> getWhenAppliedToMetalMap(){
		return Metal;
	}
	
	public List<Long> getMetalRGB(){
		return metalRGB;
	}
}
