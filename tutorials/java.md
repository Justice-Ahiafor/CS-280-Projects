## Learning Java
This tutorial covers the steps to run the basic test suite in your Java project, and how to execute individual Java files.

To start, make sure that you've opened your project directory in VS Code, and an integrated terminal (ctrl-`).
1. Run the Test.java file locally on your computer.

   We'll use the recipe in the readme.
   The command starts with `java`.
   That means we are going to be executing the `main` function in the java file specified at the end of the command, `src/Test.java`.
   
   The `-cp` command line option indicates the next string, `lib/*`, should be used for the **c**lass **p**ath.
   Essentially, this tells java that the "src/Test.java" file is going to depend on some code defined outside itself, and compiled versions of that code can be found packaged in the .jar file in the "lib" folder.

   The `-ea` command line option **e**nables **a**ssert statements.
   The `assert` keyword takes a boolean expression, and throws an error if the expression evaluates to false.
   Because this is a newer feature of Java (relatively speaking, that is), it is disabled by default.
   But it is how most of my tests work, so we need to enable it!
   
   All together,
   ```
   $ java -cp lib/* -ea src/Test.java
   ```

   You should see the same output you saw on GitHub, after checking on the continuous integration.
   All tests have passed...except there *are* no tests!

2. Run your KeyValuePair.java file directly.
   
   It's the same recipe as before, except replacing `src/Test.java` with `src/assignments/datastructures/KeyValuePair.java`.
   That's a lot to type, but most operating systems support *tab completion*.
   Type a bit of it and then tap your tab key, and you should see it fill in the rest if there are no ambiguities.
   (Depending on the operating system, tapping multiple times helps resolve ambiguities.)

   Once you've run this file, you should see the terminal display several warnings - which you'll address over the weekend - and that "KeyValuePair passes all tests."
   This is the confirmation we need.

3. Modify the test file to run the tests from KeyValuePair.
   
   Open up the Test.java file, and study the `main` method for a bit.
   This is the code that is actually run when you execute `java src/Test.java`.
   There's a bit of clever logic to make sure you are using the right recipe, but for the most part, it simply calls another method, `runTests`.

   The `runTests` method is essentially empty right now, but there's a space to add tests for new data structures like KeyValuePair.
   To do so, simply ask the KeyValuePair class to run its own main method; the syntax is: `KeyValuePair.main(args);`

   You should insert that line between the two comments about data structures, so it looks like:
   ```java
   // Test data structures.
   KeyValuePair.main(args);
   /* Call additional main routines as you create new data strutures. */
   ```
   We'll introduce tests for sorting algorithms next week.

4. Run the Test.java file locally on your computer.

   The output should still conclude by saying "All tests have passed without errors." but now it should also report that "KeyValuePair passes all tests."

5. Having made a change and tested your code to verify that change is working, it is time to share the change.
   Return to [the previous tutorial](git.md) to commit your change and integrate it into GitHub.
   Your lab is complete if I can see that KeyValuePair passes all tests in your CI workflow.

