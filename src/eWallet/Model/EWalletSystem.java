package eWallet.Model;

import java.util.ArrayList;

public class EWalletSystem {
    private final String walletName="Eraa Soft Wallet";

    ArrayList<Account> accounts = new ArrayList<Account>();

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

}
