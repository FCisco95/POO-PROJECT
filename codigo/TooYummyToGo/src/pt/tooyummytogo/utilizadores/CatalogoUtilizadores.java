package pt.tooyummytogo.utilizadores;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class CatalogoUtilizadores {
	private Map<String, Utilizador> utilizadores = new HashMap<>();
//	private Map<String, ComercianteInfo> comerciantesInfo = new HashMap<>();
	
	private static CatalogoUtilizadores instance;
	
	public static CatalogoUtilizadores getInstance() {
		if(instance == null) {
			instance = new CatalogoUtilizadores();
		}
		return instance;
	}
	
	private CatalogoUtilizadores() {
		//Não faz nada
	}
	
	public void clear() {
		utilizadores = new HashMap<>();
	}
	
	public void registarAdmin(String u, String p) {
		Utilizador utilizador = new Administrador(u, p);
		utilizadores.put(u, utilizador);
	}
	
//	public void registarComerciante(String u, String pw, PosicaoCoordenadas p) {
//		Utilizador utilizador = new Comerciante(u,pw,p);
//		ComercianteInfo comercianteInfo = new ComercianteInfo(u,p);
//		utilizadores.put(u, utilizador);
//		comerciantesInfo.put(u, comercianteInfo);
//	}
	
	public void registarCliente(String u, String pw) {
		Utilizador utilizador = new Cliente(u, pw);
		utilizadores.put(u, utilizador);
	}

	public boolean autenticar(String u, String p) {
		return utilizadores.containsKey(u) && utilizadores.get(u).autenticar(p);
	}

	public Utilizador getUtilizador(String u) {
		return utilizadores.get(u);
	}

	public Optional<Utilizador> getCliente(String username) {
		return Optional.ofNullable(utilizadores.get(username)).filter(u -> u instanceof Cliente);
	}
	
}
