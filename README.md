News App

Locate articles and breaking news headlines from news sources and blogs across the web
APK File
https://drive.google.com/file/d/1RHWSfi77XmiU79mdHf9gT3i1ICN_0jDx/view?usp=sharing

Features

- Navigation Component
- Datastore
- Room Database
- Retrofit
- Hilt Dependency Injection
- MVVM Architecture

app description

- Onboarding
- Home (recycler view of articles)

In the beginning check if it's the first time user opens the app, navigate on boarding screens, if
everything is set navigate to home screen.

since we're on free subscription, and it update articles every 1 hour and we've a limit of requests.

Architecture

The application is built with scalability in mind and care for having multiple developers working on
it. I used the Clean architectural principles to build the application. I choose this architecture
because it fosters better separation of concerns and testability.

The Application is split into a three layer architecture:

Data
Domain
Presentation
Data

The data layer handles the business logic and provides data from the Level Shoes API. This layer
uses the Repository pattern to fetch data from the data source which in this case is the Level Shoes
API.

Domain

The domain layer contains the application specifics logic. It contains interactors/use cases that
expose the actions that can be performed in the application.

Presentation

I used the MVVM pattern for the presentation layer. The Model essentially exposes the various states
the view can be in. The ViewModel handles the UI logic and provides data via Android architectural
component LiveData to the view. The ViewModel talks to the domain layer with the individual use
cases.

Libraries

Libraries used in the application are:

Jetpack
-Viewmodel - Manage UI related data in a lifecycle conscious way and act as a channel between use
cases and UI.

-Data Binding - support library that allows binding of UI components in layouts to data sources.

-Flow - Provides an observable data holder class.

-Offline cash using HTTP

Retrofit - type safe http client and supports coroutines out of the box.

okhttp-logging-interceptor - logs HTTP request and response data.

kotlinx.coroutines - Library Support for coroutines. I used this for asynchronous programming in
order to obtain data from the network.

Hilt - Dependency injection plays a central role in the architectural pattern used.

Organisation

I decided to organize my code based on features. Since many developers are expected to work on the
project, developers can easily spot the folder to work on based on feature. This can also
potentially reduce merge conflicts. It also makes it easy for new developers to come on board and if
we want, we can easily have developers dedicated to different features of the application.
