package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.Service;
import ru.job4j.accident.store.AccidentMem;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        Service service = new AccidentService(AccidentMem.getInstance());
        model.addAttribute("accidents", service.findAll());
        return "index";
    }
}
