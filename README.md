# Mockito

Mockito is framework which helps you to test and mock your objects.

Why you need to write tests
------------------------
When you test something you wish to know that the code which you wrote is correct. 

For that you need to write some tests and cover simple use cases (examples) where result should be what you assume when you wrote this code. In reality you can never test everything but basicaly the rule is that you should test atleast something general to see if it fail or not. 

The more complex logic the more tests you should write. To test some complex code is not trivial and for that here is very nice framework Mockito which should help you to do that nice and elegant. 

Description of Mockito Example
----------------------

In example bellow you can see that we wanna to test class **UserService**. 
Our service use DAO object (Data Access Object) to access and get information from database. In this case our service will find user according to the ID provided by parameter and then fetch the user from database, calculate age and update it. 

In this test we don't have access to database instance and we wanna to control, what is returned to the service. So we mock the **UserDao** object and when the method **getUserById** is called with our parameter we will return object, which we prepared before - the **User object**.

Afterwards we call service with parameter and verify that dao called method **getUserById** and **saveUser** exactly once. In the end we also capture argument of **saveUser** method and compare it to our user instance. 

Annotation **@InjectMocks** defines where to inject Mock objects defined by **@Mock** anotation. 


Example of mockito test
---------------------------
```java
@ExtendWith(MockitoExtension.class)
public class ServiceTest {
	
	@InjectMocks
	private UserService service;
	
	@Mock 
	private UserDao dao;
	
	@Test
	public void testUserAgeCalculation() {
	
		User user = new User();
		user.setEmail("mycool@mail.com");
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

	}

}
```



