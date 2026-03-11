package platform;

import java.util.regex.*;

public class InputAuthenticator{
    public final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", //Standard pattern for emails.
        Pattern.CASE_INSENSITIVE
    );

    public final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[a-z])" +      // at least one lowercase
        "(?=.*[A-Z])" +       // at least one uppercase
        "(?=.*\\d)" +         // at least one digit
        "[A-Za-z\\d@$!%*?&]{8,}$" // minimum 8 characters (special char optional)
    );

    public boolean isValidEmail(String email){
        if(email == null)
            return false;

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(String password){
        if(password == null)
            return false;

        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}