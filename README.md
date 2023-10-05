# Jetpack Compose Dark Mode Animation

This project showcases a seamless and visually appealing dark mode animation implementation for Android apps built with Jetpack Compose. Simplifying the transition between dark and light modes, it adds a touch of elegance to your app's user interface.

## Want a Detailed Guide?
[Read the Full Article](https://medium.com/@m.derakhshan/animating-dark-to-light-a-jetpack-compose-guide-for-theme-switching-on-android-e47c053cc9f4)  <!-- Replace with your Medium article link once published -->

## Features

- Smoothly transition between dark and light modes with captivating animations.
- Capture UI snapshots for a flicker-free and visually enchanting experience.
- Custom shapes and removal animation direction for added finesse.

## Usage

To integrate the Jetpack Compose Dark Mode Animation into your project, follow these steps:

1. Clone or download the project repository.

2. Include the necessary classes (`ScreenshotState`, `ViewExtensions`, `ScreenshotScope`, `RemovableDiagonalRectShape`, and `ShapeDirection`) into your Jetpack Compose project.

3. Use the provided classes in your UI components to achieve a seamless dark mode animation.

Here's a quick example:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val screenShotState = rememberScreenshotState()
            val isDarkMode = remember { mutableStateOf(false) }
            val offset = remember { mutableFloatStateOf(0f) }
            val scope = rememberCoroutineScope()
            val screenWidth = LocalConfiguration.current.screenWidthDp
            val screenWishPx = with(LocalDensity.current) { screenWidth.dp.toPx() }
            val animationOffset =
                animateFloatAsState(
                    targetValue = offset.floatValue,
                    label = "animation offset",
                    finishedListener = {
                        offset.floatValue = 0f
                        screenShotState.setBitmap(null)
                    },
                    animationSpec = tween(1200)
                )


            DarkModeTheme(darkTheme = isDarkMode.value) {
                Box(modifier = Modifier.fillMaxSize()) {
                    ScreenshotScope(
                        screenshotState = screenShotState,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background)
                    ) {
                        Content(isDarkMode = isDarkMode.value, onChangeDarkMode = {
                            scope.launch {
                                screenShotState.capture()
                                offset.floatValue = screenWishPx
                                delay(100)
                                isDarkMode.value = !isDarkMode.value
                            }
                        })
                    }
                    screenShotState.bitmap.value?.asImageBitmap()?.let {
                        Image(
                            bitmap = it,
                            contentDescription = "screen shot",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(
                                    shape = RemovableDiagonalRectShape(
                                        offset = animationOffset.value,
                                        direction = if (isDarkMode.value) ShapeDirection.ToLeft else ShapeDirection.ToRight
                                    )
                                )
                        )
                    }
                }
            }
        }
    }
}
```

## License

This project is open-source and available under the MIT License. Feel free to adapt and use it in your projects. See the [LICENSE](LICENSE) file for details.

## Questions and Contributions

If you have questions or want to contribute, feel free to reach out. Your feedback and contributions are valuable.

Happy coding!
