/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloConection.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeans.BeansMedico;

/**
 *
 * @author Daiana Barbosa
 */
public class DaoMedico {
    ConexaoBD conex=new ConexaoBD();
    //BeansMedico mod=new BeansMedico();
    
    
    public List<BeansMedico> ListarPorNome(String n){
        conex.conexao();
        try {
            List<BeansMedico> Lista;
            Lista=new ArrayList<BeansMedico>();
            PreparedStatement psm = conex.con.prepareStatement("select * from medicos where nome_medico like '%"+n+"%'");
            ResultSet rs= psm.executeQuery();
            while(rs.next()){
                BeansMedico m=new BeansMedico();
                m.setCodigo(rs.getInt("cod_medico"));
                m.setNome(rs.getString("nome_medico"));
                m.setEspecialidade(rs.getString("especialidade_medico"));
                m.setCrm(rs.getInt("crm_medico"));
                 Lista.add(m);
            }
           
            return Lista;
        } 
        catch (SQLException ex) {
            Logger.getLogger(DaoMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconect();
        return null;
    }
    
    
    public BeansMedico buscaMedico(BeansMedico mod)
    {conex.conexao();
        conex.executasql("select * from medicos where nome_medico like '%"+mod.getPesquiza()+"%'");
        try {
            conex.rs.first();
            mod.setCodigo(conex.rs.getInt("cod_medico"));
            mod.setNome(conex.rs.getString("nome_medico"));
            mod.setCrm(conex.rs.getInt("crm_medico"));
            mod.setEspecialidade(conex.rs.getString("especialidade_medico"));
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, " Medico nao cadastrado");
        }
       return mod;  
    }
    
    public void editar(BeansMedico mod){
        conex.conexao();
        try {
            PreparedStatement pst=conex.con.prepareStatement("update medicos set nome_medico=?,crm_medico=?,especialidade_medico=? where cod_medico=?");
            pst.setString(1, mod.getNome());
            pst.setInt(2, mod.getCrm());
            pst.setString(3, mod.getEspecialidade());
            pst.setInt(4, mod.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "editado com sucesso !");
            
        } catch (SQLException ex) {
                       JOptionPane.showMessageDialog(null, "erro ao editar! /n"+ex);
        }
        conex.desconect();
    }
    public void excluir(BeansMedico mod){
        conex.conexao();
        try {
            PreparedStatement pst =conex.con.prepareStatement("delete from medicos where cod_medico=?");
            pst.setInt(1, mod.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "excluido com sucesso !");
        } catch (SQLException ex) {
  JOptionPane.showMessageDialog(null,"erro ao excluir /n"+ex);
        }
        conex.desconect();
        
    }
    
    
    public void salvar(BeansMedico mod){
        conex.conexao();
        try {
            PreparedStatement pst=conex.con.prepareStatement("insert into medicos (nome_medico,especialidade_medico,crm_medico) values(?,?,?)");
             pst.setString(1, mod.getNome());
             pst.setString(2, mod.getEspecialidade());
             pst.setInt(3, mod.getCrm());
             pst.execute();
             JOptionPane.showMessageDialog(null, "dados inseridos com sucesso");
        } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null,"Medico não cadastrado !");
        }
        conex.desconect();
    }
}
