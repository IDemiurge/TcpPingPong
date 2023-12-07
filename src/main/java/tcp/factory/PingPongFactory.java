package tcp.factory;

import tcp.IConnectable;
import tcp.PingPongClient;
import tcp.PingPongConnectable;
import tcp.PingPongServer;

/**
 * Created by Alexander on 11/29/2023
 * <p>
 * Responsible for creating {@link PingPongConnectable} objects as host or client
 */
public class PingPongFactory implements IConnectableFactory {

    private final String address;
    private final int port;
    private final int messageLimit;

    public PingPongFactory(String address, int port, int messageLimit) {
        this.address = address;
        this.port = port;
        this.messageLimit = messageLimit;
    }
    @Override
    public IConnectable createHost() {
        return new PingPongServer(port, messageLimit);
    }

    @Override
    public IConnectable createClient() {
        return new PingPongClient(address, port, messageLimit);
    }
}
