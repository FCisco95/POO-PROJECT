package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.utilizadores.CatalogoUtilizadores;

public class RegistarUtilizadorHandler {
	
	private CatalogoUtilizadores catU;
	
	public RegistarUtilizadorHandler(CatalogoUtilizadores catUtilizadores) {
		this.catU = catUtilizadores;
	}
	
	/**
	 * Regista um utilizador normal(cliente).
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password) {
		catU.registarCliente(username, password);
	}
	
	/**
	 * Regista um administrador.
	 * @param Username
	 * @param Password
	 * @ensures existe um administrador com esse username
	 */
	public void registarAdmin(String username, String password) {
		catU.registarAdmin(username, password);
	}
}
