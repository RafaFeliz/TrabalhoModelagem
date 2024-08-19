import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    private Connection conexao;

    public EquipamentoDAO() {
        this.conexao = ConexaoBD.getConexao();
    }

    // Método para inserir um equipamento no banco de dados
    public void inserir(Equipamento equipamento) {
        String sql = "INSERT INTO equipamento (nome_equipamento, tipo) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.execute();
            System.out.println("Equipamento inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um equipamento no banco de dados
    public void atualizar(Equipamento equipamento) {
        String sql = "UPDATE equipamento SET nome_equipamento = ?, tipo = ? WHERE id_equipamento = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.setInt(3, equipamento.getIdEquipamento());
            stmt.executeUpdate();
            System.out.println("Equipamento atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um equipamento do banco de dados
    public void excluir(int idEquipamento) {
        String sql = "DELETE FROM equipamento WHERE id_equipamento = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEquipamento);
            stmt.execute();
            System.out.println("Equipamento excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os equipamentos do banco de dados
    public List<Equipamento> listar() {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM equipamento";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("id_equipamento"));
                equipamento.setNomeEquipamento(rs.getString("nome_equipamento"));
                equipamento.setTipo(rs.getString("tipo"));
                equipamentos.add(equipamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }

    // Método para obter um equipamento pelo ID
    public Equipamento obterPeloId(int idEquipamento) {
        String sql = "SELECT * FROM equipamento WHERE id_equipamento = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEquipamento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("id_equipamento"));
                equipamento.setNomeEquipamento(rs.getString("nome_equipamento"));
                equipamento.setTipo(rs.getString("tipo"));
                return equipamento;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o equipamento não for encontrado
    }
}
