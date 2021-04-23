# Decidophobia

## Team Members
- Isak Hølleland (Tech-lead)
- Alexander Gullestad (Code tester)
- Petter Paulsen (Secretary)
- Oscar Strømsli (Customer service)
- Lars Andre Sande (UI-Designer)

## Description
This is a work-in-progress java version of the boardgame [RoboRally](https://en.wikipedia.org/wiki/RoboRally). The gameLogic uses the [libgdx library](https://libgdx.com/).

## How to run
1. Have Java installed on your device.
2. Have Maven installed on your device.
3. Clone the project with Git.
4. Run Launcher.java in your preffered IDE (we recommend IntelliJ).

## How to play
1. Move your character using the arrow keys on your keyboard. <br>
Use the up and down key to move your charachter forwards and backwards, respectively. <br>
Use the left and right key to rotate your charachter 90° to the left and right, respectively.  
2. for Online multiplayer, the host needs to:  
    * have tcp: 9000 open in the router
    * have udp: 54777 open in the router

## Known bugs
* There are bugs related to the boardcamera when changing the size of the gameScreen the camera can get moved off the board.
* When delivering PowerDown instead of cards when playing more than 1, ConcurrentModificationException is thrown.

