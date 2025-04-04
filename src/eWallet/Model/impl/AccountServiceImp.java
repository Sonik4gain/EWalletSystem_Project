package eWallet.Model.impl;
import eWallet.Model.Account;
import eWallet.Model.EWalletSystem;
import java.util.List;
public class AccountServiceImp implements AccountService {

    private static final EWalletSystem walletSystem = new EWalletSystem();



    @Override
    public boolean createAccount(Account account) {
        List<Account> accounts = walletSystem.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(account.getUsername())) {
                return false;
            }
        }
        walletSystem.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean findAccount(Account account) {
        List<Account> accounts = walletSystem.getAccounts();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(account.getUsername())
                && accounts.get(i).getPassword().equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
