/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.siqueira.medi.connect.interfaces;

import br.siqueira.medi.connect.models.Endereco;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.SQLException;

/**
 *
 * @author eduar
 */
@WebService
public interface EnderecoWebService {
    
    @WebMethod
    Endereco insert(Endereco endereco) throws SQLException;
}
