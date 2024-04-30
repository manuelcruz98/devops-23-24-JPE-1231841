@ -0,0 +1,155 @@
# DevOps | Class Assignment 3 - Part 1

## Technical Report

### Part 1 - Virtualization with Vagrant

### Introduction

The purpose of this part of the assignment is to practice with VirtualBox using the same projects of the previous assignments but now inside a VirtualBox with Ubuntu.

### Requirements
1. Create a new VirtualBox VM with Ubuntu.
2. Clone your individual repository inside the VM.
3. Build and execute the spring boot tutorial basic project and the Gradle basic demo project.
4. For web projects you should access the web applications from the browser in your host machine.
5. For projects with a simple chat application, you should execute the server inside the VM and the client in your host machine.

### Analysis
1. Create a virtual machine.
2. Clone the repository.
3. Build and execute the projects.
4. Access the web applications from the browser in the host machine.
5. Execute the server inside the VM and the client in the host machine.

### Implementation

1. Install a new VM with Ubuntu.

2. Clone your own repository inside the VM.
   * For git clone you should generate a token.

```bash
sudo apt install git
git config --global credential.helper store
git config --global credential.helper 'cache --timeout=3600'
git clone repo.git
```

3. Install Java 17

```bash
sudo apt update
sudo apt install openjdk-17-jdk openjdk-17-jre
```
4. Set JAVA_HOME

```bash
nano ~/.bashrc
```
    * Add at the end of the file:
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```
    * Save and exit the file. Then, run the script.
```bash
source ~/.bashrc
```
### For CA1: Spring boot tutorial basic project

5. Build and execute spring boot tutorial basic project.

```bash
cd ca1/basic
````
    * Install Maven
```bash
sudo apt install maven
```
    * Execute the project
```bash
mvn spring-boot:run
```

6. Open the web browser in:
   * http://192.168.56.5:8080/

### For CA2-Part2: Practice with Gradle

7. Install Gradle


```bash
sudo apt remove gradle
wget htpps://services.gradle.org/distributions/gradle-8.6-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-8.6-bin.zip
```

**If unzip function does not work, install it with this commands:**

```bash
sudo apt update
sudo apt install unzip
```

    * Set the environment variables
```bash
echo "export GRADLE_HOME=/opt/gradle/gradle-8.6" >> ~/.bashrc
echo "export PATH=$PATH:$GRADLE_HOME/bin" >> ~/.bashrc
source ~/.bashrc
gradle -v
cd ca2/part1
```
    * Run the server inside the VM
```bash
gradle runServer
```
    * Run the client in the host machine

```bash
 gradle runClient --args="192.168.56.5 59001"
```
    * Execute the client


### Why is required to run the server inside the VM and the client in the host machine?

In order to isolate the server from the client side, we need to run the server inside the VM and the client in the host machine. This way, we can test the communication between the server and the client without any interference from the host machine.

### For CA2-Part2: Gradle basic demo project


```bash
cd ../../ca2/part2
```
    * Execute the project
```bash
gradle build
gradle bootRun
``` 

Open the web browser in:
* http://192.168.56.5:8080/

### Conclusion
This technical report focuses on familiarizing ourselves with setting up and executing projects from previous assignments within this virtual environment.

We initiated the process by setting up a new VirtualBox VM running Ubuntu, after which we proceeded to clone our respective repositories within the VM. Subsequently, we installed essential dependencies including JDK, Maven, and Gradle, ensuring that our environment was correctly configured for project execution.

For the Spring Boot tutorial basic project (CA1), we employed Maven to both build and execute the project. Afterward, we accessed the web application from the browser on the host machine.

Proceeding to CA2-Part1, we installed Gradle and launched the server within the VM, while running the client on the host machine. This segregation of server and client enabled us to evaluate their communication without any influence from the host environment.

The need to run the server inside the VM and the client on the host machine arises from the requirement to separate these components, allowing for independent testing and preventing any interference from the host environment.

Finally, we executed the Gradle basic demo project (CA2-Part2) within the VM, accessing the web application from the host machine's browser.

In conclusion, this exercise has provided us with valuable experience in working with VirtualBox and Ubuntu, enabling us to execute projects from previous assignments within this virtual environment.







