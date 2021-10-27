package com.revature;

import com.revature.controllers.Controller;
import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class App {
	private static Javalin app;
	public static void main(String[] args) {
		app = Javalin.create();
		
		configure( new UserController());
		
		app.start(8081);
		
		
	}
	
	private static void configure(Controller... controllers) {
		for (Controller c: controllers) {
			c.addRouts(app);
		}
	}
}
