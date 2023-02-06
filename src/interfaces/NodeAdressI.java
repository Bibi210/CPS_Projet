package interfaces;

/**
 * NodeAdressI
 */
public interface NodeAdressI {
    String getNodeIdentifer() throws Exception;

    boolean isFacade();

    boolean isPeer();
}