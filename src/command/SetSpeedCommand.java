package command;


import appPackage.App;
import utility.Obtiaznost;

public class SetSpeedCommand extends AppCommand {

    private final Obtiaznost obtiaznost;

    public SetSpeedCommand(App app, Obtiaznost obtiaznost) {
        super(app);
        this.obtiaznost = obtiaznost;
    }

    @Override
    public void execute() {
        app.nastavObtiaznost(this.obtiaznost);
    }
}
