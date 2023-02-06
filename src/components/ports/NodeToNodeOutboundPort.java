package components.ports;

import components.Node;
import components.interfaces.NodeCI;
import components.interfaces.NodeManagementCI;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.PeerNodeAdressI;

public class NodeToNodeOutboundPort extends AbstractOutboundPort implements NodeCI {

    public NodeToNodeOutboundPort(String uri, ComponentI owner)
            throws Exception {
        super(uri, NodeManagementCI.class, owner);
        assert uri != null && owner instanceof Node;
    }

    public NodeToNodeOutboundPort(ComponentI owner)
            throws Exception {
        super(NodeManagementCI.class, owner);
        assert owner instanceof Node;
    }

    @Override
    public PeerNodeAdressI connect(PeerNodeAdressI a) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void disconnect(PeerNodeAdressI a) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
