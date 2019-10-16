package view;

import reSwing.HJButton;
import reSwing.HJLabel;
import reSwing.HJPanel;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        this.setBounds(400, 260, 300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("XXX天气预报");
        this.setResizable(false);
        HJButton hjButton=new HJButton("测试");
//        this.add(jPanel);
//        HJLabel hjLabel=new HJLabel("标签");
//        this.add(hjLabel);
        HJPanel hjPanel=new HJPanel();
        hjPanel.add(hjButton);
        this.add(hjPanel);
    }
}
