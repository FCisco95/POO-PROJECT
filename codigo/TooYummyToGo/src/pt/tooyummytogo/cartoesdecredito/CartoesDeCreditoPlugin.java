package pt.tooyummytogo.cartoesdecredito;

public interface CartoesDeCreditoPlugin {
	
	public boolean validar(String num, int ccv, int mes, int ano);
	
	public boolean cativar(String num, int ccv, int mes, int ano, double qt);
	
	public boolean retirar(String num, int ccv, int mes, int ano, double qt);
	
}
