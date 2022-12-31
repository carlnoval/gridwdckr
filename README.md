# End to End Automation Test
## This project runs with the following
- Simple TestNG Automation using test website: https://the-internet.herokuapp.com/
- Selenium Grid with Docker, the setup
  - 1 Chrome node with 3 browser sessions
  - 1 Edge node with 3 browser sessions
  - No safari, as per SeleniumHQ "Apple is not providing them", [article link](https://github.com/SeleniumHQ/docker-selenium/issues/1635#:~:text=Apple%20is%20not%20providing%20them).
- Allure reporting with screenshots and history
- GitHub Actions:
  - deploy Selenium Grid via docker compose
  - run end-to-end test
  - publish reports to GitHub Pages
## Latest Allure Report published through GitHub pages
  - Check from this workflow link: [pages-build-deployment](https://github.com/carlnoval/gridwdckr/actions/workflows/pages/pages-build-deployment)
## Run test locally
1. Install requirements
   - Java LTS 17
   - Docker the latest version should be fine
   - to be continued...
2. to be continued...

## Things to watch out for
- Web Driver versions
- POM (project object model) hell
## Personal Reference: Related Resources
- [Google Doc Selenium Grid](https://docs.google.com/document/d/1p_Hq7guAfnhZQcFgugUn-b43YMqGaVZeVWAgwiiMsRs/edit)
- [Google Doc Allure](https://docs.google.com/document/d/1mA9I2q44ZJNCQF-Cks-DcF0rCgHn6kZMCuK3dJNRqLE/edit)