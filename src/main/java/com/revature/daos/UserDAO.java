package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	List<User> findAll();
    User findById(int id);
    boolean addUser(User user);// boolean made just voids for now 
	boolean insertUser(User user);
	boolean deleteUser(User user);
	boolean updateUser(User user);
	public boolean authenticate(String username,String Password);
}
