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
import Model.Mahasiswa;

/**
 *
 * @author Regyma
 */
public class MahasiswaDAO {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;

    private final String insertQuery = "insert into mahasiswa(npm,nama,tgllahir) "
            + " values(?,?,?)";
    private final String updateQuery = "update mahasiswa set nama=?, "
            + " tgllahir=?, where npm=?";
    private final String deleteQuery = "delete from mahasiswa where npm=?";
    private final String getByIdQuery = "select * from mahasiswa where npm =?";
    private final String getAllQuery = "select * from mahasiswa";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    
    public Mahasiswa save(Mahasiswa mahasiswa) throws SQLException{
        insertStatement.setInt(1, mahasiswa.getNpm());
        insertStatement.setString(2, mahasiswa.getNama());
        insertStatement.setString(3, mahasiswa.getTgllahir());
        insertStatement.executeUpdate();
        return mahasiswa;
    }
    
    public Mahasiswa update(Mahasiswa mahasiswa) throws SQLException {
        updateStatement.setString(1, mahasiswa.getNama());
        updateStatement.setString(2, mahasiswa.getTgllahir());
        updateStatement.setInt(3, mahasiswa.getNpm());
        updateStatement.executeUpdate();
        return mahasiswa;
    }
    
    public Mahasiswa delete(Mahasiswa mahasiswa) throws SQLException {
        deleteStatement.setInt(1, mahasiswa.getNpm());
        deleteStatement.executeUpdate();
        return mahasiswa;
    }
    
    public Mahasiswa getByNpm(int npm) throws SQLException{
        getByIdStatement.setInt(1, npm);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNpm(rs.getInt("npm"));
            mahasiswa.setNama(rs.getString("nama"));
            mahasiswa.setTgllahir(rs.getString("tgllahir"));
            return mahasiswa;
        }
        return null;
    }
    
    public List<Mahasiswa> getAll() throws SQLException{
        List<Mahasiswa> mahasiswaR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNpm(rs.getInt("npm"));
            mahasiswa.setNama(rs.getString("nama"));
            mahasiswa.setTgllahir(rs.getString("tgllahir"));
            mahasiswaR.add(mahasiswa);
        }
        return mahasiswaR;
    }
}
