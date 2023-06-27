package pt.tooyummytogo.utilizadores;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.reservas.Reserva;
import pt.tooyummytogo.exeptions.DoesNotExistException;
import pt.tooyummytogo.pagamento.Pagamento;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.pagamento.CartaoCredito;


public class Cliente extends Utilizador {
	private String nome;
	private PosicaoCoordenadas p;
	private String email;
	private List<CartaoCredito> cartoes = new ArrayList<>();
	private List<Reserva> reservas = new ArrayList<>();
	private List<Pagamento> pagamentos = new ArrayList<>();
	
	public Cliente(String u, String pw) {
		super(u, pw);
	}
	
	public boolean temCC(String numero) {
		for(CartaoCredito c: cartoes) {
			if(c.getNumero().equals(numero))
				return true;
		}
		return false;
	}

	public CartaoCredito criaCC(String numero, int ccv, int mes, int ano) {
		CartaoCredito cc = new CartaoCredito(numero, ccv, mes, ano);
		cartoes.add(cc);
		return cc;
	}

	public CartaoCredito getCC(String numero) throws DoesNotExistException {
		for(CartaoCredito c: cartoes) {
			if(c.getNumero().equals(numero))
				return c;
		}
		throw new DoesNotExistException("O cliente " + this.toString() + " nao tem um cartao de credito com numero " + numero);
	}

	public void registarPagamento(Pagamento pg, Reserva reservaCorrente) {
		pagamentos.add(pg);
		setChanged();
		notifyObservers(reservaCorrente.getCodigo());
	}

	public void associarReserva(Reserva reservaCorrente) {
		reservas.add(reservaCorrente);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PosicaoCoordenadas getP() {
		return p;
	}

	public void setP(PosicaoCoordenadas p) {
		this.p = p;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
