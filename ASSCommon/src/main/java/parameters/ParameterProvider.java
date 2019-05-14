package parameters;

public class ParameterProvider {

    public static final String SERVER_IP_ADDRESS = "localhost";
    public static final String SERVER_PORT_NUMBER = "8080";

    public static final String CLIENT_IP_ADDRESS = "localhost";
    public static final Integer CLIENT_PORT_NUMBER = 8080;

    public static final String DATABASE_CONNECTOR_DATABASE_SERVER_IP="localhost";
    public static final String DATABASE_CONNECTOR_DATABASE_PORT_NUMBER = "1433";
    public static final String DATABASE_CONNECTOR_DATABASE_NAME = "ASSDB001";
    public static final String DATABASE_CONNECTOR_DATABASE_LOGIN = "ASSDBUSER001";
    public static final String DATABASE_CONNECTOR_DATABASE_PASSWORD = "ASS2019";
    public static final String DATABASE_CONNECTOR_DATABASE_URL = "jdbc:sqlserver://"+DATABASE_CONNECTOR_DATABASE_SERVER_IP+":"+DATABASE_CONNECTOR_DATABASE_PORT_NUMBER+";databasename="+DATABASE_CONNECTOR_DATABASE_NAME;

    public static final String WINDOW_TITLE = "Agricultural Support System";
}
