package com.example.demo.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is entity class for user
 *
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
  
    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name="post_id")
    private List<Post> posts;

    @ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="USER_FOLLOWS",
		joinColumns={@JoinColumn(name="USER_ID")},
		inverseJoinColumns={@JoinColumn(name="FOLLOWS_ID")})
	private Set<User> follows = new HashSet<User>();


	@ManyToMany(mappedBy="follows")
	private Set<User> friends = new HashSet<User>();
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Set<User> getFollows() {
		return follows;
	}

	public void setFollows(Set<User> follows) {
		this.follows = follows;
	}

    public User(){

    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", posts=" + posts + ", follows=" + follows + "]";
	}

}