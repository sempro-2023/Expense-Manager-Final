package in.application;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


//--------------------------------------------------FORMAT THE REPORT SHOULD BE SHOWN----------------------------------------------------------------------------------
/*
    Monthly Expense Total

    2019, Jan: 5000
    2019, Feb: 8000
    2019, Dec: 20000
    2020, Jan: 18000
    2020, Feb: 16000

    Map Structure:

    Here <key> is the "2019, Jan"
         <value> is   5000.0F
*/
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------



/**
 * The class contains various methods to calculate PEM App reports
 * @author Atindra
 */
public class ReportService {



    /**
     * Declare a singleton repository.
     */
    private Repository repo = Repository.getRepository();                          //Report Service will get its (Report/data) from the repository



    /**
     * The method calculates month wise total and returns result in Map.
     * Its preparing data in proper order.
     * And so it uses Tree Map, data will not merge
     * @return
     */
    public Map<String, Float> calculateMonthlyTotal(){                    // Map will be the (key,value) pair just like a Dictionary
                                                                          // key is the String datatype anf value has the Float datatype

        Map<String, Float> m = new TreeMap();                             //m: map has been created //TreeMap has been created within m
                                                                          // TreeMap will automatically sort the result in the String Datatype on the basis of key

        for(Expense exp: repo.expList){                                   // To first find out the Expenses, we created a for loop
                                                                          // fetched the expense list from the repository.java

            Date expDate =  exp.getDate();                                // Expense Date was fetched from each repo.expList

            String yearMonth = DateUtil.getYearAndMonth(expDate);

            if(m.containsKey(yearMonth)){                      // contains key is used to check whether a particular key is being mapped to that Map or not (Hash Map)

                // When Expense is already present for a month

                Float total = m.get(yearMonth);               // existing total of the current month

                total = total + exp.getAmount();              // exp.getAmount() is the recent expense that should be added to the total

                m.put(yearMonth, total);                      // put will replace the old key with the updated key, So we replaced new total with Old Total
                                                              // 'yearMonth' is the 'key' and 'total' is the 'value'

            }

            else{

                //This month is not added, so we add here

                m.put(yearMonth,exp.getAmount());            // All the expenses available in the repo.expList, will be organised and will be added in map 'm'

            }

        }

        return m;                                           // return final result as Map

    }



//------------------------------------------------------------------HOW m.containsKey(yearMonth) EXECUTE----------------------------------------------------------------
/*
1. Initially, if(m.containsKey(yearMonth)) will return false (size == 0) and thus else {} will be executed.

2. As a result, m.put(yearMonth,exp.getAmount()) will add "2019,Jan" as 1000.0

3. This will result in size == 1

4. Then only the if(m.containsKey(yearMonth)) will return True and the programs inside the if block will be executed.

5. The total of 2019,Jan will be replaced by (3000+2000)

6. So the size == 1 "2019,Jan" : 5000
*/

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------



    /**
     * The method calculates year wise total and returns result in Map.
     * Its preparing data in proper order.
     * And so it uses Tree Map, data will not merge
     * @return
     */
    public Map<Integer, Float> calculateYearlyTotal(){                    // Map will be the (key,value) pair just like a Dictionary
        // key is the Integer datatype and value has the Float datatype

        Map<Integer, Float> m = new TreeMap();                             //m: map has been created //TreeMap has been created within m
        // TreeMap will automatically sort the result in the String Datatype on the basis of key

        for(Expense exp: repo.expList){                                   // To first find out the Expenses, we created a for loop
            // fetched the expense list from the repository.java

            Date expDate =  exp.getDate();                                // Expense Date was fetched from each repo.expList

            Integer year = DateUtil.getYear(expDate);

            if(m.containsKey(year)){                      // contains key is used to check whether a particular key is being mapped to that Map or not (Hash Map)

                // When Expense is already present for a year

                Float total = m.get(year);               // existing total of the current year

                total = total + exp.getAmount();              // exp.getAmount() is the recent expense that should be added to the total

                m.put(year, total);                      // put will replace the old key with the updated key, So we replaced new total with Old Total
                // 'year' is the 'key' and 'total' is the 'value'

            }

            else{

                //This year is not added, so we add here

                m.put(year,exp.getAmount());            // All the expenses available in the repo.expList, will be organised and will be added in map 'm'

            }

        }

        return m;                                           // return final result as Map

    }

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------

/*
---------------------------------------------------------Display of Categorised Total ---------------------------------------------------------------------------------

Example:

Shopping: 45672.0
Rent: 25452
Bill: 4500

Let's assume:

"Shopping" as the key
45672.0 as the total value

So to display the above manner in a SORTED WAY we can use a data structure 'TreeMap'

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/



    /**
     * The method calculates category wise total and returns result in Map.
     * Its preparing data in proper order.
     * And so it uses Tree Map, data will not merge
     * @return
     */
    public Map<String, Float> calculateCategorisedTotal(){                    // Map will be the (key,value) pair just like a Dictionary
        // key is the String datatype anf value has the Float datatype

        Map<String, Float> m = new TreeMap();                             //m: map has been created //TreeMap has been created within m
                                                                          // TreeMap will automatically sort the result in the String Datatype on the basis of key

        for(Expense exp: repo.expList){                                   // To first find out the Expenses, we created a for loop
            // fetched the expense list from the repository.java

           Long categoryId = exp.getCategoryId();



           /*

           But since we don't have to display in the form of category Id,

           we have to display in terms of category name so we use the method

           getCategoryNameById() which is present in the end of this class...

            */


           String catName = this.getCategoryNameByID(categoryId);

            if(m.containsKey(catName)){                      // contains key is used to check whether a particular key is being mapped to that Map or not (Hash Map)

                // When Expense is already present for a category

                Float total = m.get(catName);               // existing total of the current category

                total = total + exp.getAmount();              // exp.getAmount() is the recent expense that should be added to the total

                m.put(catName, total);                      // put will replace the old key with the updated key, So we replaced new total with Old Total
                // 'catName' is the 'key' and 'total' is the 'value'

            }

            else{

                //This category is not added, so we add here

                m.put(catName,exp.getAmount());            // All the expenses available in the repo.expList, will be organised and will be added in map 'm'

            }

        }

        return m;                                           // return final result as Map

    }



    /**
     * The method returns the category name for given categoryID
     * and returns null when a wrong categoryId is supplied
     * @param categoryID
     * @return
     */
    //Function used to get the category name from the category ID
    public String getCategoryNameByID(Long categoryID){
        for(Category c: repo.catList){                                          //repo.catList -> We will get the List of Categories

            if(c.getCategoryId().equals(categoryID)){                           //if c.categoryid matches with the id passed by us
                return c.getName();                                             // return the name
            }
        }
        return null;                                                             //No such Category ID Present  (if c.categoryid NOT EQUAL with the id passed by us )
    }
}