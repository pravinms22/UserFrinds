package com.example.demo.jpa;

import java.io.Serializable;

import javax.persistence.*;

/**
 * This entity class is for Comment user can post
 *
 */
@Entity
public class Post implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="post_id", updatable=false, insertable=false)
	private int post_id;
	
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	private String comment;

    public Post() {

    }

    public Post(String name) {
        this.comment = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}