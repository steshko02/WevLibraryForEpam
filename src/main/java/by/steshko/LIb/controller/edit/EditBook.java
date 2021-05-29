package by.steshko.LIb.controller.edit;

import by.steshko.LIb.domain.Book;
import by.steshko.LIb.domain.User;
import by.steshko.LIb.repos.BookRepo;
import by.steshko.LIb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class EditBook {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepo bookRepo;


    @GetMapping("/bookPage/edit")
    public String getUser(Map<String, Object> model, @RequestParam Long bookId) {
        model.put("oldBook",bookRepo.findById(bookId).orElse(new Book()));
        model.put("bookId",bookId);
        return "bookEdit";
    }
    @PostMapping("/bookPage/edit")
    public String addBook(
            @RequestParam Long bookId,
            @RequestParam String name,
            @RequestParam Integer year,
            @RequestParam String isbn,
            @RequestParam String genre,
            @RequestParam String author,
            @RequestParam String description,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Book book = new Book(name,year,isbn,genre,author,description);
        if(!file.getOriginalFilename().equals("")){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFilename = UUID.randomUUID().toString();
            String resultFilename = uuidFilename + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFilename));
            book.setFilename(resultFilename);
        }
        bookService.updateBook(book,bookId);
        return "redirect:/bookList";
    }
}
