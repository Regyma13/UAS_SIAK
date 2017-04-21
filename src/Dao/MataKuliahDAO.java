/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.MataKuliah;

/**
 *
 * @author Regyma
 */
public class MataKuliahDAO {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;

    private final String insertQuery = "insert into matakuliah(kode_mk,nama_mk,sks) "
            + " values(?,?,?)";
    private final String updateQuery = "update matakuliah set nama_mk=?, "
            + " sks=?, where kode_mk=?";
    private final String deleteQuery = "delete from matakuliah where kode_mk=?";
    private final String getByIdQuery = "select * from matakuliah where kode_mk=?";
    private final String getAllQuery = "select * from matakuliah";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    
    public MataKuliah save(MataKuliah matakuliah) throws SQLException{
        insertStatement.setInt(1, matakuliah.getKode_mk());
        insertStatement.setString(2, matakuliah.getNama_mk());
        insertStatement.setInt(3, matakuliah.getSks());
        insertStatement.executeUpdate();
        return matakuliah;
    }
    
    public MataKuliah update(MataKuliah matakuliah) throws SQLException{
        updateStatement.setInt(1, matakuliah.getKode_mk());
        updateStatement.setString(2, matakuliah.getNama_mk());
        updateStatement.setInt(3, matakuliah.getSks());
        updateStatement.executeUpdate();
        return matakuliah;
    }
    
    public MataKuliah delete(MataKuliah matakuliah) throws SQLException {
        deleteStatement.setInt(1, matakuliah.getKode_mk());
        deleteStatement.executeUpdate();
        return matakuliah;
    }
    
    public MataKuliah getByKode_mk(int kode_mk) throws SQLException{
        getByIdStatement.setInt(1, kode_mk);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            MataKuliah matakuliah = new MataKuliah();
            matakuliah.setKode_mk(rs.getInt("kode_mk"));
            matakuliah.setNama_mk(rs.getString("nama_mk"));
            matakuliah.setSks(rs.getInt("sks"));
            return matakuliah;
        }
        return null;
    }
    
    public List<MataKuliah> getAll() throws SQLException{
        List<MataKuliah> matakuliahR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            MataKuliah matakuliah = new MataKuliah();
            matakuliah.setKode_mk(rs.getInt("kode_mk"));
            matakuliah.setNama_mk(rs.getString("nama_mk"));
            matakuliah.setSks(rs.getInt("sks"));
            matakuliahR.add(matakuliah);
        }
        return matakuliahR;
    }
}
