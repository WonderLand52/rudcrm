package com.rudenkoinc.crm.app.dao;

import com.rudenkoinc.crm.app.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * finds a user given its email
     *
     * @param email - the username of the searched user
     * @return  a matching user, or null if no user found.
     */
    public User findUserByEmail(String email) {

        List<User> users = em.createNamedQuery(User.FIND_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getResultList();

        return users.size() == 1 ? users.get(0) : null;
    }

    /**
     *
     * save changes made to a user, or insert it if its new
     *
     * @param user
     */
    public void save(User user) {
        em.merge(user);
    }

    /**
     * checks if a username is still available in the database
     *
     * @param email - the username to be checked for availability
     * @return true if the username is still available
     */
    public boolean isEmailAvailable(String email) {

        List<User> users = em.createNamedQuery(User.FIND_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getResultList();

        return users.isEmpty();
    }

}
