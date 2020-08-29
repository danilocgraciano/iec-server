package iec.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.beanit.iec61850bean.BasicDataAttribute;
import com.beanit.iec61850bean.SclParseException;
import com.beanit.iec61850bean.SclParser;
import com.beanit.iec61850bean.ServerEventListener;
import com.beanit.iec61850bean.ServerModel;
import com.beanit.iec61850bean.ServerSap;
import com.beanit.iec61850bean.ServiceError;

public class IECServer implements ServerEventListener {

	private int port = 102;
	private ServerSap serverSap;
	private ServerModel serversServerModel = null;

	public static void main(String[] args) {

		try {
			new IECServer().runServer();
		} catch (SclParseException | IOException e) {
			e.printStackTrace();
		}

	}

	private void runServer() throws SclParseException, IOException {

		File file = new File("61850.icd");

		serverSap = new ServerSap(port, 0, null, SclParser.parse(new FileInputStream(file)).get(0), null);
		serverSap.setPort(port);
		serverSap.startListening(this);
		serversServerModel = serverSap.getModelCopy();

	}

	@Override
	public List<ServiceError> write(List<BasicDataAttribute> bdas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void serverStoppedListening(ServerSap serverSAP) {
		// TODO Auto-generated method stub

	}

}
