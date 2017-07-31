# restAssuredCucumberAPITests
test project setup for a sample car company's api tests using rest-assured and cucumber

## PreRequisites
* Java 8
* Maven


## Usage 
clone this repo using ```git clone https://github.com/innoprenuer/restAssuredCucumberAPITests.git```

execute ```mvn clean install``` first time to download required dependencies.

Alternatively, you can also run tests using ```mvn test``` or ```mvn clean surefire-report:report site -DgenerateReports=false```

## Reports
This project contains 3 types of reports

1.  Surefire Report
  * using ```mvn clean surefire-report:report site -DgenerateReports=false```
  * destination - **target/site/surefire-report.html**

2.  Cucumber Pretty HTML Report
  * using ```mvn clean surefire-report:report site -DgenerateReports=false``` or ```mvn test```
  * destination - **target/site/cucumber-pretty/index.html**
  
3. Cucumber Reports
  * using ```mvn clean install```
  * destination - **target/site/cucumber-reports/feature-overview.html**
