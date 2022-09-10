/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import db.DB;
import model.entities.Agenda;
import model.entities.Contato;
import model.entities.Medico;
import model.entities.Paciente;
import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;
import model.dao.DaoFactory;
import model.dao.PacienteDao;

/**
 *
 * @author giang
 */
public class Program {

    public static void main(String[] args) {
        /*
        Medico m1 = new Medico(1, "João da Silva", "345345");
        Paciente p1 = new Paciente(1, "Joaquim Marino", "2344323");
        Agenda agenda = new Agenda(Instant.parse("2022-09-08T10:00:00Z"), (byte)1, m1, p1, (byte)1);
        
        Contato contato1 = new Contato(1,"(33)3537-1221", "(33)99836-5500", p1);
        System.out.println("=== DADOS DA AGENDA ===");
        System.out.println(agenda);
        
        System.out.println("=== INFORMAÇÕES DO PACIENTE ===");
        System.out.println(contato1);
         */
        
        PacienteDao pacienteDao = DaoFactory.newPacienteDao();
        
        System.out.println("=== FindAll ===");
        /*
        List<Paciente> pacientes = pacienteDao.findAll();
        pacientes.stream().forEach(System.out::println);
        */
        
        System.out.println("=== FindById ===");
        /*
        Paciente paciente = pacienteDao.findById(1);
        System.out.println(paciente);
        */
        
        System.out.println("=== Insert ===");
        Paciente novoPaciente = new Paciente("Craig Goldy", "48523491837");
        /*
        pacienteDao.insert(novoPaciente);
        System.out.println(novoPaciente);
        */
        System.out.println("=== Update ===");
        /*
        novoPaciente.setId(5);
        novoPaciente.setNome("Billy Idol");
        pacienteDao.update(novoPaciente);
        System.out.println(novoPaciente);
        */
        System.out.println("=== Delete ===");
        /*
        pacienteDao.deleteById(6);
        System.out.println("Paciente deletado com sucesso!");
        */
        DB.closeConnection();
    }
}
