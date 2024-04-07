/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.models;

import java.sql.Timestamp;

/**
 *
 * @author eduar
 */
public class Consulta {
    
    private int id;
    private Paciente paciente;
    private Medico medico;
    private String startdDate;
    private String endDate;
    private String status;
    private String motivo_cancelamento;
    private String dateCancelamento;

    public Consulta() {}

    public Consulta(int id, Paciente paciente, Medico medico, String startdDate, String endDate, String status, String motivo_cancelamento, String dateCancelamento) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.startdDate = startdDate;
        this.endDate = endDate;
        this.status = status;
        this.motivo_cancelamento = motivo_cancelamento;
        this.dateCancelamento = dateCancelamento;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getStartdDate() {
        return startdDate;
    }

    public void setStartdDate(String startdDate) {
        this.startdDate = startdDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo_cancelamento() {
        return motivo_cancelamento;
    }

    public void setMotivo_cancelamento(String motivo_cancelamento) {
        this.motivo_cancelamento = motivo_cancelamento;
    }

    public String getDateCancelamento() {
        return dateCancelamento;
    }

    public void setDateCancelamento(String dateCancelamento) {
        this.dateCancelamento = dateCancelamento;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", paciente=" + paciente + ", medico=" + medico + ", startdDate=" + startdDate + ", endDate=" + endDate + ", status=" + status + ", motivo_cancelamento=" + motivo_cancelamento + ", dateCancelamento=" + dateCancelamento + '}';
    }
    
}