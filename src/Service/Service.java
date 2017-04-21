/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.MahasiswaDAO;
import Dao.MataKuliahDAO;
import Dao.NilaiDAO;
import Model.Mahasiswa;
import Model.MataKuliah;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Regyma
 */
public class Service {
    private MahasiswaDAO mahasiswaDAO;
    private MataKuliahDAO matakuliahDAO;
    private NilaiDAO nilaiDAO;
    private Connection connection;
    
    public void setDataSource(DataSource dataSource){
        try {
            connection = dataSource.getConnection();
            mahasiswaDAO = new MahasiswaDAO();
            matakuliahDAO = new MataKuliahDAO();
            nilaiDAO = new NilaiDAO();
           
            mahasiswaDAO.setConnection(connection);
            matakuliahDAO.setConnection(connection);
            //nilaiDAO.setConnection(connection);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Mahasiswa save(Mahasiswa mahasiswa){
        try {
            connection.setAutoCommit(false);
            mahasiswaDAO.save(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    public Mahasiswa update(Mahasiswa mahasiswa){
        try {
            connection.setAutoCommit(false);
            mahasiswaDAO.update(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    public Mahasiswa delete(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDAO.delete(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    public Mahasiswa getMahasiswa(int npm){
        try {
            return mahasiswaDAO.getByNpm(npm);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Mahasiswa> getAllMahasiswa() {
        try {
            return mahasiswaDAO.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public MataKuliah save(MataKuliah matakuliah){
        try {
            connection.setAutoCommit(false);
            matakuliahDAO.save(matakuliah);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return matakuliah;
    }
    
   public MataKuliah update(MataKuliah matakuliah) {
        try {
            connection.setAutoCommit(false);
            matakuliahDAO.update(matakuliah);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matakuliah;
    }

    public MataKuliah delete(MataKuliah matakuliah) {
        try {
            connection.setAutoCommit(false);
            matakuliahDAO.delete(matakuliah);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matakuliah;
    }

    public MataKuliah getMataKuliah(int kode_mk) {
        try {
            return matakuliahDAO.getByKode_mk(kode_mk);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<MataKuliah> getAllMataKuliah() {
        try {
            return matakuliahDAO.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
