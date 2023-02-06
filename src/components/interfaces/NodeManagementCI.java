package components.interfaces;

import java.util.Set;

import fr.sorbonne_u.components.interfaces.TwoWayCI;
import interfaces.PeerNodeAdressI;

/**
 * NodeManagementCI
 */
public interface NodeManagementCI extends TwoWayCI {

    Set<PeerNodeAdressI> join(PeerNodeAdressI a) throws Exception;

    Void leave(PeerNodeAdressI a) throws Exception;
}