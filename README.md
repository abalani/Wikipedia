## Wikipedia Automation Tests

### About

The goal of this project is to provide a way to test wikipedia's article page content to run Smoke BBD tests

### Pre - requisites

  * Maven 3.3.3+
  * Java 1.8+
  * chromedriver

### Setup Env variables

Before running any tests, SETUP an environment variable pointing to the binary of 'chromedriver'.
chromedriver is also included to the repo under test/resources/drivers/chromedriver

For example :

```bash
export SELENIUM_CHROME_DRIVER="/Users/<your_user>/chromedriver"
```

### Running the tests locally

* Required maven dependencies should be pulled by reloading the project under maven

```@CucumberOptions(features="src/test/resources/Features", glue = {"steps"}, monochrome = true, tags = "@ab")```

We can execute the tests through Cucumber TestRunner class by editing tag names.
The runner class is setup to execute any test with the tag name `@smoke`


## CI Pipeline

Execute tests everytime you push changes or create pull requests using Github Actions - Use the [ci.yaml](ci.yaml) file to specify actions to execute tests. 
Use this action in the Frontend Application repo as well as for the test framework.

Have the [ci.yaml](ci.yaml) place in `.github/worflows/` directory in your repository.

## Frequently used Cucumber Tags

 * @smoke
 * @regression
 
Note: All tags are to be in _lowercase_. 

### Smoke vs Regression + What should be tagged as Smoke

The following serves as a useful guide to determine the differences between functionality that is
considered smoke vs regression:

> Smoke: Business **Critical** functionality
> Regression: Must work functionality from Business POV, but NON-**Critical**

## Test Reports

Generated test reports after executing the tests can be accessed under target folder

* Reports can be generated in different formats by changing the format in TestRunner class

```// plugin = {"pretty", "json:target/JSONReports/report.json"}
// plugin = {"pretty", "junit:target/JUnitReports/report.xml"}```# Wikipedia
