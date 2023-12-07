package tcp.factory;

import tcp.IConnectable;

/**
 *
 * Defines the contract for creating {@link IConnectable} objects as host or client
 */
public interface IConnectableFactory {
    IConnectable createHost();

    IConnectable createClient();
}
