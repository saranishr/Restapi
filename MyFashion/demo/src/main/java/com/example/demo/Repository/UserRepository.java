package com.example.demo.Repository;
import java.util.List;
import com.example.demo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
  
    List<User> findByEmail(String email);
    Page<User> findByUsernameContaining(String username, Pageable pageable);
    @Modifying
       // Retrieve all users
       @Query("SELECT u FROM User u")
       List<User> findAllUsers();
   
       // Retrieve users by username
       @Query("SELECT u FROM User u WHERE u.username = ?1")
       List<User> findByUsername(String username);
   
       // Retrieve users by username (with pagination)
       @Query("SELECT u FROM User u WHERE u.username LIKE %?1%")
       Page<User> searchUsersByUsername(String username, Pageable pageable);
       
       // Custom INSERT (Not recommended, use save())
       @Modifying
       @Transactional
       @Query(value = "INSERT INTO User (username, email, password) VALUES (?1, ?2, ?3)",nativeQuery = true)
       void insertUser(String username, String email, String password);
       
}
