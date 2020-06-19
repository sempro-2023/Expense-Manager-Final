# Expense-Manager
1st Year 2nd Semester Project.

# Technology Used
1. IntelliJ - IDE Jetbrains for Developing Computer Software. 
2. Java - General purpose programming language.

# Requirements:
1. Any Operating System (Windows / macOS / Linux).
2. JRE Installed in Computer.
3. JDK 13 Installed.

# To Execute the file:
1. git clone https://github.com/atindra305/Expense-Manager-Final.git
2. Go to the command prompt (Windows)
3. Go to the terminal (Mac/Linux/Ubuntu)
4. Locate the file by typing command.
5. cd downloads (Press Enter)
6. java -jar PEMApp.jar (Press Enter)
7. A Character User Interface will be opened in the same terminal window.

# Features
   # User Interface
   1. Character User Interface (CUI).
   
   # Category Master.
   1. PEMService.java & Category.java
   2. User can add / manage categories

   # Expense Entry
   1. PEMService.java & Expenses.java
   2. User can add expenses by selecting specific categories.
   
   # Expense List
   1. PEMService.java & Expenses.java & DateUtil.java
   2. Raw listing of expenses added by user.
    
   # Monthly Expense Reports
   1. PEMService.java & Expenses.java & DateUtil.java
   2. User can view the month wise expense listing
   
   # Yearly Expense Reports
   1. PEMService.java & Expenses.java & DateUtil.java
   2. User can view the year wise expense listing
   
   # Category Expense Reports
   1. PEMService.java & Expenses.java & Category.java & DateUtil.java
   2. User can view the category wise expense listing

# Database
   # File Repository (Temporary)
   1. PEMService.java & Repository.java.
   2. Collection API concet used in Repository.java Class.
   3. Repository.java is for temporary storage (As long as JVM runs, storage exists. JVM terminates, Storage deleted). 
   
   # Local Machine (Permanent)
   1. Java Interface concept of Serialization and Deseraialisation
   2. After exiting the application JVM creates two files in the hard disk 
   3. 'Expenses.ser' and 'Category.ser' are the two files
   4. To retrieve the files.
   5. Run the jar file again
   6. Enter the choice as 2,3,4,5,6,7 which would be enough to show the data.
   
# For More Understanding of Code:
   1. git clone https://github.com/atindra305/Expense-Manager-Final.git
   2. Open the Files in Java IDE (IntelliJ Community is most preferable).
   3. I have added Comments also performed proper Source Code Documentations.
   4. You can read through them and get a better idea
   
   

