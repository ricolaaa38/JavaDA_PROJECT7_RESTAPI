package com.nnk.springboot;

import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAccessDeniedForUnauthenticatedUser() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    public void testLoginFailureWithInvalidCredentials() throws Exception {
        mockMvc.perform(formLogin().user("invalidUser").password("WrongPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    public void testAuthenticatedUserSessionAccessToProtectedPage() throws Exception {
        com.nnk.springboot.domain.User user = new com.nnk.springboot.domain.User();
        user.setUsername("testuser");
        user.setPassword(passwordEncoder.encode("Password!01"));
        user.setFullname("Test User");
        user.setRole("USER");
        userRepository.save(user);

        var loginResult = mockMvc.perform(formLogin().user("testuser").password("Password!01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"))
                .andReturn();

        var session = (org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession();
        Assertions.assertNotNull(session);

        mockMvc.perform(get("/user/list").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));

        userRepository.delete(user);
    }

    @Test
    public void testLogout() throws Exception {
        com.nnk.springboot.domain.User user = new com.nnk.springboot.domain.User();
        user.setUsername("logoutUser");
        user.setPassword(passwordEncoder.encode("Password!01"));
        user.setFullname("Logout User");
        user.setRole("USER");
        userRepository.save(user);

        var loginResult = mockMvc.perform(formLogin().user("logoutUser").password("Password!01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"))
                .andReturn();

        var session = (org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession();
        Assertions.assertNotNull(session);

        mockMvc.perform(get("/logout").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        userRepository.delete(user);
    }

    @Test
    public void testLoginAndLogoutAccessRestriction() throws Exception {
        com.nnk.springboot.domain.User user = new com.nnk.springboot.domain.User();
        user.setUsername("restrictedUser");
        user.setPassword(passwordEncoder.encode("Password!01"));
        user.setFullname("Restricted User");
        user.setRole("USER");
        userRepository.save(user);

        var loginResult = mockMvc.perform(formLogin().user("restrictedUser").password("Password!01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"))
                .andReturn();

        var session = (org.springframework.mock.web.MockHttpSession) loginResult.getRequest().getSession();
        Assertions.assertNotNull(session);

        mockMvc.perform(get("/logout").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(get("/user/list").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));

        userRepository.delete(user);
    }

}