/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloConection;
import java.sql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Daiana Barbosa
 */
public class ConexaoBD {
   public Statement stm;
   public ResultSet rs;
   public Connection con;
  private String driver="org.postgresql.Driver",
          caminho="jdbc:postgresql://localhost:5432/projetoclinica",
          usuario="postgres",
          senha="admin";
  public void conexao(){
      System.setProperty("jdbc.Drivers",driver);
       try {
           con=DriverManager.getConnection(caminho, usuario, senha);
           System.out.println("conectado com sucesso");
           //JOptionPane.showMessageDialog(null, "conectado com sucesso!");
       } catch (SQLException ex) {
           System.out.println("erro");
         //JOptionPane.showMessageDialog(null, "nao conectato\n"+ex);
       }
}
  public void executasql(String sql){
       try {
           stm=con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
           rs=stm.executeQuery(sql);
       } catch (SQLException ex) {
           Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  }
  
  public void desconect (){
       try {
           con.close();
           System.out.println("descnectado");
          // JOptionPane.showMessageDialog(null,"conexao fechada com sucesso");
        
       
       } catch (SQLException ex) {
           System.out.println("erro");
        //JOptionPane.showMessageDialog(null, "nao foi possivel fechar a conexao \n"+ex);
       }
  }
}
