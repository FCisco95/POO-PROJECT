package pt.tooyummytogo.facade.dto;
import java.time.LocalDateTime;
//v3
import java.util.ArrayList;
import java.util.List;

public class ComercianteInfo {

	private String nome;
	private PosicaoCoordenadas localizacao;
	private ArrayList<ProdutoInfo> produtos;
	private List<Integer> listaCodsTiposDeProduto;

	public ComercianteInfo (String u, PosicaoCoordenadas p) {
		this.nome = u;
		this.localizacao = p;
		this.listaCodsTiposDeProduto = new ArrayList<Integer>();
		this.produtos = new ArrayList<ProdutoInfo>();
	}

	public String getNome() {
		return this.nome;
	}

	public PosicaoCoordenadas getLocalizacao() {
		return this.localizacao;
	}

	public ArrayList<ProdutoInfo> getListaProdutosDoComerciante(LocalDateTime inicio, LocalDateTime fim){

		ArrayList<ProdutoInfo> nova = new ArrayList<ProdutoInfo>();
		for(int i=0; i<produtos.size(); i++) {
			if((produtos.get(i).getInicio().compareTo(inicio)<=0) && (produtos.get(i).getFim().compareTo(fim)>=0)) {
				nova.add(produtos.get(i));
			}
		}
		return nova;
	}

	public void addProdutoAListaDoComerciante(ProdutoInfo p) {
		produtos.add(p);
	}

	public boolean estaDisponivel(double d, int raio) {
		return (d <= raio ? true : false);
	}

	/**
	 * Adicionar codigo de tipo de produto a lista interna do comerciante
	 * 
	 * @param cod - codigo do tipo de produto
	 */
	public void adicionarCodigo(int cod) {
		this.listaCodsTiposDeProduto.add(cod);
	}

	/**
	 * Devolve lista interna de codigos de tipos de produtos
	 * 
	 * @return lista de codigos de tipos de produtos
	 */
	public List<Integer> listaCodsTiposDeProduto() {
		return this.listaCodsTiposDeProduto;
	}

	public List<ProdutoInfo> getListaProdutos() {
		return produtos;
	}

}
