package com.edufabricio.lab.server;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.message.Message;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class RouteBuilderUtils {
	
	public static String toString(List<Long> longList) {
		StringBuilder sb = new StringBuilder();
		Iterator<Long> it = longList.iterator();
		while(it.hasNext()){
			sb.append(it.next());
			if(it.hasNext()){
				sb.append(",");
			}
		}
		return sb.toString();
	}
	

	
	public static HttpServletRequest getServletRequest(Exchange exchange) {
		// check the remote IP from the CXF Message
		Message cxfMessage = exchange.getIn().getHeader(CxfConstants.CAMEL_CXF_MESSAGE, Message.class);
		return (HttpServletRequest) cxfMessage.get("HTTP.REQUEST");
	}

	public static byte[] dataHandlerToBytes(DataHandler dataHandler) throws IOException {
		File tmpFile = dataHandlerToFile(dataHandler);
		byte[] bFile = new byte[(int) tmpFile.length()];
		FileInputStream fileInputStream = new FileInputStream(tmpFile);
		fileInputStream.read(bFile);
		fileInputStream.close();
		return bFile;
	}

	public static File dataHandlerToFile(DataHandler dataHandler) throws IOException {
		File tmpFile = File.createTempFile("attachment", "tmp");
		tmpFile.deleteOnExit();
		FileOutputStream out = new FileOutputStream(tmpFile);
		org.apache.commons.io.IOUtils.copy(dataHandler.getInputStream(), out);
		return tmpFile;
	}
}
