import tcp.factory.IConnectableFactory;
import tcp.factory.PingPongFactory;

/**
 * Created by Alexander on 11/28/2023
 *
 * <p> Uses PlayerFactory to instantiate Players, of which one acts as host and
 * another as guest in a ping-pong chat that continues until each has received {@link SocketChat#MESSAGE_LIMIT}
 * messages.</p>
 */
public class SocketChat {

    private static final int MESSAGE_LIMIT = 10;
    private final int portNumber;
    private final String hostName;
    private final boolean singleProcess;
    private final boolean host;

    public SocketChat(int portNumber, String hostName, boolean singleProcess, boolean host) {
        this.portNumber = portNumber;
        this.hostName = hostName;
        this.singleProcess = singleProcess;
        this.host = host;
    }

    public void start(Runnable inNewProcess, String playerName, String playerName2) {
        //logging?
        IConnectableFactory factory =createFactory();
        if (host)
            new Player(factory.createHost(), playerName).chat();

        if (singleProcess) {
            // Run client in the same process
            new Player(factory.createClient(), playerName2).chat();
        } else {
            // Run client in a separate process
            inNewProcess.run();
        }

    }

    protected IConnectableFactory createFactory() {
        return new PingPongFactory(hostName, portNumber, MESSAGE_LIMIT);
    }
}
