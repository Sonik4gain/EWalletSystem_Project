package eWallet.Model.impl;

import eWallet.Model.Account;

public interface AccountService {

    boolean createAccount(Account account);

    boolean findAccount(Account account);

}
