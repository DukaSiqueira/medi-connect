/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.siqueira.medi.connect.interfaces;

import br.siqueira.medi.connect.models.Medico;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.SQLException;

/**
 *
 * @author eduar
 */
@WebService
public interface MedicoWebService {
    
    @WebMethod
    Medico insert(Medico medico) throws SQLException;
}
