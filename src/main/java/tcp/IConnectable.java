package tcp;

/**
 * Created by Alexander on 11/28/2023
 * <p>
 * Defines the basic contract for Connector to enable socket connection for a player identified by name
 */
public interface IConnectable {
    void connect(String playerName);
}
