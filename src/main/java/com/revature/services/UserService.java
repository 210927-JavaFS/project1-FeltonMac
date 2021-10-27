package com.revature.services;

import java.util.List;

import org.hibernate.Session;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImp;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserService {
	UserDAO UDAO = new UserDAOImp();

	public List<User> getAllUsers(){
		return UDAO.findAll();
	}
    public User findById(int id) {
    	User user = UDAO.findById(id);
    	if(user!=null) {
    		return user;
    	}else {
    		return new User();
    	}
    }
    public boolean addUser(User user){
    	return UDAO.addUser(user);
    }
	public boolean insertUser(User user) {
		return UDAO.insertUser(user);
	}
	public boolean deleteUser(User user) {
		return UDAO.deleteUser(user);
	}
	public boolean updateUser(User user) {
		return UDAO.updateUser(user);
	}
	
	public boolean authenticate(String username,String password) {
		try{
			User user = UDAO.findByUsername(username);
			if (user.getPassword()==(password.hashCode())) {
				return true;
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println(" no user found ");
			return false;
		}
		return false;
	}
	
	
	
	
	

//	public User authenticate(String username, String password ) {
//		if(UDAO.authenticate(username,password)) {
//		User loggedUser = UDAO.authenticate(username,password);
//		if (loggedUser!=null) {
//			System.out.println("authinetication complete ");
//		
//		if(loggedUser.hashCode(getPassword()equals(password)){
//			return loggedUser;
//		}
//		
//		return null;
//}
//}
		/*
		 * What we have stored in the database is the Username + Password hash.
		 * We can't compare the blank password provided by the user, against the hash.
		 * So we have to obtain the hash of the user input.
		 * 
		 * If the hashes are the same, user is authenticated.
		 */

}
