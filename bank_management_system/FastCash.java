
package bank_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton j1,j2,j3,j4,j5,j6,exit;
    String pinnumber;
    FastCash(String pinnumber){
        this.pinnumber=pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        
        JLabel text = new JLabel("Select withdrwal Account");
        text.setBounds(200,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway",Font.BOLD,16));
        image.add(text);
        
        j1=new JButton("Rs 100");
        j1.setBounds(170,415,150,30);
        j1.addActionListener(this);
        image.add(j1);
        
        j2=new JButton("Rs 500");
        j2.setBounds(355,415,150,30);
        j2.addActionListener(this);
        image.add(j2);
        
         j3=new JButton("Rs 1000");
        j3.setBounds(170,450,150,30);
        j3.addActionListener(this);
        image.add(j3);
        
        j4=new JButton("Rs 2000");
        j4.setBounds(355,450,150,30);
        j4.addActionListener(this);
        image.add(j4);
        
        j5=new JButton("Rs 5000");
        j5.setBounds(170,485,150,30);
        j5.addActionListener(this);
        image.add(j5);
        
        j6=new JButton(" Rs 10000");
        j6.setBounds(355,485,150,30);
        j6.addActionListener(this);
        image.add(j6);
        
        exit = new JButton("Back");
        exit.setBounds(355,520,150,30);
        exit.addActionListener(this);
        image.add(exit);
        
        
        
        
        setLayout(null);
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
        
    
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==exit){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
        else {
            String amount = ((JButton)ae.getSource()).getText().substring(3);//Rs 500
            Conn conn = new Conn();
            //int balance=0;
            try{
               ResultSet rs = conn.s.executeQuery("select * from bank where pinnumber = '"+pinnumber+"'");
               int balance=0;
               while(rs.next()){
                   if(rs.getString("type").equals("Deposit")){
                       balance += Integer.parseInt(rs.getString("amount"));
                   }else{
                       balance -= Integer.parseInt(rs.getString("amount"));
                   }
               }
               if(ae.getSource() != exit && balance < Integer.parseInt(amount)){
                JOptionPane.showMessageDialog(null, "Insufficient Balance ");
                return;
            }
               Date date = new Date();
               String query = "insert into bank values('"+pinnumber+"','"+date+"','Withdraw','"+amount+"')";
               conn.s.executeUpdate(query);
               JOptionPane.showMessageDialog(null,"Rs " + amount + " Debited Succesfully");
               
               
               setVisible(false);
               new Transactions(pinnumber).setVisible(true);
            }
            
           
            catch(Exception e){
                System.out.println(e);
            }
        }
    }

   
    public static void main(String args[]) {
        FastCash t =  new FastCash("");
    }
}

