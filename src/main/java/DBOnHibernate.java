import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DBOnHibernate {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("thePersistenceUnit");

    public static void main(String args[]){
        try {
            fillingDB();
            executeQuery();
        } finally {
            entityManagerFactory.close();
        }
    }

    public static void fillingDB(){
        City city1 = new City("Ivanovo");
        City city2 = new City("Moscow");

        Contact contact1 = new Contact("Ivan Ivanov", "+79102345678", city1.getCityId());
        Contact contact2 = new Contact("Petr Petrov", "+79209876543", city2.getCityId());

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        System.out.println(contact1);
        System.out.println(city1);
        em.persist(contact1);
        em.persist(contact2);
        em.getTransaction().commit();
        em.close();
        System.out.println("-- Contact persisted --");
        System.out.println(contact1);
        System.out.println(contact2);
    }

    private static void executeQuery() {
        System.out.println("-- executing query --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT DISTINCT e FROM Contact e INNER JOIN e.city t");
        List<Contact> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        em.close();
    }
}
