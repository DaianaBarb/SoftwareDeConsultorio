/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloBeans;

import java.util.Date;

/**
 *
 * @author Daiana Barbosa
 */
public class BeansAgenda {
    private int cod_agenda;
    private String nomemedico_agenda;
    private String nomepaci_agenda;
    private String turno_agenda;
    private String status;
    private String motivo;
    private Date data;

    public int getCod_agenda() {
        return cod_agenda;
    }

    public void setCod_agenda(int cod_agenda) {
        this.cod_agenda = cod_agenda;
    }

    public String getNomemedico_agenda() {
        return nomemedico_agenda;
    }

    public void setNomemedico_agenda(String nomemedico_agenda) {
        this.nomemedico_agenda = nomemedico_agenda;
    }

    public String getNomepaci_agenda() {
        return nomepaci_agenda;
    }

    public void setNomepaci_agenda(String nomepaci_agenda) {
        this.nomepaci_agenda = nomepaci_agenda;
    }

    public String getTurno_agenda() {
        return turno_agenda;
    }

    public void setTurno_agenda(String turno_agenda) {
        this.turno_agenda = turno_agenda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
