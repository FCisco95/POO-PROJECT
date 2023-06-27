package pt.tooyummytogo.reservas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



import pt.tooyummytogo.utilizadores.Cliente;
import pt.tooyummytogo.pagamento.Pagamento;



public class Reserva {
	private String codigo;
	private Cliente cliente;
	private List<LinhaReserva> linhasReserva;
	private List<Pagamento> pagamentos = new ArrayList<>();
	double pago = 0.0;
	
	public Reserva(String codigo) {
		this.codigo = codigo;
		linhasReserva = new ArrayList<>();
	}
	
	public void addAReserva(LinhaReserva l) {
		linhasReserva.add(l);
	}
	
	public String  getCodigo() {
		return codigo;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		
	}
	public Cliente getCliente() {
		return cliente;
	}
	
	public double getPreco() {
		double preco = 0.0;
		for(LinhaReserva lr: linhasReserva) {
			preco += lr.getSubTotal();
		}
		return preco - pago;
	}
	
	public void registarPagamento(Pagamento pg) {
		pagamentos.add(pg);
		if(!pg.getCativar()) {
			pago += pg.getValor();
		}
		
	}

	public double getValorEmFalta() {
		double total = 0;
		for(LinhaReserva lr: linhasReserva) {
			total += lr.getSubTotal();
		}
		return total;
	}

}