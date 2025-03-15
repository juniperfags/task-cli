import services.CommandService;

public class CommandLineEntryPoint {

    public static void main(String[] args) {

        new CommandService().execute(args);

    }

}