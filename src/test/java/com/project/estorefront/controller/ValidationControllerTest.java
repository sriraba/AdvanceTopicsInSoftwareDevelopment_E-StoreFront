package com.project.estorefront.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// referred from https://github.com/eea/thymeleaf-test/blob/master/src/test/java/eea/eprtr/controller/UserControllerTest.java
// TODO: Wait for discussion on how to test controllers

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
public class ValidationControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testLoginValidEmailPassword() throws Exception {
        this.mockMvc.perform(post("/validate-login").param("email", "hrishipatel99doesntexist@gmail.com").param("password", "Test1234@asdasd").param("role", "buyer"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginInvalidEmailValidPassword() throws Exception {
        this.mockMvc.perform(post("/validate-login").param("email", "asd@asd").param("password", "ASDA2@12334d").param("role", "buyer"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=Invalid+email+or+password"));
    }

    @Test
    public void testLoginValidEmailInvalidPassword() throws Exception {
        this.mockMvc.perform(post("/validate-login").param("email", "asd@asd.com").param("password", "1234").param("role", "buyer"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=Invalid+email+or+password"));
    }

    @Test
    public void testRegistrationFirstNameIsEmptyAndLastNameIsNull() throws Exception {
        this.mockMvc.perform(post("/validate-register").param("firstName","").param("lastName" ,null))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void testRegistrationFirstNameIsNullAndLastNameIsEmpty() throws Exception {
        this.mockMvc.perform(post("/validate-register").param("First Name",null).param("Last Name" ,""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void testRegistrationFirstNameAndLastNameIsEmpty() throws Exception {
        this.mockMvc.perform(post("/validate-register").param("First Name","").param("Last Name" ,""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void testRegistrationFirstNameAndLastNameIsNull() throws Exception {
        this.mockMvc.perform(post("/validate-register").param("First Name",null).param("Last Name" ,null))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void testRegisterValidEmailPassword() throws Exception {
        this.mockMvc.perform(post("/validate-register").param("email", "asd@asd.com").param("password", "ASDA2@12334d"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void testRegisterInvalidEmailValidPassword() throws Exception {
        this.mockMvc.perform(post("/validate-register").param("email", "asd@asd").param("password", "ASDA2@12334d"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void testRegisterValidEmailInvalidPassword() throws Exception {
        this.mockMvc.perform(post("/validate-register").param("email", "asd@asd.com").param("password", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void testResgisterCityIsNull() throws Exception {
        this.mockMvc.perform(post("validate-register").param("city", null))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }
    @Test
    public void testResgisterCityIsEmpty() throws Exception {
        this.mockMvc.perform(post("validate-register").param("city", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }
}
