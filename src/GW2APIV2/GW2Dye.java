package GW2APIV2;

import java.util.List;

import org.json.simple.JSONObject;

public class GW2Dye {

	public Long id;
	public String name;
	public List<Long> BaseRGBValues;
	
	//when applied to cloth
	public Long clothBrightness;
	public Long clothContrast;
	public Long clothHue;
	public Long clothSaturation;
	public Long clothLightness;
	public List<Long> clothRGB;
	
	//when applied to leather
	public Long leatherBrightness;
	public Long leatherContrast;
	public Long leatherHue;
	public Long leatherSaturation;
	public Long leatherLightness;
	public List<Long> leatherRGB;
	
	//when applied to metal
	public Long metalBrightness;
	public Long metalContrast;
	public Long metalHue;
	public Long metalSaturation;
	public Long metalLightness;
	public List<Long> metalRGB;
	
	public GW2Dye(JSONObject a){
		
	}
	
}
