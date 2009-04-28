package mymoney.model.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			sessionFactory = cfg.configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Log exception!
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}
	
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
	
	/*
	 * User template method aqui
	 * 
	 */
//	private void doTransaction() {
//		Session session = getSession();
//		Transaction tx = session.beginTransaction();
//		try {
//			// job
//			tx.commit();
//		} catch (HibernateException e) {
//			if (tx != null) tx.rollback();
//			throw e;
//		}
//	}
}