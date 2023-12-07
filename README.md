<h2> TCP Socket-based ping-pong chat</h2> 

<h1> Usage: </h1>
After building, use 
<p/>
launch.sh - runs the second player in a separate process
<p/>launch_single_process.sh - runs in the same process
<h1> Original specification: </h1> 

1. create 2 players

2. one of the players should send a message to second player 

3. when a player receives a message should send back a new message that contains the received message
   concatenated with the message counter that this player sent.

4. finalizing the program (gracefully) after the initiator sent 10 messages and received back 10 messages

5.  players can run in the same or in separate java processes
