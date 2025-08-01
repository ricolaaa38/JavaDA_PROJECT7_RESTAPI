package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
 * RatingController class for managing rating-related operations.
 * It handles requests for listing, adding, updating, and deleting ratings.
 */
@Controller
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Displays the list of ratings.
     *
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingRepository.findAll());
        return "rating/list";
    }

    /**
     * Displays the form to add a new rating.
     *
     * @param rating the Rating object to bind to the form
     * @return the name of the view to render
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Validates and saves a new rating.
     *
     * @param rating the Rating object containing rating data
     * @param result the BindingResult to check for validation errors
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the rating list
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingRepository.save(rating);
            model.addAttribute("rating", ratingRepository.findAll());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    /**
     * Displays the form to update an existing rating.
     *
     * @param id the ID of the rating to update
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Updates an existing rating.
     *
     * @param id the ID of the rating to update
     * @param rating the Rating object containing updated data
     * @param result the BindingResult to check for validation errors
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the rating list
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }
        rating.setId(id);
        ratingRepository.save(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        return "redirect:/rating/list";
    }

    /**
     * Deletes a rating by its ID.
     *
     * @param id the ID of the rating to delete
     * @param model the model to add attributes for the view
     * @return the name of the view to redirect to the rating list
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        ratingRepository.delete(rating);
        model.addAttribute("rating", ratingRepository.findAll());
        return "redirect:/rating/list";
    }
}
