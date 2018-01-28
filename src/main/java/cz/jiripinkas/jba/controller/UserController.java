package cz.jiripinkas.jba.controller;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Role;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.repositories.RoleRepository;
import cz.jiripinkas.jba.service.BlogService;
import cz.jiripinkas.jba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @ModelAttribute("user")
    public User construct(){
        return new User();
    }

    @ModelAttribute("blog")
    public Blog constructBlog(){
        return new Blog();
    }


    @RequestMapping(value = "/account")
    public String account(Model model, Principal principal){
        String name = principal.getName();
        model.addAttribute("user", userService.findOneWithBlogs(name));
        return "account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String doAddBlog(Model model, @Valid @ModelAttribute("blog") Blog blog, BindingResult result, Principal principal){
        if (result.hasErrors()){
            return account(model, principal);
        }
        String userName = principal.getName();
        blogService.save(blog, userName);
        return "redirect:/account.html";
    }

    @RequestMapping(value = "/blog/remove/{id}")
    public String removeBlog(@PathVariable Integer id){
        Blog blog = blogService.findOne(id);
        blogService.delete(blog);
        return "redirect:/account.html";
    }



}
