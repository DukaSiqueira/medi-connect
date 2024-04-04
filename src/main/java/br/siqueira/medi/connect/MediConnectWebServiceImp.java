/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package br.siqueira.medi.connect;

import br.siqueira.medi.connect.interfaces.MediConnectWebService;
import br.siqueira.medi.connect.models.Endereco;
import br.siqueira.medi.connect.models.Medico;
import br.siqueira.medi.connect.services.EnderecoService;
import br.siqueira.medi.connect.services.MedicoService;
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
}
