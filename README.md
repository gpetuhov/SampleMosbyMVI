# SampleMosbyMVI
Simple example of clean architecture Android app with MVI using Mosby (with State Reducer).

User can trigger 2 actions (intent): load text 1 and load text 2. Each will show its own progress bar and then "load" text from the repository inside its TextView. Each action emits its own stream of partial changes, which are fed into State Reducer. State Reducer generates new ViewState based on previous ViewState and the received partial change. The result ViewState is rendered into UI.

## Requirements
* Android Studio 3.5 Canary 6
* Kotlin 1.3.21
* Android Gradle Plugin 3.5.0-alpha06
* Gradle wrapper 5.2.1
* AAPT 2

## References
http://hannesdorfmann.com/android/mosby3-mvi-3