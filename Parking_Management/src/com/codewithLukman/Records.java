package com.codewithLukman;

import net.sqlitetutorial.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Records extends  JFrame{

    Records() {
        String data[][] = new String[100][10] ;
        String column[] = {"Name", "Mobile", "CarCode", "Entry Date", "Entry Time", "Exit Date", "Exit Time"};
        int i = 0, j = 0;
        try {
            Connection connection;
            connection = Connect.getConnection();
            String sql = "SELECT *FROM carRecords";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            boolean isRow = rs.next();
            while(isRow) {

                String name = "-", mbl = "-", code = "-", enterdate = "-", entertime = "-", exitdate = "-", exittime = "-";

                name = rs.getString("name");
                mbl = rs.getString("mobile");
                code = rs.getString("carcode");
                enterdate = rs.getString("entrydate");
                entertime = rs.getString("entrytime");
                exitdate = rs.getString("exitdate");
                exittime = rs.getString("exittime");
                if(name.length() == 0){
                    name = "--";
                }
                if(mbl.length() == 0){
                    mbl = "--";
                }
                if(code.length() == 0){
                    code = "--";
                }
                if(enterdate.length() == 0){
                    enterdate = "--";
                }
                if(entertime.length() == 0){
                    entertime = "--";
                }
                if(exitdate.length() == 0){
                    exitdate = "--";
                }
                if(exittime.length() == 0){
                    exittime = "--";
                }
                data[i][j++] = name;
                data[i][j++] = mbl;
                data[i][j++] = code;
                data[i][j++] = enterdate;
                data[i][j++] = entertime;
                data[i][j++] = exitdate;
                data[i][j++] = exittime;
                isRow = rs.next();
                i++;
                j = 0;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        JTable jt = new JTable(data, column);
        jt.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(jt);

        this.add(sp);
        this.setTitle("RECORD FILE");
        this.setSize(1000, 700);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
    }
}
