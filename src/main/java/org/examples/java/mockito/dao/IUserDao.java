package org.examples.java.mockito.dao;

import java.util.List;

import org.examples.java.mockito.entity.User;

public interface IUserDao {

	/**
	 * Save user to DB
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * Get users as list
	 * @return
	 */
	List<User> getUsers();

	User getUserById(Long userId);

}