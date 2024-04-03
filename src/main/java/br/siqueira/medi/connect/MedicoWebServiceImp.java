/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package br.siqueira.medi.connect;

import br.siqueira.medi.connect.interfaces.MedicoWebService;
import br.siqueira.medi.connect.models.Medico;
import br.siqueira.medi.connect.services.MedicoService;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eduar
 */
@WebService(serviceName = "MedicoWebServiceImp")
public class MedicoWebServiceImp implements MedicoWebService{

    @Override
    public Medico insert(Medico medico) throws SQLException {
        MedicoService medicoService = new MedicoService();
        return medicoService.insert(medico);
    }

    @Override
    public ArrayList<Medico> index() throws SQLException {
        MedicoService medicoService = new MedicoService();
        return medicoService.index();
    }
    
    

}
