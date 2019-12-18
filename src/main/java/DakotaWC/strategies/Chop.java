package main.java.DakotaWC.strategies;

import main.java.DakotaWC.XPCalculator;
import main.java.DakotaWC.options.Options;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Chop implements Strategy {

    private int[] TreeIDS;

    public Chop(){
        switch (Options.getTreeType()){
            case "Normal":
                TreeIDS = new int[]{1276,1278};
                break;
            case "Oak":
                TreeIDS = new int[]{1281};
                break;
            case "Willow":
                TreeIDS = new int[]{5551, 1308, 5553, 5552};
                break;
            case "Maple":
                TreeIDS = new int[]{1307};
                break;
            case "Yew":
                TreeIDS = new int[]{1309};
        }
    }


    //@TODO Walk to Trees when you aren't near them
    @Override
    public boolean activate(){
        XPCalculator.calculateGainedXP();
        XPCalculator.calculateGainedLvls();
        XPCalculator.calculateNextLvl();
        return !Inventory.isFull() && Players.getMyPlayer().getAnimation() == -1;
    }

    @Override
    public void execute(){
        System.out.println();
        SceneObject tree = getNearestTree();
        tree.interact(SceneObjects.Option.CHOP_DOWN);
        Time.sleep(5000);
        Time.sleep(new SleepCondition() {
            @Override
            public boolean isValid() {
                return (Players.getMyPlayer().getAnimation() == -1 || Inventory.isFull());
            }
        },59000);
    }

    private SceneObject getNearestTree(){
        //Loop through all loaded Objects and return the once that match the TREE_IDS.
        for(SceneObject tree : SceneObjects.getNearest(TreeIDS)){
            //Check if the Object is around.
            if(tree != null){
                //Return the Tree Object.
                return tree;
            }
        }
        //Return null when no Object is Found.
        return null;
    }
}
