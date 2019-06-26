package magazinestore.dao;

import static org.junit.Assert.*; 
import static org.junit.Assume.assumeTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import magazinestore.entity.Categoria;
import magazinestore.entity.Rivista;

public class RivistaDAOTest {
    private static RivistaDAO rivistaDao;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rivistaDao = new RivistaDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void testUpdateRivista() throws ParseException, IOException {
      Rivista existRivista = new Rivista();
      	existRivista.setRivistaId(1);
      	
		Categoria categoria= new Categoria("Programmazione");
		categoria.setCategoriaId(15);
		
		existRivista.setCategoria(categoria);
		existRivista.setTitle("Programmazione ad ogetti(2� edizione)");
		existRivista.setDescription("manuale java");
		existRivista.setPrice(60);
		
		DateFormat dateFormat= new SimpleDateFormat("DD/mm/yyyy");
		Date publishDate = dateFormat.parse("09/05/2019");
		existRivista.setPublishDate(publishDate);
		
		String imagePath="C:\\Users\\USER\\Desktop\\ProgettoJ18\\Progetto-J18\\Progetto-J18\\image\\java.jpg";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		existRivista.setImmagine(imageBytes);
		
		Rivista updatedRivista =rivistaDao.update(existRivista);
		assertEquals(updatedRivista.getTitle(),"Programmazione ad ogetti(2� edizione)");
		
	}

	@Test
	public void testCreateRivista() throws ParseException, IOException {
		Rivista newRivista = new Rivista();
		Categoria categoria= new Categoria("Programmazione");
		categoria.setCategoriaId(15);
		
		newRivista.setCategoria(categoria);
		newRivista.setTitle("Programmazione java");
		newRivista.setDescription("manuale java");
		newRivista.setPrice(40);
		
		DateFormat dateFormat= new SimpleDateFormat("DD/mm/yyyy");
		Date publishDate = dateFormat.parse("09/05/2019");
		newRivista.setPublishDate(publishDate);
		
		String imagePath="";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		newRivista.setImmagine(imageBytes);
		
		Rivista createdRivista =rivistaDao.create(newRivista);
		assertTrue(createdRivista.getRivistaId() > 0);
		
	}
	@Test
	public void testCreate2ndRivista() throws ParseException, IOException {
		
		Rivista newRivista = new Rivista();
		Categoria categoria= new Categoria("Sport");
		
		categoria.setCategoriaId(15);

		newRivista.setCategoria(categoria);
		newRivista.setTitle("Pallavolo INTERNAZIONALE");
		newRivista.setDescription("il gioco del pallavolo");
		newRivista.setPrice(30);
		
		DateFormat dateFormat= new SimpleDateFormat("dd/mm/yyyy");
		Date publishDate = dateFormat.parse("11/05/2019");
		newRivista.setPublishDate(publishDate);
		
		String imagePath="C:\\Users\\USER\\Desktop\\ProgettoJ18\\Progetto-J18\\Progetto-J18\\image\\pallavolo-supervolley.jpg";
		byte[] imageBytes=Files.readAllBytes(Paths.get(imagePath));
		newRivista.setImmagine(imageBytes);
		
		Rivista createdRivista =rivistaDao.create(newRivista);
		assertTrue(createdRivista.getRivistaId() > 0);
		
	}	

	@Test(expected = EntityNotFoundException.class)

	public void testDeleteRivistaFail() {
		Integer rivistaId=100;
		rivistaDao.delete(rivistaId);
		assertTrue(true);	
	}    
	@Test
	public void testDeleteRivistaSuccess() {
		Integer rivistaId=2;
		rivistaDao.delete(rivistaId);
		assertTrue(true);
		
	}

	@Test
    public void testGetRivistaFail() {
		
    	Integer rivistaId=99;
    	Rivista rivista = rivistaDao.get(rivistaId);
    	assertNull(rivista);
    }
	@Test
    public void testGetRivistaSuccess() {
		
    	Integer rivistaId=1;
    	Rivista rivista = rivistaDao.get(rivistaId);
    	assertNotNull(rivista);
    }
	
@Test 
	public void testListAll() {
	List<Rivista> listRivista =rivistaDao.listAll();
	for(Rivista aRivista : listRivista) {
		System.out.println(aRivista.getTitle()+ "- " + aRivista.getDescription());
	}
	assertFalse(listRivista.isEmpty());
	}

 @Test 
 	public void testFindByTitleNotExist() {
	 String title="Titolo scorretto";
	Rivista rivista = rivistaDao.findByTitle(title);
	assertNull(rivista);
 }
 @Test
    public void testFindByTitleExist() {
	 String title="CALCIO INTERNAZIONALE";
	Rivista rivista = rivistaDao.findByTitle(title);
	System.out.println(rivista.getPrice());
	assertNotNull(rivista);
	
	 
 }
@Test
	public void testCount() {
	long totalRiviste=rivistaDao.count();
	assertEquals(2,totalRiviste);
 }
/*
@Test
public void testListByCategory() {
int categoryId=1;

List<Rivista> listRiviste= rivistaDao.listByCategory(categoryId);

assertTrue(listRiviste.size() > 0);}*/
	

}
