package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.domain.UserDTO;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * UserController class for managing user-related operations.
 * It handles requests for listing, adding, updating, and deleting users.
 */
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * Displays the list of users.
     *
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    /**
     * Displays the form to add a new user.
     *
     * @param bid the User object to bind to the form
     * @return the name of the view to render
     */
    @GetMapping("/user/add")
    public String addUser(User bid) {
        return "user/add";
    }

    /**
     * Validates and saves a new user.
     *
     * @param userDTO the UserDTO object containing user data
     * @param result the BindingResult to check for validation errors
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the user list
     */
    @PostMapping("/user/validate")
    public String validate(@Valid UserDTO userDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/add";
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getFullname());
        user.setRole(userDTO.getRole());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(userDTO.getPassword()));

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    /**
     * Displays the form to update an existing user.
     *
     * @param id the ID of the user to update
     * @param model the model to add attributes for the view
     * @return the name of the view to render
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getFullname());
        userDTO.setRole(user.getRole());

        model.addAttribute("user", userDTO);
        return "user/update";
    }

    /**
     * Validates and updates an existing user.
     *
     * @param id the ID of the user to update
     * @param userDTO the UserDTO object containing updated user data
     * @param result the BindingResult to check for validation errors
     * @param model the model to add attributes for the view
     * @return the name of the view to render or redirect to the user list
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid UserDTO userDTO,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setFullname(userDTO.getFullname());
        existingUser.setRole(userDTO.getRole());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            existingUser.setPassword(encoder.encode(userDTO.getPassword()));
        }

        userRepository.save(existingUser);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @param model the model to add attributes for the view
     * @return the name of the view to redirect to the user list
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}
