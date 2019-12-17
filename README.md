<h1> Mobile Computing Project(Android) -  <br>
    Global Mart E-commerce Grocery Shopping Application</h1>

<h1> Demo </h1>
<p>
The .apk file is attached to the repository. Sample images are added here for reference. </p>

<p>
<a href="https://ibb.co/y62X5XG"><img src="https://i.ibb.co/pfqxZxG/Whats-App-Image-2019-07-24-at-17-38-47.jpg" height="300" width="150" alt="Whats-App-Image-2019-07-24-at-17-38-47" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/5cS7Rj9/1.png" height="300" width="150" alt="1" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/RCRjwTF/2.png" height="300" width="150" alt="2" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/CPz6M0z/3.png" height="300" width="150" alt="3" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/0JdXRS6/4.png" height="300" width="150" alt="4" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/hHC6vdT/5.png" height="300" width="150" alt="5" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/K2jTFc6/6.png" height="300" width="150" alt="6" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/yVrmGtm/7.png" height="300" width="150" alt="7" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/h9Kb58X/8.png" height="300" width="150" alt="8" border="0"></a>&nbsp;
<a href="https://imgbb.com/"><img src="https://i.ibb.co/D73cYNM/9.png" height="300" width="150" alt="9" border="0"></a>&nbsp;
</p>

<h1> Description </h1>
<p>
“Global-Mart” is an E-Commerce mobile application which works on GPS-enabled and internet-connected devices. The main objective of our application is to help the users to search and order the international food items and groceries which are available across the current or preferred location. Users can add all the required items to the cart and make a payment to place the order successfully. Our application allows the users to track the delivery status of the orders and to view the order history.</p>
<p>
Global-Mart stands different among all the grocery shopping applications which are available in the play store because, it makes easy for the international people to search and purchase the desired regional food items among the limited international stores available in a foreign place.</p>

<h1> Requirements </h1>
<ul>
    <li>
        Android Studio - This application is developed in Android Studio, Android 4.0 </li>
    <li>
        Java - Java is used to develop this application. Get the latest version. </li>
    <li>
        GPS access – The device must have GPS access to use this application. </li>
    <li>
Camera- Camera of the device helps in scanning the barcode of any product to search across the stores. </li>
    <li>
Git – Git is used as version control system and for collaborating among the team. INSTALLATION NOTES </li>
</ul>

<h1> Installation Notes </h1>
<p>
When the user clicks the scanner icon in the Search screen, our application requests the user to download the “Barcode Scanner” application which is of size around 600kb. A prompt box appears on the screen for the user to provide access to download the “Barcode Scanner” application. Once the user provides the access to download the application, it redirects the user to play store of the device where the application can be downloaded. Once the “Barcode Scanner” is installed, Global Mart uses this application automatically every time the user clicks on the scanner icon in Search screen. Once the product is identified with the help of barcode, application redirects to the product details screen with relevant information about the product. </p>
   
<h1> Libraries </h1>
<p>
1) google-gson: <br>
Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. <br>
Source: https://github.com/google/gson <br>
2) Zxing: <br>
ZXing ("zebra-crossing") is an open-source, multi-format 1D/2D barcode image processing library implemented in Java, with ports to other languages. The android-integration module in this support’s integration with Barcode Scanner via Intent. </br>
Source:https://github.com/zxing/zxing/tree/master/android-integration/src/main/java/com/google/zxing/integration/android </br>
3) Google maps API: <br>
This library allows the user to access to the functionalities provided by Google Maps to get the users' location and to let the user pick the location manually. </br>
Source: https://developers.google.com/maps/documentation/android-sdk/get-api-key </br>
    </p>

<h1> Features </h1>
<ul>
    <li>
Location - 
Location feature picks the current location automatically or allows the users to choose any location manually. The chosen location helps the users in finding the stores and groceries in that region. Users can change their location at any point of the application by navigating to Home screen.
    </li>
    <li>
Search bar - 
This feature will allow the users to search any product in all the stores available in the chosen location by entering any of the key words from products name.
    </li>
    <li>
Barcode scanner - 
Barcode scanner feature is helpful to the users when the users want to search and re-order any product which is already delivered by using the barcode of the existing product. On scanning the barcode, the application prompts the user to download an application called “Barcode scanner” on downloading which, our application searches for the corresponding product information in the database and it will display the product information.
    </li>
    <li>
Shop By category - 
Shop by category is a feature which is useful when the user wants to search any product by the category into which the product falls irrespective of the store and origin of the product. This feature shows all the products of specific category selected.
    </li>
    <li>
Browse by Stores - 
This feature allows the users to search for any product based on type of stores like Indian stores, Chinese stores, Italian stores, etc., when a user wants to find a specific regional product, this feature helps in finding all such products from all the stores belonging to that region.
    </li>
    <li>
Contacting the stores - 
When a user has any specific query regarding any product in any of the stores, this feature helps in finding the contact information of the stores and call them directly from the application.
    </li>
    <li>
Recommendations - 
This feature will allow the users to provide any kind of recommendations or suggestions regarding any kind of products. Application stores this information from users in centralized database which will be redirected to specific store in backend.
    </li>
    <li>
Purchase History - 
Purchase history feature is an important functionality in which the user can see the history of orders that were placed in the past. Users can cancel the existing order which is not delivered at this point and user can also track the order to see the status of the order by clicking on track order button in purchase history screen.
    </li>
    <li>
My Cart - 
My Cart functionality allows the user to review all the products that were selected before placing the order. User can increase or decrease the quantity of products at this point. Total price including taxes is displayed at the bottom of the screen along with a Proceed to Payment button.
    </li>
    </ul>
    
<h1> Future work </h1>
<p>
Because of the time constraints and the limited project scope, we could not enhance the application with few more important and interesting features. Therefore, considering the real time scenarios, below are the few features which can be implemented as part of future work. </p>
<ul> 
    <li>
Since the current application does not contain Login functionality, session is not being maintained currently. For future enhancements, Login, Logout and Sign Up functionalities help to maintain the session by which cart, purchase history can be specific to each user. Also, for the purpose of the project, we have not implemented actual payment gateway to place the order. </li>
    <li>
        Delivery address details - Payment gateway </li>
    <li>
        Displaying deals and discount details from various stores </li>
    <li>
        Tracking the order status using Maps </li>
    <li>
        Return and refund services of the products </li>
    </ul>
