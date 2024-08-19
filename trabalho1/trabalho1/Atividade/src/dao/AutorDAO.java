
package dao;


import entity.Autor;
import entity.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private final Connection connection;

    public AutorDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Autor autor) throws SQLException {
        String query = "INSERT INTO autor(nome, nacionalidade) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                autor.setIdAutor(generatedKeys.getInt(1));
            }
        }
    }

    public List<Livro> listarLivrosPorAutor(int idAutor) throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String query = "SELECT * FROM livro WHERE id_autor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro();
                    livro.setIdLivro(rs.getInt("id_livro"));
                    livro.setTitulo(rs.getString("titulo"));
                    livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                    livro.setIdAutor(idAutor);
                    livros.add(livro);
                }
            }
        }
        return livros;
    }
}