package in.application;

/**
 1. This Repository is Temporary Storage and is stored in RAM in terms of Heaps
 2. This Repository is meant is store the Categories and the Expenses
 3. When JVM is terminated all the storage are gone
 */

import java.util.ArrayList;
import java.util.List;



/**
 * The class is used as Database/Repository and its a singleton
 * @author Atindra
 */
public class Repository {
                                                         //List Interface declaration
                                                         //public interface List<E> extends Collection<E>


    /**
     * The list hold all expenses added by user
     */
    public List<Expense> expList = new ArrayList();                // Arraylist can ve serializable




    /**
     * The list hold all expense categories added by user
     */
    public List<Category> catList = new ArrayList();



    /**
     * A singleton reference of the repository
     * So that we don't create multiple repository
     * So we have used static
     */
    private static Repository repository;                 //repository is an instance declaration



    /**
     * Private constructor to restrict object creation from outside
     */
    private Repository(){

    }



    /**
     * The method provides a singleton object of Repository
     * From the Repository class we will get only one object, no matter how many times we call it...
     * @return
     */
    public static Repository getRepository(){              //getRepository will provide you the repository object

        if(repository==null){                               //if repository is null(calling the repository for the first time) then you will get a new repository object

            repository=new Repository();                    //private constructors can be used in the same class//
        }
        return repository;                                 //return the repository object created already and recently created(by system)
    }
}
