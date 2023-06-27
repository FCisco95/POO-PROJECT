package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.produto.CatalogoTiposProduto;
import pt.tooyummytogo.utilizadores.Comerciante;

public class AdicionarTipoDeProdutoHandler {

	//atributos
	private CatalogoTiposProduto catT;
	private Comerciante comerciante;

	 /**
     * Regista um novo Tipo de Produto associado a um comerciante
     * @param tp - Tipo de produto  
     * @requires tp != null
     * @ensures Um novo tipo de produto fica associado a um comerciante
     * 
     * */
	public AdicionarTipoDeProdutoHandler(CatalogoTiposProduto catT, Comerciante comerciante) {
		this.catT = catT;
		this.comerciante = comerciante;
	}

	/**
	 * Adiciona um tipo de produto ao catalogo de tipos de produto catT
	 * e um codigo a' lista interna de codigos do comerciante na sessao
	 * 
	 * @param tp - tipo do produto
	 */
	public void registaTipoDeProduto(String tp) {
		//o codigo e' gerado segundo o tamanho do catalogo de tipos de produto (se o tamanho for 0, o cod e' 0)
		int cod = this.catT.tamanho();
		this.catT.adicionarTipoProduto(cod, tp);
		this.comerciante.adicionarCodigo(cod);
	}

}
