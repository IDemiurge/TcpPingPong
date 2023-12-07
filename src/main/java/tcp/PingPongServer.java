package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Alexander on 11/28/2023
 *
 * <p>Implements server socket behavior (accepts connections at given port)</p>
 * <p>Greets the first player that establishes connection</p>
 * <p>Controls the Counter in Ping-Pong logic, incrementing by it 1 each time it sends back a message </p>
 */
public class PingPongServer extends PingPongConnectable {

    public PingPongServer( int port, int messageLimit) {
        super("localhost", port, messageLimit);
    }

    @Override
    protected void setupConnection() {
        CountDownLatch latch = new CountDownLatch(1);
        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server ready and accepting connections...");
                latch.countDown(); // Signal that the server is ready
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    greetNewPlayer(out);
                    loopIO(in, out);
                }
            } catch (IOException e) {
                System.out.println("Server exception: " + e.getMessage());
            }
        });
        serverThread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void greetNewPlayer(PrintWriter out) {
        sendMessage(out, "Ping", 0);
        // out.println("Ping #1");
    }

    protected void sendMessage(PrintWriter out, String message, int counter) {
        out.println(message + " #" + (++counter));
    }
}
