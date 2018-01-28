package cz.jiripinkas.jba.repositories;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    public List<Blog> findByUser(User user);

}
