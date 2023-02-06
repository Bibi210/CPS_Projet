package components.interfaces;

import fr.sorbonne_u.components.interfaces.TwoWayCI;
import interfaces.PeerNodeAdressI;

/**
 * NodeCI
 */
public interface NodeCI extends TwoWayCI {

    PeerNodeAdressI connect(PeerNodeAdressI a) throws Exception;

    Void disconnect(PeerNodeAdressI a) throws Exception;
}