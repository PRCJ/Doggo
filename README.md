# Doggo
Description
------------------------------------------------------------------------------
This is mobile application which actual users will interact with.
The application is developed using Modern tools/libraries with UI implementations with Navigation architecture.
It connects with the Dog API to retrieve data. more detail of api can be found here - https://dog.ceo/dog-api/documentation/random 
This project a basic example of Retrofit and mvvm

Demo
-----------------------------------------------------------------------------------
Splash              |  First Page                 | Next Page    
:-------------------------:|:-------------------------:|:---------------------------:
![ss3](https://user-images.githubusercontent.com/40074224/138214963-a9219562-8c52-429d-82d7-d8fcbfe46063.jpg)  |  ![ss2](https://user-images.githubusercontent.com/40074224/138214972-ff8dd696-ed2b-42b0-b642-057a9cf16cfa.jpg) |![ss1](https://user-images.githubusercontent.com/40074224/138214970-0cd81bec-cb2f-4f6b-9b77-73ed0c0165ea.jpg)

Apk
------------------------------------------------------------------------------
https://drive.google.com/file/d/1oQW2ebMrGKeTv9p0ximWnVLRMyXcDcIR/view?usp=sharing


Project Structure
--------------------------------------------------------------------------------
<img width="185" alt="ps" src="https://user-images.githubusercontent.com/40074224/138231394-f2413375-ba8e-4d28-8619-1e1d01eea90a.PNG">
Libraries 
-----------------------------------------------------------------------------------
Retrofit-
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation "com.google.code.gson:gson:2.8.6"
implementation "com.squareup.okhttp3:logging-interceptor:4.9.1"

Mvvm-
implementation "android.arch.lifecycle:extensions:1.1.1"
implementation "android.arch.lifecycle:viewmodel:1.1.1"
kapt 'androidx.lifecycle:lifecycle-compiler:2.3.1'

Piccaso-
implementation 'com.squareup.picasso:picasso:2.71828'

CircularImageview-
implementation 'com.mikhaellopez:circularimageview:4.3.0'

Built with
---------------------------------------------------------------------------------

Kotlin - First class and official programming language for Android development.

Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.
    LiveData - Data objects that notify views when the underlying database changes.
    ViewModel - Stores UI-related data that isn’t destroyed on UI changes.
    ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.

Retrofit - A type-safe HTTP client for Android and Java.

Picasso- A powerful image downloading and caching library for Android


