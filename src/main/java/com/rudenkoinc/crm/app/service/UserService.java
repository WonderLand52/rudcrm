package com.rudenkoinc.crm.app.service;

import com.rudenkoinc.crm.app.dao.UserRepository;
import com.rudenkoinc.crm.app.model.Order;
import com.rudenkoinc.crm.app.model.User;
//import org.apache.log4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

import static com.rudenkoinc.crm.app.service.ValidationUtils.*;

/**
 * @author Illia Rudenko
 */

@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private static final Pattern PASSWORD_REGEX = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * creates a new user in the database
     *
     * @param firstName - the firstName of the new user
     * @param email - the user email
     * @param password - the user plain text password
     */
    @Transactional
    public void createUser(String firstName, String secondName, String email, String password) {

        if(true) {
            throw new IllegalArgumentException("hello exception");
        }
        System.out.println(">>>>> email: " + email);
        System.out.println(">>>>> firstName: " + firstName);

        assertNotBlank(firstName, "FirstName cannot be empty.");
        assertNotBlank(secondName, "SecondName cannot be empty.");
        assertMinimumLength(firstName, 6, "Username must have at least 6 characters.");
        assertNotBlank(email, "Email cannot be empty.");
        assertMatches(email, EMAIL_REGEX, "Invalid email.");
        assertNotBlank(password, "Password cannot be empty.");
        assertMatches(password, PASSWORD_REGEX, "Password must have at least 6 characters, with 1 numeric and 1 uppercase character.");

        if (!userRepository.isEmailAvailable(email)) {
            throw new IllegalArgumentException("This email is not available.");
        }

        User user = new User(firstName, secondName, new BCryptPasswordEncoder().encode(password), email);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
