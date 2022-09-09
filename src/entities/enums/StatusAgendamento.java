/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package entities.enums;

/**
 *
 * @author giang
 */
public enum StatusAgendamento {
    EM_OFERTA((byte) 1, "Em oferta"),
    PREENCHIDO((byte) 2, "Preenxido"),
    CONCLUIDO((byte) 3, "Concluído"),
    CANCELADO((byte) 4, "Cancelado");

    private Byte cod;
    private String valor;

    private StatusAgendamento(Byte cod, String valor) {
        this.cod = cod;
        this.valor = valor;
    }

    public static StatusAgendamento toEnum(Byte cod) {
        if (cod.equals("")) {
            throw new NullPointerException("Este método não aceita valor nulo!");
        }

        for (StatusAgendamento status : StatusAgendamento.values()) {
            if (cod.equals(status.getCod())) {
                return status;
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
