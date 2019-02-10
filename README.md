## Receiving Led IXI module

# Ixi module example for IOTA ICT network.

This ixi module listens address from the tangle and if that address receive transaction which have message "ON"
it will turn led on and if message is "OFF" it turns led off.

# USES:

https://github.com/Pi4J/pi4j


Please check out how your Raspberry pi versions pin numbers are defined at http://pi4j.com/ So you can figure out in which pin you should connect led on your Raspberry pi.

Connect LED with 1K ohm resistor.

YOU MUST USE RESISTOR WITH LED OTHERWISE IT MAY CAUSE DAMAGE TO YOUR RASPBERRY PI!

# TEST

 To test this IXI you must clone repo and go ReceivingLedIXI.java file and write address which you wanna use in to:
 t.address = ""; between "" you can write address with uppercase letters A-Z and number 9, make that address 81 char long.
 If you are using SendButton IXI module to send messages to this module you wanna use same address which you have make
 that module to send messages
 
 then compile with: gradle ixi and copy *.jar file in to ict/modules and restart ict
 
 # KEEP IN MIND THAT THIS MODULE DO NOT USE ANY TAGS, ENCRYPTION OR MAM. 
 # SO IF SOMEONE ELSE SEND "ON" MESSAGE TO ADDRESS YOU HAVE CHOSEN
 # IT WILL TURN LED ON, USE THIS ONLY IN TEST PURPOSE


# USE AT YOUR OWN RISK!

I am not responsible for any damage caused by running this software. Please use it at your own risk! If you don't understand what you are doing, do not use it. Use this only as template and make your own IXI module if you want to use Raspberry Pi's GPIOs.
