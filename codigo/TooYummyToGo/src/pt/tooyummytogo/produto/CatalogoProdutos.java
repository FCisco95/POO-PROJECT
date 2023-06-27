package pt.tooyummytogo.produto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import pt.tooyummytogo.utilizadores.Comerciante;

public class CatalogoProdutos {
private Map<Integer, Produto> produtos = new HashMap<>();
	
	private static CatalogoProdutos instance;
	
	public static CatalogoProdutos getInstance() {
		if(instance == null) {
			instance = new CatalogoProdutos();
		}
		return instance;
	}
	
	private CatalogoProdutos() {
		//Não faz nada
	}
	public void clear() {
		produtos = new HashMap<>();
	}
	public void adicionarProduto(int c, int q, int p, TipoProduto t, LocalDateTime i, LocalDateTime f) {
		Produto produto = new Produto(c,q,p,t,i,f);
		produtos.put(c, produto);
	}
	public Produto getProduto(int u) {
		return produtos.get(u);
	}
}
