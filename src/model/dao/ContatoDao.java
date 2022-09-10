/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import model.entities.Contato;

/**
 *
 * @author giang
 */
public interface ContatoDao {
    public void insert(Contato obj);
    public List<Contato> findAll();
    public Contato findById(Integer id);
    public void update(Contato obj);
    public void deleteById(Integer id);
    
}
