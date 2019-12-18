package main.java.DakotaWC.options;

public class Options {
    private static String treeType;
    private static String location;
    private static String botType;

    public static String getTreeType() {
        return treeType;
    }

    public static void setTreeType(String treeType) {
        Options.treeType = treeType;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        Options.location = location;
    }

    public static String getBotType() {
        return botType;
    }

    public static void setBotType(String botType) {
        Options.botType = botType;
    }
}
