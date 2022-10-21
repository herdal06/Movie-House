<h1 align="center">Movie House</h1>

<p align="center">  
IMDB-like Application Based on TMDP API
<br/>
<p align="center">For API Documentation: <a href="https://developers.themoviedb.org/3/getting-started/introduction">Click here</a></p>
</p>


## App Gif
<img src="https://github.com/herdal06/Movie-House/blob/master/arts/app.gif?raw=true"  width="25%"/>


#### App Screenshots

| Top of home screen | Bottom of home screen | 
|:-:|:-:|
| <img src="https://github.com/herdal06/Movie-House/blob/master/screenshots/home_1.png?raw=true" alt="drawing" width="300"/> | <img src="https://github.com/herdal06/Movie-House/blob/master/screenshots/home_2.png?raw=true" alt="drawing" width="300"/> |
| Movie Details | Movie Details |
| <img src="https://github.com/herdal06/Movie-House/blob/master/screenshots/movie_details.png?raw=true" alt="drawing" width="300"/> | <img src="https://github.com/herdal06/Movie-House/blob/master/screenshots/movie_details_2.png?raw=true" alt="drawing" width="300"/> |

## Tech stack
* ✅ MVVM with Clean Architecture
* ✅ [Kotlin Flow][31] - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
* ✅ [Coroutines][51] - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
* ✅ [Navigation Component][24] - Handle everything needed for in-app navigation. asynchronous tasks for optimal execution.
* ✅ [Safe-Args][25] - For passing data between destinations
* ✅ [Paging3][85] - The Paging library helps you load and display pages of data from a larger dataset from local storage or over network.
* ✅ [Dagger-Hilt][93] - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
* ✅ [ViewModel][17] - Easily schedule asynchronous tasks for optimal execution.
* ✅ [Retrofit][90]- Retrofit is a REST client for Java/ Kotlin and Android by Square inc under Apache 2.0 license. Its a simple network library that is used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API.
* ✅ [OkHttp][23] - Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
* ✅ [Coil][92] - An image loading library for Android backed by Kotlin Coroutines
* ✅ [View Binding][11] - a feature that allows you to more easily write code that interacts with views.
* ✅ [Lifecycle][22] - As a user navigates through, out of, and back to your app, the Activity instances in your app transition through different states in their lifecycle.
* ✅ [Glide][23] - for image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.


[11]: https://developer.android.com/topic/libraries/view-binding
[92]: https://coil-kt.github.io/coil/
[93]: https://developer.android.com/jetpack/compose/libraries#hilt
[51]: https://developer.android.com/kotlin/coroutines
[90]: https://square.github.io/retrofit/
[31]: https://developer.android.com/kotlin/flow
[22]: https://developer.android.com/guide/components/activities/activity-lifecycle
[17]: https://developer.android.com/jetpack/compose/state#viewmodel-state
[23]: https://square.github.io/okhttp/
[24]: https://developer.android.com/guide/navigation/navigation-getting-started
[25]: https://developer.android.com/guide/navigation/navigation-pass-data
[23]: https://github.com/bumptech/glide
[85]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
