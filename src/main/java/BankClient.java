package main.java;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankClient {

    private static final Integer clientsCount = 10;
    private static BankRemote server = null;

    public BankClient() {
        try {
            String serverUri = BankRemoteConstants.GetRemoteClientUri();
            server = (BankRemote) Naming.lookup(serverUri);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    void deposit(double value) {
        try {
            System.out.println(server.deposit(value));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    void withdraw(double value) {
        try {
            System.out.println(server.withdraw(value));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    void getBalance() {
        try {
            System.out.println(server.getBalance());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public static void main(String args[])
    {
        List<BankClient> clients = Collections.synchronizedList(new ArrayList<BankClient>());
        
        for (int i = 0; i < clientsCount; i++) {
            clients.add(new BankClient());
        }

        clients.forEach(client ->
            new Thread(() -> {
                double depositValue = 5;
                double withdrawValue = 5;

                client.deposit(depositValue);
                client.withdraw(withdrawValue);
                client.getBalance();
        }).start());
    }
}
