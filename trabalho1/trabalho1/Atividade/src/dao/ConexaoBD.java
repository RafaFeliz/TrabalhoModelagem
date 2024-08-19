package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instancia;
    private Connection connection;
    private final String url = "jdbc:sqlite:banco.bd";

    private ConexaoBD() throws SQLException {
        this.connection = DriverManager.getConnection(url);
    }

    public static ConexaoBD getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new ConexaoBD();
        } else if (instancia.getConnection().isClosed()) {
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}