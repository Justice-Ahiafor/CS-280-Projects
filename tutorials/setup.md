## One-time Setup
1. Install VS Code, if you don't already have it: https://code.visualstudio.com/Download

   Obviously, you can just open it up now, if you already have it installed.

2. Install the "Extension Pack for Java" extension by Microsoft in VS Code.
   
   Install new extensions by clicking on the “Extensions” tab on the left sidebar.

3. Install OpenJDK.

   *Running* Java applications requires one type of installation, a JRE ("Java Runtime Environment").
   But for this course, we need to install the language itself, in a JDK ("Java Development Kit").

   The extension pack we installed will prompt you to install a distribution of OpenJDK, an open-source version of Java.
   If you miss the prompt, you should be able to install it from here: https://adoptium.net/temurin/releases

   Run the installer you downloaded and follow its instructions.
   Make sure the installation includes modifying your PATH environment variable so that the "java" and "javac" commands are recognized by your command prompt.
   (The default installation settings should include this, as well as flagging the ".jar" extension as a java executable.)

   Once you’ve installed the Java language, restart VS Code so the PATH variable changes take effect.

4. Create a GitHub account with your Holy Cross email, if you don't already have one: https://github.com/

5. Fork my repository.
   
   To "fork" a repository is to create a controlled copy on your own account.
   GitHub is able to facilitate the process of coordinating changes between forked repositories and the originals, but in this case, we just want the easy copy feature.
   Click the button labeled "Fork" toward the top right of your screen.

   Navigate to your new repository.
   Everything is copied verbatim, even the readme file!
   In fact... go ahead and click on the "docs" badge at the top of the readme.
   You'll see it takes you to *my* repository's documentation.
   Our goal in this tutorial is to fix that.

6. Verify that GitHub pages deploys from branch "gh-pages".

   This repository is set up with "continuous integration" to automatically deploy documentation for all the code you write.
   GitHub hosts this documentation using its "GitHub Pages" feature.

   I *think* the default configuration should simply *work*, but I need you to double-check for me.
   Navigate to your fork (i.e. the repository living in your account, not mine), and then click on the Settings tab on the toolbar toward the top of your screen.
   Find the "Pages" tab in the list on the left.

   Now, under the "Build and Deployment" header, there should be a drop-down labeled "Source". Make sure the option "Deploy from a branch" is selected. There should be another drop-down just below it; make sure the branch is "gh-pages". (The only other options should be "main" or blank.)

   Please let me know asap if you get stuck here, because if you get stuck, so will everyone else!

7. Install git, if you don't already have it: https://git-scm.com/

   This is the command-line tool we are going to use both for *version control* and to facilitate the transfer of files between your local computer, which you can edit easily in VS Code, and your GitHub repository, where I can see them and give feedback.

   Like when installing the JDK, I believe the default installation options are good, but make sure it is configured to update your PATH variable, so that the git command is accessible from anywhere on your computer.

8. Configure git with some required metadata, if you've never used it before with your Holy Cross account.

   Inside VS Code, open up an *integrated terminal* - you're going to use this feature all the time, in this course.
   The ctrl-` hotkey usually works.

   Then, enter these commands:
   
   ```
   $ git config --global user.name "yourusername"
   $ git config --global user.email "youremail@hcc-nd.edu"
   ```

   A couple of notes:
   - When you see the "$" symbol at the start of a line in instructions, it indicates that this is the start of a new line in a command prompt. You don't actually type it.
   - Please replace the contents within quotation marks with your own GitHub username and email.

9. Clone your fork.

   To "clone" a repository is to copy it from a location "upstream" on the Internet (i.e. GitHub) to your local computer.
   
   The first step is to decide where you want the project directory to live on your computer.
   If you don't have any better ideas, I recommend your Documents folder.
   Use the `cd` terminal command to **c**hange the present working **d**irectory to your intended location.
   On my computer, this looks like
   ```
   $ cd "C:\Users\KSherbert\OneDrive - Holy Cross College\Documents"
   ```

   Now, run the `git clone` command, which just requires the URL of the repository you are cloning.
   ```
   $ git clone "https://github.com/yourusername/CS-280-Projects"
   ```

   This command creates a new directory on your computer and then downloads the contents of your GitHub repository to that directory.

10. Open your project directory in VS Code.

    The right way to use VS Code is to have an entire folder open, so that you can use the VS Code file explorer to easily switch between files.

    With VS Code already open, you can use the "File > Open Folder..." menu option.
    If you are using Windows, you can do this very easily if VS Code is closed: simply navigate to where you created your project directory, right-click it, and select the "Open with Code" option from the context menu.

    If you are using a mac, there *is* a way to get a context menu option, but it's complicated.
    Look it up if you're interested, or ask me for help during office hours sometime.

11. Open your README.md file in VS Code.

    The `.md` extension stands for "Markdown", a type of markup language, similar to HTML but designed to be very human-readable.
    You should be able to easily see how the syntax you see in VS Code translates to the readme you see in GitHub.
    But we can even see it in VS Code - right click on the file name at the top of your editor, and select the option "Open Preview".
    Now VS Code opens another tab in the editor which renders the Markdown.

    Go ahead and click on the "docs" badge at the top of the readme.
    It still takes you to *my* documentation page.
    It's time to fix that.

12. Edit the README.md file to correct the links in the [Stable] and [GitHub Actions CI] badges.
    
    You'll just need to replace my username "ksherbert-hcc" with yours.
    Be sure you do it for both "badges".

    Now go back to the preview and click on the "docs" badge one more time.
    *Voila.* Your own documentation page.

    (If you do *not* see your own documentation page, there may be some more work still in getting gh-pages set up properly. Stop and let me know asap!)

    The version of your readme that lives on GitHub is still using the wrong url.
    To fix that, we need [the next tutorial](git.md).

