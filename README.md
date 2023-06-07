# Reddit Top Posts

This repository contains the implementation of an Android app that displays the top posts from Reddit's /top section.

## Features

- **Top Posts**: The app fetches the top posts from the https://reddit.com/top section and displays them in a list format.
- **Paging**: The app supports pagination, allowing users to scroll through the list of posts and load more as they reach the end.
- **Image Downloading**: If a post contains an image, the app provides an option to download the image to the device's storage.

## Technologies Used

The following technologies and libraries were used in the implementation of this app:

- **Android SDK & Kotlin**: The app is developed using the Android Software Development Kit, targeting a minimum SDK version of Android 7.0 (Nougat).
- **Retrofit**: Retrofit is used for network communication, making it easy to retrieve data from the Reddit API.
- **Glide**: Glide is used for efficient image loading and caching, ensuring a smooth and responsive experience when displaying post images.
- **RecyclerView & Paging3 library**: The app utilizes RecyclerView to efficiently display the list of posts, providing smooth scrolling and optimized memory usage.
- **Android Architecture Components**: This app follows the recommended Android architecture principles, including ViewModel, LiveData, Flow.


![1 (1)](https://github.com/khodosyaroslav/RedditTopPosts/assets/90519548/3a4a4837-bdbe-4260-957e-5fd3cfa44a34)
