package command;

import appPackage.App;

public class NewPointCommand extends AppCommand {

    public NewPointCommand(App app) {
        super(app);
    }

    @Override
    public void execute() {
        app.newPoint();
    }
}
