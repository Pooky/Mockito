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
	 * @param bornDate
	 */
	public void calculateAndSaveUserAge(Long userId, LocalDate bornDate) {
		
		Integer age;
		// If generation X use age is 20
		if(bornDate.isAfter(LocalDate.of(2000, 12, 12))) {
			age = 20;
		}else {
			age = 99;
		}
		
		// Get user and save it
		User user = dao.getUserById(userId);
		// set age
		user.setAge(age);
		// save user
		saveUser(user);
		
	}
	

}
