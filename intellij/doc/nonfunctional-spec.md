
# Non-Functional Spec

FURPS stands for:
1. Usability: 
- Text should be easily visible from the phone so that the user does not need to look at their phone for too long
- Colors associated with common forms of color blindness should be avoided.
- Error messages should have an associated with a red pop up rather than a sound in order to not distract the driver too much from the road.
2. Reliability/Recoverability: the worst case the app will enter an error message and use the to latitudes and longitudes and the start and end time(the end time being when the app properly reloads.)
3. Performance: The app will not be able to run as efficiently without cellular connection to the internet/data. Furthermore, the app will
   not be able to take into account other outside factors such as traffic due to accidents/construction/ road closures etc.
4. Supportability: Internationalization of displayed text (text, units, number and date formatting).
5.  Implementation: software must run on android devices and run using Java code.
6. External Interfaces: must connect to google location api services, and weather services.