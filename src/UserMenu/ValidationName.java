package UserMenu;

public class ValidationName {

    public static boolean isValid(String name)
    {
        return ((name!=null) && (!name.equals("")) && (name.matches("^[a-zA-Z]*$")));
    }

}
