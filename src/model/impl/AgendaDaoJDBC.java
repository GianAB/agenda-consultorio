/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.impl;

import db.DB;
import db.DbException;
import java.security.Timestamp;
import java.util.List;
import model.dao.AgendaDao;
import model.entities.Agenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entities.enums.StatusAgendamento;

/**
 *
 * @author giang
 */
public class AgendaDaoJDBC implements AgendaDao {

    private Connection conn;

    public AgendaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    /**
     * 
     * @see Este método é chamado quando for necessário acrescentar horários na
     * agenda
     *
     * @param agenda Agenda - new Agenda(Instant dtAtendimento, Sala sala,
     * Medico medico)
     */
    @Override
    public void insert(Agenda agenda) {
        try {
            PreparedStatement st = null;
            st = conn.prepareStatement("INSERT INTO tb_agenda(dt_atendimento, sala, id_medico, status_agendamento) "
                    + "VALUES"
                    + "(?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            //Duration duracao = Duration.parse(agenda.getDtAtendimento().toString());

            //st.setTime(1, new java.sql.Time(agenda.getDtAtendimento().getEpochSecond()));
            st.setTimestamp(1, java.sql.Timestamp.from(agenda.getDtAtendimento()));
            st.setString(2, agenda.getSala().name());
            st.setInt(3, agenda.getMedico().getId());
            st.setString(4, StatusAgendamento.EM_OFERTA.name());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                rs.first();
                agenda.setStatusAgendamento((byte) 1);

                DB.closeResultSet();
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatement();
        }
    }

    @Override
    public List<Agenda> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_agenda");
            rs = st.executeQuery();
            if (rs.first()) {
                List<Agenda> list = new ArrayList<>();
                rs.first();
                do {
                    //list.add(new Agenda(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf")));
                } while (rs.next());

                return list;
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            try {
                st.close();
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public Agenda findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_agenda WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.first()) {
                Agenda agenda = new Agenda();
                rs.first();
                /*
                agenda.setId(rs.getInt("id"));
                agenda.setNome(rs.getString("nome"));
                agenda.setCpf(rs.getString("cpf"));
                 */
                return agenda;
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            try {
                st.close();
                rs.close();

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Este método é chamado quando for necessário preenxer a agenda com
     * pacientes
     *
     * @param agenda Agenda - new Agenda(Paciente paciente)
     */
    @Override
    public void update(Agenda agenda) {
        if (this.findById(agenda.getPaciente().getId()) == null) {
            throw new NullPointerException("Nenhum objeto da classe " + Agenda.class.getName() + " foi encontrado!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE tb_agenda SET id_paciente = ? WHERE dt_atendimento = ? AND sala = ?");

            st.setInt(1, agenda.getPaciente().getId());
            st.setTime(2, new java.sql.Time(agenda.getDtAtendimento().getEpochSecond()));
            st.setString(3, agenda.getSala().name());
            st.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (this.findById(id) == null) {
            throw new NullPointerException("Nenhum objeto da classe " + Agenda.class.getName() + " foi encontrado!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM tb_agenda WHERE id = ?");
            st.setInt(1, id);
            st.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
