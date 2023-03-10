package implem;

import java.util.Set;

import interfaces.ContentDescriptorI;
import interfaces.ContentNodeAdressI;
import interfaces.ContentTemplateI;

public class ContentDescriptor extends ContentTemplate implements ContentDescriptorI {

    public ContentDescriptor(String title, String albumTitle, Set<String> interpreters, Set<String> composers,
            int fileSize, String nodeAddr) {
        super(title, albumTitle, interpreters, composers);
        this._size = fileSize;
    }

    int _size = 0;
    ContentNodeAdressI _addr;

    @Override
    public ContentNodeAdressI getContentNodeAdress() {
        return this._addr;
    }

    @Override
    public long getSize() {
        return this._size; // Taille du MP3
    }

    @Override
    public boolean equals(ContentDescriptorI cd) throws Exception {

        boolean addrEqual = this._addr.getNodeIdentifer().equals(cd.getContentNodeAdress().getNodeIdentifer());
        boolean size = this.getSize() == cd.getSize();

        return _isTitleEquals(cd) && _isAlbumTitleEquals(cd) && _isIntrepretersContains(cd)
                && _isComposersContains(cd) && size && addrEqual;
    }

    @Override
    public boolean match(ContentTemplateI request) {
        if (_isTitleEquals(request))
            return true;
        if (_isAlbumTitleEquals(request))
            return true;
        if (_isIntrepretersContains(request))
            return true;
        if (_isComposersContains(request))
            return true;
        return false;

    }

    private boolean _isTitleEquals(ContentTemplateI request) {
        return request.getTitle().equals(getTitle());
    }

    private boolean _isAlbumTitleEquals(ContentTemplateI request) {
        return request.getAlbumTitle().equals(getTitle());
    }

    /**
     * > If the interpreters of the request are contained in the interpreters of the
     * content template, then
     * return true
     * 
     * @param request The request to be checked.
     * @return A boolean value.
     */
    private boolean _isIntrepretersContains(ContentTemplateI request) {
        return getInterpreters().containsAll(request.getInterpreters());
    }

    /**
     * If the composers of this template contain all the composers of the request,
     * then return true.
     * 
     * @param request The request to be fulfilled.
     * @return A boolean value.
     */
    private boolean _isComposersContains(ContentTemplateI request) {
        return getComposers().containsAll(request.getInterpreters());
    }
}