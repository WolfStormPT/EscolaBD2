import escola.ConexãoBD;

import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public static void inserirAluno(Aluno al)
    {
        ConexãoBD conn = new ConexãoBD();
        Connection c = conn.abrirBD();

        String sql = " Inser into alunos(nome,email,idade,dataNascimento) Values("
                +"'"+al.getNome()+"',"
                +"'"+al.getEmail()+"',"
                +"'"+al.getIdade()+"',"
                +"'"+new Date(al.getDataNascimento().getTime()) + "');";
        Statement stmt = null;
        try{
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Aluno inserido na base de dados com sucesso ... ");
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir o aluno:"+ex);
        }
        finally {
            conn.fecharBD(stmt);
        }
    }

    public static void inserirAlunoPreparado(Aluno al)
    {
        ConexãoBD conn = new ConexãoBD();
        Connection c = conn.abrirBD();

        String sql = "INSERT INTO alunos (nome, email, idade, dataNascimento)"
                + "Values(?,?,?,?);" ;
        PreparedStatement stmt = null;
        try{
            stmt =c.prepareStatement(sql);
            stmt.setString(1,al.getNome());
            stmt.setString(2, al.getEmail());
            stmt.setInt(3, al.getIdade());
            stmt.setDate(4, new Date(al.getDataNascimento().getTime()));
            stmt.execute();
            System.out.println("Aluno inserido ma base de dados com sucesso ...");
        }
        catch (SQLException ex){
            System.out.println("Erro, " + ex);
        }
        finally {
            conn.fecharBD(stmt);
        }
    }

    public static List<Aluno> listarAlunos() {
        ConexãoBD conn = new ConexãoBD();
        Connection c = conn.abrirBD();

        String sql = "SELECT * FROM alunos;";
        Statement stmt = null;
        ResultSet rs = null;
        List<Aluno> alunos = new ArrayList<Aluno>();

        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int num = rs.getInt("numProcesso");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int idade = rs.getInt("idade");
                Date data = rs.getDate("dataNascimento");
                alunos.add(new Aluno(nome, email, idade, data));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar os alunos:" + ex);
        } finally {
            conn.fecharBD(stmt, rs);
        }
        return alunos;
    }
    public void atualizarAlunoPreparado(Aluno al ){
        ConexãoBD conn = new ConexãoBD();
        Connection c = conn.abrirBD();

        String sql = "UPDATE alunos SET nome =?, email = ?, idade = ?, dataNascimento= WHERE numProcesso =?";        PreparedStatement stmt = null;
        try{
            stmt =c.prepareStatement(sql);
            stmt.setString(1,al.getNome());
            stmt.setString(2, al.getEmail());
            stmt.setInt(3, al.getIdade());
            stmt.setDate(4, new Date(al.getDataNascimento().getTime()));
            stmt.setInt(5,al.getNumProcesso());
            stmt.execute();
            System.out.println("Aluno Atualizado ma base de dados com sucesso ...");
        }
        catch (SQLException ex){
            System.out.println("Erro ao atualizar o aluno:  " + ex);
        }
        finally {
            conn.fecharBD(stmt);
        }

    }

    public void eliminarAlunoPreparado(Aluno al){
        ConexãoBD conn = new ConexãoBD();
        Connection c = conn.abrirBD();

        String sql = "DELETE FROM alunos WHERE numProcesso =?";

        PreparedStatement stmt = null;
        try{
            stmt = c.prepareStatement(sql);
            stmt.setInt(1,al.getNumProcesso());
            stmt.execute();
            System.out.println("Aluno eliminado ma base de dados com sucesso ...");
        }
        catch (SQLException ex){
            System.out.println("Erro ao eliminar o aluno:  " + ex);
        }
        finally {
            conn.fecharBD(stmt);
        }
    }

    public static Aluno pesquisarNumProcesso(int num){
        ConexãoBD conn = new ConexãoBD();
        Connection c = conn.abrirBD();

        String sql = "SELECT * FROM alunos WHERE numProcesso = ?;";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aluno al = null;

        try {
            stmt = c.prepareStatement(sql);
            stmt.setInt(1, num);
            rs = stmt.executeQuery();
            while (rs.next()) {
                num = rs.getInt("numProcesso");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int idade = rs.getInt("idade");
                Date data = rs.getDate("dataNascimento");
                al = new Aluno(num, nome, email, idade, data);
            }
        } catch (SQLException e) {

            System.out.println("");
        }
            throw new RuntimeException();
        }

    public static List<Aluno> pesquisarNome(String n){
        ConexãoBD conn = new ConexãoBD();
        Connection c = conn.abrirBD();

        String sql = "SELECT * FROM alunos WHERE nome LIKE % ? %; ";

        PreparedStatement stmt = null;
        ResultSet rs = null;

       List<Aluno> alunos = new ArrayList<>();

        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, n);
            rs = stmt.executeQuery();
            while (rs.next()) {
               int num = rs.getInt("numProcesso");
               String nome = rs.getString("nome");
               String email = rs.getString("email");
               int idade = rs.getInt("idade");
               Date data = rs.getDate("dataNascimento");
               alunos.add(new Aluno(num, nome, email, idade, data));
            }
        } catch (SQLException e) {

            System.out.println("Erro ao pesquisar número de processo");
        }
        finally {
            conn.fecharBD(stmt, rs);
        }
        return alunos;
    }
}
