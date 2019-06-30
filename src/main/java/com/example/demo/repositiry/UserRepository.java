package com.example.demo.repositiry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.jpa.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

	 @Query("SELECT p.comment from Post p where p.post_id IN (:ids) order by p.id ASC")  
	public List<String> findPost(@Param("ids")List<Integer> ids);
}
