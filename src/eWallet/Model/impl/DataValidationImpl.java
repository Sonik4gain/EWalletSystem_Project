package eWallet.Model.impl;

public class DataValidationImpl implements DataValidation {
    @Override
    public boolean validateUsername(String username) {

        // Username must be at least 3 characters long
        if (username.length() < 3) {
            return false;
        }
        // username must contain UpperCase first letter
        if (!Character.isUpperCase(username.charAt(0))) {
            return false;
        }
        else return true;
    }

    @Override
    public boolean validatePassword(String password) {
        // ToDO: check if password Size >=6 and must contain number, upper char , lower char and special char.
        boolean hasUpper = false; // we make this true if we find an upper case letter
        boolean hasLower = false; // we make this true if we find a lower case letter
        boolean hasDigit = false; // we make this true if we find a digit
        boolean hasSpecial = false; // finally we make this true if we find a special character
        if (password.length() < 6) { // this is for checking the length of the password
            System.out.println("Password must be at least 6 characters long.");
            return false;
        }
        for (char ch : password.toCharArray()) { // we use password.toCharArray() to convert the password into a char array then we check using advanced for loop (the one used in arrays most of the time)
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSpecial = true;
        }

        if (!hasUpper) System.out.println("Password must contain at least one uppercase letter.");
        if (!hasLower) System.out.println("Password must contain at least one lowercase letter.");
        if (!hasDigit) System.out.println("Password must contain at least one digit.");
        if (!hasSpecial) System.out.println("Password must contain at least one special character.");

        // Return true only if all conditions are met
        return hasUpper && hasLower && hasDigit && hasSpecial;

    }
}
