/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.repositories;

import br.siqueira.medi.connect.infraestructure.ConnectionFactory;
import br.siqueira.medi.connect.models.Paciente;
import br.siqueira.medi.connect.models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author eduar
 */
public class PacienteRepository {
    
    private static final String INSERT =
            "INSERT INTO PACIENTES (CPF, PESSOA_ID) "
            + "VALUES (?, ?)";
   
    private static final String INDEX = 
            "SELECT P.NOME, P.EMAIL, PC.CPF, PC.ID PC_ID,"
            + "P.ID AS P_ID "
            + "FROM PACIENTES AS PC "
            + "INNER JOIN PESSOAS AS P ON PC.PESSOA_ID = P.ID "
            + "INNER JOIN ENDERECOS ON P.ENDERECO_ID = ENDERECOS.ID "
            + "ORDER BY P.NOME ASC;";
    
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

    public ArrayList<Paciente> index() throws SQLException{
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(INDEX);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                Paciente paciente = new Paciente();
                
                pessoa.setNome(rs.getString("NOME"));
                pessoa.setEmail(rs.getString("EMAIL"));
                pessoa.setId(rs.getInt("P_ID"));
                
                paciente.setCpf(rs.getString("CPF"));
                paciente.setId(rs.getInt("PC_ID"));
                paciente.setPessoa(pessoa);
                pacientes.add(paciente);
            }
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        
        return pacientes;
    }

    public Paciente update(Paciente paciente) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            
            EnderecoRepository enderecoRepository = new EnderecoRepository();
            enderecoRepository.update(paciente.getPessoa().getEndereco());
            
            PessoaRepository pessoaRepository = new PessoaRepository();
            pessoaRepository.update(paciente.getPessoa());
            
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
