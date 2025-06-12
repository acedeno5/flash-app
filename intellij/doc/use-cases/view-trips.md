# Use case name, e.g., view leaderboard


## 1. Primary actor and goals
Who is the main interested party and what goal(s) this use case is designed to help them achieve.


the user that wants to compare who has the fastest avg speed(distance/time), attempting to beat their fastest time.


## 2. Other stakeholders and their goals


Friends making sure that the avg speeds are accurate.




## 2. Preconditions
the user's identity is verified
the user has properly completed a drive so that the stats could be added to the leaderboard.


## 4. Postconditions


The top 10 fastest avg speeds are posted on the leaderboard for all users to see.


## 4. Workflow


The sequence of steps involved in the execution of the use case, in the form of one or more activity diagrams (please feel free to decompose into multiple diagrams for readability).


The workflow can be specified at different levels of detail:


* __Brief__: user views the top 10 fastest avg speeds among all the user across the app.
* __Casual__: users click view leaderboard upon opening the app in order to check the best avg speeds that they have on the app.
* __Fully-dressed__: users check the leaderboard when they open the app, or after a trip, in order to view the top 10 fastest avg speeds that there are recorded on the app.








Please be sure indicate what level of detail the workflow you include represents.


```plantuml
@startuml


skin rose


title View LeaderBoard


'define the lanes
|#application|User|
|#implementation|System|


|User|
start
:Logs on and starts their trip;


|System|
while (Check to verify starting location?) is (yes)
 :Starts time;
 |System|
 :end  time;
 :calculate duration of drive;
 :calculate distance/time;
 :adds the stats of the drive to the leaderboard;


endwhile (no)










|System|
:Tells user to insert right destination until the locations match what the user inserts;


stop
@enduml


```


## 5. Sequence Diagram




```plantuml


@startuml
skinparam sequenceMessageAlign center
hide footbox


title View Leaderboard Sequence


actor "User" as user
participant "FragmentLeaderboard" as fragment
participant "Controller" as main
participant "Leaderboard" as board


user -> main : clicks view leaderboard


main -> fragment : new fragment_leaderboard(leaderboard)


fragment -> board : getWorldLeaderboard()
activate board
board --> fragment : List<Record>
deactivate board


fragment -> fragment : updateLeaderboardDisplay()




fragment --> user : display sorted leaderboard


user -> fragment : clicks startNewTripButton


fragment -> main : showAddTrips()
activate main
main --> fragment : confirmation
deactivate main


@enduml






```

