package com.hyz.weather.view;

import com.hyz.weather.entity.Lifestyle;
import com.hyz.weather.entity.root.HeWeather6Lifestyle;
import com.hyz.weather.reSwing.Fonts;
import com.hyz.weather.reSwing.HJPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

/**
 * 生活指数面板
 */
public class LifestylePanel extends HJPanel {
    private LifestyleNodePanel[] node;

    public LifestylePanel() {
        super();
        TitledBorder tb = BorderFactory.createTitledBorder("今日生活指数");
        tb.setTitleFont(Fonts.MSYH_PLAIN_18);
        tb.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(tb);
        this.setLayout(new GridBagLayout());
        node = new LifestyleNodePanel[]{
                new LifestyleNodePanel(),
                new LifestyleNodePanel(),
                new LifestyleNodePanel(),
                new LifestyleNodePanel(),
                new LifestyleNodePanel(),
                new LifestyleNodePanel(),
                new LifestyleNodePanel(),
                new LifestyleNodePanel()
        };
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.insets = new Insets(4, 2, 0, 0);
        for (int i = 0; i < 8; i++) {
            constraints.gridy = i * 2;
            this.add(node[i], constraints);
        }
    }

    /**
     * 返回类型中文
     */
    private String selectType(String type) {
        String result = "";
        switch (type) {
            case "comf":
                result = "舒适度指数：";
                break;
            case "drsg":
                result = "穿衣指数：";
                break;
            case "flu":
                result = "感冒指数：";
                break;
            case "sport":
                result = "运动指数：";
                break;

            case "trav":
                result = "旅游指数：";
                break;
            case "uv":
                result = "紫外线指数：";
                break;
            case "cw":
                result = "洗车指数：";
                break;
            case "air":
                result = "空气污染扩散条件指数：";
                break;
        }
        return result;
    }

    public boolean setData(HeWeather6Lifestyle heWeather6Lifestyle) {
        return setData(heWeather6Lifestyle.getLifestyle());
    }

    public boolean setData(List<Lifestyle> lifestyleList) {
        for (int i = 0; i < 8; i++) {
            Lifestyle lifestyle = lifestyleList.get(i);
            node[i].getType().setText(selectType(lifestyle.getType()));
            node[i].getBrf().setText(lifestyle.getBrf());
            node[i].getTxt().setText(textFold(lifestyle.getTxt(), 50));
//            node[i].getTxt().setText(lifestyle.getTxt());
        }
        return true;
    }

    private String textFold(String text, int location) {
        StringBuilder stringBuilder = new StringBuilder("<html><p>" + text + "</p></html>");
        if (text.length() > location) {
            stringBuilder.insert(location + 9, "<br/>");
        }
        return stringBuilder.toString();
    }
}
