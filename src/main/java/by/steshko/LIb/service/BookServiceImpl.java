package by.steshko.LIb.service;

import by.steshko.LIb.api.BookService;
import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.BookRepo;
import by.steshko.LIb.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;

    public void deleteBook(Long bookId){
        if(bookRepo.existsById(bookId)){
            bookRepo.deleteById(bookId);
        }
    }

    public void updateBook(Book newBook,Long bookId){
       Book bookFromDB = bookRepo.findById(bookId).orElse(new Book());
       if(newBook.getFilename() == null)
            newBook.setFilename(bookFromDB.getFilename());

       bookFromDB = newBook;
       bookFromDB.setId(bookId);
       bookRepo.save(bookFromDB);
    }

    public void save(Book book) {
        bookRepo.save(book);
    }

    public String filePathCreate(MultipartFile file){
        if(file!=null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFilename = UUID.randomUUID().toString();
            return uuidFilename + "." + file.getOriginalFilename();
        }
        return null;
    }

    public  Map<User, List<Book>> getAllUsersAndOrders() {
        Iterable<User> users = userRepo.findAll();
        Map<User, List<Book>> hash = new HashMap<>();
        for(User user:users) {
            List<Long> booksId = user.getOrderedBooksId();
            List<Book> books = new ArrayList<>();
            if(!booksId.isEmpty()) {
                for (Long id : booksId) {
                    books.add(bookRepo.findById(id).orElse(new Book()));
                }
                hash.put(user, books);
            }
        }
        return hash;
    }

    public Iterable<Book> getAll() {
       return bookRepo.findAll();
    }

    public Book findById(Long bookId) {
       return bookRepo.findById(bookId).orElse(new Book());
    }

    public List<Book> findByName(String name) {
        return bookRepo.findByName(name);
    }
}
