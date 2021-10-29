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

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {
	
	private static Javalin app;
	public static void main(String[] args) {
		setDATATABLE();
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
//		this.re_id = re_id;
//		this.amount = amount;
//		this.submitted = submitted;
//		this.resolved = resolved;
//		this.description = description;
//		this.author = author;
//		this.resolver = resolver;
//		this.status = status;
//		this.type = type;
		Reimbursement r = new Reimbursement(200.00, LocalDateTime.now(), LocalDateTime.now(),
				"first reimb",null,null,new Status("pending"),new Type("lodging"));
		List<Reimbursement> list=new ArrayList<Reimbursement>();
		list.add(r);
			User u = new User("oneone","oneone".hashCode(),"kirby","the star warrior", "starpower@gmail.com",
					  list,new Role("admin"));
		}
	
}
