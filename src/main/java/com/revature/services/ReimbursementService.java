package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDAOImp;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	ReimbursementDAO RDAO = new ReimbursementDAOImp();

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
		return RDAO.insertReimb(reimbursement);
	}
	public boolean deleteReimbursement(Reimbursement reimbursement) {
		return RDAO.deleteReimb(reimbursement);
	}
	public boolean updateReimbursement(Reimbursement reimbursement) {
		return RDAO.updateReimb(reimbursement);
	}

}
