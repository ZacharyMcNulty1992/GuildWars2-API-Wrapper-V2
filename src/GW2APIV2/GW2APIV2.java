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

	// Internet connection variables and class instances
	private InternetConnection ic; // this class handles all the Internet
									// connection related methods
	private String Standard_URL = "https://api.guildwars2.com/v2/"; // the root
																	// url to
																	// start in
																	// v2
	private String apiKey; // apikey given by the user

	/*
	 * default Constructor
	 * 
	 * will result in a slower experience if this constructor and functionality
	 * will be limited to v2/item(s) endpoints only
	 */
	public GW2APIV2() {
		ic = new InternetConnection();
		apiKey = null;
	}

	/*
	 * use the constructor below if you want use for the following
	 * 
	 * -Transactions on the TradingPost -Account information -Character
	 * information -Guild information
	 * 
	 * NOTE: the APIKEY must be valid if it is not then a runtime error will be
	 * thrown
	 */

	public GW2APIV2(String apiK) throws RuntimeException {

		ic = new InternetConnection(apiK);
		apiKey = apiK;
	}

	/*********************
	 * Protected Methods *
	 *********************/

	/*
	 * setUpAccount Params: none Returns: a fully initalized Account class
	 * 
	 * NOTE: this method is only accessed if the InternetConnection class has a
	 * valid apiKey
	 */

	protected GW2Accounts setUpAccount() {
		try {
			URL u = new URL("https://api.guildwars2.com/v2/tokeninfo");
			URL accDetails = new URL("https://api.guildwars2.com/v2/account");

			GW2Accounts a = new GW2Accounts(apiKey);
			JSONObject accountTokenObject = ic.getJsonObj(u);
			JSONObject accountDetails = ic.getJsonObj(accDetails);

			// this permission is always true with valid api-keys
			a.supplyAccountTokenInfo(accountTokenObject);
			a.supplyAccountDetails(accountDetails);

			// testing to find that information to supply the accounts class
			if (a.charactersPermission) {
				URL chara = new URL(Standard_URL + "/characters");
				JSONArray charactersList = ic.getJsonArray(chara);
				a.supplyCharacterList(charactersList);
			}
			if (a.inventoryPermission) {
				URL bankDetails = new URL(
						"https://api.guildwars2.com/v2/account/bank");
				JSONArray bankList = ic.getJsonArray(bankDetails);
				a.supplyBankInfo(bankList);

				URL MatsBankDetails = new URL(
						"https://api.guildwars2.com/v2/account/materials");
				JSONArray matsList = ic.getJsonArray(MatsBankDetails);
				a.supplyMaterialsBank(matsList);
			}
			if (a.walletPermission) {
				URL walletDetails = new URL(
						"https://api.guildwars2.com/v2/account/wallet");

				String money = "https://api.guildwars2.com/v2/currencies/";
				URL moneyURL;

				List<JSONObject> walletList = (List) ic.getJsonArray(walletDetails);
				List<GW2Currency> wallet = new ArrayList<GW2Currency>();

				// initalize the currencies for the account associated with the
				// apiKey
				for (JSONObject b : walletList) {
					money = "https://api.guildwars2.com/v2/currencies/"
							+ b.get("id");
					moneyURL = new URL(money);
					JSONObject x = ic.getJsonObj(moneyURL);

					wallet.add(new GW2Currency(x, (Long) b.get("value")));

				}

				// supplies the account class the wallet information
				a.supplyWallet(wallet);

			}
			if (a.unlocksPermission) {
				URL skinURL = new URL(Standard_URL + "account/skins");
				URL dyeURL = new URL(Standard_URL + "account/dyes");

				List<Long> dyeIdObj = (List) ic.getJsonArray(dyeURL);

				List<GW2Dye> dyeList = new ArrayList<GW2Dye>();

				// variables for getting the dyes from the color endpoint
				String colorString = Standard_URL + "colors/";
				URL colorURL = new URL(colorString);

				for (Long id : dyeIdObj) {
					// make a new url for the color endpoint using the dye id
					colorURL = new URL(colorString + id);

					// get the JSONObject
					JSONObject v = ic.getJsonObj(colorURL);

					dyeList.add(new GW2Dye(v));

				}

				List<Long> skinIdObject = (List) ic.getJsonArray(skinURL);
				List<GW2Skin> skinsList = new ArrayList();
				URL skinsURL = new URL(Standard_URL + "skins/");

				for (Long id : skinIdObject) {
					skinsURL = new URL(Standard_URL + "skins/" + id);

					JSONObject skin = ic.getJsonObj(skinsURL);

					skinsList.add(new GW2Skin(skin));
				}

				// now give the lists to the account class
				a.supplyUnlockInfo(dyeList, skinsList);

			}
			if (a.pvpPermission) {

				// make the url for the pvp stats endpoint
				URL pvpURL = new URL(Standard_URL + "pvp/stats");

				// get a JSONObject that holds pvp stats
				JSONObject pvpStats = ic.getJsonObj(pvpURL);

				// make a url for the ids of pvp matches
				pvpURL = new URL(Standard_URL + "pvp/games");

				// get and object with a list of games ids in it
				List<String> pvpGames = (List) ic.getJsonObj(pvpURL);

				// make an empty string and a variable for the number of game
				// ids
				String idList = "";
				int size = pvpGames.size();

				// now make a comma seperated list of game ids
				for (int x = 0; x < size; x++) {
					if (x > 0)
						idList += ", " + pvpGames.get(x);
					else
						idList += pvpGames.get(x);
				}

				// make a new url for gtting the games by id
				pvpURL = new URL(Standard_URL + "pvp/games?ids=" + idList);

				// now get a list of games
				List<JSONObject> pvpGame = (List) ic.getJsonArray(pvpURL);

				a.supplyPvpInformation(pvpGame, pvpStats);

				return a;
			}
		} catch (MalformedURLException e) {
			System.out.println("an exception has occured while making URLs");
			e.printStackTrace();
		} catch (IOException e) {
			System.out
					.println("an error has occured while getting JSONObjects");
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
	 * setAPIKey Params: String : a valid api-key Returns: N/A NOTE: this will
	 * not throw any errors if the api-key is invalid although all objects that
	 * require an api-key will return null
	 */
	public void setAPIKey(String apiK) {
		apiKey = apiK;
		ic = new InternetConnection(apiKey);
	}

	/*****************
	 * Item Methods *
	 *****************/

	/*
	 * getItemDetails Params: an integer that represents an item id Returns: an
	 * item object Throws: Malformed URL Exception Notes: will throw the
	 * exception if the id provided is not an actual item in the game
	 */
	public GW2Item getItemDetails(Long id) {
		String UpdatedURL = Standard_URL + "items/" + id.intValue();
		try {
			URL u = new URL(UpdatedURL);
			JSONObject o = ic.getJsonObj(u);

			String type = (String) o.get("type");

			GW2Item g = null;

			switch (type) {

			case "Armor":
				g = new GW2Armor(o);
				break;
			case "Back":
				g = new GW2Back(o);
				break;
			case "Bag":
				g = new GW2Bag(o);
				break;
			case "Consumable":
				g = new GW2Consumable(o);
				break;
			case "Container":
				g = new GW2Container(o);
				break;
			case "CraftingMaterial":
				g = new GW2Item(o);
				break;
			case "Gathering":
				g = new GW2Gathering(o);
				break;
			case "Gizmo":
				g = new GW2Gizmo(o);
				break;
			case "MiniPet":
				g = new GW2Item(o);
				break;
			case "Tool":
				g = new GW2SalvageKit(o);
				break;
			case "Trait":
				g = new GW2Item(o);
				break;
			case "Trinket":
				g = new GW2Trinket(o);
				break;
			case "Trophy":
				g = new GW2Item(o);
				break;
			case "UpgradeComponent":
				Map details = (Map) o.get("details");
				String secondaryType = (String) details.get("type");
				if (secondaryType.equals("Rune"))
					g = new GW2Rune(o);
				else
					g = new GW2UpgradeComponent(o);
				break;
			case "weapon":
				g = new GW2Weapon(o);
				break;
			default:
				g = new GW2Item(o);
				break;
			}

			return g;
		} catch (MalformedURLException e) {
			System.out
					.println("an exception has occured while creating the URL to fetch item id's");
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * getItems
	 *  
	 * Params: none 
	 * returns: a Map containing the names of items
	 * indexed by their id
	 */
	public HashMap<String, Long> getItems() throws IOException, ParseException,
			InterruptedException {

		HashMap<String, Long> listOfNames = ic.getMapOfNames();

		return listOfNames;
	}

	/*
	 * getItemsST 
	 * 
	 * Params: none
	 * Returns: HashMap (Keys: names of items, Values: item ids)
	 */
	public HashMap<String, Long> getItemsST() {

		HashMap<String, Long> a = ic.getItemsST();

		return a;

	}
	/*
	 * getListOfItemIds
	 * 
	 * Params: none
	 * Returns: List of item ids represented as Longs
	 */
	public List<Long> getListOfItemIDs() {

			JSONArray array;
			try {
				array = ic.getJsonArray(new URL(Standard_URL + "items/"));

				List<Long> a = (List) array;

				return a;
				
			} catch (MalformedURLException e) {
				System.out.println("an error occured in creating URL to get the list of item ids");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("an error has occured while fetching the JSONArray");
				e.printStackTrace();
			}

		return null;
	}

	/*
	 * getXItems Params: the paging index (a number between 0 and 228), the
	 * number of objects on each page (MAX: 200) Returns: a list of 200 items
	 * 
	 * NOTE: the paging index must be less than 228 if retrieving 200
	 * objects(the max amount per page)
	 */

	public List<GW2Item> getXItems(int pageNum, int numOfObj) {

		List<GW2Item> itemList = new ArrayList<GW2Item>();
		List<JSONObject> a = ic.getXItems(pageNum, numOfObj);

		int x = 1;

		GW2Item g = null;

		for (JSONObject o : a) {

			String type = (String) o.get("type");

			switch (type) {

			case "Armor":
				g = new GW2Armor(o);
				break;
			case "Back":
				g = new GW2Back(o);
				break;
			case "Bag":
				g = new GW2Bag(o);
				break;
			case "Consumable":
				g = new GW2Consumable(o);
				break;
			case "Container":
				g = new GW2Container(o);
				break;
			case "CraftingMaterial":
				g = new GW2Item(o);
				break;
			case "Gathering":
				g = new GW2Gathering(o);
				break;
			case "Gizmo":
				g = new GW2Gizmo(o);
				break;
			case "MiniPet":
				g = new GW2Item(o);
				break;
			case "Tool":
				g = new GW2SalvageKit(o);
				break;
			case "Trait":
				g = new GW2Item(o);
				break;
			case "Trinket":
				g = new GW2Trinket(o);
				break;
			case "Trophy":
				g = new GW2Item(o);
				break;
			case "UpgradeComponent":
				Map details = (Map) o.get("details");
				String secondaryType = (String) details.get("type");
				if (secondaryType.equals("Rune"))
					g = new GW2Rune(o);
				else
					g = new GW2UpgradeComponent(o);
				break;
			case "weapon":
				g = new GW2Weapon(o);
				break;
			default:
				break;
			}

			itemList.add(g);

		}
		
		return itemList;
	}

	/*
	 * getIcon PNG
	 * 
	 * Params: a valid icon Url in a string format Returns: an image in
	 * png format
	 */
	public Image getIconPNG(String iconURL) {

		return ic.getIconPNG(iconURL);
	}

	/*
	 * getIconJPG 
	 * 
	 * Params: a valid icon Url in a string format Returns: an image in
	 * jpg format
	 */
	public Object getIconJPG(String iconURL) {

		return ic.getIconJPG(iconURL);
	}

	/********************
	 * Account Methods * API KEY NEEDED * FOR MOST METHODS*
	 ********************/

	/*
	 * getAccount 
	 * 
	 * Params: none 
	 * Returns: an instance of the GW2Accounts class which is fully instanciated 
	 * Throws: RuntimeException if an apiKey was not supplied on the creation of this class
	 */

	public GW2Accounts getAccount() {

		if (!ic.apiKeySupplied)
			throw new RuntimeException();

		return setUpAccount();

	}

	/*
	 * getTrait 
	 * 
	 * Params: a Trait id 
	 * Returns: a trait object
	 */
	public GW2Trait getTrait(Long id) {
		return ic.getTrait(id);
	}

	public HashMap<String, String> getCommonlyUsedAssets() {
		return ic.getMapOfCommonAssets();
	}

	/*************************
	 * Trading Post Methods * API KEY Optional *
	 *************************/

	/*
	 * GW2TradingPost
	 * 
	 * Params: none
	 * Returns: a trading post Object used for accessing trading post specific endpoints
	 */
	public GW2TradingPost getTradingPost() {

		if (apiKey != null)
			return new GW2TradingPost(apiKey);
		else
			return new GW2TradingPost();
	}

}
