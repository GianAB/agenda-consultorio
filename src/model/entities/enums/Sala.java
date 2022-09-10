/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model.entities.enums;

/**
 *
 * @author giang
 */
public enum Sala {
    SALA_1((byte) 1, "Sala 1"),
    SALA_2((byte) 2, "Sala 2"),
    SALA_3((byte) 3, "Sala 3"),
    SALA_4((byte) 4, "Sala 4");

    private Byte cod;
    private String valor;

    private Sala(Byte cod, String valor) {
        this.cod = cod;
        this.valor = valor;
    }

    public static Sala toEnum(Byte cod) {
        if (cod.equals("")) {
            throw new NullPointerException("Este método não aceita valor nulo!");
        }

        for (Sala sala : Sala.values()) {
            if (cod.equals(sala.getCod())) {
                return sala;
            }
        }
        throw new ArrayIndexOutOfBoundsException("Código inexistente!");
    }

    public Byte getCod() {
        return cod;
    }

    public void setCod(Byte cod) {
        this.cod = cod;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
