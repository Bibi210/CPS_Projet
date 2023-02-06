package components.ports;

import components.Node;
import components.interfaces.NodeCI;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.PeerNodeAdressI;

public class NodeInboundPort extends AbstractInboundPort implements NodeCI {

    public NodeInboundPort(String uri, ComponentI owner)
            throws Exception {
        super(uri, NodeCI.class, owner);
        assert uri != null && owner instanceof Node;
    }

    @Override
    public PeerNodeAdressI connect(PeerNodeAdressI a)
            throws Exception {
        return this.getOwner().handleRequest(
                owner -> ((Node) owner).connect(a));
    }

    @Override
    public Void disconnect(PeerNodeAdressI a) throws Exception {
        return this.getOwner().handleRequest(
                owner -> ((Node) owner).disconnect(a));
    }

}
