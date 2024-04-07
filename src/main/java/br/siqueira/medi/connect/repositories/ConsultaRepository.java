/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.repositories;

import br.siqueira.medi.connect.infraestructure.ConnectionFactory;
import br.siqueira.medi.connect.models.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author eduar
 */
public class ConsultaRepository {
    
    private static final String INSERT =
            "INSERT INTO CONSULTAS (PACIENTE_ID, MEDICO_ID, START_DATE, END_DATE) "
            + "VALUES (?, ?, ?, ?);";
    
    private static final String CHECK_CONSULTAS_DIA_PACIENTE = 
            "SELECT COUNT(*) AS TOTAL "
            + "FROM CONSULTAS "
            + "WHERE CONSULTAS.PACIENTE_ID = ? "
            + "AND DATE(START_DATE) = ? ";
    
    private static final String CHECK_CONSULTAS_DIA_MEDICO = 
            "SELECT COUNT(*) AS TOTAL "
            + "FROM CONSULTAS "
            + "WHERE CONSULTAS.MEDICO_ID = ? "
            + "AND DATE(START_DATE) = ? ";    

    public Consulta insert(Consulta consulta) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            
            ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, consulta.getPaciente().getId());
            ps.setInt(2, consulta.getMedico().getId());
            LocalDateTime startDate = LocalDateTime.parse(consulta.getStartdDate());
            LocalDateTime endDate = startDate.plusHours(1);
            ps.setObject(3, startDate);
            ps.setObject(4, endDate);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            rs.next();
            consulta.setId(rs.getInt(1));

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return consulta;
    }
    
    public int countConsultasDoDiaPaciente(Consulta consulta, LocalDateTime startDate) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(CHECK_CONSULTAS_DIA_PACIENTE);
            ps.setInt(1, consulta.getPaciente().getId());
            ps.setDate(2, java.sql.Date.valueOf(startDate.toLocalDate()));
            rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

        return total;
    }
    
    public int countConsultasDoDiaMedcio(Consulta consulta, LocalDateTime startDate) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(CHECK_CONSULTAS_DIA_MEDICO);
            ps.setInt(1, consulta.getMedico().getId());
            ps.setDate(2, java.sql.Date.valueOf(startDate.toLocalDate()));
            rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt("TOTAL");
            }

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

        return total;
    }
}
