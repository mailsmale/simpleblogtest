package cz.jiripinkas.jba.controller;

import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign_up")
public class RegisterController {

    @Autowired
    private UserService userService;


    @ModelAttribute("user")
    public User construct() {
        return new User();
    }

    @RequestMapping
    public String signUp() {
        return "sign_up";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String doSignUp(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign_up";
        }
        userService.save(user);
        return "redirect:/sign_up.html?success=true";
    }

    @RequestMapping("/available")
    @ResponseBody
    public String userIsAvailable(@RequestParam String username){
        Boolean userNameIsAvailableForRegistration =
                userService.findOne(username) == null;
        return userNameIsAvailableForRegistration.toString();
    }

}
