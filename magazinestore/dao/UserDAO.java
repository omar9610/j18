package magazinestore.dao;

import magazinestore.entity.Utenti;

import javax.persistence.EntityManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO extends JpaDAO<Utenti> implements GenericDAO<Utenti> {
    public UserDAO() {
    }

    @Override
    public Utenti create(Utenti user) {
        return super.create(user);
    }

    @Override
    public Utenti update(Utenti user) {
        return super.update(user);
    }

    @Override
    public Utenti get(Object userId) {
        return super.find(Utenti.class, userId);
    }
    
    public Utenti findByEmail(String email) {
    	
    	List<Utenti> listUsers = super.findWithNamedQuery("Users.findByEmail", "email", email);
		if (listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		
		return null;
    }
    
    public boolean checkLogin(String email, String password) {
    	Map<String, Object> parameters = new HashMap<>();
    	parameters.put("email", email);
    	parameters.put("password", password);
    	
    	List<Utenti> listUsers = super.findWithNamedQuery("Users.checkLogin", parameters);
    	
    	if (listUsers.size() == 1) {
			return true;
		}
    	return false;
    }

    @Override
    public void delete(Object userId) {
        super.delete(Utenti.class, userId);
    }

    @Override
    public List<Utenti> listAll() {
        return super.findWithNamedQuery("Users.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Users.countAll");
    }
    
}
