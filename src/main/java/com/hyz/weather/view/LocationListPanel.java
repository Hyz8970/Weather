package com.hyz.weather.view;

import com.hyz.weather.action.HistoryAction;
import com.hyz.weather.action.RegionAction;
import com.hyz.weather.entity.History;
import com.hyz.weather.entity.Region;
import com.hyz.weather.reSwing.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LocationListPanel extends HJPanel {
    private RegionAction regionAction;
    private HistoryAction historyAction;
    private HJPanel historyListPanel;
    private HJLabel historyTag, regionTag;
    private HJButton close;
    private JList<String> regionJList;
    private List<Region> regionList;
    private String[] regionNameArray;
    private String selectResult;

    public LocationListPanel() {
        super();
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, MDColor.BLUE_DARK));
        regionAction = new RegionAction();
        historyAction = new HistoryAction();
        historyTag = new HJLabel("历史");
        historyTag.setFont(Fonts.MSYH_PLAIN_18);
        regionTag = new HJLabel("地区");
        regionTag.setFont(Fonts.MSYH_PLAIN_18);
        close = new HJButton(Icons.CLOSE_BLACK);
        close.setBounds(this.getWidth() - Icons.CLOSE.getIconWidth(), 0,
                Icons.CLOSE.getIconWidth(), Icons.CLOSE.getIconHeight());
        close.addActionListener((e) -> this.setVisible(false));

        //历史面板
        historyListPanel = new HJPanel();
        historyListPanel.setLayout(new GridBagLayout());
        refreshHistory();
        HJPanel historyClose = new HJPanel();
        historyClose.setLayout(new BorderLayout());
        historyClose.add(historyTag, BorderLayout.CENTER);
        historyClose.add(close, BorderLayout.EAST);
        historyClose.add(historyListPanel, BorderLayout.SOUTH);

        //地区选择面板
        HJPanel regionSelectPanel = new HJPanel();
        regionSelectPanel.setLayout(new BorderLayout());
        regionList = regionAction.provinceList();
        int regionSize = regionList.size();
        regionNameArray = new String[regionSize];
        for (int i = 0; i < regionSize; i++) {
            regionNameArray[i] = regionList.get(i).getName();
        }
        regionJList = new JList<>(regionNameArray);
        regionJList.setFont(Fonts.MSYH_PLAIN_18);
        JScrollPane scrollPane = new JScrollPane(regionJList);

        regionSelectPanel.add(regionTag, BorderLayout.NORTH);
        regionSelectPanel.add(scrollPane, BorderLayout.CENTER);
        regionJList.addListSelectionListener((event) -> {
            //相应鼠标的按下和释放，仅需按下响应
            if (regionJList.getValueIsAdjusting()) {
                JList source = (JList) event.getSource();
                int selectedIndex = source.getSelectedIndex();
                //还是会相应两次，第二次index变-1。(￣_￣|||)
                if (selectedIndex < 0) {
                    return;
                }
                if (!regionNameArray[selectedIndex].equals("上一级")) {
                    //非省级的第0个被“上一级占用”
                    if (regionNameArray[0].equals("上一级")) {
                        selectedIndex--;
                    }
                    //选中其中的一个省份
                    boolean result = selectRegionListen(regionList.get(selectedIndex), false);
                    if (result) {
                        //选到最小单位的地区，凭以刷新天气
                        Region region = regionList.get(selectedIndex);
                        if (!region.getCid().equals("")) {
                            selectResult = region.getCid();
                        } else {
                            selectResult = region.getName();
                        }
                        close.doClick();//模拟点击以触发MainWindow的监听
                    }
                } else {
                    //选中上一级
                    selectRegionListen(regionList.get(selectedIndex), true);
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(historyClose, BorderLayout.NORTH);
        this.add(regionSelectPanel, BorderLayout.CENTER);
    }

    /**
     * 选择地区
     *
     * @param region 选中的地区
     * @param upDown 向下还是向上
     */
    public boolean selectRegionListen(Region region, boolean upDown) {
        if (region.getLevel() == 3 && !upDown) {
            return true;
        } else {
            //上一级
            if (upDown) {
                regionList = regionAction.nextRegionList(region.getParentId());
                regionList = regionAction.nextRegionList(regionList.get(0).getParentId());
                int size = regionList.size();
                regionNameArray = new String[size];
                if (regionList.get(0).getLevel() == 1) {
                    //省级
                    for (int i = 0; i < size; i++) {
                        regionNameArray[i] = regionList.get(i).getName();
                    }
                } else {
                    //非省级
                    regionNameArray = new String[size + 1];
                    regionNameArray[0] = "上一级";
                    for (int i = 0; i < size; i++) {
                        regionNameArray[i + 1] = regionList.get(i).getName();
                    }
                }
            } else {
                //下一级
                regionList = regionAction.nextRegionList(region.getId());
                int size = regionList.size();
                regionNameArray = new String[size + 1];
                regionNameArray[0] = "上一级";
                for (int i = 0; i < size; i++) {
                    regionNameArray[i + 1] = regionList.get(i).getName();
                }
            }
            regionJList.setListData(regionNameArray);
            return false;
        }
    }

    public String getSelectResult() {
        return selectResult;
    }

    public HJButton getClose() {
        return close;
    }

    /**
     * 刷新历史记录列表
     */
    public boolean refreshHistory() {
        GridBagConstraints historyGBC = new GridBagConstraints();
        historyGBC.fill = GridBagConstraints.BOTH;
        historyGBC.gridx = 0;
        historyGBC.gridy = 0;
        historyGBC.gridwidth = 1;
        historyGBC.gridheight = 1;
        List<History> historyList = historyAction.getHistoryList();
        int historySize = historyList.size();
        historyListPanel.removeAll();
        historyListPanel.repaint();
        ActionListener removeListener = e -> {
            JButton source = (JButton) e.getSource();
            historyAction.delHistory(source.getName());
            refreshHistory();
        };
        ActionListener selectListener = e -> {
            JButton source = (JButton) e.getSource();
            selectResult = source.getName();
            close.doClick();
        };
        for (int i = 0; i < historySize; i++) {
            historyGBC.gridy++;
            History history = historyList.get(i);
            HistoryNodePanel historyNodePanel = new HistoryNodePanel(history.getName(),history.getCid());
            if (history.getIsUse() == 1) {
                //当前
                historyNodePanel.getTag().setBackground(MDColor.BLUE_LIGHT);
            }
            historyNodePanel.getTag().addActionListener(selectListener);
            historyNodePanel.getClose().addActionListener(removeListener);
            historyListPanel.add(historyNodePanel, historyGBC);
        }
        historyListPanel.revalidate();
        return true;
    }
}
