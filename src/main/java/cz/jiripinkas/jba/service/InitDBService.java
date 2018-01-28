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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class InitDBService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogService blogService;

    @PostConstruct
    public void init(){
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        User userAdmin = new User();
        userAdmin.setName("admin");
        userAdmin.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userAdmin.setPassword(encoder.encode("admin"));
        List<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);
        roles.add(roleUser);
        userAdmin.setRoles(roles);
        userRepository.save(userAdmin);

/*        Blog blogJava = new Blog();
        blogJava.setName("jdbc");
        blogJava.setUrl("http://www.springframework.org/schema/jdbc");
        blogJava.setUser(userAdmin);
        blogRepository.save(blogJava);*/

        Blog blogJavaVids = new Blog();
        blogJavaVids.setName("javavids");
        blogJavaVids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
        blogJavaVids.setUser(userAdmin);
        blogRepository.save(blogJavaVids);
        blogService.saveItems(blogJavaVids);


        /*Item item1 = new Item();
        item1.setBlog(blogJava);
        item1.setTitle("first");
        item1.setLink("http://www.springframework.org/schema/jdbc");
        item1.setPublishedDate(new Date());
        itemRepository.save(item1);

        Item item2 = new Item();
        item2.setBlog(blogJava);
        item2.setTitle("second");
        item2.setLink("http://www.springframework.org/schema/jdbc");
        item2.setPublishedDate(new Date());
        itemRepository.save(item2);

        Item item3 = new Item();
        item3.setBlog(blogJava);
        item3.setTitle("third");
        item3.setLink("http://www.springframework.org/schema/jdbc");
        item3.setPublishedDate(new Date());
        itemRepository.save(item3);

        Item item4 = new Item();
        item4.setBlog(blogJava);
        item4.setTitle("forth");
        item4.setLink("http://www.springframework.org/schema/jdbc");
        item4.setPublishedDate(new Date());
        itemRepository.save(item4);

        Item item5 = new Item();
        item5.setBlog(blogJava);
        item5.setTitle("fifth");
        item5.setLink("http://www.springframework.org/schema/jdbc");
        item5.setPublishedDate(new Date());
        itemRepository.save(item5);*/
    }
}
