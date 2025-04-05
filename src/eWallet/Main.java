package eWallet;

import eWallet.Model.Interfaces.ApplicationService;
import eWallet.Model.impl.ApplicationServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApplicationServiceImpl appService = new ApplicationServiceImpl();
        appService.start();


    }
}
