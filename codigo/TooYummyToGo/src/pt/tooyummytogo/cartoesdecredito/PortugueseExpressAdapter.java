package pt.tooyummytogo.cartoesdecredito;

import pt.portugueseexpress.InvalidCardException;
import pt.portugueseexpress.PortugueseExpress;

public class PortugueseExpressAdapter implements CartoesDeCreditoPlugin{

	private PortugueseExpress api;
	
	public PortugueseExpressAdapter() {
		this.api = new PortugueseExpress();
	}
	
	public void setTodos(String num, int ccv, int mes, int ano) {
		api.setCcv(ccv);
		api.setNumber(num);
		api.setMonth(mes);
		api.setYear(ano);
	}

	@Override
	public boolean validar(String num, int ccv, int mes, int ano) {
		setTodos(num, ccv, mes, ano);
		return api.validate();
	}

	@Override
	public boolean cativar(String num, int ccv, int mes, int ano, double quant) {
		setTodos(num, ccv, mes, ano);
		boolean valido = api.validate();
		if (valido) {
			try {
				api.block(quant);
			} catch (InvalidCardException e) {
				e.printStackTrace();
			}
		}
		return valido;
	}

	@Override
	public boolean retirar(String num, int ccv, int mes, int ano, double quant) {
		setTodos(num, ccv, mes, ano);
		boolean valido = api.validate();
		if (valido) {
			try {
				api.charge(quant);
			} catch (InvalidCardException e) {
				e.printStackTrace();
			}
		}
		return valido;
	}
}
