# HetnalUSB

A standard Android application project built with Kotlin and Gradle.

## Project Overview

- **Name:** HetnalUSB
- **Primary Language:** Kotlin (v2.0.21)
- **Build System:** Gradle (v8.13.2) with Kotlin DSL (`.gradle.kts`)
- **Min SDK:** 33
- **Target SDK:** 36
- **Architecture:** Standard Android Application structure with a single `app` module.
- **Features:**
  - USB Device Listing: Lists all connected USB devices with their Vendor ID (VID) and Product ID (PID) in hexadecimal format.

## Building and Running

### Prerequisites
- Android Studio or IntelliJ IDEA with Android plugin.
- JDK 11 (as specified in `app/build.gradle.kts`).

### Key Commands
Use the Gradle wrapper (`./gradlew` on Unix-like systems, `gradlew.bat` on Windows) for all build tasks.

- **Assemble Debug APK:**
  ```bash
  ./gradlew assembleDebug
  ```
- **Install Debug APK on connected device/emulator:**
  ```bash
  ./gradlew installDebug
  ```
- **Run Unit Tests:**
  ```bash
  ./gradlew test
  ```
- **Run Instrumented Tests:**
  ```bash
  ./gradlew connectedAndroidTest
  ```
- **Clean Build:**
  ```bash
  ./gradlew clean
  ```
- **Lint Check:**
  ```bash
  ./gradlew lint
  ```

## Development Conventions

- **Language Style:** Follows the "official" Kotlin code style as per `gradle.properties` (`kotlin.code.style=official`).
- **Dependencies:** Managed using Version Catalogs (`gradle/libs.versions.toml`). Always update dependencies in the catalog rather than hardcoding versions in `build.gradle.kts`.
- **AndroidX:** The project uses AndroidX libraries exclusively (`android.useAndroidX=true`).
- **Layouts:** Standard XML-based layouts located in `app/src/main/res/layout/`.
- **Naming Conventions:**
  - Packages: `com.example.hetnalusb`
  - Activities: PascalCase (e.g., `MainActivity`)
  - Layouts: snake_case (e.g., `activity_main.xml`)
  - Resources: snake_case (e.g., `ic_launcher_background.xml`)

## Project Structure
- `app/`: Main application module.
  - `src/main/java/`: Kotlin source code.
  - `src/main/res/`: Android resources (layouts, strings, drawables).
  - `src/test/`: Local unit tests.
  - `src/androidTest/`: Instrumented tests for Android devices.
- `gradle/`: Gradle wrapper and version catalog configuration.
- `build.gradle.kts`: Root-level build configuration.
- `settings.gradle.kts`: Project settings and module inclusion.
