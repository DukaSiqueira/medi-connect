/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.siqueira.medi.connect.interfaces;

import br.siqueira.medi.connect.models.Endereco;
import br.siqueira.medi.connect.models.Medico;
import br.siqueira.medi.connect.models.Paciente;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eduar
 */
@WebService
public interface MediConnectWebService {
    
    @WebMethod
    Endereco insertEndereco(Endereco endereco) throws SQLException;
    
    @WebMethod
    Medico insertMedico(Medico medico) throws SQLException;
    
    @WebMethod
    ArrayList<Medico> indexMedicos() throws SQLException;
    
    @WebMethod
    void inactiveMedico(Medico medico) throws SQLException;
    
    @WebMethod
    Medico updateMedico(Medico medico) throws SQLException;
    
    @WebMethod
    Paciente insertPaciente(Paciente paciente) throws SQLException;
    
    @WebMethod
    ArrayList<Paciente> indexPacientes() throws SQLException;
    
    @WebMethod
    Paciente updatePaciente(Paciente paciente) throws SQLException;
}
