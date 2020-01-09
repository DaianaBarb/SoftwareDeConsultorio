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
import modeloBeans.BeansUsuario;

/**
 *
 * @author Daiana Barbosa
 */
public class DaoUsuario {

    ConexaoBD conex = new ConexaoBD();

    //BeansUsuario mod=new BeansUsuario();
    public List<BeansUsuario> ListarUsuarios() {

        try {
            List<BeansUsuario> lista;
            lista = new ArrayList<BeansUsuario>();
            String sql = "select * from usuarios";
            PreparedStatement pst = conex.con.prepareStatement("select * from usuarios");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeansUsuario c = new BeansUsuario();
                c.setCod(rs.getInt("cod_usu"));
                c.setNome(rs.getString("nome_usu"));
                c.setSenha(rs.getString("senha_usu"));
                c.setTipo(rs.getString("tipo_usu"));

                lista.add(c);
            }
            return lista;

        } catch (SQLException ex) {
            System.out.println("erro ao retornar lista");
        }

        conex.desconect();
        return null;

    }

    public BeansUsuario buscaUsuario(BeansUsuario mod) {
        conex.conexao();
        conex.executasql("select * from usuarios where nome_usu like '%" + mod.getPesquiza() + "%'");
        try {
            conex.rs.first();
            mod.setCod(conex.rs.getInt("cod_usu"));
            mod.setNome(conex.rs.getString("nome_usu"));
            mod.setSenha(conex.rs.getString("senha_usu"));
            mod.setTipo(conex.rs.getString("tipo_usu"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " usuario nao cadastrado");
        }
        return mod;
    }

    public void editar(BeansUsuario mod) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("update usuarios set nome_usu=?,senha_usu=?,tipo_usu=? where cod_usu=?");

            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getSenha());
            pst.setString(3, mod.getTipo());
            pst.setInt(4, mod.getCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "editado com sucesso !");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao editar! /n" + ex);
        }
        conex.desconect();
    }

    public void excluir(BeansUsuario mod) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from usuarios where cod_usu=?");
            pst.setInt(1, mod.getCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "excluido com sucesso !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao excluir /n" + ex);
        }
        conex.desconect();

    }

    public void salvar(BeansUsuario mod) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("insert into usuarios (nome_usu,senha_usu,tipo_usu) values(?,?,?)");

            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getSenha());
            pst.setString(3, mod.getTipo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "dados inseridos com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Medico não cadastrado !");
        }
        conex.desconect();
    }
}
