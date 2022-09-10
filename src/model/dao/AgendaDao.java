/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import model.entities.Agenda;

/**
 *
 * @author giang
 */
public interface AgendaDao {
    public void insert(Agenda obj);
    public List<Agenda> findAll();
    public Agenda findById(Integer id);
    public void update(Agenda obj);
    public void deleteById(Integer id);
    
}
