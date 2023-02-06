
package components.connectors;

import components.interfaces.NodeCI;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.PeerNodeAdressI;

/**
 * NodeManagementServiceConnector
 */
public class NodeServiceConnector extends AbstractConnector implements NodeCI {

    @Override
    public PeerNodeAdressI connect(PeerNodeAdressI a) throws Exception {
        return ((NodeCI) this.offering).connect(a);
    }

    @Override
    public Void disconnect(PeerNodeAdressI a) throws Exception {
        return ((NodeCI) this.offering).disconnect(a);
    }

}