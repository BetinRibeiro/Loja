package Bin;

import java.sql.Date;
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
@Table(name = "compra")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;
	private Date data;
	private float total;
	@OneToMany(mappedBy = "compra", fetch=FetchType.EAGER)
	private Set<ItemCompra> lista;
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
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Set<ItemCompra> getLista() {
		return lista;
	}
	public void setLista(Set<ItemCompra> lista) {
		this.lista = lista;
	}
	public Compra(Integer id, Date data, float total, Set<ItemCompra> lista) {
		super();
		this.id = id;
		this.data = data;
		this.total = total;
		this.lista = lista;
	}
	public Compra(Date data, float total, Set<ItemCompra> lista) {
		super();
		this.data = data;
		this.total = total;
		this.lista = lista;
	}
	public Compra() {
		super();
	}
	
	
}
