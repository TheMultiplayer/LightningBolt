# LightningBolt
**WARNING: This was last used >2 years ago, and is likely out-of-date!**
I've uploaded it so that other teams wanting to use a force-feedback joystick have a starting point.
## What is this?
This is a tool that interfaces NetworkTables and SDL to allow an FRC robot to drive a force feedback joystick.  It is designed to operate on Windows; the main class is Bolt.
## NetworkTables interface
The network tables client will look for the fields ```fX``` and ```fY``` and drive the joystick accordingly.  The version of NetworkTables bundled may be out of date now, so you may have to update it.

To set your team number for NetworkTables, use the ```settings.yml``` file.
## Joystick Driver
This requires a joystick with FF enabled and a valid driver!  On top of that, the file ```SDL.dll``` contains the SDL library used to interface with the joystick.