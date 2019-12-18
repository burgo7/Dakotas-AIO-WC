package main.java.DakotaWC.options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsMenu extends JFrame {

    private JPanel panel = new JPanel();
    private JComboBox<String> typeBox = new JComboBox<>();
    private JComboBox<String> treeBox = new JComboBox<>();
    private JComboBox<String> locationBox = new JComboBox<String>();
    private JButton run = new JButton("Run");

    public OptionsMenu() {
        setTitle("Dakota's AIO Chopping");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,200,200);
        panel.setLayout(new GridLayout(4,2));

        // Botting Type Select
        JLabel typeLabel = new JLabel("    Bot Type");
        typeLabel.setBounds(20,20,70,20);
        panel.add(typeLabel);
        typeBox.setModel(
                new DefaultComboBoxModel<>(new String[]{"Powerchop","Banking"}));
        typeBox.setBounds(20,40,160,20);
        panel.add(typeBox);

        // Tree Type Select
        JLabel treeLabel = new JLabel("    Tree Type");
        treeLabel.setBounds(20,60,70,20);
        panel.add(treeLabel);

        treeBox.setModel(
                new DefaultComboBoxModel<>(
                        new String[]{"Normal", "Oak", "Willow", "Maple", "Yew"}));
        treeBox.setBounds(20,80,160,20);
        panel.add(treeBox);

        // Location Select
        JLabel locLabel = new JLabel("    Location");
        locLabel.setBounds(20,100,70,20);
        panel.add(locLabel);

        locationBox.setModel(
                new DefaultComboBoxModel<String>());
        locationBox.setBounds(20,120,160,20);
        panel.add(locationBox);

        //Listeners
        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!typeBox.getSelectedItem().toString().equals("Banking")){
                    locationBox.setModel(
                            new DefaultComboBoxModel<String>()
                    );
                } else {
                    locationBox.setModel(
                            new DefaultComboBoxModel<>(treeToLoc(treeBox.getSelectedItem().toString()))
                    );
                }
            }
        });

        treeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!typeBox.getSelectedItem().toString().equals("Banking")){
                    locationBox.setModel(
                            new DefaultComboBoxModel<String>()
                    );
                } else {
                    locationBox.setModel(
                            new DefaultComboBoxModel<>(treeToLoc(treeBox.getSelectedItem().toString()))
                    );
                }
            }
        });

        run.setBounds(80,140,40,20);
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Options.setBotType(typeBox.getSelectedItem().toString());
                Options.setTreeType(treeBox.getSelectedItem().toString());
                if(Options.getBotType().equals("Banking"))
                    Options.setLocation(locationBox.getSelectedItem().toString());
                dispose();
            }
        });
        panel.add(new JLabel(""));
        panel.add(run);
        add(panel);
    }

    private String[] treeToLoc(String tree){
        switch(tree){
            case "Normal":
                return new String[]{"Varrock West"};
            case "Maple":
                return new String[]{"Seer's Village"};
            case "Oak":
                return new String[]{"Draynor","Varrock West"};
            case "Willow":
                return new String[]{"Draynor", "Seer's Village"};
            case "Yew":
                return new String[]{"Edgeville", "Seer's Village"};
            case "Magic":
                return new String[]{"Tree Gnome Stronghold","Seer's Village"};
            default:
                return new String[]{""};
        }
    }
}
