# Human Maintenance

A personal, offline-first Android application for organizing everyday life in one place.

The goal is to keep common life management tasks simple, fast, and local. No accounts, cloud sync, subscriptions, or unnecessary complexity.

## Current Features

###  Calendar

A daily planner for appointments, events, reminders, and other scheduled items.

* Browse any day using previous/next navigation
* Each day stores its own data independently
* Create, edit, and view calendar items
* Multiple categories, priorities, and visual color indicators

###  To-Do

A daily task manager built around prioritization.

* Browse tasks by day
* Create, edit, and view tasks
* Daily task lists
* Priority and effort indicators
* Designed for quick visual scanning

###  Finance

A lightweight finance tracker focused on recurring personal finances.

* Create, edit, and view finance items
* Track income and expenses
* Store recurring financial commitments
* Local data only

## Planned Features

The finance module will expand into a personal cash-flow dashboard with:

* Monthly income overview
* Monthly expenses
* Savings tracking
* Available money calculation
* Sustainability indicators
* Spending analytics
* Financial insights
* Purchase affordability checks

The To-Do and Calendar modules will continue to gain quality-of-life improvements such as smarter sorting, filtering, and additional productivity features.

## Design Goals

* Offline-first
* Local storage using Room
* Fast and lightweight
* Clean Material Design interface
* Dark theme focused
* Simple navigation
* No advertisements
* No accounts
* No cloud services
* No telemetry or analytics

## Building a Release APK

From the project root:

**Linux**
```bash
./gradlew assembleRelease
```

**Windows**
```cmd
gradlew.bat assembleRelease
```

Output:
```text
app/build/outputs/apk/release/app-release.apk
```

## Installing or Updating on a Device

Enable USB debugging and connect the device.
```bash
adb install -r app/build/outputs/apk/release/app-release.apk
```
The `-r` option replaces the existing installation while preserving app data.

## Technology

* Kotlin
* Jetpack Compose
* Room Database
* Material 3

## Project Status

This project is actively developed for personal use while exploring Android development and modern app architecture.

Current implementation focuses on the core experience (create, view, update, delete) across all modules before expanding into analytics, automation, and additional productivity features.

## Development Note

If developing on multiple machines, use the same Android signing key on all of them.

Using different signing keys causes Android to treat builds as different applications, requiring the existing app to be uninstalled before installing a new build, which removes all locally stored data.

A simple approach is to keep the following files outside version control and copy them to each development machine:

```text
keys/shared.keystore
keystore.properties
```

Add both to `.gitignore` to avoid committing them to the repository.

