/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author giang
 */
public class Contato {
    private Integer id;
    private String telefone;
    private String celular;
    private Paciente paciente;
    
    public Contato(){
    }

    public Contato(Integer id, String telefone, String celular, Paciente paciente) {
        this.id = id;
        this.telefone = telefone;
        this.celular = celular;
        this.paciente = paciente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contato{");
        sb.append("\nid=").append(id);
        sb.append("\ntelefone=").append(telefone);
        sb.append("\ncelular=").append(celular);
        sb.append("\npaciente=").append(paciente);
        sb.append("\n}");
        return sb.toString();
    }
    
    
}
