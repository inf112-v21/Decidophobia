# Manual Tests
### ServerTests
#### 1:
    Remark:
    This test still works, but the code isn't set up for this test at the moment,
    but it will still work since it passed before and was just to safely state that
    we have set up kryonet correctly and that we can exchange data globally and locally.
    
    The test:
    Tested online and LAN internet communication.
    Special requiermeants for online is having open a UDP-TCP port (host)
    and having the hosts public ip (online) or local ip (LAN).
    
    The manual test was performed like this: 
            1. Used 1 home computer as host, and another as client. 
            2. The client sent a request. 
            3. The host printed the request and then responded. 
            4. The client printed out the response. 
    This was successful both for LAN and Online.
    
#### 2:
    Tested if the flag makes the player dissapear.
    The gameLogic is made so by visting all the flags in order grants the player victory, 
    hence the player wants to move towards the flag.
    
    The test was preformed like this:
        1. The gameLogic starts.
        2. The Player Moves the ingame player.
        3. PLayer moves towards the flag.
        4. The player dissapears.
    It was a succsessfull test, the player dissapeared.

#### 3:
    Tested if the Pit kills the player.
    In the gameLogic the  pit is made as a hindarance for the player.
    
    The test was preformed like this:
        1. The gameLogic starts.
        2. The player moves the ingame player.
        3. The player moves toward the pit.
        4. The player  dissapear, hence the player is dead.
    The test was successfull

#### 4 GUI:
    Tested if the application worked correctly over 2 computers:
    I run the computers side by side and visually check if they pass.
    The main menu:
        * Tested if "play multiplayer" button worked by sending the user to JoinScreen.
        * Tested if "quit" exited the game and stop all currently running Threads.
        * Tested if you can come back to the menu after getting the joinScreen.
    The join screen:
        * Tested if host button starts lobby.
        * Tested if join-button joins a lobby by using the ip or stays on the joinScreen if join is not
        possible. Tested this by having the second computer hosting (then it could joind) and when it was
        not hosting the main computer stayed on the joinScreen.
        * Tested if cancel-button sent the user back to main menu.
    The lobby:
        * Tested if host starts a lobby, that he sees himself.
        * Tested if a player joins the lobby that the lobby gets updated for all players.
        * Tested if the lobby updated when a player is ready (it unchecked for both players)
        * Tested if default rules and a board were selected when starting a lobby.
        * Tested if newRules were applied by clicking the button (host) the rules changed for  
        all players.
        * Tested if map gets correctly uploaded to the server by the host, by using println in the server-request-
        handler.
        * Tested if a player does not have the selected board (selected in the rules) that it  
        downloads it from the server (the map is uploaded by the host). 
        * Tested if all are ready the game starts for all players.
        * Tested if a non-host player quits, that it gets removed by the lobby.
        * Tested if a host quits, that all other players gets kicked and sent to a different screen.
        * Tested if a player changes his nickname, that it gets updated for all players.
            - note that ',' cuts the nickname (Carl,Petterson, -> Carl).
            - since , is used to split information between Client/Server.
    The Game:
        * Tested if board loaded equally for both computers.
        * Tested if cards load and show on screen when dealt out.
        * Tested if cards are clickable and if they get sent when 5 cards are reached.
        * Tested if card in hand gets sent to chosen-cards, when clicked and when chosen-cards gets clicked that
        they get put back in the hand.
        * Tested if playerTags (the icon showing each players robots status) show for all players.
        * Tested if boards (actually the boardCamera) is moveable by the mouse against the edges of the screen
        while holding right mousebutton.
        * Tested if the board can move off screen, it can only if you resize the screen.
        * Tested if scrolling zooms the board.
        * Tested if cards and playerTags are visible for different screensizes by resizeing the window while
        playing.
    All of these tests passed.

        