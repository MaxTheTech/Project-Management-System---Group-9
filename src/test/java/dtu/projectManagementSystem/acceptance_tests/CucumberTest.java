package dtu.projectManagementSystem.acceptance_tests;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

/* Important: 
for Cucumber tests to be recognized by Maven, the class name has to have
either the word Test in the beginning or at the end. 
For example, the class name CucumberTests (Test with an s) will be ignored by Maven.
*/
//TOKEN: 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

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
		plugin = { "html:target/cucumber/wikipedia.html"},
		monochrome=true,
		snippets = SnippetType.CAMELCASE,
		glue = {"dtu.projectManagementSystem.acceptance_tests.steps"}
)

/*
@RunWith(Cucumber.class)
@CucumberOptions(plugin="summary"
		 ,features={"features"}
		 ,snippets = SnippetType.CAMELCASE
		 ,publish= false
		,glue = {"dtu.projectManagementSystem.acceptance_tests"}
		 )
*/
public class CucumberTest {
}
