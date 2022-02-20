package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.Service;

import javax.servlet.http.HttpServletRequest;

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
        model.addAttribute("rules", service.findAllRules());
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam Integer id, HttpServletRequest req) {
        accident.setId(id);
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(service.findRulesByIds(ids));
        service.saveOrUpdateAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.findAllTypes());
        model.addAttribute("rules", service.findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(service.findRulesByIds(ids));
        service.saveOrUpdateAccident(accident);
        return "redirect:/";
    }
}
