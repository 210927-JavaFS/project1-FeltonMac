package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	List<User> findAll();
    User findById(int id);
    boolean addUser(User user);// boolean made just voids for now 
	void insertUser(User user);
	void deleteUser(User user);
}
