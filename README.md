### Perfecto-Bobcat-BDD Sample project

This sample project is designed to get you up and running within few simple steps.

Begin with installing the dependencies below, and continue with the Getting Started procedure below.

### Dependencies
There are several prerequisite dependencies you should install on your machine prior to starting to work with this project:

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

* An IDE to write your tests on - [Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/marsr) or [IntelliJ](https://www.jetbrains.com/idea/download/#)


## Downloading the Sample Project

* Clone this repository.

* After downloading and unzipping the project to your computer, open it from your IDE by choosing the folder containing the build.gradle


### Steps to integrate with Perfecto </br>

1. Clone/ Download this project.</br>

2. Run gradle tasks:</br>

    `gradle clean cucumber -Dwebdriver.url={Perfecto cloud url. E.g. https://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast} -Dwebdriver.cap.securityToken={Perfecto Security token} -Dbobcat.config.contexts={Bobcat context. E.g. chrome/firefox/perfecto}`</br>

### Jenkins CI Dashboard integration:
1. Setup a job in any CI like Jenkins.</br>
2. Create a build task -> Execute shell.</br>
3. Enter the below shell command and run your job :</br>

	`gradle clean cucumber -Dwebdriver.url={Perfecto cloud url. E.g. https://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast} -Dwebdriver.cap.securityToken={Perfecto security token} -Dreportium-job-name=${JOB_NAME} -Dreportium-job-number=${BUILD_NUMBER} -Dbobcat.config.contexts={Bobcat context. E.g. chrome/firefox/perfecto}` </br>

#### Note:

1. Substitute your Perfecto URL and securityToken respectively (without flower brackets)/ pass them as job parameters.</br>

2. ${JOB_NAME} & ${BUILD_NUMBER} are Jenkins internal environment variables which will return the job name and current job number.</br>

3. This [link](https://developers.perfectomobile.com/display/PD/Generate+security+tokens) will showcase how to generate Perfecto security token.</br>

4. Note: Enter the physical location of gradle if it wasnt identified by Jenkins while executing.</br>