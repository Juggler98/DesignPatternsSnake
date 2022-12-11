package command;

import appPackage.App;

public abstract class Command implements ICommand {
    protected final App app;
    public Command(App app) {
        this.app = app;
    }
}
