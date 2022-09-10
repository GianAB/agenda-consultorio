/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.dao.PacienteDao;
import model.entities.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giang
 */
public class PacienteDaoJDBC implements PacienteDao {

    private Connection conn;

    public PacienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Paciente obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO tb_paciente(nome, cpf) "
                    + "VALUES"
                    + "(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getCpf());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                rs.first();
                obj.setId(rs.getInt(1));

                DB.closeResultSet();
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatement();
        }
    }

    @Override
    public List<Paciente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_paciente");
            rs = st.executeQuery();
            if (rs.first()) {
                List<Paciente> list = new ArrayList<>();
                rs.first();
                do {
                    list.add(new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf")));
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
    public Paciente findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_paciente WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.first()) {
                Paciente paciente = new Paciente();
                rs.first();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));

                return paciente;
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
    public void update(Paciente obj) {
        if (this.findById(obj.getId()) == null) {
            throw new NullPointerException("Nenhum objeto da classe "+ Paciente.class.getName()+" foi encontrado!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE tb_paciente SET nome = ? WHERE id = ?");
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());
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
            throw new NullPointerException("Nenhum objeto da classe "+ Paciente.class.getName()+" foi encontrado!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM tb_paciente WHERE id = ?");
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
