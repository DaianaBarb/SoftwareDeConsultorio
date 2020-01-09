/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloBeans;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Daiana Barbosa
 */
public class ModeloTabela extends AbstractTableModel {
private ArrayList linhas=null;
private String[] colunas=null;

    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

public ModeloTabela(ArrayList lin,String[] col){
    setLinhas(lin);
    setColunas(col);
}
    @Override
    public int getRowCount() {
    return linhas.size();
    }

    @Override
    public int getColumnCount() {
   return colunas.length;
   
    }

@Override
    public String getColumnName(int numCol) {
return colunas[numCol];
    }
@Override
    public Object getValueAt(int numLin, int numCol){
       Object[] linha;
    linha = (Object[])getLinhas().get(numLin);
       return linha[numCol];
    }
    
}
