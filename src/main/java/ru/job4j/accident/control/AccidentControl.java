package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.Service;

@Controller
public class AccidentControl {
    private final Service service;

    public AccidentControl(AccidentService accidents) {
        this.service = accidents;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model) {
        model.addAttribute("types", service.findAllTypes());
        model.addAttribute("accident", service.findById(id));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam Integer id) {
        AccidentType type = service.findTypeById(accident.getType().getId());
        accident.setId(id);
        accident.setType(type);
        service.saveOrUpdateAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.findAllTypes());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        AccidentType type = service.findTypeById(accident.getType().getId());
        accident.setType(type);
        service.saveOrUpdateAccident(accident);
        return "redirect:/";
    }
}
