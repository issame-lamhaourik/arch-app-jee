package myboot.app3.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import myboot.app3.model.Writer;

@Repository
@Transactional
public interface WriterRepository extends CrudRepository<Writer, Long> {

}