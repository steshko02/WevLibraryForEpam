package by.steshko.LIb.controller;

import by.steshko.LIb.api.BookService;
import by.steshko.LIb.domain.Book;
import by.steshko.LIb.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AddController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BookService bookServiceImpl;

    @GetMapping("/addBook")
    public String main(Map<String, Object> model){
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(
            @RequestParam String name,
            @RequestParam Integer year,
            @RequestParam String isbn,
            @RequestParam String genre,
            @RequestParam String author,
            @RequestParam String description,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Book book = new Book(name,year, isbn, genre, author, description);

        String resultFilename = bookServiceImpl.filePathCreate(file);
        file.transferTo(new File(uploadPath+"/"+resultFilename));
        book.setFilename(resultFilename);
        bookServiceImpl.save(book);
        return "addBook";
    }
}
