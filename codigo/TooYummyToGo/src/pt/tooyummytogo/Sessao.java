package pt.tooyummytogo;

import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;
import pt.tooyummytogo.produto.CatalogoProdutos;
import pt.tooyummytogo.produto.CatalogoTiposProduto;
import pt.tooyummytogo.produto.Produto;
import pt.tooyummytogo.produto.TipoProduto;
import pt.tooyummytogo.reservas.CatalogoReservas;
import pt.tooyummytogo.utilizadores.CatalogoComerciantes;
import pt.tooyummytogo.utilizadores.CatalogoUtilizadores;
import pt.tooyummytogo.utilizadores.Comerciante;
import pt.tooyummytogo.utilizadores.Utilizador;

public class Sessao {

    private Utilizador utilizador;
    private CatalogoUtilizadores catU;
    private CatalogoComerciantes catC;
    private CatalogoProdutos catP;
    private CatalogoTiposProduto catT;
    private CatalogoReservas catR;
    
    public Sessao(String u, CatalogoUtilizadores catUtilizadores, CatalogoComerciantes catComerciantes, CatalogoProdutos catProdutos, CatalogoTiposProduto catTiposProduto, CatalogoReservas catReservas) {
        this.utilizador = catUtilizadores.getUtilizador(u);
        this.catU = catUtilizadores;
        this.catC = catComerciantes;
        this.catP = catProdutos;
        this.catT = catTiposProduto;
        this.catR = catReservas;
    }

    // UC4
    public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() {
        return new AdicionarTipoDeProdutoHandler(catT, catC.getComerciante(utilizador.toString()));
    }

    // UC5
    public ColocarProdutoHandler getColocarProdutoHandler() {
        return new ColocarProdutoHandler(catC.getComerciante(utilizador.toString()), catT);
    }

    public EncomendarHandler getEncomendarComerciantesHandler() {
        return new EncomendarHandler(this.utilizador, this.catU, this.catC, this.catP, this.catT, this.catR);
    }
}
