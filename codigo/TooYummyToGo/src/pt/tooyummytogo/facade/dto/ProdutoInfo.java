package pt.tooyummytogo.facade.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import pt.tooyummytogo.produto.TipoProduto;

public class ProdutoInfo {

	private int codigo;
	private int quantidade;
	private int preco;
	private TipoProduto tipo;
	private LocalDateTime inicio;
	private LocalDateTime fim;

	public ProdutoInfo(int c, int q, TipoProduto t) {
		this.codigo = c;
		this.quantidade = q;
		this.tipo = t;
	}

	public void setInicio(LocalDateTime i) {
		this.inicio = i;
	}

	public void setFim(LocalDateTime f) {
		this.fim = f;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setPreco(int p) {
		this.preco = p;
	}

	public int getPreco() {
		return this.preco;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
