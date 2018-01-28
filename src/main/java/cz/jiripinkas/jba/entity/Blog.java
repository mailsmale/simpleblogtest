package cz.jiripinkas.jba.entity;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Blog {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 1000)
    @URL(message = "Invalid URL")
    @Size(min = 4, message = "URL must be at least 4 characters")
    private String url;

    @Column(length = 1000)
    @Size(min = 4, message = "Name must be at least 4 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    //@OneToMany(mappedBy = "blog",fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
    private List<Item> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


}
