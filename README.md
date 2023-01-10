# End to End Automation Test
## This project runs with the following
- Simple TestNG Automation using test website: https://the-internet.herokuapp.com/
- Selenium Grid with Docker, the setup
  - 1 Chrome node with 3 browser sessions
  - 1 Edge node with 3 browser sessions
  - No Safari images for M1-ARM processors
    - > We won't provide macOS images in Docker because Apple is not providing them.
    - [Article link for the quote above:](https://github.com/SeleniumHQ/docker-selenium/issues/1635#:~:text=Apple%20is%20not%20providing%20them)
- Allure reporting with screenshots and history
  - Up to 20 past test runs are kept in history
- GitHub Actions:
  - deploy Selenium Grid via docker compose
  - run end-to-end test
  - publish reports to GitHub Pages
## Latest Allure Report published through GitHub pages
  - [Link to the latest Allure Reports](https://carlnoval.github.io/gridwdckr)
## How to run test locally
1. Install the following
    1. Install Java (at least LTS 17): [Download page](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
        - check with: `java --version`
    2. Install Maven 3.8.6 or above: [Official Install guide](https://maven.apache.org/install.html)
        - check with: `mvn --version`
    3. Docker the latest version should be fine
    4. Install Allure Framework: [Official install guide](https://docs.qameta.io/allure/#_installing_a_commandline)
2. Clone the Repo to local machine
3. Start Selenium Grid (Hub and Nodes setup)
   1. Navigate to the project root directory
   2. Run command: `docker compose -f docker/armseleniumgrid.yml up -d`
   3. Ensure that the containers started properly by:
      - Visually check in Docker Desktop Dashboard
      - Visit the following pages
        - Selenium Grid Node page: http://localhost:4444/ui
        - Selenium Grid Hub pages (password is `secret`)
          - Chrome: http://localhost:7901
          - Firefox: http://localhost:7902
4. Run the test
   - `mvn clean test`
   - If the test runs in headed mode, the Selenium Grid Hub pages will show the browsers being automated
5. Serve local Allure report
   - `allure serve allure-results`

## Things to watch out for
- POM (project object model) hell
## Personal Google Docs related resources
- [Google Doc Selenium Grid](https://docs.google.com/document/d/1p_Hq7guAfnhZQcFgugUn-b43YMqGaVZeVWAgwiiMsRs/edit)
- [Google Doc Allure](https://docs.google.com/document/d/1mA9I2q44ZJNCQF-Cks-DcF0rCgHn6kZMCuK3dJNRqLE/edit)