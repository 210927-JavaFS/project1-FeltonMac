package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAOImp implements ReimbursementDAO {

	@Override
	public List<Reimbursement> getAll() {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> reimbList = session.createQuery("From Reimbursement").list();
//		for (Reimbursement r : reimbList) {
//				User user= session.get(User.class,r.getAuthor());
//				r.setUser(user);
//				System.out.println(user);
		// }
		return reimbList;
	}

	@Override
	public Reimbursement getById(int reimb_id) {
		Session session = HibernateUtil.getSession();
		//Reimbursement reimb = (Reimbursement) session.createQuery("From Reimbursement");
		Reimbursement reimb = session.get(Reimbursement.class, reimb_id);
		return reimb;
	}

	@Override
	public boolean insertReimb(Reimbursement reimb) {	
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(reimb);
			tx.commit();
			//HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateReimb(Reimbursement reimb) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(reimb);
			tx.commit();
			//HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteReimb(Reimbursement reimb) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(reimb);
			tx.commit();
			//HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
