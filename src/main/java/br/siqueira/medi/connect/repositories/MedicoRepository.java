/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.repositories;

import br.siqueira.medi.connect.infraestructure.ConnectionFactory;
import br.siqueira.medi.connect.models.Especialidade;
import br.siqueira.medi.connect.models.Medico;
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
public class MedicoRepository {
    
    private static final String INDEX = 
            "SELECT P.NOME, P.EMAIL, M.CRM, E.DESCRICAO, M.ID M_ID,"
            + "E.ID E_ID, P.ID AS P_ID "
            + "FROM MEDICOS AS M "
            + "INNER JOIN ESPECIALIDADES AS E ON M.ESPECIALIDADE_ID = E.ID "
            + "INNER JOIN PESSOAS AS P ON M.PESSOA_ID = P.ID "
            + "INNER JOIN ENDERECOS ON P.ENDERECO_ID = ENDERECOS.ID "
            + "ORDER BY P.NOME ASC;";
    
    private static final String CHECK_MEDICO_ATIVO =
            "SELECT * "
            + "FROM MEDICOS "
            + "INNER JOIN PESSOAS ON MEDICOS.PESSOA_ID = PESSOAS.ID "
            + "WHERE MEDICOS.ID = ?";
    
    public MedicoRepository() {}
    
    // Chamar os repositories para cada um fazer sua inserção
    public Medico insert (Medico medico) throws SQLException {
        String queryMedico = 
                "INSERT INTO MEDICOS (CRM, ESPECIALIDADE_ID, PESSOA_ID)"
                + "VALUES (?, ?, ?);";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            
            /* Realiza a inserção do Endereço */
            EnderecoRepository enderecoRepository = new EnderecoRepository();
            enderecoRepository.insert(medico.getPessoa().getEndereco());
            
            /* Realiza a inserção da Pessoa */
            PessoaRepository pessoaRepository = new PessoaRepository();
            pessoaRepository.insert(medico.getPessoa());
            

            /* Realiza a inserção do Médico */
            ps = conn.prepareStatement(queryMedico, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, medico.getCrm());
            ps.setInt(2, medico.getEspecialidade().getId());
            ps.setInt(3, medico.getPessoa().getId());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            rs.next();
            medico.setId(rs.getInt(1));
            
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return medico;
    }

    public ArrayList<Medico> index() throws SQLException {
        ArrayList<Medico> medicos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(INDEX);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                Especialidade especialidade = new Especialidade();
                Medico medico = new Medico();
                
                pessoa.setNome(rs.getString("NOME"));
                pessoa.setEmail(rs.getString("EMAIL"));
                pessoa.setId(rs.getInt("P_ID"));
                
                especialidade.setDescricao(rs.getString("DESCRICAO"));
                especialidade.setId(rs.getInt("E_ID"));
                
                medico.setCrm(rs.getString("CRM"));
                medico.setId(rs.getInt("M_ID"));
                medico.setPessoa(pessoa);
                medico.setEspecialidade(especialidade);
                medicos.add(medico);
            }
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        
        return medicos;
    }

    public Medico update(Medico medico) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            
            EnderecoRepository enderecoRepository = new EnderecoRepository();
            enderecoRepository.update(medico.getPessoa().getEndereco());
            
            PessoaRepository pessoaRepository = new PessoaRepository();
            pessoaRepository.update(medico.getPessoa());
            
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        
        return medico;
    }

    public void inactive(Medico medico) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            PessoaRepository pessoaRepository = new PessoaRepository();
            pessoaRepository.inactive(medico.getPessoa());
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
    }

    public Medico checkActive(Medico medico) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(CHECK_MEDICO_ATIVO);
            ps.setInt(1, medico.getId());
            rs = ps.executeQuery();
            
            if (rs.next()) {
               Pessoa pessoa = new Pessoa();
               medico = new Medico();

               pessoa.setNome(rs.getString("NOME"));
               pessoa.setEmail(rs.getString("EMAIL"));
               pessoa.setIs_active(rs.getBoolean("IS_ACTIVE"));

               medico.setCrm(rs.getString("CRM"));
               medico.setPessoa(pessoa);
           }
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        
        return medico;
    }
}
