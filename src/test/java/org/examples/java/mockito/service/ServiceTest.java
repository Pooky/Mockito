package org.examples.java.mockito.service;

import java.nio.channels.AsynchronousCloseException;
import java.time.LocalDate;

import org.examples.java.mockito.dao.UserDao;
import org.examples.java.mockito.entity.User;
import org.examples.java.service.IUserService;
import org.examples.java.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock 
	private UserDao dao;
	
	@Test
	public void testUserAgeCalculation() {
	
		
		User user = new User();
		user.setEmail("email");
		user.setName("Martin");
		user.setUserId(15L);
		
		// Mock dao and return user when the user function is called
		when(dao.getUserById(15L)).thenReturn(user);
		
		service.calculateAndSaveUserAge(15L, LocalDate.of(1999, 12, 12));
		
		// Catch dao argument
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
		
		
		// Verify call
		verify(dao, times(1)).getUserById(15L);
		verify(dao, times(1)).saveUser(userCaptor.capture());
		
		// Compare user captured and user which we inserted
		assertEquals(user,  userCaptor.getValue());
		
		// Check age we have it should be 99
		assertEquals(Integer.valueOf(99), user.getAge());
		
		
		
	}

}
