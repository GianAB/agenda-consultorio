/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import model.entities.enums.Sala;
import model.entities.enums.StatusAgendamento;
import java.time.Instant;

/**
 *
 * @author giang
 */
public class Agenda {

    private Instant dtAtendimento;
    private Sala sala;
    private Medico medico;
    private Paciente paciente;
    private StatusAgendamento statusAgendamento;

    public Agenda() {
    }

    public Agenda(Paciente paciente) {
        this.paciente = paciente;
    }

    public Agenda(Instant dtAtendimento, Byte codSala, Medico medico) {
        this.dtAtendimento = dtAtendimento;
        this.sala = Sala.toEnum(codSala);
        this.medico = medico;
    }

    public Agenda(Instant dtAtendimento, Byte codSala, Medico medico, Paciente paciente, Byte codStatusAgendamento) {
        this.dtAtendimento = dtAtendimento;
        this.sala = Sala.toEnum(codSala);
        this.medico = medico;
        this.paciente = paciente;
        this.statusAgendamento = statusAgendamento.toEnum(codStatusAgendamento);
    }

    public Instant getDtAtendimento() {
        return dtAtendimento;
    }

    public void setDtAtendimento(Instant dtAtendimento) {
        this.dtAtendimento = dtAtendimento;
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
        sb.append("\ndtAtendimento=").append(dtAtendimento);
        sb.append("\nsala=").append(sala);
        sb.append("\nmedico=").append(medico);
        sb.append("\npaciente=").append(paciente);
        sb.append("\nstatusAgendamento=").append(statusAgendamento);
        sb.append("\n}");
        return sb.toString();
    }

}
