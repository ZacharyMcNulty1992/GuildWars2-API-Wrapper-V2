package GW2APIV2;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import GW2APIV2.Account.Trait.GW2Trait;
import GW2APIV2.TradingPost.GW2Transaction;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/**
 * Created by Zach McNulty on 6/21/2015.
 */
public class InternetConnection{


    private GW2Parser parser;
    private JSONParser JsonParser;
    private JSONObject obj;
    private JSONArray jArray;
    private JSONAware jAware;
    private KeyStore ks;
    private HttpsURLConnection urlConnection;
    private SSLContext SocketFactory;
    private String AuthToken;
    private String apiKey;
    private String baseURL;
    private String itemURL;
    private String TokenInfoURL;
    private Thread p;
    
    public boolean apiKeySupplied;
    
    /*
     * Default Constructor
     */
    public InternetConnection(){
    	
    	apiKey = null;
    	apiKeySupplied = false;
    	
        createKeyStore();
        initConnection();
        
        //init variables
        baseURL = "https://api.guildwars2.com/v2/";
        itemURL = baseURL +"items";
		TokenInfoURL = null;
        
        JsonParser = new JSONParser();
    }

    /*
     * this Constructor is used when endpoints that require an apikey are being accessed
     * 
     * NOTE: ApiKey must be valid or else it will not function
     */
    
    public InternetConnection(String ApiKey)throws RuntimeException{
    	
    	apiKey = ApiKey;
    	apiKeySupplied = true;
    	
        createKeyStore();
        initConnection(); 
        
        //init variables
        baseURL = "https://api.guildwars2.com/v2/";
        itemURL = baseURL + "items";
		TokenInfoURL = baseURL + "tokeninfo";
        
        JsonParser = new JSONParser();
    }

    /*
     * starts the connection when the object is created using a new thread
     */

    protected void createKeyStore(){

        //try to create a keystore to hold trusted ca's
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null,null);
        }
        catch(Exception e) {e.printStackTrace();}
    }

    //called before any other methods
    //initializes the connection the the api as well as sets up the socket factory
    protected void initConnection(){
        //setup the sslSocketFactory through the SSLContext
        try {
            SocketFactory = SSLContext.getInstance("SSL");
            
            SocketFactory.init(null, null, new SecureRandom());
        }
        catch(Exception e){e.printStackTrace();}
    }

    //called to get JSON objects from the supplied url
    protected BufferedReader getContent(URL u)throws IOException, URISyntaxException {
    	
        try {
        	
        	URI uri = new URI(u.getProtocol(), u.getUserInfo(), u.getHost(), u.getPort(), u.getPath(), u.getQuery(), u.getRef());
        	u = uri.toURL();
        	
            urlConnection = (HttpsURLConnection) u.openConnection();
            
            if(apiKey != null)
            	urlConnection.setRequestProperty("Authorization: ", "Bearer " + apiKey);
            else
            	urlConnection.addRequestProperty("Authorization: ", "Bearer " + AuthToken);
            
            urlConnection.setSSLSocketFactory(SocketFactory.getSocketFactory());
            
            return new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8")));

        }catch(IOException e){
            e.printStackTrace();
        }
        return new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8")));
    }

    //used only in conjunction with the render service
    protected Image getRenderedContent(URL render){
    	
    	Image i = null;
    	
    	try {
            urlConnection = (HttpsURLConnection) render.openConnection();
            
            urlConnection.addRequestProperty("Authorization", "Bearer" + AuthToken);
           
            urlConnection.setSSLSocketFactory(SocketFactory.getSocketFactory());
            i =  ImageIO.read(render);

            return i;
        }catch(IOException e){
            e.printStackTrace();
        }
        return i;
    
    }
    

    /******************
     * public methods *
     ******************/

    /*
     * getJsonObject
     * Params: a URL the leads to a jason object
     * Returns: a JSONObject
     * 
     * NOTE: will error if URL is Malformed or if the URL does not give a JSONObject as a response
     */

    public JSONObject getJsonObj(URL u){

        try{
            obj = (JSONObject) JsonParser.parse(getContent(u));
            return obj;
        }catch(Exception e){
            e.printStackTrace();
        }
        return obj;
    }
    
    /*
     * getJsonArray
     * Params: a URL that responds with a JSONArray
     * Retruns: the JSONArray at the given URL
     * 
     * NOTE will error if the URL is does not respond with a JSONArray or if the URL is Malformed
     */
    
    public JSONArray getJsonArray(URL u)throws IOException{
        try{
            jArray = (JSONArray) JsonParser.parse(getContent(u));
            return jArray;
        }catch(Exception e){
            e.printStackTrace();
        }
        return jArray;
    }
    
    /*
     * getJsonAware
     * Params: a URL where the url will give anything other than a JSONObject or JSONArray
     * Returns: a JSONAware Object
     * 
     * use then when calling for things like rendering an image from the website renderer or for things that basically wont fit in the catagory
     * of the JSONObject
     */
   public JSONAware getJsonAware(URL u){
	   try{
		   jAware = (JSONAware) JsonParser.parse(getContent(u));
		   return jAware;
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return jAware;
   }
    
   /****************
    * Item Methods *
    ****************/
   
    @SuppressWarnings("unchecked")
	public HashMap getMapOfNames() throws IOException, ParseException, InterruptedException{  

    	try{
    		parser = new GW2Parser();
            parser.start();
    		
    		JSONArray ja = null;
        	List<JSONObject> g = null;
        	URL newItemURL = null;
        	
    		//build url to return multiple JSONObjects in an array
    		System.out.println("Loop Start");
    		
    		//this loops 206 times which is incredibly slow
    		//multi thread this have one get the request and have the other parse the object
    		for(int x = 0; x < 207; x++){
    			itemURL = itemURL + "?page=" + x + "&page_size=200";
    			newItemURL = new URL(itemURL);
    			
    			ja = getJsonArray(newItemURL);

    			g = (List<JSONObject>) ja;
    			
    			//give the new list to the parser
    			parser.supplyNewList(g);
    			
    			//reset the url
    			itemURL = "https://api.guildwars2.com/v2/items";
    			
    		}//end of loop
    		
    	}//catch(MalformedURLException e){
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	//wait till the parser is finished
    	while(true){
			Thread.sleep(2000);
			if(parser.finishedProcessing())
				break;
    	}
		//return the list of names
		return parser.getMap();
    }

    /*
     * Params: a string representation of the icon's URL
     * Returns: a rendered image
     */
    
    public Image getIcon(String iconUrl){
    	
    	try{
    		URL IU = new URL(iconUrl + ".png");
    		
    		Image k = getRenderedContent(IU);
    		
    		return k;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    
    
    /*******************
     * API Key Methods *
     *******************/
    
    /*
     * Params: a string representation of the api key id
     * returns: a JSONArray of the persmissions available to the supplied key
     */
    public JSONArray getTokenInfo(String apiKey){
    	
    	URL ak;
    	
		try {
			ak = new URL(TokenInfoURL + apiKey);
			
			JSONObject pl = getJsonObj(ak);
			JSONArray ja = (JSONArray) pl.get("permissions");
			
			return ja;
	    			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    public GW2Trait getTrait(Long id) {
		try{
			
			URL traitURL = new URL(baseURL + "traits/" + id);
			
			GW2Trait x = new GW2Trait(getJsonObj(traitURL));
			
			return x;
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
    
}
