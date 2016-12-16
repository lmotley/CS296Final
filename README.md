# CS296Final
Text based adventure game final project written in Clojure.
To get started, simply "lein run" from the terminal. A list of commands will be displayed if you enter "help," and are also included below:
(NOTE: the single quotes are merely stylistic. Do not include them when typing the command in the game.)

---------------------------
GO NORTH: 'north'
GO SOUTH: 'south'
GO WEST: 'west'
GO EAST: 'east'
SEE ROOM DESC: 'look'
CHECK INVENTORY: 'inventory'
QUIT GAME: 'quit'
EXPLORE ROOM: 'explore'
----------------------------
HINT: When you first enter a room, the words of some objects will be in caps. These are explorables: if you enter the explore command, you will be prompted with what you want to explore. If you type one of the all caps names (in lower case), you will explore that object.
For example:
"You are in the lounge. What do you want to do?"
explore
"What do you want to explore?"
couch

Will let you explore the couch.
