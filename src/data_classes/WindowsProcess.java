package data_classes;

public class WindowsProcess {

    String name;

    String PID;
    String type;
    String memory;

    public WindowsProcess(String name, String PID, String type, String memory) {

        this.name = name;
        this.PID = PID;
        this.type = type;
        this.memory = memory;
    }


    public String getPID() {
        return PID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMemory() {
        return memory;
    }
}
