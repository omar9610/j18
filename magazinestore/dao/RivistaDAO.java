package magazinestore.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import magazinestore.entity.Rivista;

public class RivistaDAO extends JpaDAO<Rivista> implements GenericDAO<Rivista> {

	public RivistaDAO() {
	}

	@Override
	public Rivista create(Rivista rivista) {
		
		rivista.setLastUpdtaedTime(new Date());
		return super.create(rivista);
	}

	@Override
	public Rivista update(Rivista rivista) {
	rivista.setLastUpdtaedTime(new Date());
		return super.update(rivista);
	}

	@Override
	public Rivista get(Object rivistaId) {
	
		return super.find(Rivista.class,rivistaId);
	}

	@Override
	public void delete(Object rivistaId) {
		
		super.delete(Rivista.class,rivistaId);
	}

	@Override
	public List<Rivista> listAll() {

		return super.findWithNamedQuery("Rivista.findAll");
	}
     
	public Rivista findByTitle(String title) {
		List <Rivista> result = super.findWithNamedQuery("Rivista.findByTitle", "title",title);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	@Override
	public long count() {
		
		return super.countWithNamedQuery("Rivista.countAll");
	}
	public List<Rivista> listNewRiviste (){
		return super.findWithNamedQuery("Rivista.listNew",0,4);
		
	}

	public List<Rivista> listByCategory(int categoryId) {
		return super.findWithNamedQuery("Rivista.findByCategory","catId",categoryId);
	}
	
	public List<Rivista> search(String keyword){
		return super.findWithNamedQuery("Rivista.search","keyword",keyword);
		
	}
	
	public long countByCategory(int categoryId) {
		return super.countWithNamedQuery("Rivista.countByCategory","catId",categoryId);
	}

}
