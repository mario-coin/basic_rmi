package src.main.java;

public final class BankRemoteConstants {
    
    private static final String RemoteServerPrefix = "rmi";
    private static final String RemoteServerUrl = "localhost";
    public static final Integer RemoteServerPort = 1040;
    private static final String RemoteServerUrn = "BankRemoteService";

    public static final String GetRemoteServerUri()
    {
        return RemoteServerPrefix + "://" + RemoteServerUrl + ":" + RemoteServerPort + "/" + RemoteServerUrn;
    }

    public static final String GetRemoteClientUri()
    {
        return "//" + RemoteServerUrl + ":" + RemoteServerPort + "/" + RemoteServerUrn;
    }
}
