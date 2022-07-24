package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentRepository accidents;
    private final AccidentTypeRepository types;
    private final RuleRepository rules;

    public AccidentControl(AccidentRepository accidents, AccidentTypeRepository types, RuleRepository rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> resType = new ArrayList<>();
        types.findAll().forEach(resType::add);
        model.addAttribute("types", resType);
        List<Rule> resRule = new ArrayList<>();
        rules.findAll().forEach(resRule::add);
        model.addAttribute("rules", resRule);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       HttpServletRequest req) {
        AccidentType type = types.findById(accident.getType().getId()).get();
        accident.setType(type);
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            for (String id : ids) {
                accident.addRule(rules.findById(Integer.parseInt(id)).get());
            }
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        List<AccidentType> resType = new ArrayList<>();
        types.findAll().forEach(resType::add);
        model.addAttribute("types", resType);
        List<Rule> resRule = new ArrayList<>();
        rules.findAll().forEach(resRule::add);
        model.addAttribute("rules", resRule);
        model.addAttribute("accident", accidents.findById(id).get());
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident,
                         HttpServletRequest req) {
        AccidentType type = types.findById(accident.getType().getId()).get();
        accident.setType(type);
        String[] ids = req.getParameterValues("rIds");
        for (String id : ids) {
            accident.addRule(rules.findById(Integer.parseInt(id)).get());
        }
        accidents.save(accident);
        return "redirect:/";
    }
}
