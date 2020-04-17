package in.application;

import java.io.*;

import java.util.*;



/**
 * The class contains most of the operations related to PEM application.
 * <p>
 *     This class prepares a menu and various methods are present to handle the user action.
 *     The class makes use of <code>Repository</code> to store the data.
 *     Also using <code>ReportService</code> to generate different required reports.
 * </p>
 * @author Atindra
 */
public class PEMService {



    /**
     * Declare a reference of repository by calling a static method which returns a singleton repository object.
     */
    Repository repo = Repository.getRepository();



    /**
     * Declare a reference of ReportService to call different methods to calculate reports.
     */
    ReportService reportService = new ReportService();    // Creating an instance variable



    /**
     * Declare a scanner object to take standard input from keyboard.
     */
    private Scanner in = new Scanner(System.in);  //input stream from where we will get input from the user


    /**
     * This variable stores the value of menu-choice
     */
    private int choice;


    /**
     * Call this constructor to create PEMService Object with default details.
     * When this method is called, it will deserialize the object
     * It will read the data from the file
     * and will return back to the object
     */
    public PEMService(){                                                                          // Treating as a startup
        //prepareSampleDate(); //TODO: Delete this method call after testing is completed

        restoreRepository();

    }



    /**
     * This method prepares a PEMApp menu using switch-case and infinite loop.
     * Also wait ask for user choice.
     */
    public void showMenu(){

        while (true){                             //infinite loop

            printMenu();                          //This will print the menu and thus will be a place to enter the choice by the user

            switch(choice){

                case 1:

                    onAddCategory();
                    pressAnyKeyToContinue();
                    break;

                case 2:

                    onCategoryList();
                    pressAnyKeyToContinue();
                    break;

                case 3:

                    onExpenseEntry();
                    pressAnyKeyToContinue();
                    break;

                case 4:

                    onExpenseList();
                    pressAnyKeyToContinue();
                    break;

                case 5:

                    onMonthlyExpenseList();
                    pressAnyKeyToContinue();
                    break;

                case 6:

                    onYearlyExpenseList();
                    pressAnyKeyToContinue();
                    break;

                case 7:

                    onCategorizedExpenseList();
                    pressAnyKeyToContinue();
                    break;

                case 0:
                    onExit();                     //This method exits current program by terminating running JVM
                    break;

            }
        }

    }



    /**
     * This method prints a menu(Command Line Interface CLI menu).
     */
    public void printMenu(){

        System.out.println("------------------PEM Menu-----------------");
        System.out.println("1. Add Category");
        System.out.println("2. Category List");
        System.out.println("3. Expense Entry");
        System.out.println("4. Expense List");
        System.out.println("5. Monthly Expense List");
        System.out.println("6. Yearly Expense List");
        System.out.println("7. Categorised Expense List");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------------");

        System.out.print("Enter your choice: ");
        choice = in.nextInt();
    }



    /**
     * This method is called to hold a output screen after processing the requested task
     * and wait for any character input to continue to the menu
     */
    private void pressAnyKeyToContinue(){
                                                            //We used try-catch block since 'System.in.read()'  was showing an error "unreported exception IOException"

                                                            //try allows a block of code to be tested for errors while executing
        try {
            System.out.println("Press any key to continue...");

            System.in.read();                               //Used to read the character
        }
                                                            //catch allows a block of code to be executed if any error occurs in try
        catch(IOException ex) {
                                                            //IOException are thrown when there is an issue in the input or output file operation
                                                            //while the application is performing certain tasks accessing the files

            ex.printStackTrace();                           //This is a tool used to handle exceptions and errors
        }
    }



    /**
     * This method is taking expense category name as input to add new category in the system.
     * And will also save to the Repository
     */
    public void onAddCategory(){

        in.nextLine();                                      //new line character is read here which is already present in stream and it's not in use for now
        System.out.print("Enter Category Name: ");
        String catName = in.nextLine();                      //Taking input from the user and storing in the catName
        Category cat = new Category(catName);                //cat is an object for Category
        repo.catList.add(cat);                               //Adding cat object to the List<Category> which is present in the Repository.java    //.add in List.java
        System.out.println("Success: Category Added");       //Success

    }



    /**
     * Call this method to print existing category List
     */
    public void onCategoryList(){

        System.out.println("Category List");
        List<Category> clist = repo.catList;                                      //getting all the categories here from the repository and stored in clist object
        for(int i =0; i<clist.size();i++){
            Category c = clist.get(i);                                            //c is the category object
            System.out.println((i+1)+". "+ c.getName()+", "+c.getCategoryId());   //Print all the categories     //c.getName() -> Category Name
                                                                                  //c.getCategoryId -> Category Id (just for reference) (internally created ID)
        }

    }

    //-------------------------------------------------------------(ILLUSTRATION)--------------------------------------------------------------------------------------


    //If we enter two categories:
    //Cat1
    //Cat2

    // onCategoryList() will print out in the way:
    // 1. Cat1
    // 2. Cat2

    // Cat1 and Cat2 are stored in the repository.java in the means of an array

    // This means Cat1 as an index as 0 and Cat2 has an index of 1 in the ArrayList (which is also a form of an array)


    // What if we chose 2 as a choice with respect to the onExpenseEntry(); it means that we need the index as 1 (i.e., 1 less than the choice)
    // And so we had to use a small logic behind in onExpenseEntry()

    // Category selectedCat = repo.catList.get(catChoice-1);


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------



    /**
     * Call this method to enter expense detail.
     * The entered details will be added in Repository
     */
    public void onExpenseEntry(){

        System.out.println("Enter details for the expense entry...");
        onCategoryList();                                                         //Select Category  //Category list with unique ID will be printed
        System.out.print("Choose Category: ");
        int catChoice = in.nextInt();                                             //Storing the category choice

        Category selectedCat = repo.catList.get(catChoice-1);                     //Explanation is given in the (ILLUSTRATION)
                                                                                  //Will get the selectedCat from the repo.CatList.get(catChoice-1)

        System.out.print("Enter Amount: ");                                       // Spent Amount to be entered by the user
        float amount = in.nextFloat();


        System.out.print("Enter Remark: ");                                       // Remark is the description for where was the money was spent
        in.nextLine();
        String remark  = in.nextLine();


        System.out.print("Enter Date(DD/MM/YYYY): ");                             // Enter Date by the user in DD/MM/YYYY format
        String dateAsString = in.nextLine();
        Date date = DateUtil.stringtoDate(dateAsString);




        //ADD EXPENSE DETAIL IN exp OBJECT

        Expense exp = new Expense();                                            // exp is the Expense Object
        exp.setCategoryId(selectedCat.getCategoryId());                         // user will set the category id
        exp.setAmount(amount);                                                  // user will enter the amount spent
        exp.setRemark(remark);                                                  // user will enter the description for where the money was spent
        exp.setDate(date);                                                      // user will enter the date in DD/MM/YYYY format


        //STORE exp in temporary database 'Repository.java'

        repo.expList.add(exp);


        System.out.println("Success: Expense Added");


    }



    /**
     * The method prints all entered expenses
     */
    // To Display the Expense Entry along with Category ID, Amount Spent, Remark and the date we used onExpenseList()
    public void onExpenseList(){

        System.out.println("Expense Listing...");

        List<Expense> expList = repo.expList;                   // From List<Expense> we will get all the expenses


        for(int i = 0; i<expList.size();i++){

            Expense exp = expList.get(i);

            String catName = reportService.getCategoryNameByID(exp.getCategoryId());

            String dateString = DateUtil.dateToString(exp.getDate());                    //we will get the date and that will be converted into String

            System.out.println((i+1)+". "+catName+", "+exp.getAmount()+", "+exp.getRemark()+", "+dateString);        // exp.getCategoryId() gets the category ID
                                                                                                                        //(i+1) is the serial number
        }


    }



    /**
     * This method is called from menu to prepare month-wise-expense-total.
     * It is using <code>ReportService</code> to calculate the report.
     * The returned result is printed by this method.
     * This method invokes a call to generate report, then result is printed by this method.
     */
    public void onMonthlyExpenseList() {

        System.out.println("Monthly Expense Total...");

        Map<String, Float> resultMap = reportService.calculateMonthlyTotal();     // resultMap will store the return m (values) reportService.calculateMonthlyTotal()

        Set<String> keys = resultMap.keySet();                                    // keySet is used to create a set out of the key elements contained in the hash map

        for(String yearMonth : keys){                                             // all the keys which are being extracted are a combination of year and month
            //2019, 01

            String[] arr = yearMonth.split(",");

            String year = arr[0];

            Integer monthNo = new Integer(arr[1]);

            String monthName = DateUtil.getMonthName(monthNo);

            System.out.println(year+","+monthName+" : "+resultMap.get(yearMonth));         // View as: <2019, Jan : 5000> in the console

        }

    }



    /**
     * This method is called from menu to prepare yearly-wise-expense-total.
     * It is using <code>ReportService</code> to calculate the report.
     * The returned result is printed by this method.
     * This method invokes a call to generate report, then result is printed by this method.
     */
    public void onYearlyExpenseList() {

        System.out.println("Yearly Expense Total ...");

        Map<Integer, Float> resultMap = reportService.calculateYearlyTotal();

        Set<Integer> years = resultMap.keySet();

        Float total = 0.0F;

        for(Integer year: years){

            Float exp = resultMap.get(year);

            total = total+exp;

            System.out.println(year+" : "+exp);

        }

        System.out.println("-------------------------------------------");

        System.out.println("Total Expense(INR): "+total);

        System.out.println();

    }



    /**
     * This method is called from menu to prepare category-wise-expense-total.
     * It is using <code>ReportService</code> to calculate the report.
     * The returned result is printed by this method.
     * This method invokes a call to generate report, then result is printed by this method.
     */
    public void onCategorizedExpenseList() {

        System.out.println("Category wise Expense Listing...");

        Map<String, Float> resultMap = reportService.calculateCategorisedTotal();

        Set<String> categories = resultMap.keySet();

        Float netTotal = 0.0F;

        for (String categoryName: categories){

            Float catWiseTotal = resultMap.get(categoryName);

            netTotal = netTotal + catWiseTotal;

            System.out.println(categoryName+" : "+ catWiseTotal);

        }

        System.out.println("-------------------------------------------");

        System.out.println("Net Total : "+ netTotal);

    }



    /**
     * This method stops JVM.
     * Before that it's storing the repository Permanently
     * It's like a shut down hook.................................when an application closes, some tasks are to be performed
     * Its closing PEM Application.
     */
    public void onExit(){
        persistRepository();                                                    // Permanently store Repository
        System.exit(0);

    }




    //--------------------------------------------------------[FOR TESTING PURPOSES ONLY]------------------------------------------------------------------------------


    /**
     * This method is preparing sample data for the testing purpose.
     * It should be removed once the application is tested OK.
     */
    // Sample Date for automatic generation of the outputs
    public void prepareSampleDate(){

        Category catParty = new Category("Party");                       //Created a Category Object for Party

        delay();                                                               //Called a delay function

        Category catShopping = new Category("Shopping");                //Created a Category Object for Shopping

        delay();

        Category catGift = new Category("Gift");                         //Created a Category Object for Gift



        // We have added the above sample data to the repository
        repo.catList.add(catParty);
        repo.catList.add(catShopping);
        repo.catList.add(catGift);



        // Jan 2019
        Expense e1 = new Expense(catParty.getCategoryId(), 1000.0F, DateUtil.stringtoDate("01/01/2019"),"New Year Celebration"); // e1 obj

        delay();                                                               // called for a delay of 10 ms


        Expense e2 = new Expense(catParty.getCategoryId(), 2000.0F, DateUtil.stringtoDate("02/01/2019"),"Bachelor Party");

        delay();


        // Feb 2019
        Expense e3 = new Expense(catShopping.getCategoryId(), 200.0F, DateUtil.stringtoDate("14/02/2019"),"Father's Birthday Gift");

        delay();


        Expense e4 = new Expense(catParty.getCategoryId(), 100.0F, DateUtil.stringtoDate("28/02/2019"),"Cousin Sister Birthday Gift");

        delay();


        //Dec 2019
        Expense e5 = new Expense(catGift.getCategoryId(), 500.0F, DateUtil.stringtoDate("25/12/2019"),"Christmas Party");

        delay();


        //Jan 2020
        Expense e6 = new Expense(catParty.getCategoryId(), 700.0F, DateUtil.stringtoDate("10/01/2020"),"N/A");

        delay();


        //Feb 2020
        Expense e7 = new Expense(catShopping.getCategoryId(), 100.0F, DateUtil.stringtoDate("07/02/2020"),"Shopping with Relatives");

        delay();


        //Mar 2020
        Expense e8 = new Expense(catGift.getCategoryId(), 5000.0F, DateUtil.stringtoDate("02/03/2020"),"Friend's Birthday Gift");

        delay();

        //Feb 2020
        Expense e9 = new Expense(catShopping.getCategoryId(), 8000.0F, DateUtil.stringtoDate("07/02/2020"),"Laptop");

        delay();

        //Feb 2015
        Expense e10 = new Expense(catShopping.getCategoryId(), 9000.0F, DateUtil.stringtoDate("07/02/2015"),"Phone");

        delay();





        // We have added all the above to expense List (expList) in the Repository.java

        repo.expList.add(e1);
        repo.expList.add(e2);
        repo.expList.add(e3);
        repo.expList.add(e4);
        repo.expList.add(e5);
        repo.expList.add(e6);
        repo.expList.add(e7);
        repo.expList.add(e8);
        repo.expList.add(e9);
        repo.expList.add(e10);



    }



    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------


    //-------------------------------------------------------------------NEED TO CREATE A DELAY FUNCTION---------------------------------------------------------------

    // 1. The constructor prepareSampleData() when executed will be executed within 1 millisecond,
    // 2. As a reason, the category ID which was initially told to assign values in the 'long' format will now get the same category ID for all the categories.
    // 3. Which is thereby doesn't meet are plans
    // 4. We plan of the system to assign unique IDs to different categories been created by the user.

    // 5. And so we have added a 10 millisecond delay, which will function as follows...
    // 6. (i) A category will be created,
    //    (ii) Recent time will be created by the system,
    //    (iii) There's a 10 millisecond delay
    //    (iv) Now when the second category will be created, it's ID will be automatically be differ.

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * The method sleep a thread for 10 milli second
     */
    private void delay(){

        try{
            // Thread.sleep is used to pause the execution of a particular command for a specified amount of time
            Thread.sleep(10);                              // After creating a category object, delay function sleeps for 10 milli second
        }
        catch(InterruptedException ex){                          // InterruptedException is thrown when a thread is either waiting or sleeping

            ex.printStackTrace();
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------




    /*
    ---------------------------------------------------------------FILE HANDLING: SERIALIZATION------------------------------------------------------------------------
    On Exit... persistRepository is called...


    FileOutputStream -> Use to write data to file

    ObjectOutputStream -> used to write the java objects

    .writeObject() -> write an object to the stream

    -------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */


    private void persistRepository() {

        serialize("expenses.ser",repo.expList);             // Serialise the expense List

        serialize("category.ser",repo.catList);             // Serialise the category List

    }





    /*
    file -> file name to be saved ... path of file can also be specified

    obj -> Object to be serialised
     */

    public void serialize(String file, Object obj){                    // Reusable method to serialise

        try

        {

            FileOutputStream fos = new FileOutputStream(file);          //expenses.ser; file to be created to store the data

            ObjectOutputStream oos = new ObjectOutputStream(fos);       // we write

            oos.writeObject(obj);                                              // store expense list in file


            //use-finally block - TODO
            oos.close();

            fos.close();
        }

        catch (Exception ex)

        {

            ex.printStackTrace();

        }

    }
    //------------------------------------------------------------------------FILE HANDLING: DESERIALIZATION-----------------------------------------------------------
    /*

    Using the ObjectOutputStream we write

    ObjectInputStream -> we read

    .readObject -> Read the object from the serialised Class

     */
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void restoreRepository() {                                                       //called from restoreRepository

        /*
        1. deser("expenses.ser") -> We will get an object

        2. But we know that the object which we have written is basically the list of Expense.

        3. So the object that will be thus obtained will be expList.

        4. So expList will be returned.

        5. So we have DOWN-CASTED deser("expense.ser") as '(List<Expense>) deser("expenses.ser")'.

        6. Thus this object gets read.
         */

        List<Expense> expList = (List<Expense>) deser("expenses.ser");                // Expense will be deserialized

        List<Category> catList = (List<Category>) deser("category.ser");              // Category will be deserialized



        // We now want to test if data has been registered here or not.

        if(expList != null){                               // if we have some expenses available and is not empty; Time-being all expenses go to List<Expense> expList


            //Set exisiting expenses in repository

            repo.expList = expList;          //expList -> is that expense list read from the file "expenses.ser" after deserialization and further initialised to repo

        }

        if(catList != null){

            repo.catList = catList;

        }

    }



    public Object deser(String file){                                // After reading from the file, it will return as a object

        try

        {

            FileInputStream fis = new FileInputStream(file);

            ObjectInputStream ois =  new ObjectInputStream(fis);

            Object obj = ois.readObject();                      //deserialize; obj is a deserialized object which got read from the file

            return obj;                                         // now the same obj read is returned here

        }

        catch (Exception ex)

        {

            //ex.printStackTrace();

            System.out.println("No existing data present");

            return null;                                        // null-> no object found

        }



    }

}
