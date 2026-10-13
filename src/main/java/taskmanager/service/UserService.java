package taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import taskmanager.model.User;
import taskmanager.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(User user, BindingResult bindingResult) {
        boolean registered = registerUser(user);

        if (!registered) {
            bindingResult.rejectValue( "username", "username.exists", "Username already exists" );
            return false;
        }

        return true;
    }

    public boolean login(String username, String password, Model model) {
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent() || !user.get().getPassword().equals(password)) {
            model.addAttribute("loginError", "Invalid username or password");
            return false;
        }

        return true;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void validatePassword(User user, BindingResult bindingResult) {
        if (!user.getPassword().isEmpty()
                && user.getPassword().length() < 6) {
            bindingResult.rejectValue("password", "password.length", "Password must contain at least 6 characters");
        }
    }

    private boolean registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }
}
