package pt.tooyummytogo.reservas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.produto.Produto;

public class LinhaReserva {
	
	private List<ProdutoInfo> produtos;

	public LinhaReserva() {
		this.produtos = new ArrayList<ProdutoInfo>();
	}

	public void addProduto(ProdutoInfo p) {
		
		produtos.add(p);
	}


	public double getSubTotal() {
		double subTotal = 0;
		for(ProdutoInfo p: produtos) {
		
			subTotal += p.getPreco();
		}
		return subTotal;
	}

}
