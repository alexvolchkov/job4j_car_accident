package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        service.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("accident", service.findById(1).orElse(new Accident()));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        service.update(accident);
        return "redirect:/";
    }
}
