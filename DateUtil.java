package in.application;

// Refer to DateUtility img in the PEM folder

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * This class contains static methods to handle dates
 * @author Atindra
 */
public class DateUtil {


    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};




    /**
     * This method converts string-date into Date object
     * @param dateAsString string formatted date (ex. 11/03/2019:  DD/MM/YYYY)
     * @return returns a DATE object for input date-string
     */
    public static Date stringtoDate(String dateAsString){                                  // This method is taking date in String format and providing Date Object

        try
        {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");               // In JAVA, dd/MM/yyyy is the standard format
            return df.parse(dateAsString);                                                  // parse is a method which takes String as input and coverts date to object
        }

        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }



    /**
     * This method converts Date object to String
     * @param date Date object to be converted to String
     * @return String date in DD/MM/YYYY format
     */
    public static String dateToString(Date date){                                         // This method is taking date as date object and providing as a String

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(date);

    }



    /**
     * The method returns Year and Month from given date in Year, Month-No format :
     * Ex: 2019,01 ; 2019,02 and so on...
     * @param date year and month will be extracted from this date
     * @return return year and month for input date
     */
    public static String getYearAndMonth(Date date) {                               // Static is used to call the function just using '.' and creating no object for it

        SimpleDateFormat df = new SimpleDateFormat("yyyy,MM");        // MMM is used to print the three letter month like JAN, FEB, MAR,...
        return df.format(date);

    }



    /**
     * Returns Year for input date
     * @param date
     * @return
     */
    public static Integer getYear(Date date){                               // Static is used to call the function just using '.' and creating no object for it

        SimpleDateFormat df = new SimpleDateFormat("yyyy");        // MMM is used to print the three letter month like JAN, FEB, MAR,...
        return new Integer(df.format(date));

    }



    /**
     * This method returns month Name for the given month number
     * 01: Jan, 02: Feb,....,12:Dec
     * @param monthNo month number between 1 to 12
     * @return returns month name for input month number
     */
    public static String getMonthName(Integer monthNo){

        return MONTHS[monthNo-1];
    }


}
