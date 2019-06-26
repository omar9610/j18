package magazinestore.dao;

import magazinestore.dao.UserDAO;
import magazinestore.entity.Utenti;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

public class UserDAOTest{

    private static UserDAO userDAO;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userDAO = new UserDAO();
    }

    /**Create user's tests*/
    @Test
    public void testCreateUser() {
        Utenti utente1 = new Utenti();
        utente1.setEmail("or@gmail.it");
        utente1.setFullName("r");
        utente1.setPassword("1234");

        utente1 = userDAO.create(utente1);
        assertTrue(utente1.getUserId() > 0);
    }

    /*Case there is not fields*/
    @Test(expected = PersistenceException.class)
    public void testCreateUsersFieldsNotSet() {
        Utenti utente1 = new Utenti();
        utente1 = userDAO.create(utente1);
    }
    /**Update user's tests*/
    @Test
    public void testUpdateUser() {
        Utenti utente = new Utenti();
        utente.setUserId(9);
        utente.setEmail("BBB@gmail.com");
        utente.setFullName("BBB");
        utente.setPassword("dsff");

        utente = userDAO.update(utente);
        String expected = "dsff";
        String actual = utente.getPassword();
        assertEquals(expected, actual);
    }


    @Test
    public void testGetUsersFound() {
        Integer userId = 9;
        Utenti utente = userDAO.get(userId);

        assertNotNull(utente);
        System.out.println(utente.getEmail());
    }

    @Test
    public void testGetUserNotFound() {
        Integer userId = 99;
        Utenti utente =userDAO.get(userId);
        assertNull(utente);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 10;
        userDAO.delete(userId);
        Utenti utente = userDAO.get(userId);

        assertNull(utente);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeleteNonExistUsers() {
        Integer userId = 55;
        userDAO.delete(userId);
    }

    @Test
    public void testListAll() {
        List<Utenti> listUsers = userDAO.listAll();

        for (Utenti utente : listUsers) {
            System.out.println(utente.getEmail());
        }
        assertTrue(listUsers.size() > 0);
    }

    @Test
    public void testCount() {
        long totalUsers = userDAO.count();
        assertEquals(9,totalUsers);
    }

    @Test
    public void testCheckLoginSuccess() {
    	String email = "alaeldin5595@gmail.com";
    	String password = "qqqqqq";
    	boolean loginResult = userDAO.checkLogin(email, password);
    	
    	assertTrue(loginResult);
    }
    
    @Test
    public void testCheckLoginFailed() {
    	String email = "alaeldi@gmail.com";
    	String password = "sfsdf";
    	boolean loginResult = userDAO.checkLogin(email, password);
    	
    	assertFalse(loginResult);
    }
    
    @Test
    public void testFindByEmail() {
    	String email = "gad@gmail.com";
    	Utenti user = userDAO.findByEmail(email);
    	
    	assertNotNull(user);
    }
    
    @AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}