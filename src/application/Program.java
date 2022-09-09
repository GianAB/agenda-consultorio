/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import entities.Agenda;
import entities.Contato;
import entities.Medico;
import entities.Paciente;
import entities.enums.Sala;
import entities.enums.StatusAgendamento;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author giang
 */
public class Program {
    public static void main(String[] args) {
        Medico m1 = new Medico(1, "João da Silva", "345345");
        Paciente p1 = new Paciente(1, "Joaquim Marino", "2344323");
        Agenda agenda = new Agenda(Instant.parse("2022-09-08T10:00:00Z"), (byte)1, m1, p1, (byte)1);
        
        Contato contato1 = new Contato(1,"(33)3537-1221", "(33)99836-5500", p1);
        System.out.println("=== DADOS DA AGENDA ===");
        System.out.println(agenda);
        
        System.out.println("=== INFORMAÇÕES DO PACIENTE ===");
        System.out.println(contato1);
    }
}
