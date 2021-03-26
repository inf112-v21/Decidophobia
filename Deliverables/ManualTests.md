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
    The game is made so by visting all the flags in order grants the player victory, 
    hence the player wants to move towards the flag.
    
    The test was preformed like this:
        1. The game starts.
        2. The Player Moves the ingame player.
        3. PLayer moves towards the flag.
        4. The player dissapears.
    It was a succsessfull test, the player dissapeared.

#### 3:
    Tested if the Pit kills the player.
    In the game the  pit is made as a hindarance for the player.
    
    The test was preformed like this:
        1. The game starts.
        2. The player moves the ingame player.
        3. The player moves toward the pit.
        4. The player  dissapear, hence the player is dead.
    The test was successfull
