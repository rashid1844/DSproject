 <img src="https://github.com/rashid1844/DSproject/blob/master/pics/logo.png" alt="drawing" width="300"/>

<pre>



</pre>



### Description
An application that allows a shopper to get a live review for a desired product. This extends the capabilities of online shopping and provides a better and more realistic experience. Classification is done based item category, as each user might be an expert in a specific category.

Credit to Dr.Majid Khonji for providing the idea of application, and the support during the project.






### To create an account:
1. go to sign-up page
2. enter a valid phone number and password
3. An SMS code we be sent, go to the verifying page and type it in.


<img src="https://github.com/rashid1844/DSproject/blob/master/pics/signup.png" alt="drawing" width="200"/> <img src="https://github.com/rashid1844/DSproject/blob/master/pics/activate.png" alt="drawing" width="200"/>






### Main pages:
1. Items: shows list of items which can be used to send a review request.
2. Request: list of other peoples requests sorted into categories. 
   * user requests are not shown here.
   * any request that gets replied is removed
3. Reply: list of replies to requests sent by user.
4. Settings: user's balance, and list of user's requests



### To send a review request:
##### three ways possible:
1. go to items page, and click on the desired item, and add a description
2. go to items page, select custom item, add item name, and description
3. go to items page, select URL item, add URL of desired item.
   - works on amazon, ali-express, noon

<img src="https://github.com/rashid1844/DSproject/blob/master/pics/items.png" alt="drawing" width="200"/>


### To replay to a request:
* go to request page, select the category, click on the item to view the description, and click for longer time to send a reply.
<img src="https://github.com/rashid1844/DSproject/blob/master/pics/request.png" alt="drawing" width="200"/>



### To View a response:
* go to replies, click for longer time to see the response, after viewing click on the item again to rate it.
<img src="https://github.com/rashid1844/DSproject/blob/master/pics/replies.png" alt="drawing" width="200"/>



### Balance:
* Each user has a balance of 100 by default, and after rating a video, the amount is moved from your account to reviewer account, with 5% deducted.
* Amount depends on the rating + view time.
<img src="https://github.com/rashid1844/DSproject/blob/master/pics/setting.png" alt="drawing" width="200"/>


### Security features:
* SSL encrypted communication using public certificate signed by Let's Encrypt Authority X3.
* Passwords are stored after being hashed using SHA-1.
* Database credentials are not stored in the server side directly.
* SQL injection protection: by validation every input in the server side.
* Two way authentication: by using a preshared key for the server to validate the application.

 
![ssl cert](https://github.com/rashid1844/DSproject/blob/master/pics/ssl.jpg)


### Software architecture:
* the Application is developed in java.
* Server side has php code with MySQL database.
* REST API is used for communication.


### Streaming:
* uses RTMP "Real Time Messaging Protocol"
* RTMP is an application layer protocol.
* It uses TCP protocol, port 1935.
* API provided by MUX.com
* provides video and audio from replier to requester only.
* On client side, it uses m3u8 format videos over HTTPS.
<img src="https://github.com/rashid1844/DSproject/blob/master/pics/stream.png" alt="drawing" width="700"/>




### Automation part:
* URL parsing: allows the user to directly copy the URL instead of writing the item name.
* Category classification: classifies the items into categories, with the help of data that has almost twenty thousand items.
<img src="https://github.com/rashid1844/DSproject/blob/master/pics/url.png" alt="drawing" width="200"/>


