package dao;

import entity.Livro;

import java.sql.*;

public class LivroDAO {
    private final Connection connection;

    public LivroDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Livro livro) throws SQLException {
        String query = "INSERT INTO livro(titulo, ano_publicacao, id_autor) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdAutor());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                livro.setIdLivro(generatedKeys.getInt(1));
            }
        }
    }

    public void atualizar(Livro livro) throws SQLException {
        String query = "UPDATE livro SET titulo = ?, ano_publicacao = ? WHERE id_livro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdLivro());
            stmt.executeUpdate();
        }
    }

    public void excluir(int idLivro) throws SQLException {
        String query = "DELETE FROM livro WHERE id_livro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idLivro);
            stmt.executeUpdate();
        }
    }
}