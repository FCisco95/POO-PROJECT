package pt.tooyummytogo.utilizadores;
//v3
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.produto.TipoProduto;
import pt.tooyummytogo.reservas.Reserva;

public class Comerciante implements Observer {

	private String nome;
	private String password;
	private PosicaoCoordenadas localizacao;
	private ArrayList<ProdutoInfo> produtos;
	private List<Integer> listaCodsTiposDeProduto;
	private List<Reserva> reservas = new ArrayList<>();

	public Comerciante (String u, String pw, PosicaoCoordenadas p) {
		this.nome = u;
		this.password = pw;
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

	public ArrayList<ProdutoInfo> getListaProdutosDoComerciante(){
		return produtos;
	}

	public void addProdutoAListaDoComerciante(ProdutoInfo p) {
		produtos.add(p);
	}

	public boolean estaDisponivel(double d, int raio) {
		return d <= raio ? true : false;
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
	
	public void associarReserva(Reserva reservaCorrente, Cliente c) {
		reservas.add(reservaCorrente);
		c.addObserver(this);
	}
	
	public void desassociarCliente(Cliente c) {
		c.deleteObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Cliente && arg instanceof String) {
			Cliente c = (Cliente) o;
			String codReserva = (String) arg;
			System.out.println("Encomenda com sucesso! Cliente:"+c.getNome()+". Código Reserva:"+codReserva+".");
		}
		
	}
	
}
