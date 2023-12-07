
import java.io.IOException;

/**
 * Created by Alexander on 11/28/2023
 * <p>
 * Main Class that accepts command line arguments to create a SocketChat with Server/Client in one or two processes
 */
public class Launcher {

    private static final int PORT = 1234;

    public static void main(String[] args) {
        int portNumber = PORT;
        String hostName = "localhost";
        boolean singleProcess;
        boolean host = true;

        if (args.length > 1) {
            /* launched with ProcessBuilder */
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
            host = false;
            singleProcess = true;
        } else {
            singleProcess = args.length > 0 && "single".equals(args[0]);
        }

        SocketChat chat = new SocketChat(portNumber, hostName, singleProcess, host);
        chat.start(() -> launchInNewProcess(), "Host", "Guest");
    }

    private static void launchInNewProcess() {
        try {
            String classpath = System.getProperty("java.class.path");
            ProcessBuilder builder = new ProcessBuilder("Java", "-cp", classpath, "Launcher", "localhost", String.valueOf(PORT));
            builder.inheritIO();
            builder.start();
            System.out.println("Started the client process");
        } catch (IOException e) {
            System.out.println("There was a problem starting the client process: " + e.getMessage());
        }
    }


}

