
package bank_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {
    JTextField amount;
    JButton deposit,back;
    String pinnumber;
    Deposit(String pinnumber){
        this.pinnumber=pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        
        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setBounds(170,300,400,20);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway",Font.BOLD,16));
        image.add(text);
        
        amount = new JTextField();
        amount.setBounds(170,350,320,25);
         amount.setFont(new Font("Raleway",Font.BOLD,22));
        image.add(amount);
        
        deposit = new JButton("Deposit");
        deposit.setBounds(355,485,150,30);
        deposit.addActionListener(this);
        image.add(deposit);
        
        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);
        
       
        
        
        
        
        
        
        setLayout(null);
        setSize(900,900);
        setLocation(300,0);
        //setUndecorated(true);
        setVisible(true);
        
    
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==deposit){
            String number= amount.getText();
            Date date= new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount ");
            }
            else{
                try{
                Conn conn = new Conn();
                String query = "insert into bank values('"+pinnumber+"','"+date+"','Deposit','"+number+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Rs "+number+" Deposited Succesfully");
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }
        else if (ae.getSource()==back){
           setVisible(false);
           new Transactions(pinnumber).setVisible(true);
           
        }
    }

   
    public static void main(String args[]) {
        Deposit d =  new Deposit("");
    }
}