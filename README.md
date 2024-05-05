# Cookease

Cookease is an Android application designed to help users discover and explore various recipes. Leveraging the Spoonacular API, Cookit provides users with access to a vast database of recipes, allowing them to search for recipes.

## Features

- **Recipe Search**: Users can search for recipes using keywords, ingredients.
- **Recipe Details**: Detailed information about each recipe, including ingredients, instructions and nutritional information.
- **Save Favorites**: Users can save their favorite recipes for quick access later.
- **Offline Access**: Save recipes for offline access, ensuring users can access their favorite recipes even without an internet connection.
- **Dark Mode Support**: Cookit supports both light and dark themes for optimal user experience.
- **MVVM Architecture**: Cookit is built using the Model-View-ViewModel (MVVM) architecture pattern for a clean and maintainable codebase.
- **Room Database**: Utilizes Room Persistence Library to cache and manage recipe data locally on the device.
- **Coroutine**: Asynchronous programming using Kotlin coroutines for efficient and responsive app performance.
- **Retrofit**: Networking library for making API requests to Spoonacular API.
- **Coil**: Image loading library for efficient image loading and caching.

## Getting Started

To get started with Cookit, follow these steps:

1. Clone the repository from GitHub: `git clone https://github.com/realfredricko/Cookit.git`
2. Open the project in Android Studio.
3. Obtain an API key from Spoonacular API (https://spoonacular.com/food-api) and replace the placeholder in the code with your API key.
4. Build and run the application on an Android device or emulator.

## Dependencies

Cookit relies on the following dependencies:

- Retrofit: A type-safe HTTP client for Android and Java.
- Room: Persistence library for storing recipe data locally.
- Coil: Image loading library for displaying recipe images.
- Coroutine: Library for asynchronous programming in Kotlin.
- ViewModel and LiveData: Architecture components for managing UI-related data.
- Material Components: UI components for Android apps following the Material Design guidelines.

## Contributing

Contributions to Cookit are welcome! If you'd like to contribute, please fork the repository, make your changes, and submit a pull request.
