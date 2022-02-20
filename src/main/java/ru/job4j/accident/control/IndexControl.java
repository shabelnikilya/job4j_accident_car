package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.Service;
import ru.job4j.accident.store.AccidentJdbcTemplate;

@Controller
public class IndexControl {
    private final AccidentJdbcTemplate service;

    public IndexControl(AccidentJdbcTemplate service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", service.findAll());
        return "index";
    }
}
