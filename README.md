# IoT-openDS

This is an `open-source` fork of [openDS-oss](https://github.com/breznak/openDS-oss) which is a fork of [openDS](https://www.opends.de/). 

## Changes in this repository:
 - assets included (no installation required)
 - new task added for 
 
## Installation
 - just clone this repository
 - open the project in IDE
  - edit `Project properties > Run > Working directory`: set to your root of OpenDS (absolute path)
 - run

## Motivation
 - Thanks to contributions from (https://github.com/breznak) and (https://github.com/breznak/openDS-oss)
 - The tasks on openDS ran poorly and I needed to use the reaction test, so I fixed it
 
## License
 - files conributed by OpenDS.de keep their license (GPL-v2) and copyright
 - breznak's modifications to those code also keep the license
 - code contributed by openDS-oss developers keep their license and copyright (GPL-v2)
 - My code also keeps (GPL-v2)

## Conferences & Papers



# Original README: 
OpenDS (Open-source Driving Simulator)
======================================

Version 3.5 - November 13th, 2015


OpenDS is an open source driving simulator for research. The software is 
programmed entirely in Java and is based on the jMonkeyEngine framework, 
a scene graph based game engine which is mainly used for rendering and 
physics computation. OpenDS is distributed under the terms of GNU General 
Public License (GPL).



1. What is contained in this folder
-----------------------------------
The folder you downloaded contains sources and an already built version of OpenDS 
including the Drive Analyzer tool to replay a drive. Furthermore, library files 
needed to run OpenDS - from sources and binaries - as well as the assets folder
(containing scenes, models and tasks) is included. More details about the available 
tasks can be found in paragraph 7. In addition, you will also find the JavaDoc 
files, the license text, the multi-driver server and a client to receive data from 
the running simulator (settings controller server). 



2. Getting started
------------------
Binaries for Windows, Mac OS and Linux are contained in this folder. 

1. Make sure you have installed the Java Runtime Environment (JRE) version 8 
   or higher. If not, download it from http://www.java.com
2. Run 'OpenDS.jar' either by double clicking or using console (type: 
   'java -jar OpenDS.jar'). Windows users could also run "start.bat"
3. Select resolution and proceed with clicking 'OK'.
4. Specify driver’s name (optional).
5. Select which task to load and click 'Start'.

To stop the application, hit the ESC key or close the window.
Press F1 during simulation for default key assignment.

NOTICE: File 'startProperties.properties' might help you to start pre-defined
        tasks even faster. You can set up the display settings and skip the 
        selection screen by uncommenting 'showsettingsscreen=false'. Furthermore,
        you can set the name of the driver and the task to start when executing 
        OpenDS. 



3. Building OpenDS
------------------
Sources for Windows, Mac OS and Linux are contained in the "src" folder. The 
following steps show how to setup the source code for Eclipse. Of course, you
can use your favored IDE.

1. Make sure you have installed the Java Development Kit (JDK) version 1.8 
   or higher. If not, download it from 
   http://www.oracle.com/technetwork/java/javase/downloads/index.html
2. Start Eclipse and import an existing Project (File -> Import...). 
   Select 'General' -> 'Existing Projects into Workspace' and click 'Next'.
3. Select this folder as root directory and click 'Finish'.
4. Make sure the 'assets' folder has been added to your project.

You can skip the next two instructions if there are no Build Path errors.
5. Make sure that all jar files (counting 101) that can be found in 'lib' or 
   any of its sub-folders have been added to the Build Path.
6. Right-click the project and select 'Build Path' -> 'Configure Build 
   Path...' to open the 'Properties'-dialog. Go to tab 'Libraries' and 
   click 'Add Class Folder'. Select the check box of folder 'Logo' which 
   can be found at 'assets/Textures' as well as the check box of folder 
   'log4j' which can be found at 'assets/JasperReports'. Click 'OK' to close 
   both dialog windows.

7. Run OpenDS by right-clicking eu.opends.main.Simulator in Eclipse's Package 
   Explorer and selecting 'Run As' -> 'Java Application'.
8. Select resolution and proceed with clicking 'OK'.
9. Specify driver’s name (optional).
10.Select which task to load and click 'Start'.

To stop the application, hit the ESC key or close the window.
Press F1 during simulation for default key assignment.



4. Where to find documentation
------------------------------
More information can be found in the download area of the project website.



5. Contributors
---------------
a) Concept: Christian Müller
b) Architecture and development: Rafael Math
c) Other contributions:
	Saied Tehrani
	Michael Feld
	Otávio Biasutti
	Daniel Braun
	Gleb Banas
	Till Maurer
	Dastin Rosemann
	Paul Jacob Hoepner
	Malvin Danhof
	Tarek Schneider
	Michael Walz
	Eric Audehm
d) How to contribute? 
   Please write us using the contact form on the project website.



6. Credits
----------
Digital media assets have been taken from jMonkeyEngine (http://www.jmonkeyengine.org)
if no other reference can be found in the corresponding folder.

The "BigCity" model has been provided by Paul Jacob Hoepner from the Technical 
University of Berlin, Germany

The Oculus Rift Extension has been provided by Malvin Danhof, Tarek Schneider, Michael 
Walz, and Eric Audehm from Hochschule Konstanz (University of Applied Sciences), Constance, Germany

Breznak's github contributors and his contributions for the cognitive code


7. Available tasks
------------------

OldReactionTest/reactionTest.xml (Powerful computer required!)

This task contains a reaction experiment with instruction screen. The driver has to 
drive in the center lane at full speed and react to suddenly appearing signs. On red X 
signs the driver has to decelerate while keeping the center lane. On green arrow signs 
the driver has to change to the indicated lane at full speed. After a confirmation 
sound is played, the car is advised to go back to driving in the center lane at full 
speed. After the drive, a PDF file showing the driver's performance will be presented.

FixedReactionTest/reactionTest-fixed.xml (Fixed framerate issues)

Similar to the first test but now the framerate has been fixed and the road texture
is much easier to view by participants. In addition the code in the simulator has been
changed to allow for data entry by participants.

