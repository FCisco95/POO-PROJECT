package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//v5



import pt.tooyummytogo.produto.CatalogoProdutos;
import pt.tooyummytogo.produto.CatalogoTiposProduto;
import pt.tooyummytogo.reservas.CatalogoReservas;
import pt.tooyummytogo.reservas.LinhaReserva;
import pt.tooyummytogo.reservas.Reserva;
import pt.tooyummytogo.utilizadores.CatalogoComerciantes;
import pt.tooyummytogo.utilizadores.CatalogoUtilizadores;
import pt.tooyummytogo.utilizadores.Cliente;
import pt.tooyummytogo.utilizadores.Utilizador;
import pt.tooyummytogo.cartoesdecredito.CartoesDeCreditoPlugin;
import pt.tooyummytogo.cartoesdecredito.MonsterCardAdapter;
import pt.tooyummytogo.cartoesdecredito.PluginFactory;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.pagamento.Pagamento;


public class EncomendarHandler {

	private Cliente cliente;
	private PosicaoCoordenadas coordUtilizador;
	double valorEmFalta = 0.0;
	static final int RAIO_DEFAULT = 5000;
	private CatalogoUtilizadores catU;
	private CatalogoComerciantes catC;
	private CatalogoProdutos catP;
	private CatalogoTiposProduto catT;
	private CatalogoReservas catR;
	private MonsterCardAdapter mcCC;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	private Reserva reserva;
	private LinhaReserva carrinho;


	public EncomendarHandler(Utilizador utilizador, CatalogoUtilizadores catU, CatalogoComerciantes catC,
			CatalogoProdutos catP, CatalogoTiposProduto catT, CatalogoReservas catR) {

		this.cliente = (Cliente) utilizador;
		this.catU = catU;
		this.catC = catC;
		this.catP = catP;
		this.catT = catT;
		this.catR = catR;
		this.carrinho = new LinhaReserva();
		this.inicio = LocalDateTime.now();
		this.fim = inicio.plus(1,ChronoUnit.HOURS);
		this.reserva = new Reserva (catR.tamanho());
		this.reserva.setCliente((Cliente) utilizador);
	}
	
	/**
     * Indica a localizacao atual do Cliente
     * @param coordinate - coordenadas do Cliente
     * @return lista de comerciantes disponiveis num raio de 5km
     *            que tenham produtos para recolha durante a proxima hora
     */
	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinate) {

		this.coordUtilizador = coordinate; // guarda as coordenadas do utilizador
		return this.catC.getComerciantesDisponiveis(coordinate, RAIO_DEFAULT, inicio, fim);
	}

	/**
     * Altera o raio de procura de comerciantes para i
     * @param i - numero de metros
     * @return lista de comerciantes disponiveis num raio de i metros com
     *            que tenham produtos para recolha durante a proxima hora
     * @requires i > 0
     */
	public List<ComercianteInfo> redefineRaio(int i) {

		return this.catC.getComerciantesDisponiveis(this.coordUtilizador, i, inicio, fim);
	}

	/**
     * Cliente redefine um periodo em que estah disponivel para recolha
     * @param now - hora atual
     * @param plusHours - horas adicionadas
     * @return lista de comerciantes que tenham produtos disponiveis
     *            em janelas temporais que se sobreponham com aquela indicada
     *            pelo utilizador.
     * @requires now != null && plusHours != null
     */
	public List<ComercianteInfo> redefinePeriodo(LocalDateTime now, LocalDateTime plusHours) {
        //ir ao catComerciantes buscar todos os comerciantes
        //em cada comerciante percorrer a lista de produtos disponiveis
        //se [inicioCo, fimCo] se cruzar com o [now, plusHours], por numa lista de ComercianteInfo
        //devolver lista
        List<ComercianteInfo> todosComerciantes = this.catC.listaComercianteInfo();
        List<ComercianteInfo> comerciantesNoPeriodo = new ArrayList<ComercianteInfo>();
        for (int i = 0; i<todosComerciantes.size(); i++) {
            List<ProdutoInfo> produtos = todosComerciantes.get(i).getListaProdutos();
            for (int j = 0; j<produtos.size(); j++) {
                ProdutoInfo produtoAtual = produtos.get(j);
                if ((produtoAtual.getInicio().isBefore(now) && produtoAtual.getFim().isAfter(plusHours)) || (now.isBefore(produtoAtual.getInicio()) && plusHours.isAfter(produtoAtual.getFim()))) {
                    if (!comerciantesNoPeriodo.contains(todosComerciantes.get(i))) {
                        comerciantesNoPeriodo.add(todosComerciantes.get(i));
                    }
                }
            }
        }
        this.inicio = now;
        this.fim = plusHours;
        return comerciantesNoPeriodo;
    }

	/**
     * O Cliente escolhe comerciante que pretende visitar.
     * @param comercianteInfo - comerciante a visitar
     * @return lista de produtos que esse comerciante tem disponivel
     * @requires comercianteInfo != null
     */
	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		
		return comercianteInfo.getListaProdutosDoComerciante(inicio, fim);
	}

	 /**
     * Cliente inclui na sua compra um produto p, indicando a quantidade i
     * @param p - produto a incluir na compra
     * @param i - quantidade a adicionar
     * @requires p != null && i > 0
     */
	public void indicaProduto(ProdutoInfo p, int i) {
		 for (int j = 0; j<i; j++) {
	            this.carrinho.addProduto(p);
	        }
		 this.reserva.addAReserva(carrinho);
	}

	/**
     * O Cliente confirma a sua compra indicando os dados de pagamento
     * @param string - numero do cc
     * @param string2 - data de validade
     * @param string3 - ccv
     * @return - codigo da reserva
     * @throws InvalidCreditCardException - se o cartao nao for valido
     */
	public String indicaPagamento(String string, String string2, String string3) {
		
		String [] dateArr = string2.split("/");
		int mes = Integer.parseInt(dateArr[0]);
		int ano = Integer.parseInt(dateArr[1]);
		int ccv = Integer.parseInt(string3);

		if(!cliente.temCC(string)) {
			cliente.criaCC(string, ccv, mes, ano);
		}
		
		//adapter
		for (CartoesDeCreditoPlugin p : PluginFactory.getPluginsList()) {
			if(p.validar(string, ccv, mes, ano)) {
				Pagamento pg = new Pagamento(false, valorEmFalta);
				cliente.registarPagamento(pg, reserva);	
				p.retirar(string, ccv, mes, ano, valorEmFalta);
				return reserva.getCodigo();
			}
		}
		return "Nao foi possivel";

		//Pagamento pg = new Pagamento(false, valorEmFalta);
		//cliente.registarPagamento(pg);
		//mcCC.retirar(string, ccv, mes, ano, valorEmFalta);

		//return reserva.getCodigo();
	}

}
