package cz.jiripinkas.jba.service;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.entity.Role;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.repositories.BlogRepository;
import cz.jiripinkas.jba.repositories.ItemRepository;
import cz.jiripinkas.jba.repositories.RoleRepository;
import cz.jiripinkas.jba.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public User findOneWithBlogs (Integer id) {
        User user = findOne(id);
        List<Blog> blogs = blogRepository.findByUser(user);
        for (Blog blog : blogs) {
            List<Item> items = itemRepository.findByBlog(blog,
                    new PageRequest(0, 10, Sort.Direction.ASC, "publishedDate"));
            /*items = itemRepository.findByBlog(blog);*/
            blog.setItems(items);

        }
        user.setBlogs(blogs);
        return user;
    }

    public void save(User user) {
        user.setEnabled(true);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User findOneWithBlogs(String name) {
        User user = userRepository.findByName(name);
        return findOneWithBlogs(user.getId());
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public User findOne(String userName) {
        return userRepository.findByName(userName);
    }
}
