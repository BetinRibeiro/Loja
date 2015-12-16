package Persistence.Conexao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
 
public class HibernateUtil {
 
      public static final SessionFactory session = buildSessionGactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionGactory() {
		try {
			Configuration con = new AnnotationConfiguration();
			
			//o xml é o mapeamento e é feito de forma automatica 
			//coloca todas as libs e cria um cfg,xml
			con.configure("Persistence/hibernate.cfg.xml");
			return con.buildSessionFactory();
		} catch (Throwable e) {
			System.out.println("Deu erro : " +e);
			throw new ExceptionInInitializerError();
		}
	}
	
	public static SessionFactory getSession() {
		return session;
		
		
	}
}



