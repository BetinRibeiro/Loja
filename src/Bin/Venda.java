package Bin;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;
	private Date data;
	private float valor;
	private float custo;
	@OneToMany(mappedBy = "venda", fetch = FetchType.EAGER)
	private Set<ItemVenda> lista;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}

	public Set<ItemVenda> getLista() {
		return lista;
	}

	public void setLista(Set<ItemVenda> lista) {
		this.lista = lista;
	}

	public Venda(Integer id, Date data, float valor, float custo, Set<ItemVenda> lista) {
		super();
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.custo = custo;
		this.lista = lista;
	}

	public Venda(Date data, float valor, float custo, Set<ItemVenda> lista) {
		super();
		this.data = data;
		this.valor = valor;
		this.custo = custo;
		this.lista = lista;
	}

	public Venda() {
		super();
	}

}
