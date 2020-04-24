# INFO5100-Jan2020-Project

This repository is for all members of INFO 5100 - Jan 2020 class collaborative project work

# Dependencies Required for running this project
lib/mssql-jdbc-8.2.2.jre8.jar
lib/java-json.jar
lib/azure-storage.jar
lib/jackson-core.jar
maven


# What you should do to compile and run the code
Add the .jar files above to the Project Structure->Libraries manually for Intellij ide. For other ides please Google how to add dependencies.
--4/14/2020
For now, you should exclude the IncentivesManagerImpl.java to run the code


# Things You Should Know to Create Incentives for Testing Purposes
There are two ways to create an incentive for your testing or any other purposes.
1. Using the GUI from Case5 bcreated by Group #3. **(Strongly recommend!!!)**
  * Run src/ui/guiforcase4/Main.java, input your DealerID, then you can click click click to the Create Screen.
  You can find the Lucidchart for dealer's GUI here: https://www.lucidchart.com/documents/edit/75f3138b-4f63-463c-b1bc-c3d771413586/0_0?shared=true
  * You can only create incenitves for **either one vehicle or a group of vehicles**
  * All the Incentive Details (on the right side of Create Screen) are mandatory
  
2. **(Please don't do this way)** Manually adding an incentive into the database, but **you should add it in the same format like we create it**, or dealers will have problems managing their incentives.
  * The "FilterList" column in the database contains the information of all the searching filters, **it's not "null" or empty String like ""**, it's a **long String joined by six meaningful Strings from a String Array**
  So, use the first approach to create incentives, or you will have to read the code for the *"convertFilterListToString()"* method in scr/ui/guiforcase5/CreatePage.java 
  * The *"vehicleIdList"* column in the database is an empty String like this -> ""
