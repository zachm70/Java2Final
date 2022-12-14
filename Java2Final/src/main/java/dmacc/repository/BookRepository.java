package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
