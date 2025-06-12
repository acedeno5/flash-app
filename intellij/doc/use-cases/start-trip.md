# Use case name, e.g., start trip


## 1. Primary actor and goals
Who is the main interested party and what goal(s) this use case is designed to help them achieve.
The user who is interested in gathering statistics on their drives, mark starting location and begin timer


## 2. Other stakeholders and their goals


Friends making sure that the avg speeds are accurate.
## 2. Preconditions


The user is identified and verifying the starting location.


## 4. Postconditions


What must be true upon successful completion of the use case.


Timer duration starts
Time/ weather conditions are logged


## 4. Workflow


The sequence of steps involved in the execution of the use case, in the form of one or more activity diagrams (please feel free to decompose into multiple diagrams for readability).


The workflow can be specified at different levels of detail:


* __Brief__: the user begins a drive and inputs their starting location(latitude and alongitude);
*
* __Casual__: most common scenarios and variations;
* __Fully-dressed__: all scenarios and variations.


Please be sure indicate what level of detail the workflow you include represents.






```plantuml
@startuml


skin rose


title Start Trip


'define the lanes
|#application|User|
|#implementation|System|


|User|
start
:Logs on and starts their trip;


|System|
while (Check to verify starting location?) is (yes)
 :Starts timer;
 |System|
 :access weather info;
 :end  timer;
endwhile (no)










|System|
:Tells user to insert right destination until the locations match what the user inserts;


stop
@enduml
```
# 5. Sequence Diagram


```plantuml
@startuml
skinparam sequenceMessageAlign center
hide footbox


title Start Trip Sequence


actor "User" as user
participant "FragmentAddTrips" as fragment
participant "Controller" as main
participant "Trips" as trips


user -> fragment : enters start coordinates


fragment -> fragment : validate coordinates


fragment -> trips : startTrip(startLat, startLong)
activate trips
trips --> fragment : confirmation message
deactivate trips


fragment -> fragment : initializeTimer


fragment -> fragment : updateUIState


fragment -> fragment : updateTripDisplay


fragment -> main : notifyTripStarted
activate main
main --> fragment : confirmation
deactivate main


fragment --> user : display start confirmation


@enduml
```

