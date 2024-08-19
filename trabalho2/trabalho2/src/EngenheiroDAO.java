import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO {
    private Connection conexao;

    public EngenheiroDAO() {
        this.conexao = ConexaoBD.getConexao();
    }

    // Método para inserir um engenheiro no banco de dados
    public void inserir(Engenheiro engenheiro) {
        String sql = "INSERT INTO engenheiro (nome_engenheiro, especialidade) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.execute();
            System.out.println("Engenheiro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um engenheiro no banco de dados
    public void atualizar(Engenheiro engenheiro) {
        String sql = "UPDATE engenheiro SET nome_engenheiro = ?, especialidade = ? WHERE id_engenheiro = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.setInt(3, engenheiro.getIdEngenheiro());
            stmt.executeUpdate();
            System.out.println("Engenheiro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um engenheiro do banco de dados
    public void excluir(int idEngenheiro) {
        String sql = "DELETE FROM engenheiro WHERE id_engenheiro = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEngenheiro);
            stmt.execute();
            System.out.println("Engenheiro excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Engenheiro> listar() {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT * FROM engenheiro";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("id_engenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("nome_engenheiro"));
                engenheiro.setEspecialidade(rs.getString("especialidade"));
                engenheiros.add(engenheiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engenheiros;
    }

    public Engenheiro obterPeloId(int idEngenheiro) {
        String sql = "SELECT * FROM engenheiro WHERE id_engenheiro = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEngenheiro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("id_engenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("nome_engenheiro"));
                engenheiro.setEspecialidade(rs.getString("especialidade"));
                return engenheiro;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o engenheiro não for encontrado
    }
}
