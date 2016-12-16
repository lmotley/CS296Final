(ns final.core
    (:require [clojure.core.match :refer [match]]
	      [clojure.string :as str])
    (:gen-class))

(def theMap
    {:foyer {:desc "An eerie entrance to the Haunted Angrave Manor. The lights suddenly flicker on as you set foot inside, and the door slams behind you. Gathering your nerves, you look around: the room is fairly empty, except for the doors to your north, west, and east. You are determined, however, to explore the manor and solve the mystery of its past. But, first, HINT: maybe you should enter 'help' to find out the commands available to you. "
	     :title "in the foyer"
	     :dir {:north :grand-hall
		   :west :lounge
		   :east :library}
	     :explorables {:nothing "Nothing happens here."}}
     :lounge {:desc "This is where Lord Angrave must have entertained his guests once upon a time. Now, it is in ruins, and contains only a moldly and spider-infested COUCH, as well as a cavernous, blackened FIREPLACE. "
	      :title "in the lounge"
	      :dir {:north :dining-room
		    :east :foyer}
	      :explorables {:couch "The couch is quite ornate, and though it's very worn, you can tell the design is some sort of Chinese dragon. Its ruby eyes gleam at you. You sit down on the oouch, and suddenly, it collapses! You fall with a start, and a hidden drawer pops out of the bottom. Once you recover, you notice a key inside. You pocket it in your inventory. "
			    :fireplace "You stare at the fireplace, whose gaping chamber is tall enough to fit you inside. The edges have been carved to look like the mouth of a hideous boar. As you take a cautious step toward it, the fire leaps to life, and you fall back. You scurry backwards to safety and the fire calms down. You vow never to get close again. "}}
     :dining-room {:desc "Once known for employing the finest chefs of the Cordon-Bleu, the only things being served in this room now are the skeletons of rats and rotten food. Before you lays a grand oak TABLE, covered in dust, and moth-eaten, green-upholstered CHAIR(s). "
		   :title "in the dining room"
		   :dir {:north :kitchen
			 :east :grand-hall
			 :south :lounge}
		   :explorables {:table "You examine the large table before you. Fine china and sterling silverware has been set out for a sort of eerie last meal. " 				     
				 :chair "You pull out the most astute and commanding chair at the end of the table (probably Lord Angrave's) and take a seat. It creaks ominously under your weight. You consider pocketing some of the fine silverware in front of you to sell, but think better of it and get up. "}}
     :kitchen {:desc "One of the most terrifying rooms in the entire manor: it sports a medieval, black iron OVEN, as well as several CUPBOARD(s) falling off their hinges. Flies buzz in the air around you. You wonder if you should check one of the DRAWER(s) for something to defend yourself with. "
	       :title "in the kitchen"
	       :dir {:east :billiard-room
		     :south :dining-room}
	       :explorables {:oven "You walk up to the towering oven. You attempt to peer inside its grill, but the room is already dark, and inside is an abyssmal black. Pulling at the oven door, you manage to pry it open with rusty 'SCREEEEEEE.' You're about to look inside, when suddenly, a cloud of bats bursts from inside and into the kitchen! They swarm around you, tangling themselves up in your hair and clothes; you bat frantically at them, rolling around on the kitchen floor. After a protracted struggle, you untangle the last one from your hair, and they all pour out the kitchen door. "
			     :cupboard "You reach for the handle to the cupboard nearest you, but find nothing inside but petrified meats and vegetables and...guano? " 
			     :drawer "You pull open a drawer and start rifling around. Odds and end clang around, mostly useless, forgotten pieces of silverware. On the last drawer you open, however, you notice a slight indentation on the bottom. You press down, and a secret compartment springs back, revealing a key. "}}
     :grand-hall {:desc "A once opulent central room with a grand spiral STAIRCASE, one that Lord and Lady Angrave must have looked very regal coming down from. It has since collapsed, and the upper floor is inaccesible. Behind it is a much less fancy CLOSET, where cleaning supplies might've been kept. " 
	 	  :title "in the grand hall"
		  :dir {:north :billiard-room
			:west :dining-room
			:east :portrait-hall
			:south :foyer}
		  :explorables {:staircase "You climb up the first few steps of the grand staircase, and imagine what the room must've been like in its former glory. Suddenly, you think you can hear the sound of a grand piano playing. As you watch in awe, dozens of ghostly figures appear before you, refined lords and ladies in evening wear. You can see right through them. The music gets louder, and they begin to parade around the room in a ghostly waltz. A woman breaks off from her partner and floats over to you. She gently takes your hand and places something in it. Then she smiles and the whole scene vanishes. You open your hand, and there is a key. You add it to your inventory. "
			       :closet "You open the shabby wooden closet, and find it dank and empty. You wonder if you should try poking around the STAIRCASE instead. " }}
     :billiard-room {:desc "A place where guests once retired to play games, it seems to be in the best condition of any of the other rooms in the manor. A set of billiard CUE(s) lays in a pile on the floor, knocked over and forgotten, and BALL(s) are in a mess around the billiard table, as if they were abandoned in the middle of a game. "
		     :title "in the billiard room"
		     :dir {:west :kitchen
			   :east :secret-room
			   :south :grand-hall}
		     :explorables {:ball "You walk over to the table, and examine the game in front of you. You notice something odd about the shape of the 8 ball: there is a seam around the middle. You pick it up and give a twist. A catch inside releases, and the ball opens, revealing a plush chamber inside that hold a key. You pocket it in your inventory. "
				   :cue "You pick up a billiard cue and feel its weight. You were never all that good at the game; the closest thing you got to liking billiards was the magic 8 ball you owned as a kid. "}}
     :library {:desc "A grand old place full of battered, cobwebbed tomes, ones of all languages and genres. It seems the Angraves were great book collectors. Many books have fallen off the shelves and their pages have turned brown with the passing of time, like they may crumble in your fingers. You see a section on FANTASY books, MYSTERY books, and ROMANCE books. "
	       :title "in the library"
	       :dir {:north :portrait-hall
		     :west :foyer}
	       :explorables {:fantasy "You browse through some of the fantasy books on the shelves. Titles include 'The Lord of the Rings,' 'The Once and Future King,' and 'Dune.' You wonder if that last one should be refiled. "
			     :mystery "One of the books in the mystery section is sticking out further than the others. You pull it down from the shelf, only to find that the middle has been hollowed out, and inside is a key. You pocket the key in your inventory. "
			     :romance "There is a small collection of romance novels on the shelves, some embossed with jewels and painted with gold. " }}
     :portrait-hall {:desc "The eyes of many an Angrave stare down at you here: some even seem to follow you. Three are more prominent than the rest; though their paint is peeling and cracked, you can make out the images of a LORD, and LADY, and a DOG. "
		     :title "in the portrait hall"
		     :dir {:north :secret-room
			   :west :grand-hall
			   :south :library}
		     :explorables {:lord "You look up at the portrait of the man of the house, Lord Angrave. He appears to be an astute British man with an impeccable blue polo shirt. A placard below reads 'Master of System Programming, Tragically Lost at C.'"
				   :lady "You look up at the portrait of the woman of the house, Lady Angrave. The paint has peeled away from her face, leaving it obscured except for her eyes, which you can swear you feel following you around the room."
				   :dog "You gaze up at the portrait of the family dog. A placard below reads, 'Foobar: his bark was worse that his byte.' " }}
     :secret-room {:desc "At last, you slot all the keys in their holes, and give each a turn. While a mighty turn of the tumblers, the door unlocks and swings open. The mystery of the Angrave Manor finally reveals itself to you...inside lays a SKELETON, a LETTER, and a LOCKET. "
		   :title "in the secret room"
		   :dir {:west :billiard-room
			 :south :portrait-hall}
		   :explorables {:skeleton "You examine the skeleton of what appears to be a man. It has long since decayed to a blanched lily white. Its clothes are elegant velvet and satin, if a bit musty. Its boney fingers are covered in rich jewels, including a signet ring stamped with 'LAA'. "
				 :letter "You reach for the letter on the ground and pick it up. It reads: 'MY DEAREST BEATRICE: IT HAS BEEN SEVERAL FORTNIGHTS NOW SINCE I HAVE LAST GAZED INTO YOUR EMERALD EYES AND WATCHED THE SUN GLINT OFF YOUR GOLDEN TRESSES. MY HEART CANNOT BEAR TO BE APART FROM YOURS MUCH LONGER, AND I WORRY THAT I WILL NEVER SEE THE DAY WHEN YOU RETURN TO ME. MY DESPAIR HAS REACHED SUCH A DEPTH THAT I HAVE DECIDED TO SEAL OFF THE MANOR AND LIVE IN RECLUSION UNTIL YOUR RETURN. I SHALL GAZE OUT THE WINDOW OF MY STUDY EVERY DAY, WAITING FOR YOUR SHIP TO REACH THE HARBOR. IN FACT, I DO NOT THINK I SHALL BUDGE FROM THIS SPOT, FOR MY HEART IS SO HEAVY, UNTIL THAT DAY. I LONG FOR THE BLESSED HOUR WHEN OUR HEARTS MEET ONCE MORE. TRULY YOURS, LAWRENCE.' Well, it seems you have at last solved the mystery of the Angrave Manor! Congratulations! You may quit the game, or continue exploring the house. "
				 :locket "A golden locket, finely engraved with an elaborate 'B' on the front. You open it, and inside is a black and white photo of an astute looking woman, smiling serenely."}}}) 
(def adventurer
    {:location :foyer
     :seen #{}
     :explored #{}
     :keys '[]})

(defn go [dir player]
    (let [location (player :location)
	  dest (->> theMap location :dir dir)]
	(if (nil? dest)
	    (do (println "You can't go that way. ")
		 player)
	    (if (= dest :secret-room)
		(if(= (count (player :keys)) 5)
		    (assoc-in player [:location] dest)
		    (do (println "A locked room. There's panel of five keyholes beside the door. It seems you don't have enough keys to enter.")
			 player))
	    (assoc-in player [:location] dest)))))

(defn explore [player exp addk]
    (if (nil? (((theMap (player :location)):explorables) exp))
  	(do (println "Not a valid location.") player)
	(if ((player :explored) exp)
   	    (do (println (str "You have already explored the " exp ". "))
	         player)
	    (do (println (((theMap (player :location)):explorables) exp))
		(let [nuplayer (update-in player [:explored] #(conj % exp))]
		    (if addk
			(update-in nuplayer [:keys] #(conj % addk))
                         nuplayer ))))))

(defn inventory [player]
    (do (println(player :keys))
	 player))

(defn help [player]
    (do (println "---------------------------")
	(println "GO NORTH: 'north'")
        (println "GO SOUTH: 'south'")
        (println "GO WEST: 'west'")
    	(println "GO EAST: 'east'")
    	(println "SEE ROOM DESC: 'look'")
    	(println "CHECK INVENTORY: 'inventory'")
    	(println "QUIT GAME: 'quit'")
    	(println "EXPLORE ROOM: 'explore'")
	(println "---------------------------")
	(println "HINT: When you first enter a room, the words of some objects will be in caps. These are explorables: if you enter the explore command, you will be prompted with what you want to explore. If you type one of the all-caps names, you will explore that object.")
	player))

(defn quit []
    (System/exit 0))

(defn toKeywords [commands]
    (mapv keyword (str/split commands #"[.,?! ]+")))

(defn exploreRespond [player]
    (println "What do you want to explore?")
    (def c (read-line))
    (def command (toKeywords c))
    (match command
	[:couch] (explore player :couch :green-key)
	[:fireplace] (explore player :fireplace false)
	[:table] (explore player :table false)
	[:chair] (explore player :chair false)
	[:oven] (explore player :oven false)
	[:cupboard] (explore player :cupboard false)
	[:drawer] (explore player :drawer :red-key)
	[:staircase] (explore player :staircase :blue-key)
	[:closet] (explore player :closet false)
	[:ball] (explore player :ball :silver-key)
	[:cue] (explore player :cue false)
	[:fantasy] (explore player :fantasy false)
	[:mystery] (explore player :mystery :black-key)
	[:romance] (explore player :romance false)
	[:lord] (explore player :lord false)
	[:lady] (explore player :lady false)
	[:dog] (explore player :dog false)
	[:skeleton] (explore player :skeleton false)
	[:letter] (explore player :letter false)
	[:locket] (explore player :locket false)
	_ (do (println "I don't understand you.")
	      player)))

(defn respond [player command]
    (match command
        [:look] (update-in player [:seen] #(disj % (-> player :location)))
        [:north] (go :north player)
	[:west] (go :west player) 
	[:east] (go :east player)
        [:south] (go :south player)
	[:explore] (exploreRespond player)
	[:inventory] (inventory player)
	[:help] (help player)
	[:quit] (quit)
        _ (do (println "I don't understand you. ")
              player))) 

(defn status [player]
    (let [location (player :location)]
	(print (str "You are " (-> theMap location :title) ". "))
	(when-not ((player :seen) location)
	    (print (-> theMap location :desc)))
	(update-in player [:seen] #(conj % location))))

(defn -main
    "I don't do a whole lot."
    [& args]
    (loop [local-map theMap
	   local-player adventurer]
	(let [pl (status local-player)
	    	 _   (println "What do you want to do? ")
	    	 command (read-line)] 
	    (recur local-map (respond pl (toKeywords command)))))) 
