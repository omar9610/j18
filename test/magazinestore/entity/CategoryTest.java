package magazinestore.entity;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryTest {
    public static void main(String[] args) {
        Categoria categoriaEntity = new Categoria("more more Java");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Progetto-J18");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(categoriaEntity);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        System.out.println("A Category object was persisted");
    }
}
