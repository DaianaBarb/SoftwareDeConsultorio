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
import modeloBeans.BeansPaciente;
import modeloBeans.BeansUsuario;

/**
 *
 * @author Daiana Barbosa
 */
public class DaoPaciente {

    ConexaoBD conex = new ConexaoBD();
    //BeansPaciente mod=new BeansPaciente(); 
    public int codbairro;
    public String nomebairro;

    public void salvar(BeansPaciente mod) {
        buscaCodBairro(mod.getBairro_paci());
        conex.conexao();

        try {

            PreparedStatement pst = conex.con.prepareStatement("insert into paciente (nome,nasc,rg,tel,rua,cep,comple,bairrocod) values (?,?,?,?,?,?,?,?)");

            pst.setString(1, mod.getNome_Paci());
            pst.setString(2, mod.getNasc_paci());
            pst.setString(3, mod.getRg_paci());
            pst.setString(4, mod.getTel_paci());
            pst.setString(5, mod.getRua_paci());
            pst.setString(6, mod.getCep_paci());
            pst.setString(7, mod.getComple_paci());
            pst.setInt(8, codbairro);
            pst.execute();
            JOptionPane.showMessageDialog(null, "dados inseridos com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Paciente não cadastrado !");
        }
        conex.desconect();
    }

    public void buscaCodBairro(String nome) {
        try {
            conex.conexao();
            conex.executasql("select * from bairro where nome_bairro = '" + nome + "'");
            conex.rs.first();
            codbairro = conex.rs.getInt("cod_bairro");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao buscar bairro");
        }
        conex.desconect();
    }

    public void buscanomebairro(int cod) {
        try {
            conex.conexao();
            conex.executasql("select * from bairro where cod_bairro = '" + cod + "'");
            conex.rs.first();
            nomebairro = conex.rs.getString("nome_bairro");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao buscar bairro");
        }
        conex.desconect();
    }

    public void editar(BeansPaciente mod) {
        buscaCodBairro(mod.getBairro_paci());
        conex.conexao();
        try {
            PreparedStatement smt = conex.con.prepareStatement(" update paciente set "
                    + "nome=?,nasc=?,rg=?,tel=?,rua=?,cep=?,"
                    + "comple=?,bairrocod=? where cod=?");
            smt.setString(1, mod.getNome_Paci());
            smt.setString(2, mod.getNasc_paci());
            smt.setString(3, mod.getRg_paci());
            smt.setString(4, mod.getTel_paci());
            smt.setString(5, mod.getRua_paci());
            smt.setString(6, mod.getCep_paci());
            smt.setString(7, mod.getComple_paci());
            smt.setInt(8, codbairro);
            smt.setInt(9, mod.getCod_Paci());

            smt.execute();
            JOptionPane.showMessageDialog(null, "editado com sucesso");
        } catch (SQLException ex) {
            System.out.println("erro");
        }
        conex.desconect();

    }

    public void Excluir(BeansPaciente mod) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from paciente where cod=?");
            pst.setInt(1, mod.getCod_Paci());
            pst.execute();
            JOptionPane.showMessageDialog(null, "excluido com sucesso !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao excluir /n" + ex);
        }
        conex.desconect();
    }

    public BeansPaciente buscaPaciente(BeansPaciente mod) {
        conex.conexao();

        conex.executasql("select * from paciente where nome like '%" + mod.getPesquiza() + "%'");
        try {
            conex.rs.first();
            // isso sempre tem que ser na ordem do bando se n da erro !!
            mod.setCod_Paci(conex.rs.getInt("cod"));
            mod.setNome_Paci(conex.rs.getString("nome"));
            mod.setNasc_paci(conex.rs.getString("nasc"));
            mod.setRg_paci(conex.rs.getString("rg"));
            mod.setTel_paci(conex.rs.getString("tel"));
            mod.setRua_paci(conex.rs.getString("rua"));
            mod.setCep_paci(conex.rs.getString("cep"));
            mod.setComple_paci(conex.rs.getString("comple"));
            buscanomebairro(conex.rs.getInt("bairrocod"));
            mod.setBairro_paci(nomebairro);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Paciente nao cadastrado");
        }
        return mod;
    }

}
