# Extreme21
An Extreme game of 21 (Based on the Resident Evil 7 minigame "Survival 21")

4-7-2019

Changes/Bug Fixes:
- Removed Summary.
- Functionality for "Aces In Play" has been added. Some Aces will now remain in play and can be removed through other Ace effects (Destroy, Destroy+, etc.)
- Added new Aces (Destroy, Destroy+, Extraction, One Up, Two Up, Two Up+, Return, Remove)
- Updated AcesList.md to have a check mark (✓) next to Aces that have been programmed.
- Updated code for Extreme21Frame.java to be more readable and less redundant.
- Fixed an issue where Ace Info was giving the Ace's Name instead of the Description.
- Changed logic for Opponent's Aces (No longer will be an ArrayList. Instead, it will be an array with a fixed size due to an Opponent having a limited variety of Aces.)
----------------------------------------------------------------------------------------------------
3-31-2019  
Summary: The main focus of this update was "Aces In Play". An Ace that is in play means it remains on the field until it is Destroyed, Replaced, or removed due to the Round/Match ending. An Ace can be "Destroyed" with the Aces Destroy, Destroy+, and several others that I plan to add. An Ace can be Replaced under specific conditions. For example, two Aces that raise the limit cannot be active at the same time. The one used the latest is the one that remains. Lastly, an Ace in play is removed when the Match/Round ends. This happens to all Aces in play. Their effects when leaving the field do not get triggered this way. I managed to add in the Two Up Ace as my main way to test this out. Buttons were used to show if an Ace is in play. This will later be changed to a maximum of 6 buttons for both players. The buttons themselves will also be changed in a later update to something like a picture. A nice little thing I added is allowing the buttons highlight text to tell you what the Ace does. 

Objectives:
1. Add in functionality for destroying/removing Aces that remain in play.
2. Fix Opponent Logic for using Aces.
3. Add more Aces into the game.
4. Add in more opponents. They will be: Knight, Bishop, Rook, King, Queen.
5. Add in Save function.
6. Add in EXP System.
7. Eventually swap out numbers for actual picture of cards.
8. Eventually swap out buttons for Aces in play with pictures.

Bugs:
- Using more than 3 Aces in play will cause an error. This will be fixed next update when the logic for Aces in play is adjusted.
- Opponent logic for Ace use needs to be adjusted.
- Adjust when delays occurr for smoother gameplay.
----------------------------------------------------------------------------------------------------
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
- Some scenarios where the Opponent should Win cause a Draw instead.
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
