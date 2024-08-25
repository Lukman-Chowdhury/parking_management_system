package com.codewithLukman;

import net.sqlitetutorial.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Homepage extends JFrame implements ActionListener {


    JFrame homePage = new JFrame("HomePage");
    JLabel label1 = new JLabel();
    JLabel background = new JLabel();

    JButton enterButton = new JButton("NEW ENTRY");
    JButton exitButton = new JButton("EXIT");
    JButton recordButton = new JButton("Records");
    JButton searchButton = new JButton("Search Car");

    JTextField search = new JTextField("CarCode");

    public Homepage(){


        enterButton.setBounds(280, 240, 410, 40);
        enterButton.setFocusable(false);
        enterButton.setFont(new Font("civics", Font.BOLD, 20));
        enterButton.setForeground(new Color(0,194,110));
        enterButton.setBackground(new Color(0xFFFFFF));
        enterButton.addActionListener(this);

        exitButton.setBounds(280, 290, 410, 40);
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("civics", Font.BOLD, 20));
        exitButton.setBackground(new Color(0xFFFFFF));
        exitButton.setForeground(new Color(181,0,0));
        exitButton.addActionListener(this);

        recordButton.setBounds(280, 340, 410, 40);
        recordButton.setFocusable(false);
        recordButton.setFont(new Font("civics", Font.BOLD, 20));
        recordButton.setBackground(new Color(0xFFFFFF));
        recordButton.setForeground(Color.gray);
        recordButton.addActionListener(this);


        search.setBackground(Color.white);
        search.setBounds(280, 390, 250, 40);
        search.setBorder(null);
        search.setFont(new Font("civic", Font.PLAIN, 15));
        search.setForeground(Color.gray);
        this.add(search);



        searchButton.setBounds(540, 390, 150, 40);
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("civics", Font.BOLD, 20));
        searchButton.setBackground(new Color(0xFFFFFF));
        searchButton.setForeground(new Color(0,194,110));
        searchButton.addActionListener(this);
        this.add(searchButton);

        label1.setBounds(200, 50, 800, 200);
        label1.setForeground(Color.white);
        label1.setFont(new Font("Verdana", Font.BOLD, 35));
        label1.setText("Parking Management System");

        this.setTitle("Parking Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //this.getContentPane().setBackground(new Color(0x403502));

        ImageIcon img = new ImageIcon("C:\\Users\\Lukman Chowdhury\\Downloads\\1209.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0,0,1000,700);
        this.add(background);

        background.add(label1);
        background.add(enterButton);
        background.add(exitButton);
        background.add(recordButton);

        this.setVisible(true);

    }

    public  void actionPerformed(ActionEvent e){
        if(e.getSource() == enterButton){
            new EnterPage();
        }
        else if(e.getSource() == exitButton){
            new ExitPage();
        }
        else if(e.getSource() == recordButton){
            new Records();
        }
        else if(e.getSource() == searchButton){
            String str = search.getText();


            if (str.length() == 0) {
                JOptionPane.showMessageDialog(this, "PLEASE ENTER YOUR CAR CODE!", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    Connection connection;
                    connection = Connect.getConnection();
                    String sql = "SELECT *FROM carRecords WHERE carcode = '" + str + "'";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    boolean isRow = rs.next();
                    if (isRow) {
                        String ex = "";
                        while (isRow) {
                            ex = rs.getString("exitdate");
                            isRow = rs.next();
                        }
                        new PaySlip(str);
                        search.setText("CarCode");
                    } else {
                        JOptionPane.showMessageDialog(this, "Car Not Found!", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }

}
