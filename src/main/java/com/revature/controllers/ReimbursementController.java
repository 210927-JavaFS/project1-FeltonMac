package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {
	private static Logger log = LoggerFactory.getLogger(ReimbursementController.class);

		private ReimbursementService reimbursementService = new ReimbursementService();

		public Handler getAllReimbursements = (ctx) -> {
			log.info("getAllReimbursements attempt: "+ctx.body());

			List<Reimbursement> list = reimbursementService.findAll();
			ctx.json(list);
			ctx.status(200);
		};

		public Handler getReimbursement = (ctx) -> {
			log.info("getReimbursement attempt: "+ctx.body());

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
			log.info("addReimbursement attempt: "+ctx.body());

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
			log.info("update Reimbursement attempt: "+ctx.body());

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
		
		public Handler approveReimbursement = (ctx) -> {
			if (ctx.req.getSession(false) != null) {
				try {
					String id = ctx.pathParam("reimbursementint");
					System.out.println(id+"--------------------------------------------------------------------------------------------------------------------------------------------------");
					int reimbursementint = Integer.parseInt(id);
					if (reimbursementService.approveStatus(reimbursementint)) {
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
		app.put("/reimbursementsapprove/:reimbursementint", this.approveReimbursement);
	}
	

}
