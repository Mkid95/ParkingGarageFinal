Parking Garage App by Matthew Espinoza

This App includes two activities, a Main activity used to log in and pass
the account data into the second activity, the parking garage activity

depending on whether the logged in account that has passed an admin or not
it well either do the following
-if the account is just a user they will see their car and other 
information such as their cost for the day. they can then hit print reciept
to remove their car where they would recieve their stub and leave the parking lot

-if the user is an admin then a different layout is presented. This layout is a 
scroll view of all the cars that are currently parked. Any vehicle selected will
bring up the ticket of said vehicle with the option to remove the car. There is 
also empty slots that can be selected where a new account and car can be added 
to the selected parking spot.

All Data in this app is randomly generated using the Data class
-all vehicles are given a random value of type and are given a random time range
between 6am to 5pm
Names passwords, license plates are generated on the spot
-all time comparisons are done with a variable called simulated time so as to
avoid problems with real time comparison since all origin dates of vehicles, have
a set time span range.

The only Account that is not randomly generated is user Mkid with password 1453
and can be used to see the administrator capabilities.
