# BlackJackBasicStrategy
Java console application which recommends actions during a game of blackjack based on basic strategy principles.

## How To Use
Run the application by running Main.java.

The console will ask if you would like to quit or start a new game.

At the start of the game you must enter the dealer's faceup card and your two cards.

Upon entering the values, the console will output the recommended action.

## Standing / Surrender / Double Down
If your recommended option is to 'stand', you will be immediately prompted if you would like to play the next hand.

If available, it will prompt the user to 'surrender'. 

You may also be prompted to 'Double Down' which stops the play and prompts for another hand. 

The dealer's next cards are irrelevant to the application since they are drawn after your decisions.

## Hitting
If the recommended option is to 'hit' you will be prompted for your next card.

This cycle will continue until you either reach a 'stand' or 'BUST' conclusion. 

## Splitting
If your recommended play is to 'Split' you will be asked for the next two cards.

The cycle will continue like above for each hand until a resolution is made.

!!! Current Issue:
The application currently does not support splitting multiple hands!
