package ch.bfh.ti.laborlight.sensortest;

import java.io.IOException;

import ch.quantasy.tinkerforge.tinker.agency.implementation.TinkerforgeStackAgency;
import ch.quantasy.tinkerforge.tinker.application.definition.TinkerforgeApplication;
import ch.quantasy.tinkerforge.tinker.core.implementation.TinkerforgeStackAddress;

import com.tinkerforge.NotConnectedException;
import com.tinkerforge.TimeoutException;

public class SensorMain {

	public static void main(final String[] args) throws IOException, TimeoutException, NotConnectedException {
		System.out.println("Start");
		final TinkerforgeApplication testApp = new SensorTest();
		final TinkerforgeStackAddress identifier = new TinkerforgeStackAddress(
			"localhost");
		System.out.println("Starting");
		TinkerforgeStackAgency.getInstance().getStackAgent(identifier)
			.addApplication(testApp);
		System.out.println("Running");
		System.in.read();
		TinkerforgeStackAgency.getInstance().getStackAgent(identifier)
			.removeApplication(testApp);
	    }
	
}
