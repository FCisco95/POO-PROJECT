package pt.tooyummytogo.cartoesdecredito;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

public class MonsterCardAdapter implements CartoesDeCreditoPlugin{

	private MonsterCardAPI mAPI;
	private Card card;
	
	public MonsterCardAdapter() {
		mAPI = new MonsterCardAPI();
	}
	
	/*
	 * Checks if a Credit Card is valid
	 */
	public boolean validar(String num, int ccv, int mes, int ano) {
		card = new Card(num, String.valueOf(ccv), String.valueOf(mes), String.valueOf(ano));
		card.validate();
		return mAPI.isValid(card);
	}
	
	
	/*
	 * Blocks amount from credit card c.
	 */
	public boolean cativar(String num, int ccv, int mes, int ano, double qt) {
		card = new Card(num, String.valueOf(ccv), String.valueOf(mes), String.valueOf(ano));
		if(mAPI.isValid(card)) {
			mAPI.block(card, qt);
			return true;
		}
		return false;
	}

	/*
	 * Removes amount from credit card c.
	 */
	public boolean retirar(String num, int ccv, int mes, int ano, double qt) {
		card = new Card(num, String.valueOf(ccv), String.valueOf(mes), String.valueOf(ano));
		if(mAPI.isValid(card)) {
			mAPI.charge(card, qt);
			return true;
		}
		return false;
	}

}
