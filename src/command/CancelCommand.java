package command;

import appPackage.App;

public class CancelCommand extends AppCommand{
    public CancelCommand(App app) {
        super(app);
    }

    @Override
    public void execute() {
        app.cancel();
    }
}
