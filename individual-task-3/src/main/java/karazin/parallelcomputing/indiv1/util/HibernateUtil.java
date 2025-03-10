package karazin.parallelcomputing.indiv1.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Створення SessionFactory за допомогою конфігураційного файлу
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Помилка створення SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
