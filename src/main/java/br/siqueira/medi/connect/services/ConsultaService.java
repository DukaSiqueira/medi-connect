/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.siqueira.medi.connect.services;

import br.siqueira.medi.connect.models.Consulta;
import br.siqueira.medi.connect.models.Medico;
import br.siqueira.medi.connect.models.Paciente;
import br.siqueira.medi.connect.repositories.ConsultaRepository;
import br.siqueira.medi.connect.repositories.MedicoRepository;
import br.siqueira.medi.connect.repositories.PacienteRepository;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author eduar
 */
public class ConsultaService {

    public Consulta insert(Consulta consulta) throws SQLException{
        validateConsulta(consulta);
        ConsultaRepository consultaRepository = new ConsultaRepository();
        return consultaRepository.insert(consulta);
    }
    
    public void validateConsulta(Consulta consulta) throws SQLException {
        LocalDateTime startDate = LocalDateTime.parse(consulta.getStartdDate());
        LocalDateTime minStartDate = LocalDateTime.now().plusMinutes(30);
        
        
        LocalTime inicioFuncionamento = LocalTime.of(7, 0);
        LocalTime fimFuncionamento = LocalTime.of(18, 0);
        
        System.out.println(startDate.getHour() < minStartDate.getHour());
        System.out.println(startDate.getMinute()< minStartDate.getMinute());
        
//        if (startDate.toLocalTime().isAfter(fimFuncionamento)) {
//            throw new IllegalArgumentException("A última consulta do dia só pode ser agendada para as 18:00!");
//        }
//
//        if (startDate.toLocalTime().isBefore(inicioFuncionamento)) {
//            throw new IllegalArgumentException("A primeira consulta do dia só pode ser agendada após as 07:00!");
//        }
        
//        if (startDate.getHour() < minStartDate.getHour()
//                || startDate.getMinute()< minStartDate.getMinute()) {
//            throw new IllegalArgumentException("Fora do intervalo!");
//        }
        
        PacienteRepository pacienteRepository = new PacienteRepository();
        Paciente paciente = pacienteRepository.checkActive(consulta.getPaciente());
        
        if (paciente == null || !paciente.getPessoa().isIs_active()) {
            throw new IllegalArgumentException("Paciente inativo!");
        }
        
        MedicoRepository medicoRepository = new MedicoRepository();
        Medico medico = medicoRepository.checkActive(consulta.getMedico());
        
        if (medico == null || !medico.getPessoa().isIs_active()) {
            throw new IllegalArgumentException("Médico inativo!");
        }
        
        ConsultaRepository consultaRepository = new ConsultaRepository();;
//        int consultasDiaPaciente = consultaRepository.countConsultasDoDiaPaciente(consulta, startDate);
//        if (consultasDiaPaciente > 0) {
//            throw new IllegalArgumentException("O Paciente já possui consulta agendada para o dia de hoje!");
//        }
        
        int consultasDiaMedico = consultaRepository.countConsultasDoDiaMedcio(consulta, startDate);
        if (consultasDiaMedico > 0) {
            throw new IllegalArgumentException("O Médico já possui consulta agendada para esse horário!");
        }
    }
    
}
