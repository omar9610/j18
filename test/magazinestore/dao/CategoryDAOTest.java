package magazinestore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import magazinestore.entity.Categoria;

public class CategoryDAOTest {
    
    private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
        categoryDAO = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreateCategoria() {
		Categoria newCat = new Categoria("Java");
		Categoria category = categoryDAO.create(newCat);
		
		assertTrue(category != null && category.getCategoriaId() > 0);
	}

	@Test
	public void testUpdateCategoria() {
		Categoria cat = new Categoria("Java Core");
		cat.setCategoriaId(1);
		
		Categoria category = categoryDAO.update(cat);
		
		assertEquals(cat.getName(),  category.getName());
		
	}

	@Test
	public void testGet() {
		Integer catId = 8;
		Categoria cat = categoryDAO.get(catId);
		
		assertNotNull(cat);
	}

	@Test
	public void testDeleteObject() {
		Integer catId = 3;
		categoryDAO.delete(catId);
		
		Categoria cat = categoryDAO.get(catId);
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Categoria> listCategory = categoryDAO.listAll();
		for (Categoria categoria : listCategory) {
			System.out.println(categoria.getName() + " ,");
		}
		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long totalCategory = categoryDAO.count();
		
		assertEquals(5, totalCategory);
	}
	
	@Test
	public void testFindByName() {
		String name = "Java Core";
		Categoria categoria = categoryDAO.findByName(name);
		
		assertNotNull(categoria);
	}
	
	@Test
	public void testFindByNameNotFound() {
		String name = "Java Core1";
		Categoria categoria = categoryDAO.findByName(name);
		
		assertNull(categoria);
	}

}
