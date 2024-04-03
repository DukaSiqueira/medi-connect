/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.repositories;

import br.siqueira.medi.connect.infraestructure.ConnectionFactory;
import br.siqueira.medi.connect.models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author eduar
 */
public class PessoaRepository {
    
    public PessoaRepository() {}
    
    public Pessoa insert(Pessoa pessoa) throws SQLException {
        String query = 
                "INSERT INTO PESSOAS (NOME, EMAIL, TELEFONE, ENDERECO_ID)"
                + "VALUES (?, ?, ?, ?);";
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = new ConnectionFactory().getConnection();
            
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, pessoa.getTelefone());
            ps.setInt(4, pessoa.getEndereco().getId());
            ps.executeUpdate();
            
            rs = ps.getGeneratedKeys();
            rs.next();
            pessoa.setId(rs.getInt(1));
            
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return pessoa;        
    }
    
}
