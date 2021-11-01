package com.revature.services;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.HibernateException;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDAOImp;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImp;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.Type;

public class ReimbursementService {
	ReimbursementDAO RDAO = new ReimbursementDAOImp();
	UserDAO UDAO = new UserDAOImp();
	
	
	public List<Reimbursement> findAll(){
		return RDAO.getAll();
	}
    public Reimbursement findById(int id) {
    	Reimbursement Reimbursement = RDAO.getById(id);
    	if(Reimbursement!=null) {
    		return Reimbursement;
    	}else {
    		return new Reimbursement();
    	}
    }
  
	public boolean insertReimbursement(Reimbursement reimbursement) {
		if(reimbursement.getAuthor()==null) {
			reimbursement.setAuthor(UDAO.findById(1));
		}
		if(reimbursement.getSubmitted()==null) {
			reimbursement.setSubmitted(LocalDateTime.now().toString());
		}
		if(reimbursement.getDescription()==null) {
			reimbursement.setDescription("no descriptioin inserted default selected");
		}
		if(reimbursement.getStatus()==null) {
			reimbursement.setStatus(new Status("pending"));
		}
		if(reimbursement.getType()==null) {
			reimbursement.setType(new Type("lodging"));
		}
		
		return RDAO.insertReimb(reimbursement);
	}
	public boolean deleteReimbursement(int reimbursementint) {
		Reimbursement reimbursement= findById(reimbursementint);
		return RDAO.deleteReimb(reimbursement);
	}
	public boolean updateReimbursement(Reimbursement reimbursement) {
		return RDAO.updateReimb(reimbursement);
	}
	public boolean approveStatus(Reimbursement reimbursement) {
		try {
			Reimbursement DAOreimb = RDAO.getById(reimbursement.getRe_id());
		    DAOreimb.setStatus(new Status("approved"));
		    RDAO.insertReimb(DAOreimb);
		    return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
// make find reimbursement by id 
}
