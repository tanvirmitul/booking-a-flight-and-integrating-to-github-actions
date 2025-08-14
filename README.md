# booking-a-flight-and-integrating-to-github-actions
### This is a project where a booking site is automated using cucumber java.

## Scenerio
1. Navigate to: https://www.shohoz.com/air-tickets
2. Select the following flight search criteria: 
- From: Chattogram 
- To: Dhaka 
- Departure Date: 23 September, 2025 
- Travelers: 2 Adults 
- Class: Premium Economy 
3. Click Done, then click Search. 
4. In the search results: 
- Filter and select US-Bangla Airlines. 
- Scroll down and click BOOK TICKET on the last flight. 
5. Verify that the &quot;Review fare to Dhaka&quot; modal appears. 
6. Verify that the Total Price shown in the modal matches the price displayed in the flight list. 
7. Click CONTINUE. 
8. Close the sign-in modal. 
9.  Capture the prices of the currently listed US-Bangla Airlines flights into an array. 
10.  Modify the filter selection: 
- Deselect US-Bangla Airlines 
- Select Novo Air 
11.  Capture the updated Novo Air flight prices into another array. 

12. Compare the two arrays and:  Assert that there is a price difference between the two airline
selections.

## Technology and Tool Used
- Selenium Webdriver
- Cucumber-Java
- TestNG
- Java
- Gradle
- intellij idea 
  
## How to run this project
- clone this project
- open with intellij idea
- visit .feature file
- run scenario
## Github Actions Integration
This project includes a fully automated CI pipeline using GitHub Actions, designed to run tests on every push or pull request to the main branch.
What it does?
- Builds the project using Gradle on a Windows runner.
- Sets up JDK 17 with Temurin distribution.
- Executes all Cucumber scenarios defined in the .feature files
Workflow File
The CI configuration is defined in .github/workflows/run-tests.yml. Here's a quick overview of the key steps:
- Checkout code
- Set up JDK 17
- Configure Gradle
- Run tests using ./gradlew clean test





