# Extreme21
An Extreme game of 21 (Based on the Resident Evil 7 minigame "Survival 21")

3-21-2019
Summary: After some work, I finally figured out how to get the delay I needed during the game. The JFrame runs on a separate Thread  so simply using Thread.sleep does nothing to it. Using the Timer Class, I was able to create the delay I need. I also managed to get the Aces to work and added in the Exchange Ace. Currently only the Player has access to Aces, but I will let the Opponent use them soon. The main goal now is to figure out how to have "Aces In Play". If an Ace is in play, it will appear in the menu for the Player to see. This is the next important step.

Objectives:
1. Add in functionality for Aces that remain in play.
2. Add more Aces into the game.
3. Add in more opponents. They will be: Knight, Bishop, Rook, King, Queen.
4. Add in Save function.
5. Add in EXP System.
6. Eventually swap out numbers for actual picture of cards.

Bugs:
- Adjust when delays occurr for smoother gameplay.
- Nothing happens if either Player or Opponent run out of Life.

----------------------------------------------------------------------------------------------------
2-27-2019
Summary: Adding all files on here. I've been very busy working on this project for a few weeks now. So why this? Well it has been rough applying to jobs with no experience. I figured one way to show my skills would be to create my own side project. Out of all things, I chose to remake the Resident Evil 7 minigame "Survival 21" in Java.
This will look nowhere near as good as the console game does, but that isn't the point. The goal is to see if I can recreate it or not. While I am recreating it, I am putting in my own personal touch. I am adding in new Trump cards, renamed to Aces. I am also planning to add an EXP system and save function, but that is near the end of this project's life.

Objectives:
1. Integrate Aces into the game. They are coded but so far do nothing.
2. Add in more opponents. They will be: Knight, Bishop, Rook, King, Queen.
3. Add in pauses for the GUI to create suspense like in the game.
4. Add in Save function.
5. Add in EXP System.

Bugs:
- Label for opponent's hand is not revealed when match ends.
- Implemented delays do not work correctly. They have been removed until they are working as intended.
