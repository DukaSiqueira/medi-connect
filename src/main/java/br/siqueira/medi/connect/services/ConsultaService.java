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
import java.util.List;
import java.util.Random;

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
        validarHorario(consulta);
        validarDisponibilidadeMedico(consulta);
        validarStatusPaciente(consulta);
        validarStatusMedico(consulta);
        validarConsultasDiaPaciente(consulta);
        validarConsultasDiaMedico(consulta);
    }

    private void validarHorario(Consulta consulta) {
        LocalDateTime startDate = LocalDateTime.parse(consulta.getStartdDate());
        LocalDateTime minStartDate = LocalDateTime.now().plusMinutes(30);

        LocalTime inicioFuncionamento = LocalTime.of(7, 0);
        LocalTime fimFuncionamento = LocalTime.of(18, 0);

        if (startDate.toLocalTime().isAfter(fimFuncionamento)) {
            throw new IllegalArgumentException("A última consulta do dia só pode ser agendada para as 18:00!");
        }

        if (startDate.toLocalTime().isBefore(inicioFuncionamento)) {
            throw new IllegalArgumentException("A primeira consulta do dia só pode ser agendada após as 07:00!");
        }

        if (startDate.isBefore(minStartDate)) {
            throw new IllegalArgumentException("A consulta deve ser agendada com pelo menos 30 minutos de antecedência!");
        }
    }

    private void validarDisponibilidadeMedico(Consulta consulta) throws SQLException {
        MedicoRepository medicoRepository = new MedicoRepository();
        if (consulta.getMedico() == null || consulta.getMedico().getId() == 0) {
            List<Medico> medicosDisponiveis = medicoRepository.findAvailableMedicos(LocalDateTime.parse(consulta.getStartdDate()));

            if (medicosDisponiveis.isEmpty()) {
                throw new IllegalArgumentException("Nenhum médico disponível para a data/hora especificada!");
            }

            Random random = new Random();
            Medico medicoAleatorio = medicosDisponiveis.get(random.nextInt(medicosDisponiveis.size()));
            consulta.setMedico(medicoAleatorio);
        }
    }

    private void validarStatusPaciente(Consulta consulta) throws SQLException {
        PacienteRepository pacienteRepository = new PacienteRepository();
        Paciente paciente = pacienteRepository.checkActive(consulta.getPaciente());

        if (paciente == null || !paciente.getPessoa().isIs_active()) {
            throw new IllegalArgumentException("Paciente inativo!");
        }
    }

    private void validarStatusMedico(Consulta consulta) throws SQLException {
        MedicoRepository medicoRepository = new MedicoRepository();
        Medico medico = medicoRepository.checkActive(consulta.getMedico());

        if (medico == null || !medico.getPessoa().isIs_active()) {
            throw new IllegalArgumentException("Médico inativo!");
        }
    }

    private void validarConsultasDiaPaciente(Consulta consulta) throws SQLException {
        ConsultaRepository consultaRepository = new ConsultaRepository();
        LocalDateTime startDate = LocalDateTime.parse(consulta.getStartdDate());
        int consultasDiaPaciente = consultaRepository.countConsultasDoDiaPaciente(consulta, startDate);

        if (consultasDiaPaciente > 0) {
            throw new IllegalArgumentException("O Paciente já possui consulta agendada para o dia de hoje!");
        }
    }

    private void validarConsultasDiaMedico(Consulta consulta) throws SQLException {
        ConsultaRepository consultaRepository = new ConsultaRepository();
        LocalDateTime startDate = LocalDateTime.parse(consulta.getStartdDate());
        int consultasDiaMedico = consultaRepository.countConsultasDoDiaMedcio(consulta, startDate);

        if (consultasDiaMedico > 0) {
            throw new IllegalArgumentException("O Médico já possui consulta agendada para esse horário!");
        }
    }
    
    public void cancelarConsulta(Consulta consulta) throws SQLException {
        validarCancelamento(consulta);
        ConsultaRepository consultaRepository = new ConsultaRepository();
        consultaRepository.cancelarConsulta(consulta);
    }
    
    private void validarCancelamento(Consulta consulta) throws SQLException {
        validarConsultaExistente(consulta);
        validarMotivoCancelamento(consulta);
        validarAntecedenciaCancelamento(consulta);
    }

    private void validarConsultaExistente(Consulta consulta) throws SQLException {
        ConsultaRepository consultaRepository = new ConsultaRepository();
        Consulta consultaReal = consultaRepository.findById(consulta.getId()); 

        if (consultaReal == null) {
            throw new IllegalArgumentException("Consulta não encontrada");
        }
    }

    private void validarMotivoCancelamento(Consulta consulta) {
        if (consulta.getMotivo_cancelamento() == null || consulta.getMotivo_cancelamento().trim().isEmpty()) {
            throw new IllegalArgumentException("É obrigatório informar o motivo do cancelamento");
        }
    }

    private void validarAntecedenciaCancelamento(Consulta consulta) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        String startDateTimeStr = consulta.getStartdDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr, formatter);

        Duration duracao = Duration.between(dataHoraAtual, startDateTime);

        if (duracao.toHours() < 24) {
            throw new IllegalArgumentException("A consulta só pode ser cancelada com antecedência mínima de 24 horas");
        }
    }
}
