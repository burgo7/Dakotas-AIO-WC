package main.java.DakotaWC;


import main.java.DakotaWC.options.*;
import main.java.DakotaWC.strategies.*;
import org.parabot.core.Context;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.accessors.Client;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.Skill;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

@ScriptManifest(
        author = "Dakota",
        name = "Dakota's AIO WC",
        category = Category.WOODCUTTING,
        version = 1.0,
        description = "All your woodcutting needs",
        servers = {"2006rebotted"})

public class Main extends Script implements Paintable {
    private Timer SCRIPT_TIMER;
    private ArrayList<Strategy> strategies;

    @Override
    public boolean onExecute(){
        strategies = new ArrayList<>();

        OptionsMenu optionsMenu = new OptionsMenu();
        optionsMenu.setVisible(true);
        while(optionsMenu.isVisible()){
            Time.sleep(300);
        }

        System.out.println(Options.getBotType());
        System.out.println(Options.getTreeType());
        if(Options.getBotType().equals("Banking"))
            System.out.println(Options.getLocation());

        strategies.add(new LoginHandler());
        strategies.add(new Chop());
        if(Options.getBotType().equals("Powerchop")){
            strategies.add(new Drop());
        } else if (Options.getBotType().equals("Banking")) {
            strategies.add(new Bank());
        } else {
            strategies.add(new Fletch());
        }
        Keyboard.getInstance().sendKeys("::togglegfx");
        Time.sleep(200);
        Keyboard.getInstance().pressKey(KeyEvent.VK_ENTER);
        XPCalculator.calculateBaseXP();
        XPCalculator.calculateBaseLvl();
        SCRIPT_TIMER = new Timer();
        provide(strategies);
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        try {
            Graphics2D g = (Graphics2D) graphics;
            Color c2 = new Color(0, 153, 51, 80);
            g.setColor(c2);
            g.setBackground(c2);
            g.fillRect(4, 232, 160, 20);

            Color c = new Color(51, 102, 0, 160);
            g.setColor(c);
            g.setBackground(c);
            g.fillRect(4, 252, 160, 85);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString("Dakota's AIO WC", 9, 247);
            g.setFont(new Font("Arial", Font.BOLD, 11));
            g.drawString("Lvl: " + XPCalculator.currentLvl + "(" + XPCalculator.gainedLvl + ")", 9, 270);
            g.drawString("Next Lvl In: " + XPCalculator.nextLvl,9,290);
            g.drawString("EXP(P/H): " + XPCalculator.formatNumber((int) XPCalculator.gainedXP) + "(" + XPCalculator.formatNumber(SCRIPT_TIMER.getPerHour(XPCalculator.gainedXP)) + ")", 9, 310);
            g.drawString("Runtime: " + SCRIPT_TIMER.toString(), 9, 330);
        } catch (Exception e) {
        }
    }
}
