package eu.opends.trigger;

import com.jme3.input.InputManager;
import com.jme3.input.Joystick;
import com.jme3.input.JoystickAxis;
import com.jme3.input.JoystickButton;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.JoyAxisTrigger;
import com.jme3.input.controls.JoyButtonTrigger;
import com.jme3.input.controls.KeyTrigger;


import eu.opends.drivingTask.settings.SettingsLoader;
import eu.opends.drivingTask.settings.SettingsLoader.Setting;
import eu.opends.input.KeyBindingCenter;
import eu.opends.main.Simulator;

/**
 *   This class allows for a trigger action to change a keybinding
 *   on a specified trial. You can disable a keybinding, enable one,
 *   or change the current input binded to the control.
 *   Options: SteeringWheelRight, SteeringWheelLeft, Accelerator, Brake
 **/
public class KeyBindTriggerAction extends TriggerAction
{
    private Simulator sim;
    private String mappingName;
    private String actionType;
    private InputManager inputManager;

    public KeyBindTriggerAction(Simulator sim, float delay, int maxRepeat, String mappingName, String actionType)
    {
        super(delay, maxRepeat);
        this.mappingName = mappingName;
        this.actionType = actionType;
        this.inputManager = sim.getInputManager();
        this.sim = sim;
    }

    @Override
    protected void execute()
    {
        if(!isExceeded())
        {
            if(sim.getCurrentTrial() == 1)
            {
                int steeringControllerID;
                int steeringAxis;
                boolean invertSteeringAxis;
                SettingsLoader settingsLoader = Simulator.getSettingsLoader();
                if(actionType.equals("enable")) {
                    System.out.println("Enabling Keybinding: " + mappingName);
                    switch(mappingName) {
                        case "SteeringWheelRight":
                            steeringControllerID = settingsLoader.getSetting(Setting.Joystick_steeringControllerID, 0);
                            steeringAxis = settingsLoader.getSetting(Setting.Joystick_steeringAxis, 1);
                            invertSteeringAxis = settingsLoader.getSetting(Setting.Joystick_invertSteeringAxis, false);
                            inputManager.addMapping(mappingName, new JoyAxisTrigger(steeringControllerID, steeringAxis, invertSteeringAxis));
                            break;
                        case "SteeringWheelLeft":
                            steeringControllerID = settingsLoader.getSetting(Setting.Joystick_steeringControllerID, 0);
                            steeringAxis = settingsLoader.getSetting(Setting.Joystick_steeringAxis, 1);
                            invertSteeringAxis = settingsLoader.getSetting(Setting.Joystick_invertSteeringAxis, false);
                            inputManager.addMapping(mappingName, new JoyAxisTrigger(steeringControllerID, steeringAxis, !invertSteeringAxis));
                            break;
                        case "Brake":
                            int brakeControllerID = settingsLoader.getSetting(Setting.Joystick_brakeControllerID, 0);
                            int brakeAxis = settingsLoader.getSetting(Setting.Joystick_brakeAxis, 5);
                            boolean invertBrakeAxis = settingsLoader.getSetting(Setting.Joystick_invertBrakeAxis, true);
                            inputManager.addMapping(mappingName, new JoyAxisTrigger(brakeControllerID, brakeAxis, invertBrakeAxis));
                            break;
                        case "Accelerator":
                            int acceleratorControllerID = settingsLoader.getSetting(Setting.Joystick_acceleratorControllerID, 0);
                            int acceleratorAxis = settingsLoader.getSetting(Setting.Joystick_acceleratorAxis, 6);
                            boolean invertAcceleratorAxis = settingsLoader.getSetting(Setting.Joystick_invertAcceleratorAxis, true);
                            inputManager.addMapping(mappingName, new JoyAxisTrigger(acceleratorControllerID, acceleratorAxis, invertAcceleratorAxis));
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid actionType.");
                    }


                } else if (actionType.equals("disable")) {
                    System.out.println("Disabling Keybinding: " + mappingName);
                    //inputManager.deleteMapping(mappingName);
                    switch(mappingName) {
                        case "SteeringWheelRight":
                            steeringControllerID = settingsLoader.getSetting(Setting.Joystick_steeringControllerID, 0);
                            steeringAxis = settingsLoader.getSetting(Setting.Joystick_steeringAxis, 1);
                            invertSteeringAxis = settingsLoader.getSetting(Setting.Joystick_invertSteeringAxis, false);
                            inputManager.deleteTrigger(mappingName, new JoyAxisTrigger(steeringControllerID, steeringAxis, invertSteeringAxis));
                            break;
                        case "SteeringWheelLeft":
                            steeringControllerID = settingsLoader.getSetting(Setting.Joystick_steeringControllerID, 0);
                            steeringAxis = settingsLoader.getSetting(Setting.Joystick_steeringAxis, 1);
                            invertSteeringAxis = settingsLoader.getSetting(Setting.Joystick_invertSteeringAxis, false);
                            inputManager.deleteTrigger(mappingName, new JoyAxisTrigger(steeringControllerID, steeringAxis, !invertSteeringAxis));
                            break;
                        case "Brake":
                            int brakeControllerID = settingsLoader.getSetting(Setting.Joystick_brakeControllerID, 0);
                            int brakeAxis = settingsLoader.getSetting(Setting.Joystick_brakeAxis, 5);
                            boolean invertBrakeAxis = settingsLoader.getSetting(Setting.Joystick_invertBrakeAxis, true);
                            inputManager.deleteTrigger(mappingName, new JoyAxisTrigger(brakeControllerID, brakeAxis, invertBrakeAxis));
                            System.out.println("Deleted brake keybinding.");
                            break;
                        case "Accelerator":
                            int acceleratorControllerID = settingsLoader.getSetting(Setting.Joystick_acceleratorControllerID, 0);
                            int acceleratorAxis = settingsLoader.getSetting(Setting.Joystick_acceleratorAxis, 6);
                            boolean invertAcceleratorAxis = settingsLoader.getSetting(Setting.Joystick_invertAcceleratorAxis, true);
                            inputManager.deleteTrigger(mappingName, new JoyAxisTrigger(acceleratorControllerID, acceleratorAxis, invertAcceleratorAxis));
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid actionType.");
                    }
                }
                updateCounter();
            }
        }
    }
}
