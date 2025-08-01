package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * RuleNameController class for managing rule names.
 * It handles requests for listing, adding, updating, and deleting rule names.
 */
@Controller
public class RuleNameController {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Displays the list of rule names.
     *
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        return "ruleName/list";
    }

    /**
     * Displays the form to add a new rule name.
     *
     * @param bid the RuleName object to bind to the form
     * @return the name of the view to render
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * Validates and saves a new rule name.
     *
     * @param ruleName the RuleName object containing rule name data
     * @param result the BindingResult to check for validation errors
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the rule name list
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameRepository.save(ruleName);
            model.addAttribute("ruleNames", ruleNameRepository.findAll());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    /**
     * Displays the form to update an existing rule name.
     *
     * @param id the ID of the rule name to update
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * Updates an existing rule name.
     *
     * @param id the ID of the rule name to update
     * @param ruleName the RuleName object containing updated data
     * @param result the BindingResult to check for validation errors
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the rule name list
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleName", ruleNameRepository.findAll());
        return "redirect:/ruleName/list";
    }

    /**
     * Deletes a rule name by its ID.
     *
     * @param id the ID of the rule name to delete
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the rule name list
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameRepository.delete(ruleName);
        model.addAttribute("ruleName", ruleNameRepository.findAll());
        return "redirect:/ruleName/list";
    }
}
