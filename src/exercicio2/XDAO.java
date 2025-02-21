package exercicio2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class XDAO {
    private final String URL = "jdbc:postgresql://localhost:5432/seu_banco";
    private final String USER = "postgres";
    private final String PASSWORD = "mc110534$";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void inserir(X x) {
        String sql = "INSERT INTO X (nome, idade) VALUES (?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, x.getNome());
            stmt.setInt(2, x.getIdade());
            stmt.executeUpdate();
            System.out.println("Registro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<X> listar() {
        List<X> lista = new ArrayList<>();
        String sql = "SELECT * FROM X";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                X x = new X();
                x.setId(rs.getInt("id"));
                x.setNome(rs.getString("nome"));
                x.setIdade(rs.getInt("idade"));
                lista.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(X x) {
        String sql = "UPDATE X SET nome = ?, idade = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, x.getNome());
            stmt.setInt(2, x.getIdade());
            stmt.setInt(3, x.getId());
            stmt.executeUpdate();
            System.out.println("Registro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM X WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Registro excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}