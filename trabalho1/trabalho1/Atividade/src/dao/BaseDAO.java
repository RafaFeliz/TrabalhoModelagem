package dao;

import java.sql.Connection;
import java.sql.SQLException;

// DAO -> Data Access Object
class BaseDAO {

    public Connection con() throws SQLException {
        return FabricaDeConexoes.getInstance().getConnection();
    }

}