package com.sys.dao;


import com.sys.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static void main(String[] args) {
        List<User>result=list();
        result.stream().forEach(n->{
            System.out.println(n);
        });
    }

    //ctrl+shift+u
    public static List<User> list() {
        List<User> result = new ArrayList<>();
        String DRIVER = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/test";//主机：端口号/连接名
        String USERNAME = "root";//账号
        String PASSWORD = "root";//密码
        String sql = "select*from SYS_USER ";//表名

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            //注册驱动
            Class.forName(DRIVER);
            //获取连接 java连接mysql
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //实例化PreparedStatement
            ps = conn.prepareStatement(sql);
            //结果集
            User user=null;
            rs = ps.executeQuery();
            while (rs.next()) {
                //把结果集中的数据放入list集合中
                user=new User();
                Integer id=rs.getInt("id");
                String name = rs.getString("name");
                Integer age=rs.getInt("age");
                String sex = rs.getString("sex");
                String createTime=rs.getString("create_time");
                String felFlag = rs.getString("del_flag");
                user.setId(Long.valueOf(id));
                user.setName(name);
                user.setAge(age);
                user.setSex(sex);
                user.setCreateTime(createTime);
                user.setDelFlag(felFlag);
                result.add(user);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }
    }

