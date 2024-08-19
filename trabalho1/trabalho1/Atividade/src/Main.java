import dao.AutorDAO;
import dao.LivroDAO;
import entity.Autor;
import entity.Livro;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Obtenção da conexão com o banco de dados usando Singleton
        Connection connection = util.ConexaoBD.getInstancia().getConnection();

        // Instanciação dos DAOs
        AutorDAO autorDAO = new AutorDAO(connection);
        LivroDAO livroDAO = new LivroDAO(connection);

        try {
            // Criação das tabelas Autor e Livro
            String createAutor = """
                    CREATE TABLE IF NOT EXISTS autor (
                        id_autor INTEGER PRIMARY KEY AUTOINCREMENT,
                        nome VARCHAR(250),
                        nacionalidade VARCHAR(100)
                    )
                    """;
            String createLivro = """
                    CREATE TABLE IF NOT EXISTS livro (
                        id_livro INTEGER PRIMARY KEY AUTOINCREMENT,
                        titulo VARCHAR(250),
                        ano_publicacao INTEGER,
                        id_autor INTEGER,
                        FOREIGN KEY(id_autor) REFERENCES autor(id_autor)
                    )
                    """;

            Statement stat = connection.createStatement();
            stat.execute(createAutor);
            stat.execute(createLivro);

            // Inserção de um autor
            Autor autor = new Autor();
            autor.setNome("Machado de Assis");
            autor.setNacionalidade("Brasileiro");
            autorDAO.inserir(autor);

            // Inserção de um livro associado ao autor
            Livro livro = new Livro();
            livro.setTitulo("Pequeno Principe");
            livro.setAnoPublicacao(1990);
            livro.setIdAutor(autor.getIdAutor());
            livroDAO.inserir(livro);

            // Atualização de um livro
            livro.setTitulo("Pequeno Principe");
            livroDAO.atualizar(livro);

            // Listagem de todos os livros de um autor específico
            System.out.println("Livros do autor " + autor.getNome() + ":");
            var livros = autorDAO.listarLivrosPorAutor(autor.getIdAutor());
            for (Livro l : livros) {
                System.out.println(l.getTitulo() + " (" + l.getAnoPublicacao() + ")");
            }

            // Deletando um livro
            livroDAO.excluir(livro.getIdLivro());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}