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

    public Consulta() {}

    public Consulta(int id, Paciente paciente, Medico medico, String startdDate, String endDate) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.startdDate = startdDate;
        this.endDate = endDate;
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

    @Override
    public String toString() {
        return "Cosnulta{" + "id=" + id + ", paciente=" + paciente + ", "
                + "medico=" + medico + ", "
                + "startdDate=" + startdDate + ", endDate=" + endDate + '}';
    }
    
}
