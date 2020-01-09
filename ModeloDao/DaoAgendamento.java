/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDao;

import ModeloConection.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeans.BeansAgenda;

/**
 *
 * @author Daiana Barbosa
 */
public class DaoAgendamento {
    ConexaoBD conex=new ConexaoBD();
    int codmed,codpaci;
    
    public void salvar(BeansAgenda agenda){
        buscarmedico(agenda.getNomemedico_agenda());
        buscapaciente(agenda.getNomepaci_agenda());
        conex.conexao();
        try {
            PreparedStatement pst =conex.con.prepareStatement("insert into agendamento (turno_agenda,codmedico_agenda,data_agenda,motivo_agenda,status_agenda,codpaci_agenda) values (?,?,?,?,?,?)");
            pst.setString(1, agenda.getTurno_agenda());
            pst.setInt(2, codmed);
            pst.setDate(3, new java.sql.Date(agenda.getData().getTime()) );
            pst.setString(4, agenda.getMotivo());
            pst.setString(5, agenda.getStatus());
            pst.setInt(6, codpaci);
            pst.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        conex.desconect();
        
    }
    public void buscapaciente(String  nomepaciente){
       conex.conexao();
       conex.executasql("select *from paciente where nome ='"+nomepaciente+"'");
        try {
            conex.rs.first();
            codpaci=conex.rs.getInt("cod");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "paciente n encontrado");
        }
       conex.desconect();
    }
    public void buscarmedico(String nomemedico){
        conex.conexao();
        conex.executasql("select *from medicos where nome_medico='"+nomemedico+"'");
        try {
            conex.rs.first();
            codmed=conex.rs.getInt("cod_medico");
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "medico n encontrado");
        }
        conex.desconect();
    }
    
}
