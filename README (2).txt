localshop by Muhi Eddin Tahhan and Joy Korji

we built an app that resembles an online market where the user can put products for sale.
the app will start in the log in activity where you can log in if you already have an account. you can also creat one if you don't have one.
if you choose to register, you will have to give your first name, last name, and email and password, confirm the password and agree on
the rules and regulation. The app will not allow you to continue if you don't fill all the fields. after regestring, your account will be 
set in the data base and you will be able to log in. After loging in, you will have to create your profile by putting a picture and a phone
number and identify if you are a male or female. all these info, are editible later on if you ever want to change any thing in your profile.
After logging in, you will go to the dashboard where you can see all the products from other users. you can add products to your cart and 
you will see that it will give you the total price if you choose to go to cart and to check out. you can also create your own product
to be displayed in the dashboard for sell. obveiusly you won't be able to buy because you are the one who put this product for sale. you
can put an image, product title, discription and price. once you set everything the program will upload the product to data base and show
it on the dashboard to all the users    

we used Navigation Button activity as the main activity to go to after signing in or registering. we used that type of
activity because it was easier to do that than creating 3 separate activities with 3 buttons and adding an intenet for each one.
with the navigation button activity we were using the classes that are called fragments to display our content. what's also special
about the program is that we put all the strings and the dimensions, dimensions, and colors in xml files in values folder so that it won't
be hard coded and so it can pass across different platforms. we also added some fetures into classes and used it in the xml so it would 
be easier and reduce the time to design the app and focus more time on the logic. we put all classes of that type into utilities package.
we also added constant into an object class to make it easier to call them and use them. we used one class "the BaseActivity & BaseFragment"
to put all the main functions that could be used constantly like showing and hiding the dialog bar, and made it open class so the other
classes could inharate these functions. we used firestore database for the storing the data and for authentication and registration 
perposes. we also used a third party plugin called glidload for controling photos and how we show it on the app. we tried to keep classes
as orgnized as possible by putting them in different packages based on what they do in the aplication. for showing the products, we used
recyclerview and put all the adapters in one package. 

during the time we were making the app, we faced a lot of difficulties:
1- xml and layout files: during building the application and while the application was growing and getting more complex, it started to get
harder and harder to try to implement the style classes in the layout files. the app would crash so many times that we decided to give up
on using these classes in some spots and code everything from scratch on the xml instead of using these classes. Also we tried to use 
google material which would cause the program to crash sometimes.

2- functions: some of the functions were sensitive to repetitions like show and hide process dialog function and show the action bar
function. Just stopping them or comment them out in some spots, would make the program works. so we did not bother figuering out why it 
did not work, we just moved on. Luckly we had backups to get back to the last point where the app was working to try to follow where 
everything went wrong.

3- firestore: there has been a loooooooooot of problems with the connection to the firestore out of no where. we would lose connection
and clearly that would stop the login, registery, and uploading data to the firestore. the code is perfectly fine, but the connection or
the emulator sometiems would ruin everything and we have wasted so many hours and gave up so many fetures just trying to fix these problems
. we would either refresh the firestore page, or wipe the data from the emulator to make it work.

4- time: we had a tight schedual with the exams and other projects and so we did not have a lot of time to focus 100% on our project. 
we managed to add so many cool fetures but it was sad to decided to give up some of them along the way for the sake of having and app
that is 100% functional.

at the end we hope that you liked the app and have fun when browsing it ;) 

Merry Vhristmas and a happy new year :)  

Thank you, 

Muhi Eddin Tahhan & Joy Korji   
  
 