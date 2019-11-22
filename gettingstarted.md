## Getting started with SkyStone

To get started programming with AndroidStudio for SkyStone for AverageSavages
you'll need [AndroidStudio](https://developer.android.com/studio), the
[SkyStone](https://github.com/FIRST-Tech-Challenge/SkyStone) release package, and
the [Teamcode repository](https://github.com/FTC-14868/skystone-teamcode).

### AndroidStudio Install

Install [AndroidStudio](https://developer.android.com/studio) and decide upon
a directory for keeping Android projects--SkyStone is an Android app.

### SkyStone Robot Controller (RC) package

1.	Download the latest source code release from the 
	[SkyStone releases](https://github.com/FIRST-Tech-Challenge/SkyStone/releases)
    repository page on GitHub. Presently, v5.3 is the latest. Use the .zip link for
	Windows (.tar.gz if on Linux or you prefer).
2.	Unzip/Extract the downloaded source code repository into the location you chose
    for your AndroidStudio projects.
3.  Within the downloaded project, go into the following directory
    **TeamCode/src/main/java/org/firstinspires/ftc/** and remove or rename
	the **teamcode** directory (*note the lowercase, not the top-level TeamCode*).

### Download or clone the AverageSavages teamcode repository

How you perform the next step will depend upon whether you intend to publish or
share your changes with the team.

1.  Get the latest *teamcode* source

	#### I want to share my changes with the team.

	If you intend to do so, you'll need to create an account on
	[GitHub](https://github.com). You can mark your email address as
	private (*probably a good idea, definitely so if you're a minor*). Once you have
	an account, you'll have your own version of the repository, and can request
	your changes be merged into the team's repository. I won't cover Git or GitHub
	usage here, but GitHub has guides, and there's an excellent
	[Git Book](https://git-scm.com/book/en/v2). The Git and GitHub clients are both
	useful, and AndroidStudio has built in support for tracking your changes.

	a.	Fork the [FTC-14868 SkyStone teamcode](https://github.com/FTC-14868/skystone-teamcode)
        repository by clicking the **Fork** icon in the upper-right portion of the
	    page (alongside **Watch** and **Star**).
		
	b.	Clone your fork using Git/GitHub into **TeamCode/src/main/java/org/firstinspires/ftc/**,
		and make sure that the name of the directory is **teamcode**. The full directory
		should be **.../TeamCode/src/main/java/org/firstinspires/ftc/teamcode**, and there
		shouldn't be any directories underneath, just the java files and this files that
		you can see on the repository page.

	#### I want to just download the code and make some local edits

	a. Download the latest [skystone-teamcode](https://github.com/FTC-14868/skystone-teamcode/archive/master.zip)
		source archive.
		
	b. Install it

2.  Finally, in AndroidStudio, go to *File->Open* and find the path to your SkyStone
    project directory (e.g. **.../SkyStone-5.3**), and open it.
3.  Once the project loads, use the project navigator to find the teamcode package
	(**TeamCode/java/org.firstinspires.ftc.teamcode/**) and open up any of the Java
	classes you would like to edit. You can find more information on building in the
	AndroidStudio guides online, or in the [SkyStone wiki](https://github.com/FIRST-Tech-Challenge/SkyStone/wiki).
	Beware that some data on the *SkyStone Wiki* is out of date with respect to the
	latest AndroidStudio version.
4.  Edit source code and rebuild. Information on loading the Android application can
	be found in the [SkyStone Wiki](https://github.com/FIRST-Tech-Challenge/SkyStone/wiki).
	Generally, it's as easy as plugging your phone in to your computer and pressing the *Play/Run*
	button in AndroidStudio, after you've setup *Developer options* in your phone.

