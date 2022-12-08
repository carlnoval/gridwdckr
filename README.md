# Boiler Plate: TestNG project
## Requirements
- Java LTS 17
## Related Resource
- [Google Doc Selenium Grid](https://docs.google.com/document/d/1p_Hq7guAfnhZQcFgugUn-b43YMqGaVZeVWAgwiiMsRs/edit)
- [Google Doc Allure](https://docs.google.com/document/d/1mA9I2q44ZJNCQF-Cks-DcF0rCgHn6kZMCuK3dJNRqLE/edit)
## What comes with this project
- Simple TestNG Automation on: https://the-internet.herokuapp.com/
- Allure reporting
- Selenium Grid with Docker, the setup
  - 1 Chrome node with 3 sessions
  - 1 Edge node with 3 sessions
  - 1 Edge node with 3 sessions
  - No safari, as per SeleniumHQ "Apple is not providing them", [article link](https://github.com/SeleniumHQ/docker-selenium/issues/1635#:~:text=Apple%20is%20not%20providing%20them).
## Things to watch out for
- Web Driver versions
- POM (project object model) hell