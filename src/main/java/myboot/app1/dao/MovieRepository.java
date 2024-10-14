package myboot.app1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import myboot.app1.model.Movie;

@Repository
@Transactional
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	List<Movie> findByName(String name);

	List<Movie> findByNameLike(String name);
	
	@Query("SELECT m FROM Movie m WHERE m.year < 2000")
	List<Movie> listByDate();

	List<Movie> findByYear(int year);
	//List<Movie> findByNameAndYear(String name, int year);
//	@Query("SELECT m FROM Movie m WHERE m.name = :name AND m.year = :year")
//	List<Movie> findByNameAndYear(@Param("name") String name, @Param("year") int year);

	@Query("SELECT m FROM Movie m WHERE (:year = 0 OR m.year = :year) AND m.name LIKE :name")
	List<Movie> findWithFilters(@Param("name") String name, @Param("year") int year);
}