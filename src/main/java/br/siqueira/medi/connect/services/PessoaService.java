/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.services;

import br.siqueira.medi.connect.models.Pessoa;
import br.siqueira.medi.connect.repositories.PessoaRepository;
import java.sql.SQLException;

/**
 *
 * @author eduar
 */
public class PessoaService {
    
    public Pessoa insert(Pessoa pessoa) throws SQLException {
        PessoaRepository pessoaRepository = new PessoaRepository();
        return pessoaRepository.insert(pessoa);
    }
    
    public void validateInsertPessoa(Pessoa pessoa) {
        if (pessoa.getNome().isEmpty() ||
                pessoa.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome não informado!");
        }
        
        if (pessoa.getEmail().isEmpty() ||
                pessoa.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail não informado!");
        }
        
        if (pessoa.getTelefone().isEmpty() ||
                pessoa.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Telefone não informado!");
        }
    }
    
    
    public void validateUpdatePessoa(Pessoa pessoa) {
        if (!pessoa.getEmail().isEmpty() ||
                !pessoa.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-mail não pode ser alterado!");
        }
    }
    
    public void validateInactivePessoa(Pessoa pessoa) {
        if (pessoa.getId() == 0) {
            throw new IllegalArgumentException("Pessoa ID não informado para"
                    + "realizar inativação!");
        }
    }
    
}
