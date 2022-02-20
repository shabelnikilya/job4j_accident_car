package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentRepositoryService;
import ru.job4j.accident.service.Service;

@Controller
public class IndexControl {
    private final Service service;

    public IndexControl(AccidentRepositoryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", service.findAll());
        return "index";
    }
}
