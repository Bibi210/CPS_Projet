package implem;

import interfaces.PeerNodeAdressI;

public class PeerNodeAdress implements PeerNodeAdressI {
    String _nodeIdentifier = null;
    String _enteringPortUri = null;

    public PeerNodeAdress(String nodeIdentifier, String enteringPortUri) {
        this._nodeIdentifier = nodeIdentifier;
        this._enteringPortUri = enteringPortUri;
    }

    @Override
    public String getNodeIdentifer() {
        return this._nodeIdentifier;
    }

    @Override
    public String getNodeUri() {
        return this._enteringPortUri;
    }

    @Override
    public boolean isFacade() {
        return false;
    }

    @Override
    public boolean isPeer() {
        return true;
    }

}
