package mymoney.model.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

/**
 * Classe que eh responsavel pela comunicacao com o Hibernate
 * e fazer persistencia, consultas, etc.
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
	 * Carrega a sessao corrente em relacao a thread atual.
	 * Como a aplicacao sera utilizada em Tomcat/afins, cada
	 * requisicao sera numa thread separada, garantido a nao-sobreposicao de sessoes.
	 * @return Uma sessao baseada na thread atual.
	 * @throws HibernateException Caso algum problema com o Hibernate ocorra.
	 */
	private static Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Retorna os objetos que se encaixam no criterio requerido.
	 * @param clazz Classe a ser utilizada.
	 * @param expressions Expressoes que serao avaliadas.
	 * @return Lista de objetos que casam com as expressoes enviadas.
	 */
	public static List<?> createQueryBasedOnExpressions(
			Class<?> clazz, Collection<SimpleExpression> expressions) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria crit = createCriteria(session, clazz, expressions);
		List<?> list = crit.list();
		session.close();
		return list;
	}

	private static Criteria createCriteria(Session session, Class<?> clazz, Collection<SimpleExpression> expressions) {
		Criteria crit = session.createCriteria(clazz);
		for (SimpleExpression se : expressions) {
			crit.add(se);
		}
		return crit;
	}
	
	/**
	 * Salva um objeto na base de dados. Deve ser utilizado quando
	 * o objeto nao existir. Caso o objeto ja exista, utilizar {@link HibernateUtil#update(Object)}.
	 * @param obj Objeto a ser salvo.
	 * @return Um possivel valor gerado que serve como chave. <code>null</code> caso
	 * contrario.
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
	 * Atualiza um objeto ja existente no banco de dados.
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
	 * que objetos que nao existam causem excecoes do tipo
	 * {@link LazyInitializationException}.
	 * @param clazz Classe do objeto a ser carregado.
	 * @param id Chave primaria do objeto.
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
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			throw e;
		}
	}
	
}
