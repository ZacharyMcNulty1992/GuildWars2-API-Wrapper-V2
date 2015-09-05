package GW2APIV2;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

public class GW2Dye {

	public Long id;
	public String name;
	public List<Long> BaseRGBValues;
	
	//when applied to cloth
	public HashMap<String, Long> Cloth;
	public List<Long> clothRGB;
	
	//when applied to leather
	public HashMap<String, Long> Leather;
	public List<Long> leatherRGB;
	
	//when applied to metal
	public HashMap<String, Long> Metal;
	public List<Long> metalRGB;
	
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
	
}
