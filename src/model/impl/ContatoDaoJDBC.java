/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.dao.ContatoDao;
import model.entities.Contato;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dao.DaoFactory;
import model.entities.Paciente;

/**
 *
 * @author giang
 */
public class ContatoDaoJDBC implements ContatoDao {

    private Connection conn;

    public ContatoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Contato obj) {
        if (DaoFactory.newPacienteDao().findById(obj.getPaciente().getId()) == null) {
            throw new NullPointerException("Nenhum objeto da classe " + Paciente.class.getName() + " foi encontrado!");
        }
        
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO tb_contato(telefone, celular, id_paciente) "
                    + "VALUES"
                    + "(?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getTelefone());
            st.setString(2, obj.getCelular());
            st.setInt(3, obj.getPaciente().getId());

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
    public List<Contato> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_contato");
            rs = st.executeQuery();
            if (rs.first()) {
                List<Contato> list = new ArrayList<>();
                rs.first();
                do {
                    Paciente paciente = DaoFactory.newPacienteDao().findById(rs.getInt("id_paciente"));
                    list.add(new Contato(rs.getInt("id"), rs.getString("telefone"), rs.getString("celular"), paciente));

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
    public Contato findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_contato WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.first()) {
                Contato contato = new Contato();
                rs.first();
                contato.setId(rs.getInt("id"));
                contato.setTelefone(rs.getString("telefone"));
                contato.setCelular(rs.getString("celular"));
                contato.setPaciente(DaoFactory.newPacienteDao().findById(rs.getInt("id_paciente")));

                return contato;
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
    public void update(Contato obj) {
        if (this.findById(obj.getId()) == null) {
            throw new NullPointerException("Nenhum objeto da classe " + Contato.class.getName() + " foi encontrado!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE tb_contato SET telefone = ? AND  celular = ? WHERE id = ?");
            
            st.setString(1, obj.getTelefone());
            st.setString(2, obj.getCelular());
            st.setInt(3, obj.getId());
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
            throw new NullPointerException("Nenhum objeto da classe " + Contato.class.getName() + " foi encontrado!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM tb_contato WHERE id = ?");
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
