package cz.jiripinkas.jba.repositories;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByBlog(Blog blog, Pageable pageable);
    List<Item> findByBlog(Blog blog);
    Item findByBlogAndLink(Blog blog, String link);
}
