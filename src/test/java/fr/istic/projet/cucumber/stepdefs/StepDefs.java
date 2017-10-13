package fr.istic.projet.cucumber.stepdefs;

import fr.istic.projet.WeekandgoApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = WeekandgoApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
