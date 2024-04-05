/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.services;

import br.siqueira.medi.connect.models.Paciente;
import br.siqueira.medi.connect.repositories.PacienteRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eduar
 */
public class PacienteService {

    public Paciente insert(Paciente paciente) throws SQLException {
        /* Validação de Endereço */
        EnderecoService enderecoService = new EnderecoService();
        enderecoService.validateInsertEndereco(paciente.getPessoa().getEndereco());
        
        /* Validação de Pessoa */
        PessoaService pessoaService = new PessoaService();
        pessoaService.validateInsertPessoa(paciente.getPessoa());
        
        validateInsertPaciente(paciente);
        
        PacienteRepository pacienteRepository = new PacienteRepository();
        return pacienteRepository.insert(paciente);
    }
    
    public ArrayList<Paciente> index() throws SQLException{
        PacienteRepository pacienteRepository = new PacienteRepository();
        return pacienteRepository.index();        
    }

    private void validateInsertPaciente(Paciente paciente) {
        if (paciente.getCpf().isBlank() ||
                paciente.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF não informado!");
        }
    }
    
}
