/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.services;

import br.siqueira.medi.connect.models.Endereco;
import br.siqueira.medi.connect.repositories.EnderecoRepository;
import java.sql.SQLException;

/**
 *
 * @author eduar
 */
public class EnderecoService {
    
    public Endereco insert(Endereco endereco) throws SQLException {
        EnderecoRepository enderecoRepository = new EnderecoRepository();
        return enderecoRepository.insert(endereco);
    }
    
    public void validateInsertEndereco(Endereco endereco) {
        if (endereco.getLogradouro().isEmpty() ||
                endereco.getLogradouro().isBlank()) {
            throw new IllegalArgumentException("Logradouro não informado!");
        }
        
        if (endereco.getBairro().isEmpty() ||
                endereco.getBairro().isBlank()) {
            throw new IllegalArgumentException("Bairro não informado!");
        }
    }
    
}
