package org.example.bookshop.admin;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.entity.Author;
import org.example.bookshop.entity.Book;
import org.example.bookshop.entity.Category;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @GetMapping
    public String index(){ 
        return "admin/admin-index";
    }

    @GetMapping("/books")
    public String listBooks(Model model){
        model.addAttribute("categories", adminService.listAllCategories());
        model.addAttribute("authors", adminService.listAllAuthors());
        model.addAttribute("book", new Book());
        model.addAttribute("books", adminService.listAllBooks());
        return "admin/books";
    }

    @PostMapping("/books")
    public String saveBook(Book book,  BindingResult result){
        if(result.hasErrors()){
            return "admin/books";
        }
        adminService.saveBook(book, book.getCategory().getId(),
                book.getAuthor().getId());
        return "redirect:/admin/books";
    }

    @GetMapping("/authors")
    public String listAuthors(Model model){
        model.addAttribute("categories", new Author());
        model.addAttribute("authors", adminService.listAllAuthors());
        return "admin/authors";
    }

    @PostMapping("/authors")
    public String saveAuthor(Author author, BindingResult result){
        if(result.hasErrors()){
            return "admin/authors";
        }
        adminService.saveAuthor(author);
        return "redirect:/admin/authors";
    }

    @GetMapping("/list-category")
    public String listAllCategory(Model model){
        model.addAttribute("category",new Category());
        model.addAttribute("categories",
                adminService.listAllCategories());
        return "admin/categories";
    }
    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult result){
        if(result.hasErrors()){
            return "admin/categories";
        }
        adminService.saveCategory(category);
        return "redirect:/admin/list-category";
    }

}
