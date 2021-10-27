package com.revature.controllers;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImp;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {
	private UserService userService = new UserService();
	
	public Handler getAllUsers = (ctx) ->{
		List<User> list = userService.getAllUsers();
		ctx.json(list);
		ctx.status(200);
	};
	
	
	
	
	@Override
	public void addRouts(Javalin app) {
		app.get("/users", this.getAllUsers);
	}

}
