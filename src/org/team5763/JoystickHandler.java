package org.team5763;

import at.wisch.joystick.FFJoystick;
import at.wisch.joystick.JoystickManager;
import at.wisch.joystick.exception.FFJoystickException;
import at.wisch.joystick.ffeffect.ConstantEffect;
import at.wisch.joystick.ffeffect.Effect;
import at.wisch.joystick.ffeffect.direction.CartesianDirection;

public class JoystickHandler {
    ConstantEffect activeEffect;
    CartesianDirection dir;
    FFJoystick joystick;
    public JoystickHandler() {
        try {
            JoystickManager.init();
            JoystickManager.reopenJoysticks();
            JoystickManager.enableJoystickEventPolling();
        } catch (FFJoystickException e) {
            System.err.println("Unable to prepare joystick controller.");
        }
        try {
            joystick = JoystickManager.getFFJoystick();
            stickInit();
        } catch (Exception e) {
            System.err.println("Unable to lock joystick.");
        }
    }
    private void stickInit() {
        System.out.println("[joystick] Preparing stick for use...");
        joystick.stopAll();
        joystick.destroyAll();
        dir = new CartesianDirection();
        dir.setCartesianXCoordinate(0);
        dir.setCartesianYCoordinate(0);
        dir.setCartesianZCoordinate(0);
        activeEffect = new ConstantEffect();
        activeEffect.setDirection(dir);
        activeEffect.setEffectLength(Effect.INFINITE_LENGTH);
        activeEffect.setLevel(0);
        activeEffect.setAttackLevel(0);
        activeEffect.setAttackLength(0);
        activeEffect.setFadeLevel(0);
        activeEffect.setFadeLength(0);
        activeEffect.setEffectDelay(0);
        joystick.playEffect(activeEffect, 1);
    }
    public void drive(double x, double y) {
        int pwr =
            (int)(Effect.MAX_LENGTH * Math.sqrt(x * x + y * y) / Math.sqrt(2));
        dir.setCartesianXCoordinate((int)(-1000 * x));
        dir.setCartesianYCoordinate((int)(1000 * y));
        activeEffect.setDirection(dir);
        activeEffect.setStrength(pwr);
        joystick.updateEffect(activeEffect);
    }
}
