package by.steshko.LIb.service;

import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.BookRepo;
import by.steshko.LIb.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private  MailSender mailSender;

    public boolean tryDeleteBook(Long bookId){
        if(bookRepo.existsById(bookId)){
            bookRepo.deleteById(bookId);
            return true;
        }
        return false;
    }

    public boolean ifPresentInOrders(Long userId,Long bookId){
        User userFromDB = userRepo.findById(userId).orElse(null);
        if(userFromDB!=null){
            Long bookIdFromBD = userFromDB.getOrderedBooksId().stream()
                    .filter(b->b.equals(bookId))
                    .findAny().orElse(null);
            return bookIdFromBD != null;
        }
        return false;
    }

    public void updateBook(Book newBook,Long bookId){
       Book bookFromDB = bookRepo.findById(bookId).orElse(new Book());
       if(newBook.getFilename() == null)
            newBook.setFilename(bookFromDB.getFilename());

       bookFromDB = newBook;
       bookFromDB.setId(bookId);
       bookRepo.save(bookFromDB);
    }
}
