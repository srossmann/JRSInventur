/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jrsinventur;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 *
 * @author rossmann
 */
public class MDECommunication {

    public void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    public void ListCommPort() {
    // get list of ports available on this particular computer,
        // by calling static method in CommPortIdentifier.
        Enumeration<?> pList = CommPortIdentifier.getPortIdentifiers();

        // Process the list.
        while (pList.hasMoreElements()) {
            CommPortIdentifier cpi = (CommPortIdentifier) pList.nextElement();
            System.out.print("Port " + cpi.getName() + " ");
            if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println("is a Serial Port: " + cpi);
            } else if (cpi.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
                System.out.println("is a Parallel Port: " + cpi);
            } else {
                System.out.println("is an Unknown Port: " + cpi);
            }
        }
    }
}
