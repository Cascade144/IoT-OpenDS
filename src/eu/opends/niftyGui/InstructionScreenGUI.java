/*
*  This file is part of OpenDS (Open Source Driving Simulator).
*  Copyright (C) 2015 Rafael Math
*
*  OpenDS is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  OpenDS is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with OpenDS. If not, see <http://www.gnu.org/licenses/>.
*/

package eu.opends.niftyGui;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial.CullHint;
import cz.cvut.cognitive.override.DistractionScreenGUIController;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import eu.opends.main.Simulator;
import java.io.File;



/**
 * This class represents the instruction screen GUI. It provides methods 
 * to open and close, initialize and destroy the GUI and to open a given 
 * tab of the GUI.
 * 
 * @author Rafael Math
 * 
 * edited by Johnny Marek
 */
public class InstructionScreenGUI 
{
	private Nifty nifty;
	private final Simulator sim;
	private boolean instructionScreenHidden = true;
	private boolean initiallyPaused = false;
	private final AssetManager assetManager;
	private final InputManager inputManager;
	private final AudioRenderer audioRenderer;
	private final ViewPort guiViewPort;
	private final FlyByCamera flyCam;
    private String xmlPath;
	
	
	/**
	 * Creates a new instance of the instruction screen.
	 * 
	 * @param sim
	 * 			SimulationBasics class.
	 */
	public InstructionScreenGUI(Simulator sim)
	{
		this.sim = sim;
		this.assetManager = sim.getAssetManager();
		this.inputManager = sim.getInputManager();
		this.audioRenderer = sim.getAudioRenderer();
		this.guiViewPort = sim.getGuiViewPort();
		this.flyCam = sim.getFlyByCamera();
	}

	
	/**
	 * Returns Nifty element of the instruction screen.
	 * 
	 * @return
	 * 			Nifty Element.
	 */
	public Nifty getNifty()
	{
		return nifty;
	}

	
	/**
	 * Shows the instruction screen.
	 * 
	 * @param layerID
	 * 			ID of layer to show
	 */
	public void showDialog(String layerID)
	{
		if(instructionScreenHidden)
		{
			sim.getShutDownGUI().hideDialog();
			sim.getKeyMappingGUI().hideDialog();
			initiallyPaused = sim.isPause();
			sim.setPause(true);
			sim.getGuiNode().setCullHint(CullHint.Always);
			initInstructionScreenGUI(layerID);
			instructionScreenHidden = false;
		}
	}
	

	/**
	 * Hides the instruction screen.
	 */
	public void hideDialog() 
	{
		if(!instructionScreenHidden)
		{
			closeInstructionScreenGUI();
			instructionScreenHidden = true;
			sim.getGuiNode().setCullHint(CullHint.Inherit);
			sim.setPause(initiallyPaused);
		}
	}


	/**
	 * Initializes the instruction screen.
	 * 
	 * @param layerID
	 * 			ID of layer to initialize
	 */
	private void initInstructionScreenGUI(String layerID)
	{
		if(sim.getCurrentTrial() > 1){
			return;
		}
		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
    	
    	// Create a new NiftyGUI object
    	nifty = niftyDisplay.getNifty();
        
        // Get layerID from interaction.xml file of coresponding DrivingTask
        // Read XML and initialize custom ScreenController
        switch (layerID){

			case "instructionScreen_1": {
				xmlPath = "Interface/InstructionScreenGUI.xml";
				nifty.fromXml(xmlPath, "start", new InstructionScreenGUIController(sim, this));
			}

            case "dataEntryScreen_1": {
                xmlPath = "Interface/InstructionScreenGUI.xml";
                nifty.fromXml(xmlPath, "start", new InstructionScreenGUIController(sim, this));
            } 
                break;
            
            case "distractionScreen_1": {
                xmlPath = "Interface"+File.separator+"DistractionScreenGUI.xml";
                nifty.fromXml(xmlPath, "start", new DistractionScreenGUIController(sim, this));
                
            } 
                break;
                
            default: System.out.println("Invalid layerID: " + layerID);
                break;
            }
                        

    	

    		
    	// attach the Nifty display to the GUI view port as a processor
    	guiViewPort.addProcessor(niftyDisplay);
    	
    	// disable fly camera, since mouse pointer is needed for user input
    	flyCam.setEnabled(false);
    	inputManager.setCursorVisible(true);
    	
    	Screen screen = nifty.getCurrentScreen();
		
    	// show given layer, hide all others
		for(Element layer : screen.getLayerElements())
			if(layer.getId().equals(layerID))
			{
				layer.show();
				for(Element elem : layer.getElements())
					elem.disableFocus();
			}
			else
				layer.hide();
	}

	
	/**
	 * Close the instruction screen.
	 */
	private void closeInstructionScreenGUI() 
	{
	nifty.exit();
        inputManager.setCursorVisible(false);
        flyCam.setEnabled(true);
	}
	
	public void writeDriverData(String element) {
		sim.getReactionCenter().setDriverStats(element);
	}
}
