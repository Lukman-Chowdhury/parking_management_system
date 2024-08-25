package com.codewithLukman;

import net.sqlitetutorial.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class EnterPage extends JFrame implements ActionListener {

    JLabel textLabel = new JLabel();
    JLabel background = new JLabel();
    JButton submitButton = new JButton();
    //Form form = new Form();
    JTextField name, mbl, car, email, date, time;
    JLabel nameLabel, mblLabel, carLabel, emailLabel, dateLabel, timeLabel;

    public EnterPage(){


        ImageIcon img = new ImageIcon("C:\\Users\\Lukman Chowdhury\\Downloads\\1635.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0,0,1000,700);
        this.add(background);


        nameLabel = new JLabel("Name");
        nameLabel.setBounds(280, 180, 80, 30);
        nameLabel.setFont(new Font("civic", Font.PLAIN, 23));
        nameLabel.setForeground(new Color(220, 222, 222));
        background.add(nameLabel);

        name = new JTextField();
        name.setBounds(360, 180, 280, 30);
        name.setBackground(new Color(220, 222, 222));
        background.add(name);

        mblLabel = new JLabel("Mobile");
        mblLabel.setBounds(280, 230, 80, 30);
        mblLabel.setFont(new Font("civic", Font.PLAIN, 23));
        mblLabel.setForeground(new Color(220, 222, 222));
        background.add(mblLabel);

        mbl = new JTextField();
        mbl.setBounds(360, 230, 280, 30);
        mbl.setBackground(new Color(220, 222, 222));
        background.add(mbl);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(280, 280, 80, 30);
        emailLabel.setFont(new Font("civic", Font.PLAIN, 23));
        emailLabel.setForeground(new Color(220, 222, 222));
        background.add(emailLabel);

        email = new JTextField();
        email.setBounds(360, 280, 280, 30);
        email.setBackground(new Color(220, 222, 222));
        background.add(email);

        carLabel = new JLabel("Car");
        carLabel.setBounds(280, 330, 80, 30);
        carLabel.setFont(new Font("civic", Font.PLAIN, 23));
        carLabel.setForeground(new Color(220, 222, 222));
        background.add(carLabel);

        car = new JTextField();
        car.setBounds(360, 330, 280, 30);
        car.setBackground(new Color(220, 222, 222));
        background.add(car);


        LocalDate presentDate = LocalDate.now();
        LocalTime presentTime = LocalTime.now();

        dateLabel = new JLabel("Date");
        dateLabel.setBounds(280, 380, 80, 30);
        dateLabel.setFont(new Font("civic", Font.PLAIN, 23));
        dateLabel.setForeground(new Color(220, 222, 222));
        background.add(dateLabel);

        date = new JTextField();
        date.setBackground(Color.gray);
        date.setBounds(360, 380, 280, 30);
        date.setBorder(null);
        date.setText(String.valueOf(presentDate));
        date.setFont(new Font("civic", Font.PLAIN, 15));
        date.setForeground(Color.white);
        background.add(date);

        timeLabel = new JLabel("Time");
        timeLabel.setBounds(280, 430, 80, 30);
        timeLabel.setFont(new Font("civic", Font.PLAIN, 23));
        timeLabel.setForeground(new Color(220, 222, 222));
        background.add(timeLabel);

        time = new JTextField();
        time.setBackground(Color.gray);
        time.setBounds(360, 430, 280, 30);
        time.setBorder(null);
        time.setText(String.valueOf(presentTime));
        time.setFont(new Font("civic", Font.PLAIN, 15));
        time.setForeground(Color.white);
        background.add(time);



        textLabel.setText("WELCOME TO THE PARKING");
        textLabel.setBounds(200,50, 600, 100);
        textLabel.setForeground(new Color(39, 172, 101));
        textLabel.setFont(new Font("Verdana", Font.BOLD, 35));
        background.add(textLabel);


        submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.setBounds(380, 510, 200, 30);
        submitButton.setFont(new Font("civic", Font.PLAIN, 20));
        submitButton.setForeground(new Color(181,0,0));
        submitButton.setBackground(Color.WHITE);
        submitButton.addActionListener(this);
        background.add(submitButton);

        //this.add(form);
        this.setTitle("Enter");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(129,133,132));
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submitButton){

            String subName, subMbl, subEmail, subCar, subDate, subTime;

            subName = name.getText();
            subMbl = mbl.getText();
            subEmail = email.getText();
            subCar = car.getText();
            subDate = date.getText();
            subTime = time.getText();



            if(subName.length() == 0 || subCar.length() == 0 || subMbl.length() == 0 || subEmail.length() == 0){
                JOptionPane.showMessageDialog(this, "Please fill up the Form first!", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            else{

                try{
                    Connection connection;
                    connection = Connect.getConnection();
                    String query = "INSERT INTO carRecords(name, mobile, carcode, entrydate, entrytime, exitdate, exittime) VALUES('" + subName + "', '" + subMbl + "', '" + subCar + "', '" + subDate + "', '" + subTime + "','','')";
                    Statement statement = connection.createStatement();
                    boolean res = statement.execute(query);
                    connection.close();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this, "Submission Successful.", "Notice", JOptionPane.PLAIN_MESSAGE);
                }

                setVisible(false);
            }
        }
    }

}
