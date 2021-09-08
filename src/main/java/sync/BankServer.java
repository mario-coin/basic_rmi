package src.main.java.sync;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import src.main.java.BankRemote;
import src.main.java.BankRemoteConstants;

public class BankServer extends UnicastRemoteObject implements BankRemote {

    static final long sleepSeconds = 1000;
    private double overallBalance;

    public BankServer() throws RemoteException {
        super(BankRemoteConstants.RemoteServerPort);
        overallBalance = 0;
    }

    private void delayMethod()
    {
        try
        {
            Thread.sleep(sleepSeconds);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public synchronized double getBalance() throws RemoteException {
        delayMethod();

        return overallBalance;
    }

    @Override
    public synchronized String withdraw(double value) throws RemoteException {
        delayMethod();
        
        if (value <= overallBalance && value > 0) {
            overallBalance -= value;
            return String.format("%3.2f sacados com sucesso.", value);
        }

        return "Saldo insuficiente.";
    }

    @Override
    public synchronized String deposit(double value) throws RemoteException {
        delayMethod();

        if (value > 0) {
            overallBalance += value;
            return String.format("Depósito de %3.2f efetuado com sucesso.", value);
        }

        return "Valor inválido para depósito.";
    }

    public static void main(String[] args) {
        try {
            BankServer server = new BankServer();
            String serverUri = BankRemoteConstants.GetRemoteServerUri();
            LocateRegistry.createRegistry(BankRemoteConstants.RemoteServerPort);
            Naming.rebind(serverUri, server);
            System.out.println("BankServer running at " + serverUri);

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
