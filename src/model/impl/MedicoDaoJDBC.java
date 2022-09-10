/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.dao.MedicoDao;
import model.entities.Medico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author giang
 */
public class MedicoDaoJDBC implements MedicoDao {

    private Connection conn;

    public MedicoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Medico obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO tb_medico(nome, crm) "
                    + "VALUES"
                    + "(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getCrm());

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
    public List<Medico> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_medico");
            rs = st.executeQuery();
            if (rs.first()) {
                List<Medico> list = new ArrayList<>();
                rs.first();
                do {
                    list.add(new Medico(rs.getInt("id"), rs.getString("nome"), rs.getString("crm")));
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
    public Medico findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM tb_medico WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.first()) {
                Medico medico = new Medico();
                rs.first();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));

                return medico;
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
    public void update(Medico obj) {
        if (this.findById(obj.getId()) == null) {
            throw new NullPointerException("Nenhum objeto da classe "+ Medico.class.getName()+" foi encontrado!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE tb_medico SET nome = ? WHERE id = ?");
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
            throw new NullPointerException("Nenhum objeto da classe "+ Medico.class.getName()+" foi encontrado!");
        }

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM tb_medico WHERE id = ?");
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
