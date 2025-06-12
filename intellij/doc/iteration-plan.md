# Iteration-Plan

Step by step plan:
1. LogOn 
    a. in order to identify the user that is starting the drive, this will allow for different people to be in the leaderboard.
2. startTrip
    a. this will begin the timer and marking the starting point/verifying the location.
3. stateWeatherConditions
    a. this will allow the user to understand outside factors that could have affected the driving duration by using the weather database.
4. endTrip
    a. this will end the timer and mark the end point/ verify that the user has actually arrived at their destination.
5. viewTrips
    a. this will allow the user to have a log of past drives and give them the ability to prove their personal records.
6. viewLeaderboard
    a. this will give the user the ability to compare themselves to others that use the app and their personal records for similar distances/drives.

# Iteration-Plan 2
Step by step plan:
Caveat: we will attempting to take into account the actual route distance that the user takes rather than the straight line
distance between point a and point b.

1. endTrip
    a. this will end the timer and mark the end point/ verify that the user has actually arrived at their destination.
2. startTrip
   a. this will begin the timer and marking the starting point/verifying the location. 
3. viewTrips
   a. this will allow the user to have a log of past drives and give them the ability to prove their personal records.


# Iteration-Plan 3
Step by step plan:
Caveat: We will attempting to take into account the actual route distance that the user takes rather than the straight line
distance between point a and point b in Android Studio and implement the Google Maps API for location services and avg speed instead of just
having latitude and longitude. Also we will have to implement the authentication for the user to log in and have a password. On top of that
we will create a frgament for this in android studio to make it look nice. We will also have to implement a World LeaderBoard because right now
due to authentication and not having a username or password to identify people we can.

1. User
   a. we will implement the username to work and password in this class
2. Trips
   a. we will implement the API in this class to maintain speed and time plus location start and finish.
3. LeaderBoard
   a. we will add a world leaderboard in this class and create a new fragment for a leaderboard or add on to the personal one. Not sure yet.