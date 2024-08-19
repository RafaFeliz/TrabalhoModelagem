import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static Connection conexao = null;

    private ConexaoBD() {
        try {
            String url = "jdbc:sqlite:banco.db";
            conexao = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConexao() {
        if (conexao == null) {
            new ConexaoBD();
        }
        return conexao;
    }

}
