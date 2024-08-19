import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    private Connection conexao;

    public MaterialDAO() {
        this.conexao = ConexaoBD.getConexao();
    }

    // Método para inserir um material no banco de dados
    public void inserir(Material material) {
        String sql = "INSERT INTO material (nome_material, quantidade) VALUES (?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.execute();
            System.out.println("Material inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um material no banco de dados
    public void atualizar(Material material) {
        String sql = "UPDATE material SET nome_material = ?, quantidade = ? WHERE id_material = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.setInt(3, material.getIdMaterial());
            stmt.executeUpdate();
            System.out.println("Material atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um material do banco de dados
    public void excluir(int idMaterial) {
        String sql = "DELETE FROM material WHERE id_material = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMaterial);
            stmt.execute();
            System.out.println("Material excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Material> listar() {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM material";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("id_material"));
                material.setNomeMaterial(rs.getString("nome_material"));
                material.setQuantidade(rs.getInt("quantidade"));
                materiais.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiais;
    }

    public Material obterPeloId(int idMaterial) {
        String sql = "SELECT * FROM material WHERE id_material = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMaterial);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("id_material"));
                material.setNomeMaterial(rs.getString("nome_material"));
                material.setQuantidade(rs.getInt("quantidade"));
                return material;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o material não for encontrado
    }
}
