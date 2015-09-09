package GW2APIV2.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import GW2APIV2.TradingPost.GW2Currency;

public class GW2Accounts {
	/*
	 * API Key Variables
	 */
	
	private String apiKey;
	private String nameOfKey;
	public boolean accountPermission;
	public boolean inventoryPermission;
	public boolean charactersPermission;
	public boolean tradingPostPermission;
	public boolean walletPermission;
	public boolean unlocksPermission;
	public boolean pvpPermission;
	public boolean buildsPermission;
	
	
	/*
	 * Account Variables
	 */
	private String id;
	private String name;
	private Long world;
	private JSONArray listOfGuilds;
	
	/*
	 * Character Variables
	 */
	private HashMap<String, GW2Character> characterMap;
	
	/*
	 * Bank variables
	 * 
	 * the bank map's key is the id of the item and the value is the quantity of that item
	 */
	private List<GW2BankItem> bank;
	
	/*
	 * Materials Variables
	 */
	private List<GW2BankedMaterial> materials;
	
	/*
	 * Wallet variables
	 */
	private List<GW2Currency> wallet;
	
	/*
	 * Constructor
	 * Params: a String representation of an api key, and an instance of the internet connection class
	 */
	
	public GW2Accounts(String apiKey){
		
		accountPermission = false;
		inventoryPermission = false;
		charactersPermission = false;
		tradingPostPermission = false;
		walletPermission = false;
		unlocksPermission = false;
		pvpPermission = false;
		buildsPermission = false;
		
		this.apiKey = apiKey;	
	}
	
	//applies the correct permissions for the supplied api key
	protected void getPermissions(JSONArray ja){
		try{
			
			if(ja.contains("account")){
				accountPermission = true;
			}
			if(ja.contains("characters")){
				charactersPermission = true;
			}
			if(ja.contains("inventories")){
				inventoryPermission = true;
			}
			if(ja.contains("tradingpost")){
				tradingPostPermission = true;
			}
			if(ja.contains("wallet")){
				walletPermission = true;
			}
			if(ja.contains("unlocks")){
				unlocksPermission = true;
			}
			if(ja.contains("pvp")){
				pvpPermission = true;
			}
			if(ja.contains("builds")){
				buildsPermission = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	/*****************
	 * Public Methods*
	 *****************/
	
	/*
	 * setters
	 */
	
	public void supplyAccountTokenInfo(JSONObject a){
		JSONArray ja = (JSONArray) a.get("permissions");
		
		getPermissions(ja);
		
		nameOfKey = (String) a.get("name");
	}
	
	public void supplyAccountDetails(JSONObject a){
		id = (String) a.get("id");
		name = (String) a.get("name");
		world = (Long) a.get("world");
		listOfGuilds = (JSONArray) a.get("guilds");
	}
	
	public void supplyCharacterList(JSONArray a){
		List<String> b = (List) a;
		
		characterMap = new HashMap<String, GW2Character>();
		
		for(String c : b){
			if(c != null)
				characterMap.put(c, new GW2Character(c, this.apiKey, inventoryPermission, buildsPermission));
			
		}
	}
	
	public void supplyBankInfo(JSONArray a){
		List<JSONObject> b = (List) a;
		
		bank = new ArrayList<GW2BankItem>();
		
		for(JSONObject c : b){
			if(c != null)
				bank.add(new GW2BankItem(c));
			else{}
		}
	}
	
	public void supplyMaterialsBank(JSONArray a){
		
		List<JSONObject> b = (List) a;
		
		materials = new ArrayList<GW2BankedMaterial>();
		
		for(JSONObject c : b){
			materials.add(new GW2BankedMaterial(c));
		}
	}
	
	public void supplyWallet(List<GW2Currency> a){
		wallet = a;
	}
	
	
	/*
	 * getters
	 */

	public String getName(){
		return name;
	}
	
	public List getBank(){
		return bank;
	}
	
	public List getMaterialsBank(){
		return materials;
	}
	
	public List getWallet(){
		return wallet;
	}
	
	public HashMap getCharacters(){
		return characterMap;
	}
	
	public Long getWorld(){
		return world;
	}
	
}