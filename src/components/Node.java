package components;

import java.util.ArrayList;
import java.util.Set;

import components.connectors.NodeServiceConnector;
import components.interfaces.NodeCI;
import components.interfaces.NodeManagementCI;
import components.ports.NodeInboundPort;
import components.ports.NodeToManagementOutboundPort;
import components.ports.NodeToNodeOutboundPort;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import interfaces.PeerNodeAdressI;

@RequiredInterfaces(required = { NodeManagementCI.class, NodeCI.class })
@OfferedInterfaces(offered = { NodeInboundPort.class })
public class Node extends AbstractComponent implements PeerNodeAdressI {

    protected NodeInboundPort _inPort;
    protected NodeToManagementOutboundPort _outPortToFacade;
    protected ArrayList<PeerNodeAdressI> _peers;
    protected ArrayList<NodeToNodeOutboundPort> _peersOutPort;

    protected Node(String inUri, String outUri) throws Exception {
        super(1, 1);
        this._inPort = new NodeInboundPort(inUri, this);
        this._outPortToFacade = new NodeToManagementOutboundPort(outUri, this);

        this._peers = new ArrayList<PeerNodeAdressI>();
        this._peersOutPort = new ArrayList<NodeToNodeOutboundPort>();
        this._inPort.publishPort();
        this._outPortToFacade.publishPort();
    }

    public PeerNodeAdressI connect(PeerNodeAdressI a) throws Exception {
        NodeToNodeOutboundPort newOutboundPort = new NodeToNodeOutboundPort(
                a.getNodeUri(), this);
        this._peersOutPort.add(newOutboundPort);
        this._peers.add(a);
        this.doPortConnection(newOutboundPort.getPortURI(), a.getNodeIdentifer(),
                NodeServiceConnector.class.getCanonicalName());
        System.out.println("Connected to " + a.getNodeIdentifer());
        return a;
    }

    public Void disconnect(PeerNodeAdressI a) throws Exception {
        NodeToNodeOutboundPort aPort = _peersOutPort.get(_peers.indexOf(a));
        this.handleRequest(
                new AbstractComponent.AbstractService<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Node caller = (Node) this.getServiceOwner();

                        aPort.disconnect(caller);
                        return null;
                    }
                });
        this._peers.remove(a);
        aPort.unpublishPort();
        aPort.destroyPort();
        this._peersOutPort.remove(aPort);
        System.out.println("Disconnected from " + a.getNodeIdentifer());
        return null;
    }

    @Override
    public String getNodeIdentifer() throws Exception {
        return this._inPort.getPortURI();

    }

    @Override
    public boolean isFacade() {
        return false;
    }

    @Override
    public boolean isPeer() {
        return true;
    }

    @Override
    public String getNodeUri() throws Exception {
        return this._inPort.getPortURI();

    }

    @Override
    public void execute() throws Exception {
        System.err.println("Start HandleRequest");

        Set<PeerNodeAdressI> neighbors = _outPortToFacade.join(this);
        this._peers.addAll(neighbors);
        System.err.println("End HandleRequest");

        for (PeerNodeAdressI peerNodeAdressI : neighbors) {
            NodeToNodeOutboundPort newOutboundPort = new NodeToNodeOutboundPort(
                    peerNodeAdressI.getNodeUri(), this);
            this._peersOutPort.add(newOutboundPort);
            this.doPortConnection(newOutboundPort.getPortURI(), peerNodeAdressI.getNodeIdentifer(),
                    NodeServiceConnector.class.getCanonicalName());

            this.handleRequest(
                    new AbstractComponent.AbstractService<Void>() {
                        @Override
                        public Void call() throws Exception {
                            Node caller = (Node) this.getServiceOwner();
                            caller._peersOutPort.get(caller._peersOutPort.size() - 1).connect(caller);
                            return null;
                        }
                    });
        }
    }

    @Override
    public synchronized void finalise() throws Exception {
        for (PeerNodeAdressI peer : this._peers)
            disconnect(peer);
        this.handleRequest(
                new AbstractComponent.AbstractService<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Node caller = (Node) this.getServiceOwner();
                        return caller._outPortToFacade.leave(caller);
                    }
                });
        this.doPortDisconnection(_outPortToFacade.getPortURI());
        _outPortToFacade.unpublishPort();
        _outPortToFacade.destroyPort();
    }

}
