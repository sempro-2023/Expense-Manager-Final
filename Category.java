package in.application;


import java.io.Serializable;

/**
 *  This is a domain class that represents a Category
 * @author Atindra
 */
public class Category implements Serializable {                                    // Until we don't make it serializable, we cannot write to the file
                                                                                   // if not made serializable, objects cannot be transported out of JVM


    /**
     * It refers to a unique category Id.
     * Here it's simply generated using current time
     */
    private Long categoryId = System.currentTimeMillis();                           // CategoryId will be automatically created by the system in Time in milliseconds



    /**
     * Name of expense category
     */
    private String name;


    public Category(String name) {                                                 // Category will take name as parameter ... (Parameterised Constructor)

        this.name = name;

    }


    public Category(Long categoryId) {

        this.categoryId = categoryId;

    }


    public Category(Long categoryId, String name){

        this.categoryId = categoryId;

        this.name = name;

    }


    public Category(){

    }


    public Long getCategoryId() {                                                    //get is used for showing

        return categoryId;

    }


    public void setCategoryId(Long categoryId) {                                     //set is used for user entry

        this.categoryId = categoryId;

    }


    public String getName() {

        return name;

    }



    public void setName(String name) {

        this.name = name;

    }
}
