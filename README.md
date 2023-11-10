# GitHub Repository Information Fetcher
This Java program retrieves data from the twbs/bootstrap GitHub repository using the GitHub API and saves the releases from the 'main' branch to a .CSV file.

# Demonstration
[YouTube - Code Demonstration](https://youtu.be/Z6A80iIRaGg)

## Usage
1. Open terminal or command line and navigate to the main directory.
2. Build the program ``` gradle build ```
3. Generate the wrapper ``` gradle wrapper ```
4. Run the program ``` ./gradlew run ```

   The program will generate a CSV file named `data.csv` within the main directory containing the Created Date, Tag name, and URL for the distribution zip file.

   Change the fetched repository by editing the 'owner' and 'repository' variables in the Task.java file.

## Dependencies
- [Gradle 6.6.1 or above](https://gradle.org/releases/)
- [Java 11 or above](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)