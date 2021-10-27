package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;


public interface ReimbursementDAO {

	public List<Reimbursement> getAll();
	public Reimbursement getById(int reimb_id);
	public boolean insertReimb(Reimbursement reimb);
	public boolean updateReimb(Reimbursement reimb);
	public boolean deleteReimb(Reimbursement reimb);
// a get all by type 
// 
}

