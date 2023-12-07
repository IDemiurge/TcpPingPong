package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alexander on 11/28/2023
 * <p>
 * Provides the basis for socket IO and handles ping-pong behavior (echoes received input back to sender until N*2 messages
 * have been sent between host and client
 */
public abstract class PingPongConnectable implements IConnectable {
    protected String playerName;
    protected final String address;
    protected final int port;
    protected final int messageLimit;

    public PingPongConnectable(String address, int port, int messageLimit) {
        this.address = address;
        this.port = port;
        this.messageLimit = messageLimit;
    }

    @Override
    public void connect(String playerName) {
        this.playerName = playerName;
        setupConnection();
    }

    protected abstract void setupConnection();

    protected void loopIO(BufferedReader in, PrintWriter out) throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (!received(inputLine, out))
                break;
        }
    }

    protected boolean received(String inputLine, PrintWriter out) {
        String lastCounter = inputLine.substring(inputLine.lastIndexOf("#") + 1, inputLine.length());
        int counter = Integer.parseInt(lastCounter);

        System.out.println(playerName + " received: " + inputLine);

        sendMessage(out, inputLine, counter++);

        //TODO controlled only by host? Which sends 'terminate' message?
        if (counter > messageLimit) {
            return false;
        }
        return true;
    }

    //TODO extract #
    protected void sendMessage(PrintWriter out, String message, int counter) {
        out.println(message + " #" + (counter));
    }

}
