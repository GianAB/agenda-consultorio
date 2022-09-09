/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import entities.enums.Sala;
import entities.enums.StatusAgendamento;
import java.time.Instant;

/**
 *
 * @author giang
 */
public class Agenda {
    private Instant dt_atendimento;
    private Sala sala;
    private Medico medico;
    private Paciente paciente;
    private StatusAgendamento statusAgendamento;
    
    public Agenda() {
    }    

    public Agenda(Instant dt_atendimento, Byte codSala, Medico medico, Paciente paciente, Byte codStatusAgendamento) {
        this.dt_atendimento = dt_atendimento;
        this.sala = Sala.toEnum(codSala);
        this.medico = medico;
        this.paciente = paciente;
        this.statusAgendamento = statusAgendamento.toEnum(codStatusAgendamento);
    }

    public Instant getDt_atendimento() {
        return dt_atendimento;
    }

    public void setDt_atendimento(Instant dt_atendimento) {
        this.dt_atendimento = dt_atendimento;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Byte codSala) {
        this.sala = Sala.toEnum(codSala);
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public StatusAgendamento getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(Byte codStatusAgendamento) {
        this.statusAgendamento = StatusAgendamento.toEnum(codStatusAgendamento);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Agenda{");
        sb.append("\ndt_atendimento=").append(dt_atendimento);
        sb.append("\nsala=").append(sala);
        sb.append("\nmedico=").append(medico);
        sb.append("\npaciente=").append(paciente);
        sb.append("\nstatusAgendamento=").append(statusAgendamento);
        sb.append("\n}");
        return sb.toString();
    }
    
    
}
