package com.codewithLukman;

import net.sqlitetutorial.Connect;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetSocketAddress;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PaySlip extends JFrame implements ActionListener {
    JTextField dateText, timeText, carCode, entryDate, entryTime, pay, localDateinput1, localDateinput2, localDateinput3, localDateinput4, localDateinput5, localDateinput6,
    localDateinput7, localDateinput8, localDateinput9, localDateinput10, localDateinput11, localDateinput12;
    JLabel dateLabel, timeLabel, carCodeLabel, entryDateLabel, entryTimeLabel, payLabel, localDateinputLabel, enter, exit;
    JButton printButton, generateBill;
    JPanel container;
    String exitdatenow, exittimenow, carcodenow;
    long payment;
    long diff;

    PaySlip(String code){

        String name = null, mbl = null, entdate = null, enttime = null;
        carcodenow = code;
        try {
            Connection connection;
            connection = Connect.getConnection();
            String sql = "SELECT *FROM carRecords WHERE carcode = '" + code + "'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                name = rs.getString("name");
                mbl = rs.getString("mobile");
                entdate = rs.getString("entrydate");
                enttime = rs.getString("entrytime");
            }

        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        LocalDate presentDate = LocalDate.now();
        LocalTime presentTime = LocalTime.now();
        exitdatenow = String.valueOf(presentDate);
        exittimenow = String.valueOf(presentTime);

        container = new JPanel();
        container.setBounds(0, 0, 500, 280);
        container.setBackground(new Color(247, 247, 247));
        container.setLayout(null);
        this.add(container);

        dateLabel = new JLabel("Exit Date");
        dateLabel.setBounds(20, 60, 100, 30);
        dateLabel.setFont(new Font("civic", Font.PLAIN, 18));
        dateLabel.setForeground(Color.black);
        container.add(dateLabel);

        timeLabel = new JLabel("Exit Time");
        timeLabel.setBounds(20, 100, 100, 30);
        timeLabel.setFont(new Font("civic", Font.PLAIN, 18));
        timeLabel.setForeground(Color.black);
        container.add(timeLabel);

        dateText = new JTextField();
        dateText.setText(String.valueOf(presentDate));
        dateText.setEditable(false);
        dateText.setBounds(110, 60, 360, 30);
        dateText.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));
        container.add(dateText);

        timeText = new JTextField();
        timeText.setText(String.valueOf(presentTime));
        timeText.setEditable(false);
        timeText.setBounds(110, 100, 360, 30);
        timeText.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));
        container.add(timeText);


        carCodeLabel = new JLabel("CarCode");
        carCodeLabel.setBounds(20, 140, 100, 30);
        carCodeLabel.setFont(new Font("civic", Font.PLAIN, 18));
        container.add(carCodeLabel);

        carCode = new JTextField();
        carCode.setBounds(110, 140, 360, 30);
        carCode.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));
        carCode.setText(code);
        carCode.setEditable(false);
        container.add(carCode);

        entryDateLabel = new JLabel("Entry Date");
        entryDateLabel.setBounds(20, 180, 100, 30);
        entryDateLabel.setFont(new Font("civic", Font.PLAIN, 18));
        container.add(entryDateLabel);

        entryDate = new JTextField();
        entryDate.setBounds(110, 180, 360, 30);
        entryDate.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));
        entryDate.setText(entdate);
        entryDate.setEditable(false);
        container.add(entryDate);


        entryTimeLabel = new JLabel("Entry Time");
        entryTimeLabel.setBounds(20, 220, 100, 30);
        entryTimeLabel.setFont(new Font("civic", Font.PLAIN, 18));
        container.add(entryTimeLabel);

        entryTime = new JTextField();
        entryTime.setBounds(110, 220, 360, 30);
        entryTime.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));
        entryTime.setText(enttime);
        entryTime.setEditable(false);
        container.add(entryTime);


        localDateinputLabel = new JLabel("Enter The DateTime To Generate Bill:");
        localDateinputLabel.setBounds(20, 300, 400, 30);
        localDateinputLabel.setFont(new Font("civic", Font.PLAIN, 15));
        this.add(localDateinputLabel);

        enter = new JLabel("Enter Date Time Year/Monty/Date/Hr/Min/Sec:");
        enter.setBounds(20, 340, 400, 30);
        enter.setForeground(Color.red);
        enter.setFont(new Font("civic", Font.PLAIN, 15));
        this.add(enter);


        localDateinput1 = new JTextField("2017");
        localDateinput1.setBounds(20, 380, 68, 30);
        localDateinput1.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput1);

        localDateinput2 = new JTextField("12");
        localDateinput2.setBounds(90, 380, 68, 30);
        localDateinput2.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));
        this.add(localDateinput2);

        localDateinput3 = new JTextField("21");
        localDateinput3.setBounds(160, 380, 68, 30);
        localDateinput3.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput3);

        localDateinput4 = new JTextField("18");
        localDateinput4.setBounds(230, 380, 68, 30);
        localDateinput4.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput4);

        localDateinput5 = new JTextField("21");
        localDateinput5.setBounds(300, 380, 68, 30);
        localDateinput5.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput5);

        localDateinput6 = new JTextField("36");
        localDateinput6.setBounds(370, 380, 68, 30);
        localDateinput6.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput6);

        exit = new JLabel("Exit Date Time Year/Monty/Date/Hr/Min/Sec:");
        exit.setBounds(20, 420, 400, 30);
        exit.setFont(new Font("civic", Font.PLAIN, 15));
        exit.setForeground(Color.red);
        this.add(exit);


        localDateinput7 = new JTextField("2017");
        localDateinput7.setBounds(20, 460, 68, 30);
        localDateinput7.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));
        this.add(localDateinput7);

        localDateinput8 = new JTextField("12");
        localDateinput8.setBounds(90, 460, 68, 30);
        localDateinput8.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput8);

        localDateinput9 = new JTextField("30");
        localDateinput9.setBounds(160, 460, 68, 30);
        localDateinput9.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput9);

        localDateinput10 = new JTextField("13");
        localDateinput10.setBounds(230, 460, 68, 30);
        localDateinput10.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput10);

        localDateinput11 = new JTextField("21");
        localDateinput11.setBounds(300, 460, 68, 30);
        localDateinput11.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput11);

        localDateinput12 = new JTextField("50");
        localDateinput12.setBounds(370, 460, 68, 30);
        localDateinput12.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));

        this.add(localDateinput12);





        payLabel = new JLabel("Total Bill   _______________  ");
        payLabel.setBounds(20, 500, 300, 30);
        payLabel.setFont(new Font("civic", Font.PLAIN,22));
        this.add(payLabel);



        pay = new JTextField();
        pay.setBounds(310, 500, 180, 30);
        //pay.setText(payment + " Taka Only");
        pay.setBorder(BorderFactory.createLineBorder(Color.decode("#f7f7f7")));
        pay.setBackground(Color.decode("#f7f7f7"));
        pay.setFont(new Font("civic", Font.BOLD, 18));

        printButton = new JButton("SAVE MONEY RECEIPT");
        printButton.setBounds(20, 610, 450, 40);
        printButton.setBackground(Color.gray);
        printButton.setForeground(Color.white);
        printButton.setFont(new Font("civic", Font.PLAIN,22));
        printButton.addActionListener(this);
        this.add(printButton);


        generateBill = new JButton("GENERATE THE BILL");
        generateBill.setBounds(20, 560, 450, 40);
        generateBill.setBackground(new Color(32, 158, 32));
        generateBill.setForeground(Color.white);
        generateBill.setFont(new Font("civic", Font.PLAIN,18));
        generateBill.addActionListener(this);
        this.add(generateBill);


        this.setTitle("Payment");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500,720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(247, 247, 247));
        this.setVisible(true);





    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == printButton){
            if(exittimenow.length() == 0 || exitdatenow.length() == 0){
                JOptionPane.showMessageDialog(this,"Car is not exited yet!","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else{
                BufferedImage imagebuf=null;
                try {
                    imagebuf = new Robot().createScreenCapture(container.bounds()); // which panel you want to save
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                Graphics2D graphics2D = imagebuf.createGraphics();
                container.paint(graphics2D);
                try {
                    ImageIO.write(imagebuf,"jpg", new File(carcodenow +".jpg"));
                } catch (Exception ex) {
                    // TODO Auto-generated catch block
                    System.out.println("error");
                }
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Successfully Saved.","Alert",JOptionPane.WARNING_MESSAGE);
                setVisible(false);
            }
        }
        if(e.getSource() == generateBill){

            int y = Integer.parseInt(localDateinput1.getText());
            int m = Integer.parseInt(localDateinput2.getText());
            int d = Integer.parseInt(localDateinput3.getText());
            int hr = Integer.parseInt(localDateinput4.getText());
            int min = Integer.parseInt(localDateinput5.getText());
            int sec = Integer.parseInt(localDateinput6.getText());
            int y2 = Integer.parseInt(localDateinput7.getText());
            int m2 = Integer.parseInt(localDateinput8.getText());
            int d2 = Integer.parseInt(localDateinput9.getText());
            int hr2 = Integer.parseInt(localDateinput10.getText());
            int min2 = Integer.parseInt(localDateinput11.getText());
            int sec2 = Integer.parseInt(localDateinput12.getText());

            LocalDateTime from = LocalDateTime.of(y, m, d,
                    hr, min, sec);
            LocalDateTime to = LocalDateTime.of(y2, m2, d2,
                    hr2, min2, sec2);

            Duration duration = Duration.between(from, to);
            diff = duration.toMinutes();
            payment = diff*10;


            pay.setText((payment + " Taka Only"));
            this.add(pay);
        }
    }
}
