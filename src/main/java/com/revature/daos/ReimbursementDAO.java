package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;


public interface ReimbursementDAO {

	public List<Reimbursement> getAll();
	public Reimbursement getById(int reimb_id);
	public void insertReimb(Reimbursement reimb);
	public void updateReimb(Reimbursement reimb);
	public void deleteReimb(Reimbursement reimb);
// a get all by type 
// 
}

