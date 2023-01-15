package command;

import appPackage.App;

public class RestartCommand extends AppCommand {
    public RestartCommand(App app) {
        super(app);
    }

    @Override
    public void execute() {
        app.restart();
    }
}
