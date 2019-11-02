package com.hyz.weather.view;

import com.hyz.weather.reSwing.HJButton;
import com.hyz.weather.reSwing.HJLabel;
import com.hyz.weather.reSwing.HJPanel;
import com.hyz.weather.reSwing.Icons;

import javax.swing.*;
import java.awt.*;

public class RegionPanel extends HJPanel {
    private HJLabel location;
    private JButton list,refresh;
    public RegionPanel(){
        super();
        this.setOpaque(false);
//        list=new HJButton(Icons.LIST);
        list=new HJButton(Icons.LIST_BLACK);
        list.setBounds(0,0,Icons.LIST.getIconWidth(),Icons.LIST.getIconHeight());
        location=new HJLabel("",JLabel.CENTER);
//        refresh=new HJButton(Icons.REFRESH);
        refresh=new HJButton(Icons.REFRESH_BLACK);
        refresh.setBounds(0,0,Icons.REFRESH.getIconWidth(),Icons.REFRESH.getIconHeight());
        this.setLayout(new BorderLayout());
        this.add(list,BorderLayout.WEST);
        this.add(location,BorderLayout.CENTER);
        this.add(refresh,BorderLayout.EAST);
    }
    public boolean setData(String region){
        location.setText(region);
        return true;
    }

    public JButton getList() {
        return list;
    }

    public JButton getRefresh() {
        return refresh;
    }
}
