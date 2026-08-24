## Learning Git
You are ready for this tutorial if all of the following conditions hold:
- You've made some changes to your project directory locally on your own computer.
- You've tested your code and verified the changes are working correctly.
- You've reached the point where you want to share those changes with me.


The power of git is not only in easily sharing code through GitHub, but through *version control*.
Whenever you want to save a "record" of the project's current state, you will create a *commit record*.
Git is a sophisticated tool tracking the *history* of *commit records* in a (usually) memory-efficient way.
But git doesn't remember every single change you make - it only remembers the changes you ask it to remember.

To start, make sure that you've opened your project directory in VS Code, and an integrated terminal (ctrl-`).

1. Stage your changes.

   Possibly you have changed many files at this point.
   We use the `git add` command to *stage* a file, i.e. tell git we want its changes to be incorporated into the next commit record.
   
   ```
   $ git add path/to/file.java
   ```

   If you want to add multiple files to the same commit record, you can run the command multiple times in sequence, or you can take advantage of *wildcards*.
   The most useful is
   ```
   $ git add *
   ```
   which stages all files that have been changed since the last commit record.

   Any files matching the patterns in the .gitignore file will be ignored when using `git add *`.
   This is especially helpful for platform dependent files like the compiled .class files in your bin folder, which should never be committed to the git history.

2. Preview your changes.
   
   Every commit record should be focused on a *specific* task, so that the git history gives a clear story of how your repository has changed.
   In major software development projects, this also facilitates an efficient "undo" procedure when user requirements change.
   
   Before you create the commit record, you should always double-check that you are committing only those changes for the task you've been working on.
   Run the command
   ```
   $ git status
   ```

   This will bring up a list of all files that have changed since the last commit record, and which ones are currently staged.

   (If you've staged something you didn't mean to, you can use the `git remove` or `git restore` commands. Ask Google for more details.)

3. Commit your changes.

   Every commit record must be accompanied by a *commit message*.
   These will be visible in GitHub and serve to tell the story I mentioned above.

   By convention, this message is an *imperative* sentence, like "Fix the bug" or "Incorporate new features".
   Imagine you are telling git what to do.

   But also, please be *specific* in your commit messages, telling git *which* bug or feature is being addressed.
   This is very important to me, because when I inevitably fall behind in grading, I will rely on the descriptions in your commit records to find the relevant changes for any given assignment.

   Here is an example:
   ```
   $ git commit -m "Correct badge links in readme"
   ```
   The command `git commit` creates a commit record from the files we previously staged.
   The `-m` is a *command-line option* which indicates that the next string should be interpreted as the commit message.

4. Push your changes.

   So far, you have updated the git history on your local computer.
   The next step is to update the git history on GitHub.

   Please remember: you make a commit record for every specific task.
   You only push these commits when you want them on the Internet.
   One typically makes several commit records before pushing.
   But it is the git *history* that gets pushed, not specific commit records, so all your committed changes will be seen on GitHub.

   Once you are sure you should push your changes, run this command.
   ```
   $ git push
   ```

   The first time you attempt this, VS Code may prompt you to log in and connect to your GitHub account. Please do so.
   Once properly authenticated, git will upload files from your computer to GitHub.

5. Check on the continuous integration in GitHub.
   
   Every time you make a push to your repository, the continuous integration configured in the `.github/CI.yml` file will attempt to rebuild documentation and run tests.
   Go to your repository in GitHub and click on the Actions tab on the toolbar toward the top of your screen.

   You should see a list of your commit messages.
   (In this repository, you will see these interspersed with commits labeled "pages build and deployment", which is a part of the continuous integration deploying updated documentation.)
   Next to each commit, you should see one of three symbols:
   - a spinning yellow circle indicates that the GitHub servers are still working on the continous integration scripts.
   - a red X indicates that an error was encountered in somewhere in continuous integration. Usually this will indicate that your code failed a test.
   - a green checkmark indicates that all continuous integration succeeded.

   In order to get completion points for a programming assignment, it needs to pass my basic test suite, so the green checkmark is necessary.
   But it might not be sufficient. To find out, we need to look closer.
   Click on the latest (closest to the top) of your commit messages.

   You should see two tasks, "Deploy javadocs." and "Run tests."
   Click on "Run tests.".

   Now you should see a sequence of stages in the continuous integration.
   Expand the one that says, "Run the project's Test.java file."
   This will show detailed output from running my test suite.
   If your commit record has a green checkmark, the output will conclude with "All tests have passed without error."

   But wait! *Which* tests were even run?

   If this is your first time, you won't see *anything* else.
   That's because *no* tests were run.
   You need to edit the Test.java file so that it runs the tests for KeyValuePair.java.
   But that's for [the next tutorial](java.md).