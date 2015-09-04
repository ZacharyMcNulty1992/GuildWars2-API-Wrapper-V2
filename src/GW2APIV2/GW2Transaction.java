package GW2APIV2;

import org.json.simple.JSONObject;

public class GW2Transaction {

	public Long id;
	public Long itemId;
	public Long price;
	public Long quantity;
	public String creationDate;
	public String purchaseDate; //will result in null if checking current transactions
	
	
	public GW2Transaction(JSONObject a){
		
		id = (Long) a.get("id");
		itemId = (Long) a.get("item_id");
		price = (Long) a.get("price");
		quantity = (Long) a.get("quantity");
		creationDate = (String) a.get("created");
		purchaseDate = (String) a.get("purchased");
		
	}
	
}
