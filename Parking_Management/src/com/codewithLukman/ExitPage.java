package com.codewithLukman;

import net.sqlitetutorial.Connect;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.SubmissionPublisher;

public class ExitPage extends  JFrame implements ActionListener {

    JLabel textLabel = new JLabel();
    JTextField carcode = new JTextField();
    JButton submitButton = new JButton("Submit");

    String exitdatenow, exittimenow, carcodenow;

    ExitPage(){

        LocalDate presentDate = LocalDate.now();
        LocalTime presentTime = LocalTime.now();
        exitdatenow = String.valueOf(presentDate);
        exittimenow = String.valueOf(presentTime);

        textLabel.setText("PLEASE ENTER YOUR CAR CODE");
        textLabel.setBounds(100,120, 500, 100);
        textLabel.setForeground(new Color(57, 59, 57));
        textLabel.setFont(new Font("civic", Font.PLAIN, 20));
        this.add(textLabel);

        carcode.setBounds(150, 205, 200,30);
        this.add(carcode);


        submitButton.setBounds(185, 260, 130, 30);
        submitButton.setFocusable(false);
        submitButton.setFont(new Font("civics", Font.BOLD, 20));
        submitButton.setForeground(new Color(181,0,0));
        submitButton.setBackground(new Color(0xFFFFFF));
        submitButton.addActionListener(this);
        //submitButton.setBorder((Border) new RoundedBorder(30));
        //submitButton.setOpaque(false);
        //submitButton.setFocusPainted(false);
        //submitButton.setBorderPainted(false);
        //submitButton.setContentAreaFilled(false);
        //submitButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.add(submitButton);

        this.setTitle("Exit");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(156, 230, 176));
        this.setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        String str = carcode.getText();
        if(e.getSource() == submitButton) {

            if (str.length() == 0) {
                JOptionPane.showMessageDialog(this, "PLEASE ENTER YOUR CAR CODE!", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            else{
                try {
                    Connection connection;
                    connection = Connect.getConnection();
                    String sql = "SELECT *FROM carRecords WHERE carcode = '" + str + "'";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    boolean isRow = rs.next();
                    if(isRow) {
                        String ex = "";
                        while(isRow){
                            ex = rs.getString("exitdate");
                            isRow = rs.next();
                        }
                        if(ex.length() != 0){
                            JOptionPane.showMessageDialog(this, "Car Already Exited!", "Alert", JOptionPane.ERROR_MESSAGE);
                        }
                        else{

                            try{
                                //Connection connection;
                                connection = Connect.getConnection();
                                String query = "UPDATE carRecords SET exitdate = '"+exitdatenow+"', exittime = '"+exittimenow+"' WHERE carcode = '"+str+"'";
                                Statement statement = connection.createStatement();
                                boolean res = statement.execute(query);
                                connection.close();
                            }
                            catch(Exception ex1){
                                JOptionPane.showMessageDialog(this, "Submission Successful.", "Notice", JOptionPane.PLAIN_MESSAGE);
                            }

                            new PaySlip(str);
                            setVisible(false);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "INVALID CAR CODE!", "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
