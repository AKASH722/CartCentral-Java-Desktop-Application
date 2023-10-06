package com.database;

import com.Records.Products;
import com.Records.User;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cartcentral", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User checkCustomerUser(String email, String password) {
        try {
            PreparedStatement checkUser = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ? AND usertype = 'customer'");
            checkUser.setString(1, email);
            checkUser.setString(2, password);
            ResultSet rs = checkUser.executeQuery();
            if (rs.next()) {
                return new User(rs.getLong("userid"), rs.getString("name"), rs.getString("email"), rs.getLong("phoneNumber"), rs.getString("usertype"), rs.getString("country"));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void SearchProducts(ArrayList<Products> products, String text) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE productName LIKE ?");
            preparedStatement.setString(1, "%" + text + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(new Products(rs.getLong("productid"), rs.getString("productName"), rs.getString("description"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    public void getAllProducts(ArrayList<Products> productList) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productList.add(new Products(rs.getLong("productid"), rs.getString("productName"), rs.getString("description"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }


    }
    public boolean checkForAdmin(String user,String pass) throws SQLException {
        Statement st= connection.createStatement();
        ResultSet rs=st.executeQuery("SELECT email,password,usertype from users");
        while (rs.next()){
            if (rs.getString("email").equals(user) && rs.getString("password").equals(pass)&&rs.getString("usertype").equals("Admin")){
                return true;
            }
        }
        return false;
    }

    public void insertCategory(String name,String shortformCategory,String adminname) throws SQLException {
        int adminid=0;
        PreparedStatement ps= connection.prepareStatement("Select userid  from users where name=? and usertype=?");
        ps.setString(1,adminname);
        ps.setString(2,"Admin");

        ResultSet rs= ps.executeQuery();
        if (rs.next()) {
            adminid = rs.getInt("userid");
            System.out.println(adminid);
        }
        System.out.println(adminid);

        PreparedStatement pst=connection.prepareStatement("INSERT INTO categories( categoryName, categoryShortName, adminId) VALUES (?,?,?)");
        pst.setString(1,name);
        pst.setString(2,shortformCategory);
        pst.setInt(3,adminid);
        pst.executeUpdate();
        System.out.println("Category added succesfully");

    }
    public void insertSubCategory(String catename,String subCategory,String adminname) throws SQLException {
        int adminid=0;
        int cateid=0;
        PreparedStatement ps= connection.prepareStatement("Select userid from users where name=? and usertype=?");
        ps.setString(1,adminname);
        ps.setString(2,"Admin");
        ResultSet rs= ps.executeQuery();
        if (rs.next()) {
            adminid = rs.getInt("userid");
            System.out.println(adminid);
        }
        PreparedStatement pst= connection.prepareStatement("Select categoryId from categories where categoryName=?");
        ps.setString(1,catename);
        ps.executeQuery();
        PreparedStatement pst2=connection.prepareStatement("INSERT INTO subcategories(subcategoryname, categoryid, adminId) VALUES (?,?,?)");
        pst2.setString(1,subCategory);
        pst2.setInt(2,cateid);
        pst2.setInt(3,adminid);
        pst2.executeUpdate();
        System.out.println("Sub category added succesfully");
    }
    public boolean deleteCategory(String Catname,String subname) throws SQLException {
        int cateid=0;
        PreparedStatement pst3= connection.prepareStatement("Select categoryId  from categories where categoryName=?");
        pst3.setString(1,Catname);
        ResultSet rst=pst3.executeQuery();
        while (rst.next()){
            cateid= rst.getInt("categoryId");
        }
        PreparedStatement pst= connection.prepareStatement("SELECT * FROM `subcategories`where categoryid=? ");
        pst.setInt(1,cateid);
        ResultSet rs=pst.executeQuery();
        if(rs.next()==false){
            PreparedStatement ps=connection.prepareStatement("DELETE FROM categories WHERE categoryId=?");
            ps.setInt(1,cateid);
            ps.executeUpdate();
            System.out.println("Category deleted Succesfully");
            return true;
        }
        else{
            System.out.println("Unable to delete Sub-Category");
        }
        return false;

    }
    public boolean deleteSubCategory(String Catname,String subname) throws SQLException {
        int subid=0;
        PreparedStatement pst3= connection.prepareStatement("Select subcategoryid from subcategories where subcategoryname=?");
        pst3.setString(1,subname);
        ResultSet rst=pst3.executeQuery();
        while (rst.next()){
            subid= rst.getInt("subcategoryid");
        }
        PreparedStatement pst= connection.prepareStatement("SELECT productId  FROM products where subcategoryid=? ");
        pst.setInt(1,subid);
        ResultSet rs=pst.executeQuery();
        if(rs.next()==false){
            PreparedStatement ps=connection.prepareStatement("DELETE FROM subcategories WHERE subcategoryid=?");
            ps.setInt(1,subid);
            ps.executeUpdate();
            System.out.println("Category deleted Succesfully");
            return true;
        }
        else{
            System.out.println("Unable to delete Sub-Category ");

        }
        return false;

    }
}
