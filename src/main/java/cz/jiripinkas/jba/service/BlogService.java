package cz.jiripinkas.jba.service;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.exceptions.RssException;
import cz.jiripinkas.jba.repositories.BlogRepository;
import cz.jiripinkas.jba.repositories.ItemRepository;
import cz.jiripinkas.jba.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RssService rssService;

    @Autowired
    private ItemRepository itemRepository;

    public void saveItems(Blog blog) {
        try {
            List<Item> itemList = rssService.getItems(blog.getUrl());
            itemList.stream().forEach(item -> {
                Item existedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
                if (existedItem == null) {
                    item.setBlog(blog);
                    itemRepository.save(item);
                }

            });
        } catch (RssException e) {
            e.printStackTrace();
        }
    }


    public void save(Blog blog, String name) {
        User user = userRepository.findByName(name);
        blog.setUser(user);
        blogRepository.save(blog);
        saveItems(blog);
    }

    @PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
    public void delete(@P("blog") Blog blog) {
        blogRepository.delete(blog);
    }

    public Blog findOne(Integer id) {
        return blogRepository.findOne(id);
    }


    @Scheduled(fixedDelay = 3600000)
    public void reloadBlogs(){
        List<Blog> blogs = blogRepository.findAll();
        blogs.stream().forEach(blog -> saveItems(blog));
    }
}
