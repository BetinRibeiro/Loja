package Bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")

public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;
	@Column(name = "descricao", length = 80)
	private String descricao;
	private float custo;
	private float quantidade;
	private float preco;
	@Column(name = "estoque_min")
	private float estMin;
	private String local;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public float getEstMin() {
		return estMin;
	}

	public void setEstMin(float estMin) {
		this.estMin = estMin;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Produto(String descricao, float custo, float quantidade, float preco, float estMin, String local) {
		super();
		this.descricao = descricao;
		this.custo = custo;
		this.quantidade = quantidade;
		this.preco = preco;
		this.estMin = estMin;
		this.local = local;
	}

	public Produto(Integer id, String descricao, float custo, float quantidade, float preco, float estMin,
			String local) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.custo = custo;
		this.quantidade = quantidade;
		this.preco = preco;
		this.estMin = estMin;
		this.local = local;
	}

	public Produto() {
		super();
	}

}
