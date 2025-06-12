# Test-Report

A short test report describing how the prototype was tested.
For system tests, this should be a textual description of the inputs provided to the system,
the outputs received, and whether they match expectations (inclusion of a transcript
or screenshots is strongly encouraged).




In this iteration we are trusting the user. We ask the user what their starting and expected ending longitude/latitude are.
We are also trusting the user in this iteration to input their start time and end time(in military time) for the drive.
By doing so, we are able to gather the avg speed from the trip.
We tested this by entering the coordinates for vassar college, being point a, and the coordinates for marist college being, 
point b. The output was approximately 14 mph. This is all we were able to achieve in the first iteration, the Haversine algorithm 
uses a straight line to compute the distance rather than the actualy route taken, essentially the distance from point a to b
may be different than the actual distance traveled by the user and that's something we will need to take into account for 
the next iteration.

Transcript:
/home/acedeno/.jdks/openjdk-21-1/bin/java -javaagent:/opt/idea-IU-242.23339.11/lib/idea_rt.jar=45383:/opt/idea-IU-242.23339.11/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /home/acedeno/Desktop/cmpu203/team-2b/intellij/out/production/intellij Main
Hello! Please input your start Latitude
41.6868
Hello! Please input your start Longitude
73.8957
At what time did you start? Insert in Military Time!(HH:MM)
03:00
Hello! Please input your end Latitude
41.7231
Hello! Please input your end Longitude
73.9345
At what time did you end? Insert in Military Time!(HH:MM)
03:12
Average speed: 13.942678346290698 mph.

Process finished with exit code 0