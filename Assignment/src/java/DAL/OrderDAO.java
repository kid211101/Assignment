/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

/**
 *
 * @author khanh doan
 */
public class OrderDAO extends BaseDAO<Order>{
    public ArrayList<Order> getAll(){
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [Order]";
        try {
           PreparedStatement statement = connection.prepareStatement(sql);
           ResultSet rs = statement.executeQuery();
           while(rs.next())
           {
               Order or = new Order();
               or.setOrid(rs.getInt(6));
               or.setCus(rs.getString(1));
               or.setBook(rs.getString(2));
               or.setStart(rs.getDate(3));
               or.setEnd(rs.getDate(4));       
               if(rs.getString(5).equals("1")){
                   or.setStatus(true);
               } else {
                   or.setStatus(false);
               }
               list.add(or);
           }
        } catch (Exception e) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    public ArrayList<Order> getListByPage(List<Order> list, int start, int end) {
        ArrayList<Order> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    
    public ArrayList<Order> getByCus(String cusid){
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT o.CusID,o.BookID,o.StartDate,o.EndDate,o.status FROM [Order] o WHERE o.CusID = ?";
        try {
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, cusid);
           ResultSet rs = statement.executeQuery();         
           while(rs.next())
           {
               Order or = new Order();
               or.setCus(rs.getString("CusID"));
               or.setBook(rs.getString("BookID"));
               or.setStart(rs.getDate("StartDate"));
               or.setEnd(rs.getDate("EndDate"));      
               or.setOrid(rs.getInt(6));
               if(rs.getInt("status")==1){
                   or.setStatus(true);
               } else {
                   or.setStatus(false);
               }
               list.add(or);
           }
        } catch (SQLException e) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    public Order takeOrder(int id){
        String sql = "SELECT * FROM [Order] WHERE OrID = ?\n";
        try {
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, id);
           ResultSet rs = statement.executeQuery();         
           if(rs.next())
           {
               Order or = new Order();
               or.setCus(rs.getString("CusID"));
               or.setBook(rs.getString("BookID"));
               or.setStart(rs.getDate("StartDate"));
               or.setEnd(rs.getDate("EndDate"));      
               or.setOrid(rs.getInt(6));
               if(rs.getInt("status")==1){
                   or.setStatus(true);
               } else {
                   or.setStatus(false);
               }
               return or;
           }
        } catch (SQLException e) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public void updateOrder(Order o) {
       try {
           String sql = "UPDATE [Order]\n"
                   + "   SET [CusID] = ?\n"
                   + "      ,[BookID] = ?\n"
                   + "      ,[StartDate] = ?\n"
                   + "      ,[EndDate] = ?\n"
                   + "      ,[status] = ?\n"
                   + " WHERE [OrID] = ?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, o.getCus());
           statement.setInt(2, Integer.parseInt(o.getBook()));
           statement.setDate(3, o.getStart());
           statement.setDate(4, o.getEnd());
           if(o.isStatus()==true){
               statement.setInt(5, 1);
           } else {
               statement.setInt(5, 0);
           }
           statement.setInt(6, o.getOrid());
           statement.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    public  void addOrder(String bid, String cid){
        long millis=System.currentTimeMillis();
        long millis2 = millis + 3*24*60*60*1000;
        Date d = new Date(millis);
        Date d2 = new Date(millis2);
        try {
           String sql = "INSERT INTO [Order](CusID,BookID,StartDate,EndDate,status) VALUES (?,?,?,?,?)";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, cid);
           statement.setInt(2, Integer.parseInt(bid));
           statement.setDate(3, d);
           statement.setDate(4, d2);
           statement.setInt(5, 0);
           statement.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void deleteOrder(int id) {
       try {
           String sql = "DELETE FROM [Order] WHERE OrID=?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, id);
           statement.executeUpdate();
       } catch (SQLException ex) {
           Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}
