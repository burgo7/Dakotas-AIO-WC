package main.java.DakotaWC.strategies;

import main.java.DakotaWC.options.Options;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.wrappers.Item;

public class Drop implements Strategy {

    private int logID = -1;

    public Drop(){
            switch (Options.getTreeType()){
                case "Normal":
                    logID = 1512;
                    break;
                case "Oak":
                    logID = 1522;
                    break;
                case "Willow":
                    logID = 1520;
                    break;
                case "Maple":
                    logID = 1518;
                    break;
                case "Yew":
                    logID = 1516;
            }
        }

    @Override
    public boolean activate() {
        return Inventory.isFull();
    }

    @Override
    public void execute(){
        for(Item log : Inventory.getItems(logID)){
            log.drop();
        }
        Time.sleep(600);
    }
}
