# marvel-app-kotlin

Android app that consists of a master-detail structure to load and show Marvel characters. 

The application is written entirely in Kotlin following a Clean Architecture based on the MVVM pattern. The project consists of 3 main modules: presentation, domain and data which define the different layers of the architecture.

The application does network HTTP requests via Retrofit, OkHttp and GSON. Loaded data is saved to SQL based database Room, which supports offline mode.

Kotlin Coroutines manage background threads with simplified code and reducing needs for callbacks.

Koin is used for dependency injection.

Glide is used for image loading.
