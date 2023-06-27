package pt.tooyummytogo.produto;

import java.util.HashMap;
import java.util.Map;

public class CatalogoTiposProduto {

	private Map<Integer, TipoProduto> tiposProduto = new HashMap<>();
	private static CatalogoTiposProduto instance;

	public static CatalogoTiposProduto getInstance() {
		if(instance == null) {
			instance = new CatalogoTiposProduto();
		}
		return instance;
	}

	private CatalogoTiposProduto() {
		//Nao faz nada
	}
	public void clear() {
		this.tiposProduto = new HashMap<>();
	}

	//m: mudei o nome para adicionarTipoDeProduto
	/**
	 * Adiciona tipo de produto ao catalogo de tipos de produtos
	 * 
	 * @param c - codigo do tipo de produto
	 * @param n - nome do tipo de produto
	 */
	public void adicionarTipoProduto(int c, String n) {
		TipoProduto tipoProduto = new TipoProduto(c,n);
		this.tiposProduto.put(c, tipoProduto);
	}

	/**
	 * Devolve o tipo de produto associado ao codigo dado
	 * 
	 * @param c - codigo do tipo de produto
	 * @return tipo de produto
	 */
	public TipoProduto getTipoProduto(int c) { //m: u para c
		return this.tiposProduto.get(c);
	}

	//m
	/**
	 * Devolve o tamanho do catalogo de tipos de produtos
	 * 
	 * @return tamanho do catalogo em int
	 */
	public int tamanho() {
		return this.tiposProduto.size();
	}
}