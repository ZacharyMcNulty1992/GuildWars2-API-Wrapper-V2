package GW2APIV2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import GW2APIV2.Account.*;
import GW2APIV2.Items.*;
import GW2APIV2.TradingPost.*;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.net.URL;

/**
 * Created by Zach McNulty on 6/17/2015.
 */
public class GW2APIV2 {


    //internet connection variables and class instances
    private InternetConnection ic; //this class handles all the internet connection related methods
    private String Standard_URL = "https://api.guildwars2.com/v2/"; //the root url to start in v2
    private String apiKey; //apikey given by the user
    
    /*
     * default Constructor
     * 
     * will result in a slower experience if this constructor and functionality will be limited to 
     * v2/item(s) endpoints only
     */
    public GW2APIV2(){
        ic = new InternetConnection();
        apiKey = null;
    }
    
    /*
     * use the constructor below if you want use for the following
     * 
     * -Transactions on the TradingPost
     * -Account information
     * -Character information
     * -Guild information
     * 
     * NOTE: the APIKEY must be valid 
     * if it is not then a runtime error will be thrown
     */
    
    public GW2APIV2(String apiK)throws RuntimeException{
    	
    	ic = new InternetConnection(apiK);
		apiKey = apiK;
    }
    

    /*********************
     * Protected Methods *
     *********************/
    
    /*
     * setUpAccount
     * Params: none
     * Returns: a fully initalized Account class
     * 
     * NOTE: this method is only accessed if the InternetConnection class has a valid apiKey
     */
    
    protected GW2Accounts setUpAccount(){
    	
    	try{
    		
    		URL u = new URL( "https://api.guildwars2.com/v2/tokeninfo");
        	URL accDetails = new URL( "https://api.guildwars2.com/v2/account");
    		
        	
    		GW2Accounts a = new GW2Accounts(apiKey);
    		JSONObject accountTokenObject = ic.getJsonObj(u);
    		JSONObject accountDetails = ic.getJsonObj(accDetails);
    		
    		//this permission is always true with valid api-keys
    		a.supplyAccountTokenInfo(accountTokenObject);
    		a.supplyAccountDetails(accountDetails);
    		
    		//testing to find that information to supply the accounts class
    		if(a.charactersPermission){
    			URL chara = new URL(Standard_URL + "/characters");
    			JSONArray charactersList = ic.getJsonArray(chara);
    			a.supplyCharacterList(charactersList);
    		}
    		if(a.inventoryPermission){
    			URL bankDetails = new URL("https://api.guildwars2.com/v2/account/bank");
    			JSONArray bankList = ic.getJsonArray(bankDetails);
    			a.supplyBankInfo(bankList);
    			
    			URL MatsBankDetails = new URL("https://api.guildwars2.com/v2/account/materials");
    			JSONArray matsList = ic.getJsonArray(MatsBankDetails);
    			a.supplyMaterialsBank(matsList);
    		}
    		if(a.walletPermission){
    			URL walletDetails = new URL("https://api.guildwars2.com/v2/account/wallet");
    			
    			String money = "https://api.guildwars2.com/v2/currencies/";
    			URL moneyURL;
    			
    			List<JSONObject> walletList = (List) ic.getJsonArray(walletDetails);
    			List<GW2Currency> wallet = new ArrayList<GW2Currency>();
    			
    			//initalize the currencies for the account associated with the apiKey
    			for(JSONObject b : walletList){
    				money = "https://api.guildwars2.com/v2/currencies/" + b.get("id");
    				moneyURL = new URL(money);
    				JSONObject x = ic.getJsonObj(moneyURL);
    				
    				wallet.add(new GW2Currency(x, (Long) b.get("value")));
    				
    			}

    			//supplies the account class the wallet information 
    			a.supplyWallet(wallet);

    		}
    		if(a.unlocksPermission){
    			
    		}
    		if(a.pvpPermission){
    			
    		}
    		
    		return a;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return null;
    }

    
    
    /******************
     * public methods *
     ******************/
    
    /***********
     * Setters *
     ***********/
    
    /*
     * setAPIKey
     * Params: String : a valid api-key
     * Returns: N/A
     * NOTE: this will not throw any errors if the api-key is invalid although all objects
     * 		 that require an api-key will return null
     */
    public void setAPIKey(String apiK){
    	apiKey = apiK;
    	ic = new InternetConnection(apiKey);
    }
    
    
    
    /*****************
     *  Item Methods *
     *****************/
    
    /*
     * getItemDetails
     * Params: an integer that represents an item id
     * Returns: an item object
     * Throws: Malformed URL Exception
     * Notes: will throw the exception if the id provided is not an actual item in the game 
     */ 
    public GW2Item getItemDetails(Long id){
        String UpdatedURL = Standard_URL + "items/" + id.intValue();
        try {
            URL u = new URL(UpdatedURL);
            JSONObject o = ic.getJsonObj(u);
            
            String type = (String) o.get("type");
            
            GW2Item g = null;
            
            switch(type){
            
            	case "Armor" : g = new GW2Armor(o);
            			   	   break;
            	case "Back" : g = new GW2Back(o);
            			   	  break;
            	case "Bag" : g = new GW2Bag(o);
            				 break;
            	case "Consumable" : g = new GW2Consumable(o);
            						break;
            	case "Container" : g = new GW2Container(o);
            					   break;
            	case "CraftingMaterial" : g = new GW2Item(o);
            							  break;
            	case "Gathering" : g = new GW2Gathering(o);
            					   break;
            	case "Gizmo" : g = new GW2Gizmo(o);
            				   break;
            	case "MiniPet" : g = new GW2Item(o);
            					 break;
            	case "Tool" : g = new GW2SalvageKit(o);
            				  break;
            	case "Trait" : g = new GW2Item(o);
            				   break;
            	case "Trinket" : g = new GW2Trinket(o);
            					 break;
            	case "Trophy" : g = new GW2Item(o);
            					break;
            	case "UpgradeComponent" : Map details = (Map) o.get("details");
            							  String secondaryType = (String) details.get("type");
            							  if(secondaryType.equals("Rune"))
            								  g = new GW2Rune(o);
            							  else
            								  g = new GW2UpgradeComponent(o);
            							  break;
            	case "weapon" : g = new GW2Weapon(o);
            					break;
            	default : break;
            }
            
            return g;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }

    /*
     * getItems
     * Params: none
     * returns: a Map containing the names of items indexed by their id
     * throws: Malformed URL Exception and JSONException
     */
    public HashMap getItems() throws IOException, ParseException, InterruptedException{
    	
    	HashMap listOfNames = ic.getMapOfNames();

    	return listOfNames;
    }
    
    /*
     * getIcon
     * Params: a valid icon Url in a string format
     * Returns: an image in png format
     */
    public Image getIcon(String iconURL){

    		return ic.getIcon(iconURL);
    }
    
    
       
    /********************
     *  Account Methods *
     *  API KEY NEEDED  *
     *  FOR MOST METHODS*
     ********************/
    
    /*
     * getAccount
     * Params: none
     * Returns: an instance of the GW2Accounts class which is fully instanciated
     * Throws: RuntimeException if an apiKey was not supplied on the creation of this class
     */
    
    public GW2Accounts getAccount(){
    	
    	if(!ic.apiKeySupplied)
    		throw new RuntimeException();
    		
    	return setUpAccount();

    }
    
    public GW2Trait getTrait(Long id){
    	return ic.getTrait(id);
    }
    
    
    /*************************
     *  Trading Post Methods *
     *  API KEY Optional     *
     *************************/
    
    public GW2TradingPost getTradingPost(){
    	
    	if(apiKey != null)
    		return new GW2TradingPost(apiKey);
    	else
    		return new GW2TradingPost();
    }
    
}
