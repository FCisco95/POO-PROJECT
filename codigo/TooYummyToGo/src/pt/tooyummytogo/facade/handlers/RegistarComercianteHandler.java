package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.utilizadores.CatalogoComerciantes;
import pt.tooyummytogo.utilizadores.CatalogoUtilizadores;

public class RegistarComercianteHandler {
	
//private CatalogoUtilizadores catU;
private CatalogoComerciantes catC;
	
	public RegistarComercianteHandler(CatalogoComerciantes catComerciantes) {
//		this.catU = catUtilizadores;
		this.catC = catComerciantes;
	}
	
	/**
	 * Regista um Comerciante.
	 * @param Username
	 * @param Password
	 * @ensures existe um comerciante com esse username
	 */
	public void registarComerciante(String username, String password, PosicaoCoordenadas p) {
//		catU.registarComerciante(username, password, p);
		catC.registarComerciante(username, password, p);
	}

}
