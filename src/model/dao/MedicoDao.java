/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import model.entities.Medico;

/**
 *
 * @author giang
 */
public interface MedicoDao {
    public void insert(Medico obj);
    public List<Medico> findAll();
    public Medico findById(Integer id);
    public void update(Medico obj);
    public void deleteById(Integer id);
    
}
