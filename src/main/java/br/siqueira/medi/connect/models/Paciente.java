/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.models;

/**
 *
 * @author eduar
 */
public class Paciente {
    
    private int id;
    private String cpf;
    private Pessoa pessoa;

    public Paciente() {}

    public Paciente(int id, String cpf, Pessoa pessoa) {
        this.id = id;
        this.cpf = cpf;
        this.pessoa = pessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", cpf=" + cpf + ", pessoa=" + pessoa + '}';
    }
    
}
