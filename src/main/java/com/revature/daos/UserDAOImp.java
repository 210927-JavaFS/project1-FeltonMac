package com.revature.daos;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAOImp implements UserDAO {

	@Override
	public List<User> findAll() {
		Session session = HibernateUtil.getSession();
		List<User> list=  null;
		

		return list;
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
	}

	@Override
	public User findById(int id) {
		Session session = HibernateUtil.getSession();
		User user = session.get(User.class, id);
		return user;
	}

	@Override
	public void insertUser(User user) {
		Session session = HibernateUtil.getSession();
		session.save(user);
		HibernateUtil.closeSession();
	}

	@Override
	public void deleteUser(User user) {
		Session session = HibernateUtil.getSession();
		session.delete(user);
		HibernateUtil.closeSession();
	}
	public void updateUser(User user){
		Session session = HibernateUtil.getSession();
		session.merge(user);
		HibernateUtil.closeSession();
	}
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
}
