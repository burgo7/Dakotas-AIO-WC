package main.java.DakotaWC.strategies;

import main.java.DakotaWC.XPCalculator;
import main.java.DakotaWC.options.Options;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;
import org.rev317.min.api.wrappers.TilePath;

import java.awt.event.KeyEvent;

public class Bank implements Strategy {
    //@TODO Add to a new Variable class
    private final Tile VWestGate1 = new Tile(3176,3428);
    private final Tile VWestGate2 = new Tile(3164,3426);
    private final Tile VWestBank = new Tile(3185,3436);
    private final Tile VWestTrees = new Tile(3162,3419);

    private final Tile[] VWestTreesToBank = new Tile[]{VWestGate2, VWestGate1, VWestBank};
    private final Tile[] VWestBankToTrees = new Tile[]{VWestGate1, VWestGate2, VWestTrees};

    private final Tile DraynorXRoads = new Tile(3104, 3249);
    private final Tile DraynorBank = new Tile(3092,3245);
    private final Tile DraynorOakTrees = new Tile(3101,3244);
    private final Tile DraynorWillowTrees = new Tile(3088,3236);

    private final Tile[] DraynorOakToBank = {DraynorXRoads,DraynorBank};
    private final Tile[] DraynorBankToOak = {DraynorOakTrees};
    private final Tile[] DraynorWillowToBank = {DraynorBank};
    private final Tile[] DraynorBankToWillows = {DraynorWillowTrees};

    private final Tile SeersBank = new Tile(2724, 3493);
    private final Tile SeersMapleTrees = new Tile(2725, 3500);
    private final Tile SeersWillowTrees1 = new Tile(2717,3496);
    private final Tile SeersWillowTrees2 = new Tile(2713, 3507);
    private final Tile SeersYewTrees1 = new Tile(2724,3478);
    private final Tile SeersYewTrees2 = new Tile(2720,3464);
    private final Tile SeersYewTrees3 = new Tile(2716,3462);

    private final Tile[] SeersMapleToBank = {SeersBank};
    private final Tile[] SeersBankToMaple = {SeersMapleTrees};
    private final Tile[] SeersWillowToBank = {SeersWillowTrees1,SeersBank};
    private final Tile[] SeersBankToWillow = {SeersWillowTrees1,SeersWillowTrees2};
    private final Tile[] SeersBankToYews = {SeersYewTrees1,SeersYewTrees2,SeersYewTrees3};
    private final Tile[] SeersYewsToBank = {SeersYewTrees2,SeersYewTrees1,SeersBank};

    private final Tile EdgeBank = new Tile(3094,3489);
    private final Tile EdgeYews1 = new Tile(3094,3474);
    private final Tile EdgeYews2 = new Tile(3088,3470);

    private final Tile[] EdgeBankToYews = {EdgeYews1,EdgeYews2};
    private final Tile[] EdgeYewsToBank = {EdgeYews1,EdgeBank};

    private int logID = -1;
    private String location;

    public Bank(){
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
        location = Options.getLocation();
    }

    @Override
    public boolean activate() {
        return Inventory.isFull();
    }

    //@TODO Rewrite with a Tilepath,Tilepath func
    @Override
    public void execute(){
        XPCalculator.addLogs(Inventory.getCount(logID));
        //MessageBoss.messageBoss(Players.getMyPlayer().getName() + " " + Options.getTreeType() + " " + XPCalculator.logsCut);
        switch (location){
            case "Varrock West":
                bankVarrockWest();
                break;
            case "Draynor":
                bankDraynor();
                break;
            case "Seer's Village":
                bankSeers();
                break;
            case "Edgeville":
                bankEdgeville();
                break;
        }
        Time.sleep(600);
    }

    private void bankEdgeville() {
        TilePath pathToBank  = new TilePath(EdgeYewsToBank);
        TilePath pathToTrees = new TilePath(EdgeBankToYews);
        while (pathToBank != null && !pathToBank.hasReached()) {
            if (!Game.isLoggedIn()) new LoginHandler().execute();
            pathToBank.traverse();
            Time.sleep(2500);
        }
        Time.sleep(600);
        bankLogs();
        while (pathToTrees != null && !pathToTrees.hasReached()) {
            if (!Game.isLoggedIn()) new LoginHandler().execute();
            pathToTrees.traverse();
            Time.sleep(2500);
        }
    }

    private void bankSeers() {
        TilePath pathToTrees;
        TilePath pathToBank;
        switch (Options.getTreeType()){
            case "Willow":
                pathToTrees = new TilePath(SeersBankToWillow);
                pathToBank = new TilePath(SeersWillowToBank);
                break;
            case "Maple":
                pathToTrees = new TilePath(SeersBankToMaple);
                pathToBank = new TilePath(SeersMapleToBank);
                break;
            default:
                pathToTrees = new TilePath(SeersBankToYews);
                pathToBank = new TilePath(SeersYewsToBank);
                break;
        }
        while (pathToBank != null && !pathToBank.hasReached()) {
            if (!Game.isLoggedIn()) new LoginHandler().execute();
            pathToBank.traverse();
            Time.sleep(2500);
        }
        Time.sleep(600);
        bankLogs();
        while (pathToTrees != null && !pathToTrees.hasReached()) {
            if (!Game.isLoggedIn()) new LoginHandler().execute();
            pathToTrees.traverse();
            Time.sleep(2500);
        }
    }

    private void bankVarrockWest(){
        TilePath pathToBank  = new TilePath(VWestTreesToBank);
        TilePath pathToTrees = new TilePath(VWestBankToTrees);
        while (pathToBank != null && !pathToBank.hasReached()) {
            if (!Game.isLoggedIn()) new LoginHandler().execute();
            pathToBank.traverse();
            Time.sleep(2500);
        }
        Time.sleep(600);
        bankLogs();
        while (pathToTrees != null && !pathToTrees.hasReached()) {
            if (!Game.isLoggedIn()) new LoginHandler().execute();
            pathToTrees.traverse();
            Time.sleep(2500);
        }
    }

    private void bankDraynor(){
        TilePath pathToBank  = new TilePath(Options.getTreeType().equals("Oak")?DraynorOakToBank:DraynorWillowToBank);
        TilePath pathToTrees = new TilePath(Options.getTreeType().equals("Oak")?DraynorBankToOak:DraynorBankToWillows);
        while (pathToBank != null && !pathToBank.hasReached()) {
            if (!Game.isLoggedIn()) new LoginHandler().execute();
            pathToBank.traverse();
            Time.sleep(2500);
        }
        Time.sleep(600);
        bankLogs();
        while (pathToTrees != null && !pathToTrees.hasReached()) {
            if (!Game.isLoggedIn()) new LoginHandler().execute();
            pathToTrees.traverse();
            Time.sleep(2500);
        }
    }

    private void bankLogs(){
        SceneObject bankWindow = SceneObjects.getClosest(2213);
        bankWindow.interact(SceneObjects.Option.USE_QUICKLY);
        while(Interfaces.getOpenInterfaceId() != 5292)
            Time.sleep(200);
        org.rev317.min.api.methods.Bank.depositAllExcept(1350, 1352, 1354, 1356, 1358, 1360, 6740);
        Keyboard.getInstance().pressKey(KeyEvent.VK_ESCAPE);
    }

}
