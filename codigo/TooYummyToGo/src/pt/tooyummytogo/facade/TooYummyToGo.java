package pt.tooyummytogo.facade;

import java.util.Optional;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;
import pt.tooyummytogo.produto.CatalogoProdutos;
import pt.tooyummytogo.produto.CatalogoTiposProduto;
import pt.tooyummytogo.reservas.CatalogoReservas;
import pt.tooyummytogo.utilizadores.CatalogoComerciantes;
import pt.tooyummytogo.utilizadores.CatalogoUtilizadores;

/**
 * Esta é a classe do sistema.
 */
public class TooYummyToGo {

	private CatalogoUtilizadores catU;
	private CatalogoComerciantes catC;
	private CatalogoProdutos catP;
	private CatalogoTiposProduto catT;
	private CatalogoReservas catR;

	public TooYummyToGo() {
		catU = CatalogoUtilizadores.getInstance();
		catU.clear();
		catC = CatalogoComerciantes.getInstance();
        catC.clear();
	}
	// UC1
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(catU);
	}
	
	/**
	 * Returns an optional Session representing the authenticated user.
	 * @param username
	 * @param password
	 * @return
	 * 
	 * UC2
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		boolean autenticado = catU.autenticar(username, password);
		if (!autenticado) return Optional.empty();
		return Optional.of(new Sessao(username, catU, catC, catP, catT, catR)); // TODO Autenticação
	}

	// UC3
	public RegistarComercianteHandler getRegistarComercianteHandler() {
		return new RegistarComercianteHandler(catC);
	}
	

}
