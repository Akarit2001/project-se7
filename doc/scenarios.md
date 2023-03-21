JPacman Scenarios
=================

Arie van Deursen, Delft University of Technology.
1. Background
This document describes a series of JPacman user scenarios, following the format of behavior-driven development.

Thus, each scenario is of the form:

Title one line describing the story

Narrative

As a [role]
 I want [feature]
So that [benefit]
Acceptance Criteria, presented as scenarios of the form:

Scenario 1: Title
Given [context]
 and  [some more context]...
When  [event] 
Then  [outcome]
 and  [another outcome]...

## 2. JPacman Overview
JPacman is a very simple JPacman derivative, to be used for educational purposes. Essential features such as multiple levels and multiple players, energizers and different ghost types are postponed for later releases (and may be implemented by students as an exercise). Furthermore, to give the game a somewhat different flavor, JPacman deviates from standard Pacman behavior in several ways. This document describes the requirements as a series of use cases, and explains what the GUI should look like.

The JPacman game is played on a rectangular board. A square on the board can be empty, or can contain the Pacman itself, one of the several ghosts, a pellet (worth 10 points), or a wall. Moveable characters such as the Pacman and the ghosts can make single-step horizontal or vertical moves. Tunnels on the border make it possible to move from one border to the opposite border. When the Pacman moves over a square containing a pellet, the player earns points and the pellet disappears. If a player and a ghost meet at the same square, the the game is over. The player wins the game once he or she has eaten all pellets.

เกม JPacman เล่นบนกระดานสีเหลี่ยม ประกอบไปด้วยตัวหน้า Home ที่มีปุ่ม TAP THE SCREEN TO START  เพื่อกดไปหน้าเลือกสกิลตัวละคร ผู้เล่นสามารถกดปุ่มเพื่อเลือกสกิลตัวละครที่ต้องการเล่น กด SELECT เพื่อไปยังหน้าเลือกธีม เเละกด BACK เมื่อต้องการกลับไปยังหน้า HOME ภายในหน้าเลือกธีม ผู้เล่นสามารถเลือกธีมที่ต้องการเล่น เมื่อเลือกเเล้วให้กด SELECT เพื่อไปยังหน้าเล่นเกม หรือกด BACK เพื่อกับไปเปลี่ยนสกิลตัวละคร 

ภายในหน้าเล่นเกม  JPacman จะประกอบไปด้วย Pacman ผี ตัวPallet(มีค่า 10 คะแนน) หรือกำแพง มีตัวละครที่มีการเครื่องไหวเช่นPacman และผี ซึ่งสามารถเคลื่อนที่ในแนวนอนหรือแนวตั้ง เมื่อ Pacman เคลื่อนที่ผ่านช่องสีเหลี่ยมที่มี Pallet ผู้เล่นจะได้รับคะแนนเเละตัว Pallet จะหายไป หากผู้เล่นเดินชนผีหรือโดนผีชน เกมจะจบลง ผู้เล่นจะแพ้ทันที ผู้เล่นจะชนะเกมได้ก็ต่อเมื่อกินPalletจนหมด



## 3. User Stories

#### Story 1: Startup

```
As a player
 I want to start the game
so that I can actually play
 
Scenario S1.1: Start.
Given the user has launched the JPacman GUI;
When  the user presses the "Start" button;
Then  the game should start.
```


#### Story 2: Move the Player

```
As a player, 
 I want to move my Pacman around on the board;
So that I can earn all points and win the game.

Scenario S2.1: The player consumes
Given the game has started,
 and  my Pacman is next to a square containing a pellet;
When  I press an arrow key towards that square;
Then  my Pacman can move to that square,
 and  I earn the points for the pellet,
 and  the pellet disappears from that square.

Scenario S2.2: The player moves on empty square
Given the game has started,
 and  my Pacman is next to an empty square;
When  I press an arrow key towards that square;
Then  my Pacman can move to that square
 and  my points remain the same.

Scenario S2.3: The move fails
Given the game has started,
  and my Pacman is next to a cell containing a wall;
When  I press an arrow key towards that cell;
Then  the move is not conducted.

Scenario S2.4: The player dies
Given the game has started,
 and  my Pacman is next to a cell containing a ghost;
When  I press an arrow key towards that square;
Then  my Pacman dies,
 and  the game is over.
  
Scenario S2.5: Player wins, extends S2.1
When  I have eaten the last pellet;
Then  I win the game.
```

#### Story 3: Move The Ghost
```
As a ghost;
 I get automatically moved around;
So that I can try to kill the player.

Scenario S3.1: A ghost moves.
Given the game has started,
 and  a ghost is next to an empty cell;
When  a tick event occurs;
Then  the ghost can move to that cell.

Scenario S3.2: The ghost moves over a square with a pellet.
Given the game has started,
 and  a ghost is next to a cell containing a pellet;
When  a tick event occurs;
Then  the ghost can move to the cell with the pellet,
 and  the pellet on that cell is not visible anymore.

Scenario S3.3: The ghost leaves a cell with a pellet.
Given a ghost is on a cell with a pellet (see S3.2);
When  a tick even occurs;
Then  the ghost can move away from the cell with the pellet,
 and  the pellet on that cell is is visible again.

Scenario S3.4: The player dies.
Given the game has started,
 and  a ghost is next to a cell containing the player;
When  a tick event occurs;
Then  the ghost can move to the player,
 and  the game is over.
```

#### Story 4: Select a character skill

```
As a player,
I want to Select a character skill ;
In order to play various character skills.

Scenario S4.1: Select skill Pacman.
Given on select Skin page;
When  the player clicks the "SELECT" button;
Then it will go to the theme selection page.
If you press back, you will go to the start page.

```
#### Story 5: Select Theme

```
As a player,
I want to Select  Theme ;
So that I can try different theme.

Scenario S5.1: Select Valentine Theme.
Given on Select Theme page;
When  the player clicks the "SELECT" button;
Then it will go to the game page in a Valentine theme.
If you press back, you will go to the start page.

Scenario S5.2: Select China Theme.
Given on Select Theme page;
When  the player clicks the "SELECT" button;
Then it will go to the game page in a China theme.
If you press back, you will go to the start page.

Scenario S5.3: Select Christmas Theme.
Given on Select Theme page;
When  the player clicks the "SELECT" button;
Then it will go to the game page in a Christmas theme.
If you press back, you will go to the start page.

Scenario S5.4: Select Halloween Theme.
Given on Select Theme page;
When  the player clicks the "SELECT" button;
Then it will go to the game page in a Halloween theme.
If you press back, you will go to the start page.

Scenario S5.5: Select Songkran Theme.
Given on Select Theme page;
When  the player clicks the "SELECT" button;
Then it will go to the game page in a Songkran theme.
If you press back, you will go to the start page.

```
#### Story 6: Show Score

```
As a player,
I want to see my score ;
So that I can know current score.

Scenario S6.1: During Playing.
Given Playing game;
When  the player look at banner;
Then  banner is show current score.

Scenario S6.2: The player dies..
Given the player dies;
When the game shows a pop-up saying You Died;
It will then display the score that the player has played.

```
#### Story 7: Popup

```
As a player,
I want to start the game or return to the home page:
In order to start that game again.

Scenario S7.1: Restart Button.
Given Popup;
When the player presses the restart button will come 
back to play the game again

Scenario S7.2: Home Button..
Given Popup;
When the player presses the Home button will return to 
the game start page



```
## 4. User Interface
```

The user interface for JPacman is relatively simple. The game is rectangular board, which can be read from a special text file with simple character encoding. On the GUI, special (animated) images or colored squares are used for the ghost, food, empty cells, and wall cells on the board. The direction of the last (attempted) move is reflected in the image used for the player. The GUI furthermore contains a "Start", and "Stop" button (at the bottom of the GUI), as well as an indicator for the amount of food eaten and the game's overall state (playing, game won, player died, ready to start the play).

เมื่อกดเกม JPacman ผู้เล่นจะเจอกับหน้า Home ซึ่งจะมีปุ่ม TAP THE SCREEN TO START กดไปยังหน้าเลือกสกิลตัวละคร ภายในหน้าสกิลตัวละครผู้เล่นจะพบ สกิลต่างๆ เช่น Pacman Buble Santy Mulan Tom Dropy Chloe ให้เลือกเล่น เมื่อผู้เล่นกดปุ่ม BACK จะกลับไปยังหน้า Home เเต่ถ้าหากกดSELECT จะพาผู้เล่นไปยังหน้าเลือกธีม ภายในหน้าเลือกธีมผู้เล่นจะพบกับธีมทั้งหมด 5 ธีม ได้เเก่ Valentine China Christmas Halloween Songkran เมื่อเลือกธีมได้เเล้วให้กด SELECT เพื่อไปยังหน้าเล่นเกม หากผู้เล่นต้องการกับไปเลือกสกิลตัวละครให้กด BACK 
หน้าเล่นเกม  ผู้เล่นจะเจอกับปุ่มเริ่มเกมเพื่อเล่นเกม ปุ่มStopgเพื่อหยุดเกม เเละธีมที่ตนเองเลือกในหน้า Selext Theme
    -ธีม Valentine ผู้เล่นจะพบกับพื้นหลัง กำแพง ผี(หัวใจ) Pallet(ช็อคโกเเลต) ที่เกี่ยวกับวันวาเลนไทน์
    -ธีม China ผู้เล่นจะพบกับพื้นหลัง กำแพง ผี(มังกร) Pallet(ซาลาเปา) ที่เกี่ยวกับวันตรุษจีน
    -ธีม Christmas ผู้เล่นจะพบกับพื้นหลัง กำแพง ผี(กวาง) Pallet(กล่องของขวัญ) ที่เกี่ยวกับวันคริสต์มาส
    -ธีม Halloween ผู้เล่นจะพบกับพื้นหลัง กำแพง ผี(หน้าฮาโลวีน) Pallet(เทียน) ที่เกี่ยวกับวันฮาโลวีน
    -ธีม Songkran ผู้เล่นจะพบกับพื้นหลัง กำแพง ผี(หน้าผียิ้ม) Pallet(ขันน้ำ) ที่เกี่ยวกับวันฮาโลวีน
เมื่อกดเริ่มเล่นผู้เล่นต้องกิน Pallet ให้หมดเมื่อหมดจะเเสดงหน้า CHAMPION เเต่ถ้าหากโดนผีชนผู้เล่นจะแพ้ เเละมี Popup เเสดงคะเเนนของผู้เล่น พร้อมปุ่ม Restart เพื่อกดเล่นอีกครั้ง กับปุ่ม Home เพื่อกับไปยังหน้า Home
   


```
## 5. Development Requirements
```

JPacman should be developed in Java. It should be set up so that it
can easily be used by (the latest versions of) standard (open source) Java development tools, such as maven, JUnit, Eclipse/IntelliJ, cobertura, etc. To allow for working with maven, the maven standard directory structure should be used.  Since the educational purposes include testing, JPacman should be delivered with an extensive test suite.

----
[![Creative Commons License](http://i.creativecommons.org/l/by-sa/4.0/88x31.png)](http://creativecommons.org/licenses/by-sa/4.0/)
