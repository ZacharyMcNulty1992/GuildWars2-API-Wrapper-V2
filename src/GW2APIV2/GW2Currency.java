package GW2APIV2;

public class GW2Currency {
	public int Gold;
	public int Silver;
	public int Copper;
	
	/*
	 * Default Constructor
	 */
	public GW2Currency(){
		Gold = 0;
		Silver = 0;
		Copper = 0;
	}
	
	
	/*
	 * convertToInGameGold
	 */
	public void convertToInGameGold(int m){

		resetCurrency();
		
		//gold
		while((m-10000) > 0){
			Gold++;
			m -= 10000;
		}
		
		//silver
		while((m - 100) > 0){
			Silver++;
			m -= 100;
		}
		
		//copper
		while((m-1) > 0){
			Copper++;
			m -= 1;
		}
		
	}
	
	public void resetCurrency(){
		Gold = 0;
		Silver = 0;
		Copper = 0;
	}
	
	
}
