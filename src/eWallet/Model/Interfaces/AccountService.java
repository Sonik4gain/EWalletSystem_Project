package eWallet.Model.Interfaces;

import eWallet.Model.impl.Account;


public interface AccountService {

    boolean createAccount(Account account);

    boolean findAccount(Account account);

}
