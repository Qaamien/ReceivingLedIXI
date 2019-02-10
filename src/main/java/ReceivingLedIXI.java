import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.iota.ict.ixi.Ixi;
import org.iota.ict.ixi.IxiModule;
import org.iota.ict.network.event.GossipEvent;
import org.iota.ict.network.event.GossipFilter;
import org.iota.ict.network.event.GossipListener;


//                                   ***USE AT YOUR OWN RISK!***
//            *** USE AT YOU OWN RISK, IF YOU DON'T KNOW WHAT YOU ARE DOING DON'T USE IT ***

// This IXI module makes led turn on/off if address which it is listening receives message ON or OFF

public class ReceivingLedIXI extends IxiModule {

    //create gpio controller
    final GpioController gpio = GpioFactory.getInstance();

    //Setup GPIO as output pin
    final GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);

    private final static Logger LOGGER = LogManager.getLogger(ReceivingLedIXI.class);


    public ReceivingLedIXI(Ixi ixi) {
        super(ixi);
    }

    @Override

    public void run() {

        LOGGER.info("Receiving led IXI started, listening address messages!");

        this.ixi.addGossipListener(new GossipListener() {

            private final GossipFilter filter = new GossipFilter();

            @Override
            public void onGossipEvent(GossipEvent gossipEvent) {

                // Filter, which make module to only listen ict network address..
                filter.watchAddress("");

                // save ascii messages to string MSG
                byte[] message = gossipEvent.getTransaction().decodedSignatureFragments.getBytes();
                String MSG = new String(message);

                // Ignores other address transactions, only let watched address transactions pass
                // and monitor ascii messages of passed transactions

                if (filter.passes(gossipEvent.getTransaction()) && MSG.equals("ON")) {

                    ledPin.high();
                    LOGGER.info("Led turned on!");

                }


                if (filter.passes(gossipEvent.getTransaction()) && MSG.equals("OFF")){

                    ledPin.low();
                    LOGGER.info("Led turned off!");

                }
            }

    });

}
}


