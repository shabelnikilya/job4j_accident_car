package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.store.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Integer id, Model model) {
        System.out.println(id);
        model.addAttribute("accident", accidents.findById(id));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam Integer id) {
        accident.setId(id);
        accidents.addOrUpdate(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidents.addOrUpdate(accident);
        return "redirect:/";
    }
}
