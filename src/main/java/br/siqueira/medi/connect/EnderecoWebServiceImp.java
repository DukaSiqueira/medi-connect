/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package br.siqueira.medi.connect;

import br.siqueira.medi.connect.interfaces.EnderecoWebService;
import br.siqueira.medi.connect.models.Endereco;
import br.siqueira.medi.connect.services.EnderecoService;
import jakarta.jws.WebService;
import java.sql.SQLException;

/**
 *
 * @author eduar
 */
@WebService(endpointInterface = "br.siqueira.medi.connect.interfaces.EnderecoWebService")
public class EnderecoWebServiceImp implements EnderecoWebService{

    @Override
    public Endereco insert(Endereco endereco) throws SQLException {
        EnderecoService enderecoService = new EnderecoService();
        return enderecoService.insert(endereco);
    }

}
