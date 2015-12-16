package Persistence.Ponte;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import Bin.Compra;
import Bin.Venda;
import Persistence.Conexao.HibernateUtil;

public class Dao {

	private static Dao instance;
	private Session sessao;
	private Transaction tx;

	public Session getSessao() {
		return sessao;
	}

	public void setSessao() {
		sessao.close();
	}

	public Dao() {
	}

	public static Dao getInstance() {
		if (instance == null)
			instance = new Dao();
		return instance;
	}

	public <T> Object buscarPorId(Class<T> clazz, Integer id) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			Object object = sessao.get(clazz, id);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessao.close();
		}
	}

	public <T> Object buscarPorIdcarregaListaCompra(Class<?> clazz, Integer id) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			Compra object = (Compra)sessao.get(clazz, id);
			Hibernate.initialize(object.getLista());
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessao.close();
		}
	}
	public <T> Object buscarPorIdcarregaListaVenda(Class<?> clazz, Integer id) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			Venda object = (Venda)sessao.get(clazz, id);
			Hibernate.initialize(object.getLista());
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessao.close();
		}
	}

	public <T> Object buscarPorNome(Class<T> clazz, String nome) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			Object object = sessao.get(clazz, nome);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessao.close();
		}
	}

	public <T> Object buscarPorChaveUnica(Class<T> clazz, Long codigo, String coluna) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			Object object = sessao.createCriteria(clazz).add(Restrictions.eq(coluna, codigo)).uniqueResult();
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessao.close();
		}
	}

	public <T> boolean salvarObjeto(T objeto) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			tx = sessao.beginTransaction();
			sessao.save(objeto);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sessao.close();
		}
	}

	public <T> boolean salvarOuAtualizarObjeto(T objeto) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			tx = sessao.beginTransaction();
			sessao.saveOrUpdate(objeto);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sessao.close();
		}
	}

	public <T> boolean deletarObjeto(T objeto) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			tx = sessao.beginTransaction();
			sessao.delete(objeto);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sessao.close();
		}
	}

	public <T> boolean deletarObjetoCascata(T objeto) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			tx = sessao.beginTransaction();
			sessao.delete(objeto);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sessao.close();
		}
	}

	public List<?> listarObjetos(Class<?> classe, String ordanacao) {
		try {
			sessao = HibernateUtil.getSession().openSession();
			Criteria criteria = sessao.createCriteria(classe).addOrder(Order.asc(ordanacao));

			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro : " + e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<?> BuscaNome(Class<?> classe, String nomeBuscar, String coluna) {
		try {
			sessao = HibernateUtil.getSession().openSession();

			if (nomeBuscar == null || nomeBuscar.trim().equals(""))
				return sessao.createCriteria(classe).addOrder(Order.desc("id")).setMaxResults(30).list();
			return sessao.createCriteria(classe).add(Restrictions.ilike(coluna, nomeBuscar, MatchMode.ANYWHERE)).setMaxResults(30).list();
			// (List<?>)
			// this.sessao.createCriteria(classe).add(Restrictions.like(coluna,
			// "%"+nomeBuscar+"%")).list();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<?> BuscaInteiro(Class<?> classe, String nomeBuscar, String coluna) {
		try {
			sessao = HibernateUtil.getSession().openSession();

			if (nomeBuscar == null || nomeBuscar.equals(0))
				return sessao.createCriteria(classe).addOrder(Order.asc(coluna)).list();
			return sessao.createCriteria(classe).add(Restrictions.ilike(coluna, nomeBuscar, MatchMode.ANYWHERE)).list();
			// (List<?>)
			// this.sessao.createCriteria(classe).add(Restrictions.like(coluna,
			// "%"+nomeBuscar+"%")).list();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<?> BuscaNomeHabilitado(Class<?> classe, String nomeBuscar, String coluna, boolean b) {
		try {
			sessao = HibernateUtil.getSession().openSession();

			if (nomeBuscar == null || nomeBuscar.trim().equals(""))
				return sessao.createCriteria(classe).addOrder(Order.asc(coluna)).list();
			return sessao.createCriteria(classe).add(Restrictions.ilike(coluna, nomeBuscar, MatchMode.ANYWHERE)).list();
			// (List<?>)
			// this.sessao.createCriteria(classe).add(Restrictions.like(coluna,
			// "%"+nomeBuscar+"%")).list();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<?> buscarInstanciaCompra(Class<?> clazz, String codigo, String coluna) {
		Integer cod = Integer.parseInt(codigo);
		try {
			sessao = HibernateUtil.getSession().openSession();
			return sessao.createCriteria(clazz).add(Restrictions.eq(coluna, cod)).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<?> BuscaData(Class<?> classe, Date date, String coluna) {
		try {
			sessao = HibernateUtil.getSession().openSession();

			Date d = date;

			Calendar c = Calendar.getInstance();

			c.setTime(d);

			// c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) -1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			Date data2 = new Date(c.getTimeInMillis());

			System.out.println(
					"" + new java.sql.Date(data2.getTime() - 1) + " " + new java.sql.Date(date.getTime()) + "");
			return sessao.createCriteria(classe)
					.add(Restrictions.between(coluna, new java.sql.Date(data2.getTime()),
							new java.sql.Date(date.getTime())))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			// (List<?>)
			// this.sessao.createCriteria(classe).add(Restrictions.like(coluna,
			// "%"+nomeBuscar+"%")).list();
		} catch (java.lang.NullPointerException e) {
			return sessao.createCriteria(classe).addOrder(Order.asc(coluna)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}
	public List<?> BuscaEntreData(Class<?> classe, Date dataInicial, Date dataFinal, String coluna) {
		try {
			System.out.println("INICIO "+dataInicial);
			System.out.println("FIM "+dataFinal);
			sessao = HibernateUtil.getSession().openSession();
			return sessao.createCriteria(classe)
					.add(Restrictions.between(coluna, dataInicial,
							dataFinal)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (java.lang.NullPointerException e) {
//			sessao.createCriteria(classe).addOrder(Order.asc(coluna)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return null;
		} catch (Exception e) {
			
			System.out.println(e);
			return null;
//		}catch (JndiException e) {
//			return null;
		} finally {
			sessao.close();
		}
	}

}
