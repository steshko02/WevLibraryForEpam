package com.asqint.webLib.controller;

import com.asqint.webLib.domain.Book;
import com.asqint.webLib.repos.BookRepo;
import org.springframework.beans.factory.annotation.Value;
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
public class AddController {
    @Value("${upload.path}")
    private String uploadPath;

    private final BookRepo bookRepo;

    public AddController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

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
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model
    ) throws IOException {
        Book book = new Book(name,year, isbn, genre, author, description);
        if(file!=null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFilename = UUID.randomUUID().toString();
            String resultFilename = uuidFilename + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+resultFilename));
            book.setFilename(resultFilename);
        }
        bookRepo.save(book);
        return "addBook";
    }
}
