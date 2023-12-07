package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Alexander on 11/28/2023
 * <p>
 * Implements client socket behavior (connects to given address:port)
 */
public class PingPongClient extends PingPongConnectable {

    public PingPongClient(String address, int port, int messageLimit) {
        super(address, port, messageLimit);
    }

    @Override
    protected void setupConnection() {
        try (Socket socket = new Socket(address, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            loopIO(in, out);

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + address);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + address);
            System.exit(1);
        }
    }
}
