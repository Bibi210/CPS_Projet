
package components.connectors;

import java.util.Set;

import components.interfaces.NodeManagementCI;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.PeerNodeAdressI;

/**
 * NodeManagementServiceConnector
 */
public class NodeManagementServiceConnector extends AbstractConnector implements NodeManagementCI {

    @Override
    public Set<PeerNodeAdressI> join(PeerNodeAdressI a) throws Exception {
        return ((NodeManagementCI) this.offering).join(a);
    }

    @Override
    public Void leave(PeerNodeAdressI a) throws Exception {
        return ((NodeManagementCI) this.offering).leave(a);
    }

}