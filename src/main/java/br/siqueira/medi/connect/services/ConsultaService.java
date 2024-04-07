/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.services;

import br.siqueira.medi.connect.models.Consulta;
import br.siqueira.medi.connect.repositories.ConsultaRepository;
import java.sql.SQLException;

/**
 *
 * @author eduar
 */
public class ConsultaService {

    public Consulta insert(Consulta consulta) throws SQLException{
        ConsultaRepository consultaRepository = new ConsultaRepository();
        return consultaRepository.insert(consulta);
    }
    
}
