package pt.tooyummytogo.pagamento;

public class CartaoCredito {
	private String numero;
	private int ccv;
	private int mes;
	private int ano;
	public CartaoCredito(String numero, int ccv, int mes, int ano) {
		this.numero = numero;
		this.ccv = ccv;
		this.mes = mes;
		this.ano = ano;
	}

	public String getNumero() {
		return numero;
	}

	public int getCCV() {
		return ccv;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

}
