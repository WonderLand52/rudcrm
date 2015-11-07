package com.rudenkoinc.crm.app.service;

import com.rudenkoinc.crm.app.dao.UserRepository;
import com.rudenkoinc.crm.app.model.User;
import com.rudenkoinc.crm.app.shared.dto.NewUserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

import static com.rudenkoinc.crm.app.service.ValidationUtils.*;

@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private static final Pattern PASSWORD_REGEX = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * create new user
     *
     */
    @Transactional
    public void createUser(NewUserDTO userDTO) {
        assertUserDetails(userDTO);
        
        String firstName = userDTO.getFirstName();
        String secondName = userDTO.getSecondName();
        String email = userDTO.getEmail();
        String password = userDTO.getPlainTextPassword();

        checkEmailAvailability(email);

        User user = new User(firstName, secondName, passwordEncoder.encode(password), email);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private void assertUserDetails(NewUserDTO userDTO) {
        assertNotBlank(userDTO.getFirstName(), "FirstName cannot be empty.");
        assertNotBlank(userDTO.getSecondName(), "SecondName cannot be empty.");
        assertNotBlank(userDTO.getEmail(), "Email cannot be empty.");
        assertMatches(userDTO.getEmail(), EMAIL_REGEX, "Invalid email.");
        assertNotBlank(userDTO.getPlainTextPassword(), "Password cannot be empty.");
        assertMatches(userDTO.getPlainTextPassword(), PASSWORD_REGEX, "Password must have at least 6 characters, with 1 numeric and 1 uppercase character.");
    }

    private void checkEmailAvailability(String email) {
        if (!userRepository.isEmailAvailable(email)) {
            throw new IllegalArgumentException("This email is not available.");
        }
    }
}
