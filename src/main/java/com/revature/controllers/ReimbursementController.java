package com.revature.controllers;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {

		private ReimbursementService reimbursementService = new ReimbursementService();

		public Handler getAllReimbursements = (ctx) -> {
			List<Reimbursement> list = reimbursementService.findAll();
			ctx.json(list);
			ctx.status(200);
		};

		public Handler getReimbursement = (ctx) -> {
			if (ctx.req.getSession(false) != null) {
				try {
					Reimbursement reimbursement = reimbursementService.findById(Integer.parseInt(ctx.pathParam("reimbursement")));
					ctx.json(reimbursement);
					ctx.status(200);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					ctx.status(406);
				}
			} else {
				ctx.status(401);
			}
		};

		public Handler addReimbursement = (ctx) -> {
			if (ctx.req.getSession(false) != null) {
				Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
				if (reimbursementService.insertReimbursement(reimbursement)) {
					ctx.status(201);
				} else {
					ctx.status(400);
				}
			} else {
				ctx.status(401);
			}
		};

		public Handler updateReimbursement = (ctx) -> {
			if (ctx.req.getSession(false) != null) {
				Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
				if (reimbursementService.updateReimbursement(reimbursement)) {
					ctx.status(200);
				} else {
					ctx.status(400);
				}
			} else {
				ctx.status(401);
			}
		};

		public Handler deleteReimbursement = (ctx) -> {
			if (ctx.req.getSession(false) != null) {
				String id = ctx.pathParam("reimbursement");
				try {
					int reimbursement = Integer.parseInt(id);
					if (reimbursementService.deleteReimbursement(reimbursement)) {
						ctx.status(200);
					} else {
						ctx.status(400);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					ctx.status(406);
				}
			} else {
				ctx.status(401);
			}
		};

	@Override
	public void addRouts(Javalin app) {
		app.get("/reimbursements", this.getAllReimbursements);
		app.get("/reimbursements/:reimbursement", this.getReimbursement);
		app.post("/reimbursements", this.addReimbursement);
		app.put("/reimbursements", this.updateReimbursement);
		app.delete("/reimbursements/:reimbursement", this.deleteReimbursement);		
	}
	

}
