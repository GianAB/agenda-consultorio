/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import db.DB;
import model.impl.AgendaDaoJDBC;
import model.impl.ContatoDaoJDBC;
import model.impl.MedicoDaoJDBC;
import model.impl.PacienteDaoJDBC;

/**
 *
 * @author giang
 */
public class DaoFactory {

    public static PacienteDao newPacienteDao() {
            return new PacienteDaoJDBC(DB.getConnection());
    }

    public static ContatoDao newContatoDao() {
            return new ContatoDaoJDBC(DB.getConnection());
    }
    
    public static MedicoDao newMedicoDao() {
            return new MedicoDaoJDBC(DB.getConnection());
    }
    
    public static AgendaDao newAgendaDao() {
            return new AgendaDaoJDBC(DB.getConnection());
    }
}
