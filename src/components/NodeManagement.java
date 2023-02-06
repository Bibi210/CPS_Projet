package components;

import java.util.Set;

import components.interfaces.NodeManagementCI;
import components.ports.NodeManagementInboundPort;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import interfaces.FacadeNodeAdressI;
import interfaces.PeerNodeAdressI;

/* Facade Node */
@OfferedInterfaces(offered = { NodeManagementCI.class })
public class NodeManagement extends AbstractComponent implements FacadeNodeAdressI {
    private Set<PeerNodeAdressI> _voisin;
    protected NodeManagementInboundPort _inPort;

    protected NodeManagement(String uri) throws Exception {
        super(uri, 1, 1);
        this._inPort = new NodeManagementInboundPort(uri, this);
        this._inPort.publishPort();
    }

    public Set<PeerNodeAdressI> join(PeerNodeAdressI newMember) throws Exception {
        Set<PeerNodeAdressI> currentVoisins = this._voisin;
        this._voisin.add(newMember);
        System.out.println("Joined " + newMember.getNodeUri());
        return currentVoisins;
    }

    public Void leave(PeerNodeAdressI leavingMember) {
        this._voisin.remove(leavingMember);
        return null;
    }

    @Override
    public String getNodeIdentifer() throws Exception {
        return this._inPort.getPortURI();
    }

    @Override
    public boolean isFacade() {
        return true;
    }

    @Override
    public boolean isPeer() {
        return false;
    }

    @Override
    public String getNodeManagementUri() throws Exception {
        return this._inPort.getPortURI();
    }

}
