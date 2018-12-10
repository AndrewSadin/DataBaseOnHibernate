import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBOnHibernate {

    private static Logger log = LoggerFactory.getLogger(DBOnHibernate.class);

    public static void main(String args[]){
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        try {
            fillingDB(sessionFactory);
            selectAllQuery(sessionFactory);
            updateContactQuery(sessionFactory);
            deleteContactQuery(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    public static void fillingDB(SessionFactory sessionFactory){
        City city1 = new City("Ivanovo");
        City city2 = new City("Moscow");
        City city3 = new City("Kineshma");

        Contact contact1 = new Contact("Ivan Ivanov", "+79102345678", city1);
        Contact contact2 = new Contact("Petr Petrov", "+79209876543", city2);
        log.info("add cites and contacts");
        log.info(city1 + "\n" + city2 + "\n" + city3);
        log.info(contact1 + "\n" + contact2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(city1);
        session.save(city2);
        session.save(city3);
        session.save(contact1);
        session.save(contact2);
        session.getTransaction().commit();
    }

    private static void selectAllQuery(SessionFactory sessionFactory) {
        log.info("loading all contacts");
        Session session = sessionFactory.openSession();
        List<Contact> allcontact = session.createQuery("FROM Contact").list();
        allcontact.forEach((x) -> log.info("" + x));
        session.close();
    }

    private static void updateContactQuery(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        log.info("update second Contact");
        session.beginTransaction();
        Contact contact = session.load(Contact.class, 5);
        City city = session.load(City.class, 3);
        contact.setCity(city);
        session.update(contact);
        List<Contact> allcontact = session.createQuery("FROM Contact").list();
        allcontact.forEach((x) -> log.info("" + x));
        session.close();
    }

    private static void deleteContactQuery(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        log.info("delete first Contact");
        session.beginTransaction();
        Contact contact = session.load(Contact.class, 4);
        session.delete(contact);
        List<Contact> allcontact = session.createQuery("FROM Contact").list();
        allcontact.forEach((x) -> log.info("" + x));
        session.close();
    }
}
