/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.services;

import br.siqueira.medi.connect.models.Medico;
import br.siqueira.medi.connect.repositories.MedicoRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eduar
 */
public class MedicoService {
   
    public Medico insert(Medico medico) throws SQLException {
        /* Validação de Endereço */
        EnderecoService enderecoService = new EnderecoService();
        enderecoService.validateEndereco(medico.getPessoa().getEndereco());
        
        
        /* Validação de Pessoa */
        PessoaService pessoaService = new PessoaService();
        pessoaService.validatePessoa(medico.getPessoa());
        
        validateMedico(medico);
        MedicoRepository medicoRepository = new MedicoRepository();
        return medicoRepository.insert(medico);
    }
    
    private void validateMedico(Medico medico) {
        /* Validação do Médico */
        if (medico.getCrm().isEmpty() ||
                medico.getCrm().isBlank()) {
            throw new IllegalArgumentException("CRM não informado!");
        }
    }

    public ArrayList<Medico> index() throws SQLException {
        MedicoRepository medicoRepository = new MedicoRepository();
        return medicoRepository.index();
    }
    
}
