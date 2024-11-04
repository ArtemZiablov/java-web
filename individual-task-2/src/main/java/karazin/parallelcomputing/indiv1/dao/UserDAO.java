package karazin.parallelcomputing.indiv1.dao;

import karazin.parallelcomputing.indiv1.model.User;

import java.util.List;

public interface UserDAO {
    void createUser(User user) throws Exception;
    User getUserByUsername(String username) throws Exception;
    List<User> getAllUsers() throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUser(int userId) throws Exception;
}