package raspberrypi;

/*
    This code Blinks an LED bulb connected to a GPIO pin using BroadComm chipest BCM 
    pin numbering system
    The code makes use of the pi4j library
 */
import com.pi4j.io.gpio.*;

public class BlinkingLED {

    //Disclaimer: I put this code together from different souces,
    //Haven't tested it yet, would like you to tell me how it workes for you. 
    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J--> GPIO Control Example ... started.");

        // in order to use the Broadcom GPIO pin numbering scheme, we need to configure the
        // GPIO factory to use a custom configured Raspberry Pi GPIO provider
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        //Provision pin 02 as an output pin --> change that number if you use another pin
        final GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiBcmPin.GPIO_02);

        /**
         * Blink every second
         */
        ledPin.blink(1000, 15000);

        Thread.sleep(500);

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
    }
}