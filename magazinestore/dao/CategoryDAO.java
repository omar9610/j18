package magazinestore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import magazinestore.entity.Categoria;
import magazinestore.entity.Utenti;

public class CategoryDAO extends JpaDAO<Categoria> implements GenericDAO<Categoria> {

	public CategoryDAO() {
	}

	@Override
	public Categoria create(Categoria category) {
		return super.create(category);
	}

	@Override
	public Categoria update(Categoria category) {
		return super.update(category);
	}

	@Override
	public Categoria get(Object id) {
		return super.find(Categoria.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Categoria.class, id);
	}

	@Override
	public List<Categoria> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}
	
	public Categoria findByName(String name) {
		List<Categoria> result = super.findWithNamedQuery("Category.findByName", "name",name);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

}
