/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.models;

/**
 *
 * @author eduar
 */
public class Medico {
    
    private int id;
    private String crm;
    private Especialidade especialidade;
    private Pessoa pessoa;

    public Medico() {}

    public Medico(int id, String crm, Especialidade especialidade, Endereco endereco, Pessoa pessoa) {
        this.id = id;
        this.crm = crm;
        this.especialidade = especialidade;
        this.pessoa = pessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Medico{" + "id=" + id + ", crm=" + crm + ", especialidade=" + especialidade + ", pessoa=" + pessoa + '}';
    }

}
