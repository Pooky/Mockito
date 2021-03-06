package org.examples.java.mockito.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.examples.java.mockito.HibernateUtils;
import org.examples.java.mockito.entity.User;

/**
 * User data access object
 *
 */
public class UserDao implements IUserDao {

	/**
	 * Entity Manager instance 
	 */
	private EntityManager em;
	
	/**
	 * It can be also injected by container but in this case we use constructor to get entity manager
	 * Otherwise it could looks like @Inject EntityManager em
	 */
	public UserDao() {
		em = HibernateUtils.getEntityManager();
	}
	
	/**
	 * Save user to DB
	 * @param user
	 */
	public void saveUser(User user) {
	
		// Begin transaction
		em.getTransaction().begin();

		em.persist(user);

		// Commit transaction
		em.getTransaction().commit();
		
	}
	
	/**
	 * Get users as list
	 * @return
	 */
	public List<User> getUsers(){
		
		Query query = em.createQuery("From User", User.class);
		
		return query.getResultList();
	}

	/**
	 * Get user by ID
	 */
	public User getUserById(Long userId) {

		Query query = em.createQuery("From User where id = :id", User.class);
		query.setParameter("id", userId);
		
		return (User) query.getSingleResult();
		
	}
	
	
	
}
