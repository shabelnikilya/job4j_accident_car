package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.store.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model) {
        model.addAttribute("types", accidents.findAllTypes());
        model.addAttribute("accident", accidents.findById(id));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam Integer id) {
        AccidentType type = accidents.findTypeById(accident.getType().getId());
        accident.setId(id);
        accident.setType(type);
        accidents.addOrUpdate(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.findAllTypes());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        AccidentType type = accidents.findTypeById(accident.getType().getId());
        accident.setType(type);
        accidents.addOrUpdate(accident);
        return "redirect:/";
    }
}
