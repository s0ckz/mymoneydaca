package mymoney.model.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

/**
 * Classe que é responsável pela comunicação com o Hibernate
 * e fazer persistência, consultas, etc.
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	// utilizado para limpar e recriar o esquema.
	private static final SchemaExport schemaExport;
	
	// utilizado para verificar a integridade do banco
	private static final SchemaUpdate schemaUpdate;

	static {
		try {
			// carrega as classes com annotations etc.
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			// configura essas classes e cria uma fabrica de sessoes
			sessionFactory = cfg.configure().buildSessionFactory();
			// soh deve criar os exportadores/atualizadores de esquema
			// apos chamar cfg.configure(), senao nao cria os mapeamentos, etc!
			schemaExport = new SchemaExport(cfg);
			schemaUpdate = new SchemaUpdate(cfg);
			// verifica a integridade do esquema.
			schemaUpdate.execute(false, true);
		} catch (Throwable ex) {
			// Log exception!
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	// usado somente para testes.
	/**
	 * Limpa o banco de dados e recria o esquema. Utilizado somente para
	 * testes 
	 */
	public static void cleanAll() {
		schemaExport.drop(false, true);
		schemaExport.create(false, true);
	}

	/**
	 * Carrega a sessão corrente em relação à thread atual.
	 * Como a aplicação será utilizada em Tomcat/afins, cada
	 * requisição será numa thread separada, garantido a não-sobreposição de sessões.
	 * @return Uma sessão baseada na thread atual.
	 * @throws HibernateException Caso algum problema com o Hibernate ocorra.
	 */
	public static Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Salva um objeto na base de dados. Deve ser utilizado quando
	 * o objeto não existir. Caso o objeto já exista, utilizar {@link HibernateUtil#update(Object)}.
	 * @param obj Objeto a ser salvo.
	 * @return Um possível valor gerado que serve como chave. <code>null</code> caso
	 * contrário.
	 * @see HibernateUtil#update(Object)
	 */
	public static Serializable save(Object obj) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			Serializable idReturned = session.save(obj);
			tx.commit();
			return idReturned;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	/**
	 * Atualiza um objeto já existente no banco de dados.
	 * @param obj Objeto a ser atualizado.
	 */
	public static void update(Object obj) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	/**
	 * Retorna um objeto de acordo com o seu identificador.
	 * Utiliza {@link Session#get(Class, Serializable)}, para evitar
	 * que objetos que não existam causem exceções do tipo
	 * {@link LazyInitializationException}.
	 * @param clazz Classe do objeto a ser carregado.
	 * @param id Chave primária do objeto.
	 * @return O objeto requerido.
	 */
	@SuppressWarnings("unchecked")
	public static Object load(Class clazz, Serializable id) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			Object gotten = session.get(clazz, id);
			tx.commit();
			return gotten;
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
	/**
	 * Remove um objeto da base de dados. 
	 * @param obj Objeto a ser removido.
	 */
	public static void delete(Object obj) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(obj);
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
}
