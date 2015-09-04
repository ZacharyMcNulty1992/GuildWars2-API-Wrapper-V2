package GW2APIV2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.net.URL;

/**
 * Created by Zach McNulty on 6/17/2015.
 */
public class GW2APIV2 {


    //internet connection variables and class instances
    InternetConnection ic;
    private Long item_id; //item being searched for if found will have a value if not will be -1
    private String Standard_URL = "https://api.guildwars2.com/v2/"; //the root url to start in v2
    private boolean apiPosTest;
    private String apiKey;
    
    /*
     * default Constructor
     * 
     * will result in a slower experience if this constructor and functionality will be limited to 
     * v2/item(s) endpoints only
     */
    public GW2APIV2(){
        ic = new InternetConnection();
        apiPosTest = false;
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
    	
    	System.out.println("created InternetConnection in GW2APIV2");
    	
    	apiPosTest = true;
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
    			URL waletDetails = new URL("https://api.guildwars2.com/v2/account/wallet");
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
            GW2Item g = new GW2Item(o);
            return g;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return null; //stub
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
