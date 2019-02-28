# Extreme21
An Extreme game of 21 (Based on the Resident Evil 7 minigame "Survival 21")

Package Aces:
This package contains all the Aces.
- Aces.java - Parent class for all Aces. Has default functions. All Aces extend this.
- AceDrawX.java - Ace with ability to draw a specific card from the deck.

Package Extreme21:
This package contains the main file, GUI, and various Game Elements.
- Extreme21.java - The Main of the project.
- Extreme21Frame.java - GUI for the game, as well as slight additional help for the game elements.
- Extreme21Game.java - Class containing all game elements. It contains everything needed for the game, including the Player and Opponent objects.
- Player.java - Class for the Player.

Package Opponents:
This package contains all opponents.
- Opponent.java - Parent class of all opponents. Has default functions. All Opponents extend this.
- OpponentPawn.java - Basic opponent the Player can face. Easy AI. 
