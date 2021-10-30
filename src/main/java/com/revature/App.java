package com.revature;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.controllers.Controller;
import com.revature.controllers.LoginUserController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {
	 static UserService us= new UserService();
	 static ReimbursementService rs = new ReimbursementService();
	private static Javalin app;
	public static void main(String[] args) {
		User u = new User(1,"oneone","oneone".hashCode(),"kirby","the star warrior", "starpower@gmail.com",
				  null,new Role("admin"));
		User u2 = new User(2,"twotwo","twotwo".hashCode(),"metaknight","fallen star warrior", "spinandslice@gmail.com",
				  null,new Role("admin"));
		Reimbursement r = new Reimbursement(1,200.00, LocalDateTime.now(), LocalDateTime.now(),
				"first reimb",u,u2,new Status("pending"),new Type("lodging"));
		Reimbursement r2 = new Reimbursement(2,300.00, LocalDateTime.now(), LocalDateTime.now(),
				"second reimb",u,u2,new Status("pending"),new Type("lodging"));
		List<Reimbursement> list=new ArrayList<Reimbursement>();
		list.add(r);
		u.setReimbursements(list);
		u2.setReimbursements(list);
		us.insertUser(u);
		us.insertUser(u2);
		rs.insertReimbursement(r);
		rs.insertReimbursement(r2);
		//setDATATABLE();
		app = Javalin.create((config)->{
			config.addStaticFiles("/static", Location.CLASSPATH);
		});
		
		configure( new UserController(), new ReimbursementController(),new LoginUserController());
		
		app.start(8081);
		
		
	}
	
	private static void configure(Controller... controllers) {
		for (Controller c: controllers) {
			c.addRouts(app);
		}
	}
		
	public static void setDATATABLE() {
		 UserService us= new UserService();
		 ReimbursementService rs = new ReimbursementService();
//		this.re_id = re_id;
//		this.amount = amount;
//		this.submitted = submitted;
//		this.resolved = resolved;
//		this.description = description;
//		this.author = author;
//		this.resolver = resolver;
//		this.status = status;
//		this.type = type;
//		 {
//		        "ersUsername": "mjon",
//		        "ersPassword": "password",
//		        "ersFirstName": "Matt",
//		        "ersLastName": "Jordan",
//		        "ersEmail": "m@mjordan",
//		        "ersUserRole": {
//		            "userRoleId": 1,
//		            "userRole": "ADMIN"
//		        }
//		    }
//
//		JSON to insert Reimbursement
//		{
//		        "reimbAmount": "124.0",
//		        "reimbSubmitted": "0",
//		        "reimbResolved": null,
//		        "reimbReceipt": null,
//		        "reimbAuthor": {
//		            "ersUsersId": "1",
//		            "ersUsername": "mjordan",
//		            "ersPassword": "password",
//		            "ersFirstName": "Matt",
//		            "ersLastName": "Jordan",
//		            "ersEmail": "mjordan@mjordan",
//		            "ersUserRole": {
//		                "userRoleId": "1",
//		                "userRole": "ADMIN"
//		            }
//		        },
//		        "reimbStatusId": {
//		            "reimbStatus": "PENDING",
//		            "reimbStatusId": "2"
//		        },
//		        "reimbTypeId": {
//		            "reimbType": "LODGING",
//		            "reimbTypeId": "1"
//		        }
//		    }
		User u = new User("oneone","oneone".hashCode(),"kirby","the star warrior", "starpower@gmail.com",
				  null,new Role("admin"));
		User u2 = new User("twotwo","twotwo".hashCode(),"metaknight","fallen star warrior", "spinandslice@gmail.com",
				  null,new Role("admin"));
		Reimbursement r = new Reimbursement(1,200.00, LocalDateTime.now(), LocalDateTime.now(),
				"second reimb",u,u2,new Status("pending"),new Type("lodging"));
		Reimbursement r2 = new Reimbursement(2,300.00, LocalDateTime.now(), LocalDateTime.now(),
				"second reimb",u,u2,new Status("pending"),new Type("lodging"));
		List<Reimbursement> list=new ArrayList<Reimbursement>();
		list.add(r);
		u.setReimbursements(list);
		u2.setReimbursements(list);
		us.addUser(u);
		us.addUser(u2);
		
		}
	
}
