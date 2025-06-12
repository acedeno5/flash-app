


# NextGen Flash system - Vision document


## 1. Introduction
We envision creating an application that allows the user to track how quickly they can get from point A to point B,
as well  as comparing their average speeds to previous average speeds. Furthermore, we envision to take into account type
of transport and weather in order to contextualize the times.


## 2. Business case
Our TS software addresses customer needs that other products do not:
1. It can keep track of your top speed while creating a database to compare your own records and against other people in the world that traveled the same distance as you.
2. It integrates with location,weather,and timing systems to simplify Top Speed logistics.


## 3. Key functionality
- Track what time the driver reaches point B.
- Record and store data about the driving conditions
- Record and store data about the weather conditions
- Record and store data of the route taken
- Automatic offline top speed processing support when external systems fail.




## 4. Stakeholder goals summary
- **User**: logs in, starts trip(which includes check weather), ends trip, views past trips, and views world leaderboard.
- **Administrator**: manage users, configure system






## Use case diagram


[//]: # (```plantuml)


[//]: # (skin rose)


[//]: # ()
[//]: # (' human actors)


[//]: # (actor "User" as user)


[//]: # ()
[//]: # ()
[//]: # ()
[//]: # (' list all use cases in package)


[//]: # (package Flash{)


[//]: # (    usecase "Log On" as logOn)


[//]: # (    usecase "State Weather Conditions" as stateWeatherConditions)


[//]: # (    usecase "Start Trip" as startTrip)


[//]: # (    usecase "Log On" as logOn)


[//]: # (    usecase "View Leaderboard" as viewLeaderboard)


[//]: # (    usecase "End Trip" as endTrip)


[//]: # (    usecase "View Trips" as viewTrips)


[//]: # ()
[//]: # (})


[//]: # ()
[//]: # (' list relationships between actors and use cases)


[//]: # (user --> logOn)


[//]: # (startTrip --|> logOn : <<extends>>)


[//]: # (startTrip --> stateWeatherConditions : <<includes>>)


[//]: # (endTrip --|> startTrip : <<extends>>)


[//]: # (viewTrips --|> logOn : <<extends>>)


[//]: # (viewLeaderboard --|> logOn : <<extends>>)


[//]: # ()
[//]: # (' )


[//]: # ()
[//]: # (```)









