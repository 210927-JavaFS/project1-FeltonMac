package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;

//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAOImp implements UserDAO {

	@Override
	public List<User> findAll() {
		Session session = HibernateUtil.getSession();
		List<User> list=  session.createQuery("FROM User").list();

		return list;
	}
		/* never really use but can with out sql syntax
		Session session = HibernateUtil.getSession();
		List<User> list;
		CriteriaBuilder critBuild = session.getCriteriaBuilder();
		CriteriaQuery<User> query = critBuild.createQuery(User.class);
		Root<User> root = query.from(User.class);
		CriteriaQuery<User> allUsers = query.select(root);
		TypedQuery<User> typed = session.createQuery(allUsers);
		list = typed .getResultList();
		
		return list;
		*/
	@Override
	public User findByUsername(String username) {
		Session session = HibernateUtil.getSession();
		List<User> list=  session.createQuery("FROM User WHERE username =" + username).list();
		User user = list.get(0);
		return user;
	}
	@Override
	public User findById(int id) {
		Session session = HibernateUtil.getSession();
		User user = session.get(User.class, id);
		return user;
	}

	@Override
	public boolean insertUser(User user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateUser(User user){
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(user);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean addUser(User user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			//HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
