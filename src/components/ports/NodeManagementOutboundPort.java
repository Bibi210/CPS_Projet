package components.ports;

import java.util.Set;

import components.NodeManagement;
import components.interfaces.NodeManagementCI;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.PeerNodeAdressI;

/**
 * ContentManagementOutboundPort
 */
public class NodeManagementOutboundPort extends AbstractOutboundPort implements NodeManagementCI {

    public NodeManagementOutboundPort(String uri, ComponentI owner)
            throws Exception {
        super(uri, NodeManagementCI.class, owner);
        assert uri != null && owner instanceof NodeManagement;
    }

    public NodeManagementOutboundPort(ComponentI owner)
            throws Exception {
        super(NodeManagementCI.class, owner);
        assert owner instanceof NodeManagement;
    }

    @Override
    public Set<PeerNodeAdressI> join(PeerNodeAdressI a) throws Exception {
        return ((NodeManagementCI) this.getConnector()).join(a);
    }

    @Override
    public Void leave(PeerNodeAdressI a) throws Exception {
        return ((NodeManagementCI) this.getConnector()).leave(a);
    }

}