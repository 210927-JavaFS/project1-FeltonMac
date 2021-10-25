package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAOImp implements ReimbursementDAO {

	@Override
	public List<Reimbursement> getAll() {
		Session session = HibernateUtil.getSession();
		List<Reimbursement> reimbList = session.createQuery("From Reimbursement").list();
		
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
	public void insertReimb(Reimbursement reimb) {	
		Session session = HibernateUtil.getSession();
		session.save(reimb);
		HibernateUtil.closeSession();// not normaly done here 
	}

	@Override
	public void updateReimb(Reimbursement reimb) {
		Session session = HibernateUtil.getSession();
		session.merge(reimb);
		HibernateUtil.closeSession();
	}

	@Override
	public void deleteReimb(Reimbursement reimb) {
		Session session = HibernateUtil.getSession();
		session.delete(reimb);
		HibernateUtil.closeSession();
	}

}
