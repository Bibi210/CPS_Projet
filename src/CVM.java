import components.Node;
import components.NodeManagement;
import components.connectors.NodeManagementServiceConnector;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.cvm.AbstractCVM;

public class CVM
		extends AbstractCVM {

	String facadeUri = "facadeUri";
	String nodeUri1 = "nodeUri1";
	String nodeUri2 = "nodeUri2";

	public CVM() throws Exception {
		super();
	}

	@Override
	public void deploy() throws Exception {
		AbstractComponent.createComponent(NodeManagement.class.getCanonicalName(), new Object[] { facadeUri });
		AbstractComponent.createComponent(Node.class.getCanonicalName(), new Object[] { nodeUri1, "oport1" });
		AbstractComponent.createComponent(Node.class.getCanonicalName(), new Object[] { nodeUri2, "oport2" });
		doPortConnection(nodeUri1, "oport1", facadeUri, NodeManagementServiceConnector.class.getCanonicalName());
		doPortConnection(nodeUri1, "oport2", facadeUri, NodeManagementServiceConnector.class.getCanonicalName());
		super.deploy();
	}

	public static void main(String[] args) {
		try {
			// Create an instance of the defined component virtual machine.
			CVM a = new CVM();
			// Execute the application.
			a.startStandardLifeCycle(20000L);
			// Give some time to see the traces (convenience).
			Thread.sleep(5000L);
			// Simplifies the termination (termination has yet to be treated
			// properly in BCM).
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
// -----------------------------------------------------------------------------
