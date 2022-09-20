package com.mikigyakorlas.todoapp.repositories;

import com.mikigyakorlas.todoapp.models.entities.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {
  List<Todo> findAll();

  @Query(nativeQuery = true, value = "SELECT * FROM todoz WHERE user_id = ?1 ORDER BY title ASC")
  List<Todo> sortTitleAsc(Integer userId, String sort);

  @Query(nativeQuery = true, value = "SELECT * FROM todoz WHERE user_id = ?1 ORDER BY title DESC")
  List<Todo> sortTitleDesc(Integer userId, String sort);

  @Query(nativeQuery = true, value = "SELECT * FROM todoz WHERE user_id = ?1 ORDER BY priority ASC")
  List<Todo> sortPriorityAsc(Integer userId, String sort);

  @Query(nativeQuery = true, value = "SELECT * FROM todoz WHERE user_id = ?1 ORDER BY priority DESC")
  List<Todo> sortPriorityDesc(Integer userId, String sort);

  @Query(nativeQuery = true, value = "SELECT * FROM todoz WHERE user_id = ?1 ORDER BY deadline ASC")
  List<Todo> sortDeadlineAsc(Integer userId, String sort);

  @Query(nativeQuery = true, value = "SELECT * FROM todoz WHERE user_id = ?1 ORDER BY deadline DESC")
  List<Todo> sortDeadlineDesc(Integer userId, String sort);
}
