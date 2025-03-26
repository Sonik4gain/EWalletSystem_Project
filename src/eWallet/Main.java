package eWallet;

import eWallet.Model.ApplicationService;
import eWallet.Model.impl.ApplicationServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApplicationServiceImpl ApplicationService = new ApplicationServiceImpl();
        ApplicationService.start();


    }
}
