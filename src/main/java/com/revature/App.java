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
		//setDATATABLE();
		app = Javalin.create((config)->{
			config.addStaticFiles("/static", Location.CLASSPATH);
		});
		
		configure( new UserController(),new ReimbursementController(),new LoginUserController());
		
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
		 Role firstrole = new Role(0,"admin");
		 Role secondrole = new Role(1,"employee");
		 Type type1 = new Type(0,"lodging");
		 Type type2 = new Type(1,"travel");
		 String timestring;
		 String timestring2;
		 Status status= new Status(0,"pending");
		 Status status2= new Status(1,"approved");
//		
			User u = new User(0,"oneone","oneone".hashCode(),"kirby","the star warrior", "starpower@gmail.com",
					   null);
			User u2 = new User(1,"twotwo","twotwo".hashCode(),"metaknight","fallen star warrior", "spinandslice@gmail.com",
					   null);
			Reimbursement r = new Reimbursement(0,200.00, timestring=LocalDateTime.now().toString() , timestring2 =LocalDateTime.now().toString(),
					"first reimb",u,u2,new Status("pending"),new Type(0,"lodging"));
			Reimbursement r2 = new Reimbursement(1,300.00,timestring=LocalDateTime.now().toString() , timestring2=LocalDateTime.now().toString() ,
					"second reimb",u,u2,new Status("pending"),new Type(0,"lodging"));
			//List<Reimbursement> list=new ArrayList<Reimbursement>();
			//list.add(r);
			u.setRole(firstrole);
			u.setRole(secondrole);
			//u.setReimbursements(list);
			//u2.setReimbursements(list);
			us.insertUser(u);
			us.insertUser(u2);
			rs.insertReimbursement(r);
			rs.insertReimbursement(r2);
		
		}
//	this.re_id = re_id;
//	this.amount = amount;
//	this.submitted = submitted;
//	this.resolved = resolved;
//	this.description = description;
//	this.author = author;
//	this.resolver = resolver;
//	this.status = status;
//	this.type = type;
//	 {
//	        "ersUsername": "mjon",
//	        "ersPassword": "password",
//	        "ersFirstName": "Matt",
//	        "ersLastName": "Jordan",
//	        "ersEmail": "m@mjordan",
//	        "ersUserRole": {
//	            "userRoleId": 1,
//	            "userRole": "ADMIN"
//	        }
//	    }
//
//	JSON to insert Reimbursement
//	{
//	        "reimbAmount": "124.0",
//	        "reimbSubmitted": "0",
//	        "reimbResolved": null,
//	        "reimbReceipt": null,
//	        "reimbAuthor": {
//	            "ersUsersId": "1",
//	            "ersUsername": "mjordan",
//	            "ersPassword": "password",
//	            "ersFirstName": "Matt",
//	            "ersLastName": "Jordan",
//	            "ersEmail": "mjordan@mjordan",
//	            "ersUserRole": {
//	                "userRoleId": "1",
//	                "userRole": "ADMIN"
//	            }
//	        },
//	        "reimbStatusId": {
//	            "reimbStatus": "PENDING",
//	            "reimbStatusId": "2"
//	        },
//	        "reimbTypeId": {
//	            "reimbType": "LODGING",
//	            "reimbTypeId": "1"
//	        }
//	    }
}
