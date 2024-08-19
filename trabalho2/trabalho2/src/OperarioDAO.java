import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO {
    private Connection conexao;

    public OperarioDAO() {
        this.conexao = ConexaoBD.getConexao();
    }

    // Método para inserir um operário no banco de dados
    public void inserir(Operario operario) {
        String sql = "INSERT INTO operario (nome_operario, funcao) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.execute();
            System.out.println("Operário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um operário no banco de dados
    public void atualizar(Operario operario) {
        String sql = "UPDATE operario SET nome_operario = ?, funcao = ? WHERE id_operario = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.setInt(3, operario.getIdOperario());
            stmt.executeUpdate();
            System.out.println("Operário atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um operário do banco de dados
    public void excluir(int idOperario) {
        String sql = "DELETE FROM operario WHERE id_operario = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idOperario);
            stmt.execute();
            System.out.println("Operário excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Operario> listar() {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT * FROM operario";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("id_operario"));
                operario.setNomeOperario(rs.getString("nome_operario"));
                operario.setFuncao(rs.getString("funcao"));
                operarios.add(operario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operarios;
    }


    public Operario obterPeloId(int idOperario) {
        String sql = "SELECT * FROM operario WHERE id_operario = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idOperario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("id_operario"));
                operario.setNomeOperario(rs.getString("nome_operario"));
                operario.setFuncao(rs.getString("funcao"));
                return operario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o operário não for encontrado
    }
}
