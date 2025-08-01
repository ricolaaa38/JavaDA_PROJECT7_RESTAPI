package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
 * TradeController class for managing trade-related operations.
 * It handles requests for listing, adding, updating, and deleting trades.
 */
@Controller
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Displays the list of trades.
     *
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeRepository.findAll());
        return "trade/list";
    }

    /**
     * Displays the form to add a new trade.
     *
     * @param bid the Trade object to bind to the form
     * @return the name of the view to render
     */
    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    /**
     * Validates and saves a new trade.
     *
     * @param trade the Trade object containing trade data
     * @param result the BindingResult to check for validation errors
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the trade list
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeRepository.save(trade);
            model.addAttribute("trade", tradeRepository.findAll());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    /**
     * Displays the form to update an existing trade.
     *
     * @param id the ID of the trade to update
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    /**
     * Updates an existing trade.
     *
     * @param id the ID of the trade to update
     * @param trade the Trade object containing updated trade data
     * @param result the BindingResult to check for validation errors
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the trade list
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }
        trade.setId(id);
        tradeRepository.save(trade);
        model.addAttribute("trade", tradeRepository.findAll());
        return "redirect:/trade/list";
    }

    /**
     * Deletes a trade by its ID.
     *
     * @param id the ID of the trade to delete
     * @param model the model to add attributes for the view
     * @return the name of the view to redirect to the trade list
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeRepository.delete(trade);
        model.addAttribute("trade", tradeRepository.findAll());
        return "redirect:/trade/list";
    }
}
