package by.steshko.LIb.api;

import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

public interface BookService {
     void deleteBook(Long bookId);

     void updateBook(Book newBook, Long bookId);

     void save(Book book);

     String filePathCreate(MultipartFile file);

     Map<User, List<Book>> getAllUsersAndOrders();

     Iterable<Book> getAll() ;

     Book findById(Long bookId) ;

     List<Book> findByName(String name) ;

}
