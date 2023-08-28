# Leap-Frog-Timer Set-up Instruction


1. Import External Libraries (sqlite jdbc + javafx):

a. After opening the file, right-click and select Properties.

b. Navigate to Java Build Path > Libraries.

c. You'll notice sqlite-jdbc is currently missing. Select it, then click Edit. Replace the library with sqlite-jdbc-3.42.0.0.jar which can be found in the zip file.

d. Similarly, select JavaFX in the libraries and edit its path, this step is similar to what we always do during the course.

2. Adjust Run Configuration:

a. Right-click on Main.java and click Run As > Run Configurations.

b. On the Main page, make sure to unselect all the checkboxes.

c. Switch to f(x)= Arguments page and add the following modules to the configuration: "--add-modules javafx.controls,javafx.fxml,javafx.media", please note that we're including javafx.media, 
which differs from our usual class configuration.

3. Test Mode (Optional):

a. Start by deleting the database.db file. Then, within src/application/model/FrogTimer.java, uncomment lines 98 and 139, and comment out lines 100 and 141. Relaunch the application. Both the 
clock and break sessions will be reduced to 5 seconds for a quicker test experience.

Now you're all set! Launch the application and enjoy!

We developed a new version of a timer that combines the functions of the Pomodoro Technique, customization of settings, statistics of activities, checklists, and a reward system.
This product is designed to help users focus on one task at a time and establish good habits, which makes it have a wide range of target users from students, professionals, freelancers, to
hobbyists. We used object-oriented design in the forms of the Model-View-Controller (MVC) pattern, interface, abstract class, and inheritance. Our product passed user testing with positive
feedback and suggestions from them.


Here are pictures:



<img width="408" alt="Picture1" src="https://github.com/MiaWangGettinJob/Leap-Frog-Timer/assets/113963408/831d1fcc-ddbb-47af-9a94-c2f358515944">
<img width="408" alt="Picture2" src="https://github.com/MiaWangGettinJob/Leap-Frog-Timer/assets/113963408/547ee4ef-d510-4307-ab4c-a09799eec68f">
<img width="408" alt="Picture3" src="https://github.com/MiaWangGettinJob/Leap-Frog-Timer/assets/113963408/8b5d4ebf-ef95-49f4-a120-94b0c940abc2">
<img width="408" alt="Picture4" src="https://github.com/MiaWangGettinJob/Leap-Frog-Timer/assets/113963408/c2a3d8f4-fd45-4770-bcdc-b877a9dde1bf">
