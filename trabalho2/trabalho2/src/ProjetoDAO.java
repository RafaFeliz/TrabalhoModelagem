import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection conexao;

    public ProjetoDAO() {
        this.conexao = ConexaoBD.getConexao();
    }

    public void inserir(Projeto projeto) {
        String sql = "INSERT INTO projeto (nome_projeto, local, data_inicio, data_termino) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(projeto.getDataTermino().getTime()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Projeto> listar() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projeto";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("id_projeto"));
                projeto.setNomeProjeto(rs.getString("nome_projeto"));
                projeto.setLocal(rs.getString("local"));
                projeto.setDataInicio(rs.getDate("data_inicio"));
                projeto.setDataTermino(rs.getDate("data_termino"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }

    // Métodos atualizar, excluir, etc.
}