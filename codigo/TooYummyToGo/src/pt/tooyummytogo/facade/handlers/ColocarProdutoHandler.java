package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.ProdutoInfo;
//import pt.tooyummytogo.produto.CatalogoProdutos;
import pt.tooyummytogo.produto.CatalogoTiposProduto;
import pt.tooyummytogo.utilizadores.Comerciante;

public class ColocarProdutoHandler {
	
	//atributos
	//private CatalogoProdutos catP;
	private CatalogoTiposProduto catT;
	private Comerciante comerciante;
	
	/**
     * Comercciante indica que disponibilizar um tipo de produto.
     * @param string - codigo do produto
     * @param i - quantidade
     * @requires string != null && i > 0
     */
	public ColocarProdutoHandler(/*CatalogoProdutos catP, */Comerciante comerciante, CatalogoTiposProduto catT) {
		//this.catP = catP;
		this.comerciante = comerciante;
		this.catT = catT;
	}
	
	/**
	 * Devolve lista de codigos de tipos de produtos de um comerciante especifico
	 * 
	 * @return lista de codigos de tipos de produtos
	 */
	public List<String> inicioDeProdutosHoje() {
		List<Integer> listaCodTiposProdutos = comerciante.listaCodsTiposDeProduto(); //lista de codigos de tipos de produto do comerciante
		List<String> listaCodEmStringTiposProdutos = new ArrayList<String>(); //lista vazia dos codigos em string dos tipos de produto a preencher
		for (int i = 0; i<listaCodTiposProdutos.size(); i++) {
			listaCodEmStringTiposProdutos.add(listaCodTiposProdutos.get(i).toString());	//transformar cods em strings
		}
		return listaCodEmStringTiposProdutos;
	}
	
	/**
	 * Adiciona produto indicado pelo comerciante a' lista de produtos interna do comerciante
	 * 
	 * @param string - codigo do tipo de produto
	 * @param i - quantidade
	 */
	public void indicaProduto(String string, int i) {
		int cod = Integer.parseInt(string);
		ProdutoInfo produto = new ProdutoInfo(cod, i, catT.getTipoProduto(cod));
		this.comerciante.addProdutoAListaDoComerciante(produto);
		
	}

	/**
     * O comerciante confirma a disponibilidade dos produtos para venda
     * @param now - horario de inicio
     * @param plusHours - horario fim
     * @ensures Comerciante fica com mais produtos associados.
     */
	public void confirma(LocalDateTime now, LocalDateTime plusHours) {
		//1. pegar na lista de produtos do comerciante
		//2. percorrer e adicionar horarioInicio e horarioFim
		List<ProdutoInfo> produtosComerciante = this.comerciante.getListaProdutosDoComerciante();
		for (int i = 0; i<produtosComerciante.size(); i++) {
			ProdutoInfo produto = produtosComerciante.get(i);
			produto.setInicio(now);
			produto.setFim(plusHours);
			produtosComerciante.add(i, produto);
		}
	}

}
