package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.store.AccidentMem;

@Controller
public class IndexControl {
    private final AccidentMem accident;

    public IndexControl(AccidentMem accident) {
        this.accident = accident;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accident.findAll());
        return "index";
    }
}
