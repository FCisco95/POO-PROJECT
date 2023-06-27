package pt.tooyummytogo.produto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Produto {

	private int codigo;
	private int quantidade;
	private int preco;
	private TipoProduto tipo;
	private LocalDateTime inicio;
	private LocalDateTime fim;
	
	public Produto(int c, int q, int p, TipoProduto t, LocalDateTime in, LocalDateTime f) {
		this.codigo = c;
		this.quantidade = q;
		this.preco = p;
		this.tipo = t;
		this.inicio = in;
		this.fim = f;
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
