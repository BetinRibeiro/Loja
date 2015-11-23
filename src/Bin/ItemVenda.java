package Bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_venda")
public class ItemVenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "venda_id")
	private Venda venda;
	@ManyToOne(optional = false)
	private Produto produto;
	private float custo;
	private float preco;
	private float quantidade;
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public float getCusto() {
		return custo;
	}
	public void setCusto(float custo) {
		this.custo = custo;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}
	public ItemVenda(Venda venda, Produto produto, float custo, float preco, float quantidade) {
		super();
		this.venda = venda;
		this.produto = produto;
		this.custo = custo;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	public ItemVenda() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ItemVenda(Produto produto, float custo, float preco, float quantidade) {
		super();
		this.produto = produto;
		this.custo = custo;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	
	
	

}
