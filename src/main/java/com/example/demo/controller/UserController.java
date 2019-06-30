package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.Post;
import com.example.demo.jpa.User;
import com.example.demo.repositiry.UserRepository;


@RestController
public class UserController {

	   @Autowired
	    private UserRepository userRepositiry;
	   
	   //create new User with name
	   @PostMapping("/api/user")
	   public String saveUser(@RequestBody User user){
		   user.setPosts(new ArrayList<Post>());
		   userRepositiry.save(user);
	    return " user  "+user.getName()+" is created  ";
	   }
	   
	   //get all users
	   @GetMapping("/api/user")
	   public List<User> getUsers(){
		   return userRepositiry.findAll();
	    
	   }
	   
	   //follow other user
	   @PutMapping("/api/user/{userName}")
	   public String followUser(@RequestBody User user,@PathVariable(name="userName")String userName){
		   User user1=userRepositiry.findByName(userName);
		   user1.getFollows().add(userRepositiry.findByName(user.getName()));
		   userRepositiry.save(user1);
	    return userName + " follows "+user.getName();
	    
	   }
	   
	   //post comment by user
	   @PutMapping("/api/user/comment/{userName}")
	   public String saveUser(@RequestBody Post post,@PathVariable(name="userName")String userName){
		   User user1=userRepositiry.findByName(userName);
		   
		   List<Post> posts=user1.getPosts();
		   posts.add(post);
		   user1.setPosts(posts);
		   userRepositiry.save(user1);
	    return "comment posted";
	   }

	   
	   //get news feed for user
	   @GetMapping("/api/posts/{userName}")
	   public List<String> getPosts(@PathVariable(name="userName")String userName){
		   User user=userRepositiry.findByName(userName);
		   List<Integer> ids=new ArrayList<>();
		   ids.add(user.getId());
		  Iterator<User> itr =user.getFollows().iterator();
		  while(itr.hasNext()){
			  ids.add(itr.next().getId());
			}
		   return userRepositiry.findPost(ids);
	    
	   }
	   
	  
	 
	   
}
