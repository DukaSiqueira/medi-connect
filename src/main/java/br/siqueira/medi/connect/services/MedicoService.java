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
        enderecoService.validateInsertEndereco(medico.getPessoa().getEndereco());
        
        /* Validação de Pessoa */
        PessoaService pessoaService = new PessoaService();
        pessoaService.validateInsertPessoa(medico.getPessoa());
        
        /* Validação do Médico */
        validateInsertMedico(medico);
        
        MedicoRepository medicoRepository = new MedicoRepository();
        return medicoRepository.insert(medico);
    }

    public ArrayList<Medico> index() throws SQLException {
        MedicoRepository medicoRepository = new MedicoRepository();
        return medicoRepository.index();
    }

    public Medico update(Medico medico) throws SQLException {
        /* Validação de Endereço */
        EnderecoService enderecoService = new EnderecoService();
        enderecoService.validateInsertEndereco(medico.getPessoa().getEndereco());
        
        /* Validação de Pessoa */
        PessoaService pessoaService = new PessoaService();
        pessoaService.validateUpdatePessoa(medico.getPessoa());
        
        /* Validação de Especialidade */
        EspecialidadeService especialidadeService = new EspecialidadeService();
        especialidadeService.validateUpdateEspecialidade(medico.getEspecialidade());
        
        /* Validação do Médico */
        validateUpdateMedico(medico);
        
        MedicoRepository medicoRepository = new MedicoRepository();
        return medicoRepository.update(medico);
    }
    
    public void inactive(Medico medico) throws SQLException{
        /* Validação de Pessoa */
        PessoaService pessoaService = new PessoaService();
        pessoaService.validateInactivePessoa(medico.getPessoa());
        
        MedicoRepository medicoRepository = new MedicoRepository();
        medicoRepository.inactive(medico);
    }
    
    private void validateInsertMedico(Medico medico) {
        /* Validação do Médico */
        if (medico.getCrm().isEmpty() ||
                medico.getCrm().isBlank()) {
            throw new IllegalArgumentException("CRM não informado!");
        }
    }
    
    private void validateUpdateMedico(Medico medico) {
        /* Validação de update do médico */
        if (!medico.getCrm().isEmpty() ||
                !medico.getCrm().isBlank()) {
            throw new IllegalArgumentException("CRM não pode ser alterado!");
        }
    }
    
}
