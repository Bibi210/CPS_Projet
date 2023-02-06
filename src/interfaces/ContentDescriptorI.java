package interfaces;

/**
 * ContentDescriptor
 */
public interface ContentDescriptorI extends ContentTemplateI {
    ContentNodeAdressI getContentNodeAdress();

    long getSize();

    boolean equals(ContentDescriptorI cd) throws Exception;

    boolean match(ContentTemplateI tm);
}