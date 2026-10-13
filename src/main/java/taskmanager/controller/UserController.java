package taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import taskmanager.model.User;
import taskmanager.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user,
                           BindingResult bindingResult) {
        userService.validatePassword(user, bindingResult);

        if (bindingResult.hasErrors() || !userService.register(user, bindingResult)) {
            return "register";
        }

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

//    @PostMapping("/login")
//    public String login(
//            @Valid @ModelAttribute("user") User user,
//            BindingResult bindingResult,
//            Model model) {
//
//        if (bindingResult.hasErrors() || !userService.login(user.getUsername(), user.getPassword(), model)) {
//            bindingResult.reject(
//                    "login.invalid",
//                    "Invalid username or password"
//            );
//            return "login";
//        }
//
//        return "redirect:/tasks";
//    }
}
