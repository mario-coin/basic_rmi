package src.main.java;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankRemote extends Remote {
    double getBalance() throws RemoteException;
    String withdraw(double value) throws RemoteException;
    String deposit(double value) throws RemoteException;
}
