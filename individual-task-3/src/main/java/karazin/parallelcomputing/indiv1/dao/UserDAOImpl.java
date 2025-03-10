package karazin.parallelcomputing.indiv1.dao;

import karazin.parallelcomputing.indiv1.model.User;
import karazin.parallelcomputing.indiv1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public void createUser(User user) throws Exception {
        logger.info("Creating user: {}", user.getUsername());
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            logger.info("User {} successfully created with id={}", user.getUsername(), user.getId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error creating user {}", user.getUsername(), e);
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        logger.info("Fetching user by username: {}", username);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL: "FROM User u WHERE u.username = :uname"
            return session.createQuery(
                            "FROM User u WHERE u.username = :uname", User.class
                    )
                    .setParameter("uname", username)
                    .uniqueResult();
        } catch (Exception e) {
            logger.error("Error fetching user {}", username, e);
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        logger.info("Fetching all users");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            logger.error("Error fetching all users", e);
            throw e;
        }
    }

    @Override
    public void updateUser(User user) throws Exception {
        logger.info("Updating user: {}", user.getUsername());
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(user); // або merge(user)
            tx.commit();
            logger.info("User {} successfully updated (ID={})", user.getUsername(), user.getId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error updating user {}", user.getUsername(), e);
            throw e;
        }
    }

    @Override
    public void deleteUser(int userId) throws Exception {
        logger.info("Deleting user with ID: {}", userId);
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
                logger.info("User with ID {} successfully deleted.", userId);
            } else {
                logger.warn("User with ID {} not found for deletion.", userId);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Error deleting user with ID {}", userId, e);
            throw e;
        }
    }
}
