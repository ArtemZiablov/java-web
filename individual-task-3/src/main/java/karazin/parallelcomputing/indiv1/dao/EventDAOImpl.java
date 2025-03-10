package karazin.parallelcomputing.indiv1.dao;

import karazin.parallelcomputing.indiv1.model.Event;
import karazin.parallelcomputing.indiv1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EventDAOImpl implements EventDAO {

    @Override
    public void createEvent(Event event) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(event); // Зберігаємо сутність
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public Event getEventById(int eventId) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Event.class, eventId);
        }
    }

    @Override
    public List<Event> getEventsByUsername(String username) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Event e WHERE e.user.username = :uname",
                            Event.class
                    )
                    .setParameter("uname", username)
                    .list();
        }
    }

    @Override
    public List<Event> getEventsByDate(String date) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Event e WHERE e.date = :dateVal",
                            Event.class
                    )
                    .setParameter("dateVal", date)
                    .list();
        }
    }

    @Override
    public void updateEvent(Event event) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(event); // Оновлюємо сутність
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void deleteEvent(int eventId) throws Exception {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Event existingEvent = session.get(Event.class, eventId);
            if (existingEvent != null) {
                session.delete(existingEvent);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
