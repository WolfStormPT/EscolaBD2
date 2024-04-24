package escola;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class ConexãoBD {
    private static String driverJDBC = "com.mysql.cj.jdbc.Driver";
    private static String user = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/escola";
    private Connection conn = null;

    public Connection abrirBD() {

        try{
            Class.forName(driverJDBC);
            conn = DriverManager.getConnection(url,user,password);
            if(conn != null){
                System.out.println("Ligação à base de dados...OK");
            }
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            System.out.println("Erro na ligaçao à base de dados!!!:" +ex);
        }
        return conn;
    }

    public void fecharBD(){
        try{

        if(conn != null){
            conn.close();
        }
    } catch (SQLException ex){
        System.out.println("Erro ao fechar a ligação à base de dados:"+ex);
        }
    }

    public void fecharBD(Statement stmt) {
        fecharBD();
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar a ligação à base de dados:" + ex);
        }
    }

    public void fecharBD(Statement stmt, ResultSet rs){
        fecharBD(stmt);
        try{
            if (rs != null){
                rs.close();
            }
        }catch(SQLException ex){
            System.out.println("Erro ao fechar a ligação á base de dados :" +ex);
        }
    }
}
