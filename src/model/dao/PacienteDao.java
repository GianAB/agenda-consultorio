/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import model.entities.Paciente;

/**
 *
 * @author giang
 */
public interface PacienteDao {
    public void insert(Paciente obj);
    public List<Paciente> findAll();
    public Paciente findById(Integer id);
    public void update(Paciente obj);
    public void deleteById(Integer id);
    
}
