package in.application;

/**
 * This class is an entry point for PersonalExpenseManager Application (PEMApp).
 * @author Atindra
 */





public class StartApp {

    /**
     * This method is creating <code>PEMService</code> object
     * and show app menu by calling showMenu() method
     * @param args
     */
    public static void main(String[] args){

        PEMService service = new PEMService();         // After object is created, constructor is called

        service.showMenu();           //This will call the showMenu method present in the PEMService to show the menu and
                                      //and ask the user for the choice
    }
}
