package org.example.bookshop.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.BookDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookDao bookDao;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "home";
    }
}
