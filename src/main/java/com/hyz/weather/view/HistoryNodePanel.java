package com.hyz.weather.view;

import com.hyz.weather.reSwing.*;

import javax.swing.*;
import java.awt.*;

public class HistoryNodePanel extends HJPanel {
    private HJButton tag,close;
    public HistoryNodePanel(String name,String cid){
        super();
        tag=new HJButton(name);
        close=new HJButton(new ImageIcon(
                Icons.CLOSE_BLACK.getImage().getScaledInstance(18, 18, Image.SCALE_FAST)
        ));
        tag.setName(cid);
        close.setName(cid);
        tag.setOpaque(true);
        tag.setFont(Fonts.MSYH_PLAIN_18);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, MDColor.BLUE_DARK));
        this.add(tag,BorderLayout.WEST);
        this.add(close,BorderLayout.EAST);
    }

    public HJButton getTag() {
        return tag;
    }

    public HJButton getClose() {
        return close;
    }
}
