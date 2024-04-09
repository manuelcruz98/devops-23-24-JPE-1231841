# DevOps Technical Report | Class Assignment 2 - Part 1

## Introduction

This report details the execution of a simple demo application supported by Gradle, along with the addition of tasks to automate various processes. The application in focus is a basic chat application facilitating message exchange among users.

## Requirements

1. **Code Replication**: Duplicate the code from the demo Gradle application into a new folder labeled CA2/part1 in the repository.
2. **Application Execution**: Review the instructions outlined in the application's readme file and proceed with its execution.
3. **Task Addition for Server Execution**: Incorporate a new task to manage the execution of the server-side aspect of the application.
4. **Unit Testing**: Implement a basic unit test and update the Gradle script accordingly to enable its execution.
5. **Backup Task Integration**: Introduce a new task to handle the backup of the application's source code.
6. **Archive Creation Task**: Implement a task to create an archive of the application's source code.
7. **Repository Tagging**: Label the repository at the conclusion of the assignment as **ca2-part1**.

## Analysis

Before delving into the implementation phase, it's crucial to analyze the requirements and identify the primary components and tasks involved:

1. **Initial Setup**: Copying the code of the demo Gradle application into a designated folder.
2. **Execution**: Following the provided instructions to run the application.
3. **Gradle Task Creation**: Adding tasks to the Gradle script to automate various processes.
4. **Unit Testing Integration**: Incorporating unit testing and ensuring compatibility with the Gradle script.
5. **Adding 2 new tasks**: Implementing tasks to manage the backup and archive creation of the application's source code.
6. **Repository Tagging**: Tagging the repository upon completion of the assignment.

## Implementation

### Initial Setup
```bash
# Create a new folder (ca2/part1) in the repository
mkdir ca2/part1

# Clone the repository without its full history
git clone --depth 1 https://bitbucket.org/pssmatos/gradle_basic_demo.git

# Copy the contents of the cloned repository to the new folder, excluding the .git 
rsync gradle_basic_demo/* ca2/part1 --exclude .git

# Remove the cloned repository
rm -rf gradle_basic_demo
```
### Execution
```bash
# Change directory to the ca2/part1 folder
cd ca2/part1

# Build the application
./gradlew build

# Run the server
java -cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001

# Run a client
./gradlew runClient
```

### Gradle Task Creation
To automate various processes, we'll add tasks to the Gradle script:

We'll create a task named `runServer` in the `build.gradle` file. This task will launch the chat server listening on port 59001.

```gradle
task runServer(type: JavaExec, dependsOn: classes) {
    group = "DevOps"
    description = "Starts the chat server listening on port 59001"
    
    classpath = sourceSets.main.runtimeClasspath
    
    mainClass = 'basic_demo.ChatServerApp'
    
    args '59001'
}
```

- Run the task `runServer` from the command line
  ```bash
  ./gradlew runServer
  ```
    - Commit the changes to the repository
  ```bash
  git add build.gradle
  
  git commit -m "#9 - Added task and unit test"
  
  git push
  ```

####  Unit Testing Integration

We'll create a basic unit test for the `ChatServer` class and update the Gradle script to include the test task.

- Create a new test file `AppTest.java` in the `src/test/java//basic_demo` folder.
    ```bash
    mkdir -p src/test/java/basic_demo
    touch src/test/java/basic_demo/AppTest.java
    ```    
    - Add a simple test to the `AppTest.java` file.
    ```java
    package basic_demo;

    import org.junit.Test;
    import static org.junit.Assert.*;

    public class AppTest {
        @Test
        public void testAppHasAGreeting() {
            App classUnderTest = new App();
            assertNotNull("app should have a greeting", classUnderTest.getGreeting());
        }
    }
    ```
    - Update the `dependencies` in the `build.gradle` file to include the test task.
    ```gradle
    dependencies {
        (...)
        testImplementation group: 'junit', name: 'junit', version: '4.12'
    }
    ```
    - Run the test from the command line
    ```bash
    ./gradlew test
    ```
    - Commit the changes to the repository, linking them to issue #12.
    ```bash
    git add .
    git commit -m "#9 - Added task and unit test

    git push
    ```
### Adding 2 new tasks

- **Task 1**: Make backups of the source code.
    - Add a new task `backup` to the `build.gradle` file.
     ```gradle
     task backup(type: Copy) {
         from 'src'
         into 'backup'
     }
     ```
    - Run the task `backup` from the command line

       ```bash
        ./gradlew backup
        ```
        - Commit the changes to the repository.
          ```bash
          git add .
          git commit -m "#10 - Copied src to backup"
          git push
          ```

- **Task 2**: Create an archive of the source code.
    - Add a new task `archive` to the `build.gradle` file.
     ```gradle
     task createZip(type: Zip) {
        from 'src'
        archiveFileName = 'src.zip'
        destinationDirectory = file('zipFile')
     }
     ```
    - Run the task `archive` from the command line

      ```bash
         ./gradlew archive
         ```
        - Commit the changes to the repository.
         ```bash
         git add .
         git commit -m "#11 - Zip src"
         git push
         ```
### Repository Tagging

- Create a new tag `ca2-part1` in the repository.
   ```bash
   git tag ca2-part1
   ```
    - Push the tag to the remote repository.
   ```bash
   git push origin ca2-part1
   ```
## Conclusion
### Conclusion

In this assignment, we delved into the realm of Gradle task creation, marking our initial foray into automating processes. By enriching the Gradle build script with tasks to manage server-side execution, source code backups, and archive creation, we gained invaluable insights into the mechanics of Gradle tasks.

Moreover, our endeavor to incorporate a basic unit test and adapt the Gradle script's dependencies to facilitate test execution provided us with a deeper understanding of Gradle's capabilities.

As a culmination of our efforts, we concluded by tagging the repository with the identifier **ca2-part1**. This assignment not only bolstered our practical skills but also laid a sturdy foundation for our ongoing exploration and mastery of Gradle's robust automation functionalities.