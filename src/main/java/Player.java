import tcp.IConnectable;

/**
 * Created by Alexander on 11/28/2023
 * <p>
 * Simple entity with name identifier that accepts {@link IConnectable} and uses it as a host or a guest in a {@link SocketChat}
 */
public class Player {

    private final IConnectable connectable;
    private final String name;

    public Player(IConnectable connectable, String name) {
        this.connectable = connectable;
        this.name = name;
    }

    public void chat() {
        connectable.connect(name);
    }

}
