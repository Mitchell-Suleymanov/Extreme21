# Extreme21
An Extreme game of 21 (Based on the Resident Evil 7 minigame "Survival 21")

Rules:
1. The Player and Opponent start the game with a Bet of 1, a Life of 10, a Hand with 2 cards (the first is facedown), and 4 Aces.
2. The Deck contains numbered cards from 2 through 11, no repeats.
3. The Player and Opponent can choose to Draw from the deck, Stay, or use an Ace.
4. Drawing or Staying will end the current turn player's turn. Using an Ace will not.
5. An Ace can do various things, such as draw a card from the deck, raise the opponent's bet, etc. Some Aces can be left in play on the field. When it is removed, it's effect end. Only 6 Aes can be on the field per player.
6. A Match ends when both the Player and Opponent choose to "Stay".
7. Whoever has the highest total Hand without going over the Limit (default of 21) will win. (Ex. 19 beats 12. 21 beats 20. 5 beats 23). If both players are over the limit, or have a Hand with the same total, then the match ends in a Draw.
8. Whoever loses the Match has to give up Life equal to the Bet. The default Bet is always 1.
9. If an Opponent's Life reaches 0, the Round will increment. The Player's Life will remain the same. There is a total of 10 Rounds.
10. If the Player's Life reaches 0, you lose and have to restart the game. All progress will be lost.

Game Terms:
- Player - This is you. You are the Player.
- Opponent - This is your opponent. An Opponent can be a Pawn, Knight, Bishop, Rook, King, Queen, etc. Every Opponent has a different strategy and available Aces.
- Life - A player's life. If it reaches 0, they lose. There is currently no way to restore Life, so be careful.
- Bet - A player's bet. The loser of the Match must give up Life equal to their Bet. This cannot drop below 0. Bets have no limit.
- Deck - The game's Deck contains 10 cards. These cards are numbered from 2 to 11. There are no repeating cards.
- Hand - The player's Hand containing their cards. The first card in a Hand is facedown. The Player knows what their facedown card is, but cannot see the Opponent's facedown card. The reverse applies to the Opponent.
- Ace - A special card to make the game more exciting. An Ace can do many things. The complete list of Aces and their functions are available in this Repository. There is no limit to how many Aces a player can hold.
- Match - A Match occurrs between the Player and Opponent. Each Match lasts until both players chose to "Stay".
- Round - A Round is a collection of Matches. A new Round begins when an Opponent is defeated.

--------------------------------------------------------------------------------------------------------------------------------
# Files

Package Aces:
This package contains all the Aces.
- Aces.java - Parent class for all Aces. Has default functions. All Aces extend this.
- AceAceOfGreed.java - Ace with ability to draw 2 Aces.
- AceAceSwitch.java - By randomly discarding 2 Aces, the user can draw new 3 Aces.
- AceAceSwitchPlus.java - Evolution of above AceSwitch. By randomly discarding 2 Aces, the user can draw new 4 Aces.
- AceDestroy.java - The user removes the last Ace in play by the opponent.
- AceDestroyPlus.java - Evolution of Destroy. The user removes all Aces in play by the opponent.
- AceDestroyPlusPlus.java - Evolution of Destroy+. The user removes all Aces in play by the opponent and restricts them from using Aces again until the Match is over.
- AceDrawX.java - Ace with ability to draw a specific card from the deck.
- AceExchange.java - Ace with ability to exchange the last face up cards the Player and Opponent have.
- AceTwoUp.java - Ace with ability to add 2 to the opposing player's Bet while it remains in play.

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
- OpponentBishop.java - Medium opponent the Player will face. Their goal is to reach 21, or the game's limit, then use the Twenty-One-Up Ace for an instant kill against the Player. Bishop will constantly use Draw Aces to draw the 2, 4, or 6 card. They also will use these drawing Aces in addition to the OneUp to constantly draw and replenish their supply of Aces.
