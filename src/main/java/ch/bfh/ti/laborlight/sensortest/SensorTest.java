package ch.bfh.ti.laborlight.sensortest;

import ch.quantasy.tinkerforge.tinker.agent.implementation.TinkerforgeStackAgent;
import ch.quantasy.tinkerforge.tinker.application.implementation.AbstractTinkerforgeApplication;
import ch.quantasy.tinkerforge.tinker.core.implementation.TinkerforgeDevice;

import com.tinkerforge.BrickletAmbientLight;
import com.tinkerforge.BrickletAmbientLight.IlluminanceListener;
import com.tinkerforge.Device;

public class SensorTest extends AbstractTinkerforgeApplication implements
		IlluminanceListener {

	//measure period in millisecs
	public static int MEASURE_PERIOD = 1000;
	public static int BRIGHT_LIGHT = 2000;
	public static int NORMAL_LIGHT = 1000;

	private BrickletAmbientLight ambientLightBricklet;

	public SensorTest() {}
	
	@Override
	public void deviceConnected(TinkerforgeStackAgent tinkerforgeStackAgent,
			Device device) {
		System.out.println(TinkerforgeDevice.getDevice(device));
		
		if ((TinkerforgeDevice.getDevice(device) == TinkerforgeDevice.AmbientLight)){
			if (this.ambientLightBricklet != null) {
				return;
			}
			this.ambientLightBricklet = (BrickletAmbientLight) device;
			this.ambientLightBricklet.addIlluminanceListener(this);
			this.setCallback(MEASURE_PERIOD);
		}
		
	}

	@Override
	public void deviceDisconnected(TinkerforgeStackAgent tinkerforgeStackAgent,
			Device device) {
		if ((TinkerforgeDevice.getDevice(device) == TinkerforgeDevice.AmbientLight)
				&& device.equals(this.ambientLightBricklet)) {
			    ((BrickletAmbientLight) device)
				    .removeIlluminanceListener(this);
			    this.ambientLightBricklet = null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public void illuminance(int illuminance) {
		if(illuminance > BRIGHT_LIGHT){
			System.out.println("it is very bright");
		}else if(illuminance > NORMAL_LIGHT){
			System.out.println("normal room brightness");
		}else{
			System.out.println("its very dark");
		}
	}
	
	private void setCallback(int callback){
		try {
			this.ambientLightBricklet.setIlluminanceCallbackPeriod(callback);
		} catch (Exception e) {
			System.out.println("device not connected");
		}
	}
	
}
