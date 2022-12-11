package command;

import appPackage.App;

public class PauseCommand extends Command{

    public PauseCommand(App app) {
        super(app);
    }

    @Override
    public void execute() {
        app.pause();
    }

}
