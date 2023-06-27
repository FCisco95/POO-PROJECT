package pt.tooyummytogo.utilizadores;

import java.util.Observable;

public abstract class Utilizador extends Observable {
	private String username;
	private String password;
	
	public Utilizador(String u, String p) {
		this.username = u;
		this.password = p;
	}
	
	public boolean tryLogin(String p) {
		return p.equals(password);
	}

	public String toString() {
		return username;
	}

	public boolean autenticar(String p) {
		return p.equals(password);
	}
}
