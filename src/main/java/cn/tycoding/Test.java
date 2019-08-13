package cn.tycoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {
    public static void main(String[] args) throws Exception {
        com1();
    }

    private static void transT1() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tumo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8","root","182764");
        String sql="insert into p1(id) value(?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\44653\\Desktop\\Desktop\\temp.txt"));
        String line=null;
        int num=0;
        while((line=br.readLine())!=null){
            System.out.println(num++);
            statement.setString(1,line);
            statement.execute();
        }
        if(statement!=null){
            statement.close();
        }
        if(connection!=null){
            connection.close();
        }
    }


    private static void com1() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tumo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8","root","182764");
        String sql="select id from p1 where id=? ";
        PreparedStatement statement=connection.prepareStatement(sql);
        BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\44653\\Desktop\\Desktop\\temp.txt"));
        BufferedWriter wr=new BufferedWriter(new FileWriter("C:\\Users\\44653\\Desktop\\sz_diff.txt"));
        String line=null;
        int num=0;
        while((line=br.readLine())!=null){
            if(num++%100==0){
                System.out.println(num);
            }
            statement.setString(1,line);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                wr.write(line);
                wr.newLine();
            }
        }
        br.close();
        wr.close();
        if(statement!=null){
            statement.close();
        }
        if(connection!=null){
            connection.close();
        }
    }

}
