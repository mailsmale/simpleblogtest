package cz.jiripinkas.jba.entity;

import cz.jiripinkas.jba.annotations.UniqueUserName;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;



@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    @Size(min = 3, message = "Name must be at least 3 characters")
    @UniqueUserName(message = "This user is already exist")
    private String name;
    @Column(unique = true)
    @Email(message = "Invalid email!")
    @Size(min = 3, message = "Email must be at least 3 characters")
    private String email;
    @Size(min = 6, message = "Password must be at least 5 characters")
    private String password;
    private Boolean enabled;
    @ManyToMany
    @JoinTable
    private List<Role> roles;
    //@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Blog> blogs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer used_id) {
        this.id = used_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
