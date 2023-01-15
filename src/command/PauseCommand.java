package command;

import appPackage.App;

public class PauseCommand extends AppCommand {

    public PauseCommand(App app) {
        super(app);
    }
    @Override
    public void execute() {
        app.pause();
    }

}
