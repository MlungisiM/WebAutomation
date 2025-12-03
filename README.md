# Selenium Framework

The aim of this project is to create a test automation framework that will be used for testing web applications. This is a Selenium Java framework designed using Page Factory design pattern.

## Getting Started

This project is a Maven project. To access this project, clone this project directly from Azure into your IntelliJ and open the Maven project from your local directory into IntelliJ.

## Prerequisites

```bash
Eclipse
JRE-21>
JDK-21>

```

## Install the below
```bash
Download and Install:
1.	Eclipse 
4.	JRE and JDK
```

## Framework
### Tools
```bash
Testing Framework: Selenium
Build Tool: Maven
Testing Tool: TestNG
Programming Language: Java
```

### Design
```bash
web-config and webdriver manager stored within framework to easily access and use. Making the solution portable.
```

```bash
Framework consists of following modules / packages:
•	DriverFactory (driver initialization classes and driver config)
•	base (Hooks and various methods)
•	pages (web applications page factory)
•	resources(web-config.properties file)
•	tests (for web tests)

```

```bash
tests use TestNG annotations following the same structure:

@BeforeMethod – This is the precondition / setup of the tests
@Test – steps to run
@AfterMethod – Post condition / resetting the driver into its initial state 

```
## Running the tests with TestNG

```bash
Open Edit run/debug configuration dialog
```

```bash
tests Class for executing web App tests.
```

```bash
To execute each Class, navigate to the respective class > right-click on it > run using the respective configuration set on previous step
```

```bash

### To execute Mobile tests

bash
1.	Launch the browser
```

```bash
2.	Change the environment details in webconfig.properties

```bash
3.	Navigate to the tests class > Right-click and run using the TestNG
```

## Healenium

[![Docker Pulls](https://img.shields.io/docker/pulls/healenium/hlm-backend.svg?maxAge=25920)](https://hub.docker.com/u/healenium)
[![License](https://img.shields.io/badge/license-Apache-brightgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![@healenium](https://img.shields.io/badge/Telegram-%40healenium-orange.svg)](https://t.me/healenium)

### Table of Contents

* [Overall information](#overall-information)
* [Structure](#structure)
* [Healenium installation](#healenium-installation)
* [Disable Healing](#disable-healing)
* [Frameworks and Appium](#frameworks-and-appium)
* [Report](#report)
* [IDEA Plugin](#idea-plugin)
* [Community](#community)

### Overall information
Healenium is an open-source library for automated testing that leverages the power of machine learning to improve the reliability and resilience of your tests.
Healenium works by automatically detecting and healing test failures caused by changes in the UI, such as element IDs or class names.

Self-healing framework based on Selenium and able to use all Selenium supported languages like Java/Python/JS/C#
Healenium acts as proxy between client and selenium server.


### Structure
Healenium includes the following services:
- `postgres-db` (PostgreSQL database to store reference selectors / healing / report)
- `hlm-proxy` (Proxying client requests to the Selenium server.)
- `hlm-backend` (CRUD service)
- `selector imitator` (Convert healed locator to convenient format)
- `selenoid`/`selenium-grid` (Selenium server)

<img width="500" alt="image" src="https://user-images.githubusercontent.com/69298932/230408855-11aefcb9-6e46-4c3a-a3da-bdad66a52a9c.png">

Healenium offers two distinct approaches to accommodate different testing needs: Healenium-Proxy and Healenium-Web. Each method provides unique advantages geared towards optimizing your test environment.

For a detailed comparison and further insights into these approaches, you can explore the following resources:
* Overview of Approaches: [Healenium Approaches Overview](https://healenium.io/docs/overview)
* How Healenium Works: Uncover the mechanics behind Healenium's intelligent healing process: [How Healenium Works](https://healenium.io/docs/how_healenium_works)

### How to start the services
- `For standard web automation` docker-compose -f docker-compose-web.yaml up -d
- `For use with Appium` docker-compose -f docker-compose-appium.yaml up -d
- `For use with Selenium Grid:` docker-compose up -d **_OR_** docker-compose -f docker-compose-selenium-v3.yaml up -d


### Verify the Services:
- 1. Use docker ps to list the running containers and confirm they are active.
- 2. Open your web browser and navigate to http://localhost:7878/healenium/report to view the Healenium report portal and confirm the backend is operational.

### Healenium installation

1. Start your journey by setting up Healenium in your development environment.
   Follow our step-by-step installation guide to integrate Healenium seamlessly into your project.
   [Download and install](https://healenium.io/docs/download_and_install)

### Disable Healing
2. Discover how to control the healing function. This section teaches you how to disable and enable healing as per your testing scenarios.
   [Disable Healing](https://healenium.io/docs/disable_healing)

### Frameworks and Appium
3. Healenium is a versatile visual testing and self-healing framework that can be seamlessly integrated with several popular test automation frameworks, enhancing your ability to create robust and reliable automated tests
   [Frameworks](https://healenium.io/frameworks) and [Appium](https://healenium.io/docs/download_and_install/hlm_appium)

### Report
4. Finish your tutorial by generating and interpreting reports. Understand how to use Healenium's reporting features to analyze and improve your test results.
   [Report](https://healenium.io/docs/report)

### IDEA Plugin
5. Enhance your IDE experience by installing the Healenium plugin. This integration simplifies the healing process and streamlines your workflow.
   [IDEA Plugin](https://healenium.io/docs/how_healenium_works)

### Community

* [Telegram chat](https://t.me/healenium)
* [GitHub Issues](https://github.com/healenium/healenium/issues)
* [YouTube Channel](https://www.youtube.com/channel/UCsZJ0ri-Hp7IA1A6Fgi4Hvg)
