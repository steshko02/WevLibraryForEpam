package by.steshko.LIb.repos;


import by.steshko.LIb.domain.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {
    List<Book> findByName(String name);
    boolean existsById(@NotNull Long id);
}
