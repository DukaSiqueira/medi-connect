/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package br.siqueira.medi.connect;

import br.siqueira.medi.connect.interfaces.MediConnectWebService;
import br.siqueira.medi.connect.models.Consulta;
import br.siqueira.medi.connect.models.Endereco;
import br.siqueira.medi.connect.models.Medico;
import br.siqueira.medi.connect.models.Paciente;
import br.siqueira.medi.connect.services.ConsultaService;
import br.siqueira.medi.connect.services.EnderecoService;
import br.siqueira.medi.connect.services.MedicoService;
import br.siqueira.medi.connect.services.PacienteService;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eduar
 */
@WebService(endpointInterface = "br.siqueira.medi.connect.interfaces.MediConnectWebService")
public class MediConnectWebServiceImp implements MediConnectWebService{

    @Override
    public Endereco insertEndereco(Endereco endereco) throws SQLException {
        EnderecoService enderecoService = new EnderecoService();
        return enderecoService.insert(endereco);
    }
    
    @Override
    public Medico insertMedico(Medico medico) throws SQLException {
        MedicoService medicoService = new MedicoService();
        return medicoService.insert(medico);
    }

    @Override
    public ArrayList<Medico> indexMedicos() throws SQLException {
        MedicoService medicoService = new MedicoService();
        return medicoService.index();
    }
    
    @Override
    public void inactiveMedico(Medico medico) throws SQLException {
        MedicoService medicoService = new MedicoService();
        medicoService.inactive(medico);
    }

    @Override
    public Medico updateMedico(Medico medico) throws SQLException {
        MedicoService medicoService = new MedicoService();
        return medicoService.update(medico);
    }

    @Override
    public Paciente insertPaciente(Paciente paciente) throws SQLException {
        PacienteService pacienteService = new PacienteService();
        return pacienteService.insert(paciente);
    }

    @Override
    public ArrayList<Paciente> indexPacientes() throws SQLException {
        PacienteService pacienteService = new PacienteService();
        return pacienteService.index();
    }

    @Override
    public Paciente updatePaciente(Paciente paciente) throws SQLException {
        PacienteService pacienteService = new PacienteService();
        return pacienteService.update(paciente);
    }

    @Override
    public void inactivePaciente(Paciente paciente) throws SQLException {
        PacienteService pacienteService = new PacienteService();
        pacienteService.inactvie(paciente);
    }

    @Override
    public Consulta insertConsulta(Consulta consulta) throws SQLException {
        ConsultaService consultaService = new ConsultaService();
        return consultaService.insert(consulta);
    }
    
}
