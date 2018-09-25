package org.examples.java.service;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.examples.java.mockito.dao.IUserDao;
import org.examples.java.mockito.entity.User;

/**
 * User service
 */
public class UserService implements IUserService {

	@Inject
	private IUserDao dao;

	public List<User> getUsers() {
		return dao.getUsers();
	}
	
	public void saveUser(User user) {
		dao.saveUser(user);
	}
	
	/**
	 * Calculate and save user age
	 * @param user
	 * @param userAge
	 */
	public void calculateAndSaveUserAge(User user, LocalDate userAge) {
		
		
		
	}
	

}
