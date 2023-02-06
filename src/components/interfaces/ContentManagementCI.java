package components.interfaces;

import java.util.Set;

import fr.sorbonne_u.components.interfaces.ComponentInterface;
import implem.ContentTemplate;
import interfaces.ContentDescriptorI;

/**
 * ContentManagementCI
 */
public interface ContentManagementCI extends ComponentInterface {
    ContentDescriptorI find(ContentTemplate cd, int sautRestants);

    Set<ContentDescriptorI> match(ContentTemplate cd, Set<ContentDescriptorI> previouslyMatched, int sautRestants);
}