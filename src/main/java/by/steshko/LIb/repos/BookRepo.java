package by.steshko.LIb.repos;


import by.steshko.LIb.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {
    List<Book> findByName(String name);
}
