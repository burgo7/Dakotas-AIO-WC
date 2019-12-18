package main.java.DakotaWC.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Interfaces;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginHandler implements Strategy {

    private Point point = new Point(432, 282);
    private Point point2 = new Point(328, 324);

    @Override
    public boolean activate(){
        return !Game.isLoggedIn() || Interfaces.getOpenInterfaceId() == 15812;
    }

    public void execute() {
        if (Game.isLoggedIn() && Interfaces.getOpenInterfaceId() == 15812) {
            Mouse.getInstance().click(point2);
        }
        if (!Game.isLoggedIn()) {
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Game.isLoggedIn();
                }
            }, 5000);
            Mouse.getInstance().click(point);
            Time.sleep(1000);
            Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
            Time.sleep(1000);
            Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
        }
    }
}