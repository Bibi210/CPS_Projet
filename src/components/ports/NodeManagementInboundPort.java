package components.ports;

import java.util.Set;

import components.NodeManagement;
import components.interfaces.NodeManagementCI;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.PeerNodeAdressI;

public class NodeManagementInboundPort extends AbstractInboundPort implements NodeManagementCI {

    public NodeManagementInboundPort(String uri,
            ComponentI owner)
            throws Exception {
        super(uri, NodeManagementCI.class, owner);
        assert uri != null && owner instanceof NodeManagement;

    }

    public NodeManagementInboundPort(
            ComponentI owner) throws Exception {
        super(NodeManagementCI.class, owner);
        assert owner instanceof NodeManagementCI;
    }

    @Override
    public Set<PeerNodeAdressI> join(PeerNodeAdressI a) throws Exception {
        return this.getOwner().handleRequest(
                owner -> ((NodeManagement) owner).join(a));
    }

    @Override
    public Void leave(PeerNodeAdressI a) throws Exception {
        return this.getOwner().handleRequest(
                owner -> ((NodeManagement) owner).leave(a));
    }

}