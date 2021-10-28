package com.revature.controllers;

import com.revature.models.LoginUser;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LoginUserController implements Controller {
	private UserService userService = new UserService();

	private Handler loginAttempt = (ctx) -> {
		LoginUser loguser = ctx.bodyAsClass(LoginUser.class);
		String uname= loguser.getUsername();
		String pass= loguser.getPassword();
		if(userService.authenticate(uname,pass)) {
			//If someone logs in I will create a session
			ctx.req.getSession(); //This will create a session for us to track the client that logged in. 
			
			ctx.status(200);
		}else {
			ctx.req.getSession().invalidate();// invalidates any open session tracking the client.
			ctx.status(401);
		}
	};
	@Override
	public void addRouts(Javalin app) {
		app.post("/login", this.loginAttempt);
	}

}
