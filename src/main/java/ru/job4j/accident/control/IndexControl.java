package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> headTable = List.of("Описание штрафа", "Марка машины", "Владелец", "Статус заявления");
        model.addAttribute("headTable", headTable);
        return "index";
    }
}
