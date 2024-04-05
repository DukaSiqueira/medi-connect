/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.repositories;

import br.siqueira.medi.connect.infraestructure.ConnectionFactory;
import br.siqueira.medi.connect.models.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author eduar
 */
public class PacienteRepository {
    
    private static final String INSERT =
            "INSERT INTO PACIENTES (CPF, PESSOA_ID) "
            + "VALUES (?, ?)";

    public Paciente insert(Paciente paciente) throws SQLException{
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            
            /* Realiza a inserção do Endereço */
            EnderecoRepository enderecoRepository = new EnderecoRepository();
            enderecoRepository.insert(paciente.getPessoa().getEndereco());
            
            /* Realiza a inserção da Pessoa */
            PessoaRepository pessoaRepository = new PessoaRepository();
            pessoaRepository.insert(paciente.getPessoa());
            

            /* Realiza a inserção do Médico */
            ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paciente.getCpf());
            ps.setInt(2, paciente.getPessoa().getId());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            rs.next();
            paciente.setId(rs.getInt(1));
            
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return paciente;
    }
    
}
