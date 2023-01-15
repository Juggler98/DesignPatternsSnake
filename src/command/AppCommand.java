package command;

import appPackage.App;

public abstract class AppCommand implements ICommand {
    protected final App app;
    public AppCommand(App app) {
        this.app = app;
    }
}
