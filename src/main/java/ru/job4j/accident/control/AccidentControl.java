package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService service;
    private final RuleService ruleService;

    public AccidentControl(AccidentService service, RuleService ruleService) {
        this.service = service;
        this.ruleService = ruleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.findAllTypes());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       HttpServletRequest req) {
        AccidentType type = service.findTypeById(accident.getType().getId()).get();
        accident.setType(type);
        String[] ids = req.getParameterValues("rIds");
        for (String id : ids) {
            accident.addRule(ruleService.findById(Integer.parseInt(id)).get());
        }
        service.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.findById(id).get());
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        service.update(accident);
        return "redirect:/";
    }
}
