package com.hyz.weather.view;

import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJButton;
import com.hyz.weather.reSwing.HJPanel;
import com.hyz.weather.reSwing.Icons;

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
