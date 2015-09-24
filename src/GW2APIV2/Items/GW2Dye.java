package GW2APIV2.Items;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

public class GW2Dye {

	private Long id;
	private String name;
	private List<Long> BaseRGBValues;
	
	//when applied to cloth
	private HashMap<String, Long> Cloth;
	private List<Long> clothRGB;
	
	//when applied to leather
	private HashMap<String, Long> Leather;
	private List<Long> leatherRGB;
	
	//when applied to metal
	private HashMap<String, Long> Metal;
	private List<Long> metalRGB;
	
	public GW2Dye(JSONObject a){
		
		id = (Long) a.get("id");
		name = (String) a.get("name");
		BaseRGBValues = (List) a.get("base_rgb");
		
		//get cloth values
		JSONObject obj = (JSONObject) a.get("cloth");
		
		Cloth.put("Brightness", (Long) obj.get("brightness"));
		Cloth.put("Contrast", (Long) obj.get("contrast"));
		Cloth.put("Hue", (Long) obj.get("hue"));
		Cloth.put("Saturation", (Long) obj.get("stauration"));
		Cloth.put("Lightness", (Long) obj.get("lightness"));
		clothRGB = (List) obj.get("rgb");
		
		//leather values
		obj = (JSONObject) a.get("leather");
		
		Leather.put("Brightness", (Long) obj.get("brightness"));
		Leather.put("Contrast", (Long) obj.get("contrast"));
		Leather.put("Hue", (Long) obj.get("hue"));
		Leather.put("Saturation", (Long) obj.get("stauration"));
		Leather.put("Lightness", (Long) obj.get("lightness"));
		leatherRGB = (List) obj.get("rgb");
		
		//metal values
		obj  = (JSONObject) a.get("metal");
		
		Metal.put("Brightness", (Long) obj.get("brightness"));
		Metal.put("Contrast", (Long) obj.get("contrast"));
		Metal.put("Hue", (Long) obj.get("hue"));
		Metal.put("Saturation", (Long) obj.get("stauration"));
		Metal.put("Lightness", (Long) obj.get("lightness"));
		metalRGB = (List) obj.get("rgb");
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
	
	public HashMap<String, Long> getWhenAppliedToClothMap(){
		return Cloth;
	}
	
	public List<Long> getClothRGB(){
		return clothRGB;
	}
	
	public HashMap<String, Long> getWhenAppliedToLeatherMap(){
		return Leather;
	}
	
	public List<Long> getLeatherRGB(){
		return leatherRGB;
	}
	
	public HashMap<String, Long> getWhenAppliedToMetalMap(){
		return Metal;
	}
	
	public List<Long> getMetalRGB(){
		return metalRGB;
	}
}
