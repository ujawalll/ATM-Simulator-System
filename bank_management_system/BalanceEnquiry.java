package bank_management_system;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class BalanceEnquiry extends JFrame implements ActionListener {
    //JTextField pin,repin;
    JButton back;
    String pinnumber;
    
    BalanceEnquiry(String pinnumber){
        this.pinnumber=pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        
        back=new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);
        
        Conn conn = new Conn();
        int balance=0;
        
        try{
               ResultSet rs = conn.s.executeQuery("select * from bank where pinnumber = '"+pinnumber+"'");
               //int balance=0;
               while(rs.next()){
                   if(rs.getString("type").equals("Deposit")){
                       balance += Integer.parseInt(rs.getString("amount"));
                   }else{
                       balance -= Integer.parseInt(rs.getString("amount"));
                   }
               }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        JLabel a= new JLabel("Your Current Balance Account is Rs " + balance);
        a.setForeground(Color.WHITE);
        a.setBounds(170,300,400,30);
        image.add(a);
 
        setLayout(null);
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
        
                 
                 
                 
                 
    }
    
    public static void main(String args[]) {
        BalanceEnquiry b= new BalanceEnquiry("");
    }
}