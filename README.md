# Test automation
Auto tests to verify the functionality of the login and registration.

For running test, you need IDE e.g. Eclipse or Intellij IDEA with installed Maven.

[Eclipse Neon](https://www.eclipse.org/neon/)
[Eclipse maven plugin](https://www.eclipse.org/m2e/)

[Intellij IDEA](https://www.jetbrains.com/idea/download/)

Then clone project. It can be done in two ways:
  - by git pugin [Eclipse git plugin](https://www.eclipse.org/egit/download/).
  Intellij IDEA has git already installed.
  
  For Eclipse: 
   1. File -> Import -> Git -> Project from git -> Clone uri -> next
   2. uri fill in with https://github.com/nikitaPro/TestAutomation
   3. set your authentication data and click next
   4. click next (master checked)
   5. choose directory, just in your workspace folder .../workspace/TestAutomation, click next
   6. at this step eclipse propose choose "import using...". To do nothing, just close.
    
    
  - by download zip with this project, and unpack zip into `TestAutomation` folder in your workspace .../workspace/TestAutomation.
  
  Import project for Eclipse:
   - File -> Import -> Maven -> Existing maven project
   - in `Root directory` enter path to .../workspace/TestAutomation, click finish. TestAutomation appear in the Package explorer.
   - in Package explorer find src/test/java and src/test/resources, do right click -> build path -> Use as Source Folder for each of two.

Run project by JUnit test framework. 

For Eclipse: right click on project -> Run as -> JUnit Test

[For Intellij IDEA](http://tutorials.jenkov.com/java-unit-testing/running-tests-with-idea.html)
