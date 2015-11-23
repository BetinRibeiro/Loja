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
@Table(name = "item_compra")
public class ItemCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;
	@ManyToOne(optional = false)
	private Produto produto;

	private float quantidade;
	private float custo;
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}
	public float getCusto() {
		return custo;
	}
	public void setCusto(float custo) {
		this.custo = custo;
	}
	public ItemCompra(Compra compra, Produto produto, float quantidade, float custo) {
		super();
		this.compra = compra;
		this.produto = produto;
		this.quantidade = quantidade;
		this.custo = custo;
	}
	public ItemCompra() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ItemCompra(Produto produto, float quantidade, float custo) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
		this.custo = custo;
	}
	
	
	
	
	

}
