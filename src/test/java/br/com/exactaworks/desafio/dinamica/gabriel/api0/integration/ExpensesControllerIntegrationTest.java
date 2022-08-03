/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exactaworks.desafio.dinamica.gabriel.api0.integration;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.exactaworks.desafio.dinamica.gabriel.api0.Api0Application;

import io.restassured.RestAssured;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@SpringBootTest(classes = {Api0Application.class}, webEnvironment
        = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class ExpensesControllerIntegrationTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.port = 8080;
    }

    @Test
    public void
            givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
        get("/api/expenses").then().statusCode(200);
    }

    @Test
    public void
            givenUrl_whenSuccessOnGetsResponseAndJsonHasOneExpense_thenCorrect() {
        get("/api/expenses/1").then().statusCode(200)
                .assertThat().body("name",
                        equalTo("Gabriel"), "description",
                        equalTo("Livro de Springboot"));
    }

    @Test
    public void
            givenUrl_whenSuccessOnGetsResponseAndJsonHasTwoExpense_thenCorrect() {
        get("/api/expenses/2").then().statusCode(200)
                .assertThat().body("name",
                        equalTo("Veloso"), "description",
                        equalTo("Livro de Hibernate"));
    }
}
