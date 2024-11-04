package karazin.parallelcomputing.indiv1.dao;


import karazin.parallelcomputing.indiv1.model.User;
import karazin.parallelcomputing.indiv1.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public void createUser(User user) throws Exception {
        logger.info("Creating user: {}", user.getUsername());
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("User {} successfully created.", user.getUsername());
            } else {
                logger.warn("User {} was not created.", user.getUsername());
            }
        } catch (SQLException e) {
            logger.error("Error creating user {}", user.getUsername(), e);
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        logger.info("Fetching user by username: {}", username);
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    logger.info("User {} found with ID {}", username, user.getId());
                    return user;
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching user {}", username, e);
            throw e;
        }
        logger.warn("User {} not found", username);
        return null;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        logger.info("Fetching all users");
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            logger.info("Total users fetched: {}", users.size());
        } catch (SQLException e) {
            logger.error("Error fetching all users", e);
            throw e;
        }
        return users;
    }

    @Override
    public void updateUser(User user) throws Exception {
        logger.info("Updating user: {}", user.getUsername());
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("User {} successfully updated.", user.getUsername());
            } else {
                logger.warn("User {} was not updated.", user.getUsername());
            }
        } catch (SQLException e) {
            logger.error("Error updating user {}", user.getUsername(), e);
            throw e;
        }
    }

    @Override
    public void deleteUser(int userId) throws Exception {
        logger.info("Deleting user with ID: {}", userId);
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("User with ID {} successfully deleted.", userId);
            } else {
                logger.warn("User with ID {} was not deleted.", userId);
            }
        } catch (SQLException e) {
            logger.error("Error deleting user with ID {}", userId, e);
            throw e;
        }
    }
}