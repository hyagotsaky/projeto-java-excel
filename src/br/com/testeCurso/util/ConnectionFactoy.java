package br.com.testeCurso.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactoy {
    private static final String urlBase = "";
    private static final String usuario = "root";
    private static final String senha = "";
    
    public static Connection getConexao(){
        Connection conexao = null;
      
        try {
            conexao = DriverManager.getConnection(urlBase, usuario, usuario);
            if(conexao != null)
            System.err.println("Conexao realizada com sucesso");
            
        } catch (Exception e) {
                        if(conexao != null)
            System.err.println("erro");
        }
        return conexao;
        
    };
    public static void closeConnection(Connection conn , PreparedStatement ps ,ResultSet rs){
       close(conn,ps,rs);
    }
    
    public static void closeConnection(Connection conn , PreparedStatement ps ){
       close(conn,ps,null);
    }
    
    public static void closeConnection(Connection conn ){
       close(conn, null, null);
    }

    private static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if(conn != null){
                conn.close();
            }
            if(ps != null){
                ps.close();
            }
            if (rs != null){
                rs.close();
            }
        } catch (Exception e) {
            System.err.println("Aconteceu um erro");
        }
    }
}
