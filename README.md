##Backend--Water My Plants

#Usage
Clone the repository, open in IntelliJ (Hopefully opens faster for you than it does for us), and  take a look!

**API Documentation**
(https://nchampag-watermyplants.herokuapp.com/v2/api-docs) [Swagger Documentation]

**Authors**

#Nisa Champagne
- [ ] (https://github.com/nisaChampagne) [Github]
#Blaine Blonquist
- [ ] (https://github.com/bquizza5) [Github]

**Project MVP**
- [ ] Login/Signup Pages: user can login into an existing account with Username and Password and a Phone Number or a user can sign up for an account with a username and password and phone number.

- [ ] Home Page: on login a user is sent to a list view page where they can see a list of plants

- [ ] Plant Page: User can create a watering schedule for a plant by adding a plant type/name + species name, watering schedule.

- [ ] Delete Plant Schedule Page: user can delete a plant from their watering schedule.

- [ ] Edit User Profile: User can change their username and phone number

**End Points**
- [ ]


**Created Objects Examples**
New plants should look like this:
{
        "species": "Lucky Bamboo1",
        "name": "Bambi",
        "location": "Kitchen",
        "schedule": 4,
        "user": {
           "userid": 4
               }                    
}
New users should look like this:
{
    "username": "username5",
    "password": "password!",
    "phonenumber": "12343214321"
}
