package in.application;
import java.io.Serializable;
import java.util.Date;



/**
 * This is a domain class represents Expense
 * @author Atindra
 */
public class Expense implements Serializable {                                  // this is not writable....
                                                                                // Until we don't make it serializable, we cannot write to the file
                                                                                // if not made serializable, objects cannot be transported out of JVM



    /**
     * A unique expense id, here it's auto-generated as current milliseconds
     */
    private Long expenseId = System.currentTimeMillis();                          //Automatic generation



    /**
     * Represents a category of this expense
     */
    private Long categoryId;                                                      //Foreign Key


    private Float amount;


    private Date date;


    private String remark;                                                       //Description




    public Expense(){

    }


    public Expense(Long categoryId, Float amount, Date date, String remark) {

        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.remark = remark;

    }


    public Long getExpenseId() {

        return expenseId;

    }


    public void setExpenseId(Long expenseId) {

        this.expenseId = expenseId;

    }


    public Long getCategoryId() {

        return categoryId;

    }


    public void setCategoryId(Long categoryId) {

        this.categoryId = categoryId;

    }


    public Float getAmount() {

        return amount;

    }


    public void setAmount(Float amount) {

        this.amount = amount;

    }


    public Date getDate() {

        return date;

    }


    public void setDate(Date date) {

        this.date = date;

    }


    public String getRemark() {

        return remark;

    }


    public void setRemark(String remark) {

        this.remark = remark;

    }
}
