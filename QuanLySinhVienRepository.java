/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Models.Student;
import java.sql.Connection;
import java.util.ArrayList;
import url.DBSQLServer;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Lenovo
 */
public class QuanLySinhVienRepository {
    private Connection sConn = null;
    
    
    public QuanLySinhVienRepository(){
        this.sConn = DBSQLServer.getConnection();
    }
    //lấy dữ liệu từ database sang
    public ArrayList<Student> filltable(){
        String sql = "select MaSV,Hoten,Email,SDT,GioiTinh,DiaChi,HinhAnh from SinhVien";
        
        ArrayList<Student> ArrayStudent = new ArrayList<>();
        try {
            PreparedStatement ps = this.sConn.prepareStatement(sql);
            
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            
            while (rs.next()) {      
                Student sv = new Student();
                
                int i = 1;
                sv.setMaSV(rs.getString(i++));
                sv.setHoTen(rs.getString(i++));
                sv.setEmail(rs.getString(i++));
                sv.setSDT(rs.getString(i++));
                sv.setGioiTinh(rs.getBoolean(i++));
                sv.setDiaChi(rs.getString(i++));
                sv.setHinhAnh(rs.getString(i++));
                
                
                ArrayStudent.add(sv);
                
            }
            System.out.println("oke init data");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi !!!!" + e);
        }
        return ArrayStudent;
    }
    
    public void update(Student sv) throws Exception{
  
        String sql = "update SinhVien set Hoten = ?, Email = ?, SDT = ?, GioiTinh = ?, DiaChi = ?, HinhAnh = ? where MaSV = ? ";
        
        try {
            PreparedStatement ps = this.sConn.prepareStatement(sql);
            int i = 1;
            
            ps.setString(i++, sv.getHoTen());
            ps.setString(i++, sv.getEmail());
            ps.setString(i++, sv.getSDT());
            ps.setBoolean(i++, sv.isGioiTinh());
            ps.setString(i++, sv.getDiaChi());
            ps.setString(i++, sv.getHinhAnh());
            ps.setString(i++, sv.getMaSV());
            
            ps.execute();
            
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi update !!! " + e);
            throw e;
        }
    }
    
    
    public void delete(Student sv) throws Exception{
        String sql = "delete from SinhVien where MaSV = ?";
        
        try {
            PreparedStatement ps = this.sConn.prepareStatement(sql);
            int i = 1;
            
            ps.setString(i++, sv.getMaSV());
            
            ps.execute();
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi detele !!!!" + e);
        }
    }
    
    
    public void Save (Student sv) throws Exception{
        String sql = "insert into SinhVien values (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = this.sConn.prepareStatement(sql);
            int i = 1;
            
            ps.setString(i++, sv.getMaSV());
            ps.setString(i++, sv.getHoTen());
            ps.setString(i++, sv.getEmail());
            ps.setString(i++, sv.getSDT());
            ps.setBoolean(i++, sv.isGioiTinh());
            ps.setString(i++, sv.getDiaChi());
            ps.setString(i++, sv.getHinhAnh());
            System.out.println("hinh anh: " + sv.getHinhAnh());
            
            ps.execute();
            
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi Save !!!");
        }
    }

    
}
