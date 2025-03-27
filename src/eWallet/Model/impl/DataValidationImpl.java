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
        char ch;
        // ToDO: check if password Size >=6 and must contain number, upper char , lower char and special char.
        if (password.length() < 6 ) {
            System.out.println("Password must be at least 6 characters long");
            return false;
        }
        for (int i =0; i < password.length(); i++){
            ch = password.charAt(i);
            if (Character.isDigit(ch) && Character.isUpperCase(ch) && Character.isLowerCase(ch)){
                return true;
            }
            else {
                return false;
           }
        }
        return true;
    }

}
