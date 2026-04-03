# Android Activity Lifecycle — Pedagogical Example

A minimal Android application designed to **observe the Activity Lifecycle in
real time** by triggering `Toast` notifications and `Snackbar` messages on each
callback, and logging invisible states to **Logcat**.

---

## 🎯 Learning Objectives

By studying and running this example, students will be able to:

- Identify the lifecycle callback methods overridden in this Activity
- Understand in which order these callbacks are invoked under different user
  scenarios
- Distinguish between UI-visible feedback (`Toast`, `Snackbar`) and background
  logging (`Log.i()`)
- Explain why certain callbacks use `Toast` while others use `Log`

---

## 📖 The Android Activity Lifecycle — Overview
```
         ┌─────────────┐
         │   Launched  │
         └──────┬──────┘
                │
          onCreate()     →  Toast + Snackbar
                │
          onStart()      →  Toast
                │
          onResume()     →  Toast
                │
         ┌──────┴──────┐
         │   Running   │  ← App is active and in the foreground
         └──────┬──────┘
                │
          onPause()      →  Toast
                │
          onStop()       →  Log.i()
                │
          onDestroy()    →  Log.i()
```

| Callback | Notification method | Reason |
|----------|-------------------|--------|
| `onCreate()` | `Toast` + `Snackbar` | Activity is visible; UI is available |
| `onStart()` | `Toast` | Activity is becoming visible |
| `onResume()` | `Toast` | Activity enters foreground |
| `onPause()` | `Toast` (SHORT) | Activity still partially visible |
| `onStop()` | `Log.i()` | Activity is hidden; no UI available for Toast |
| `onDestroy()` | `Log.i()` | Activity is gone; no UI available for Toast |

> **Key insight for students:** `Toast` requires an active UI context to display
> properly. Using `Toast` in `onStop()` or `onDestroy()` is unreliable — the
> system may discard it. `Log` is the correct tool for tracing invisible states.

---

## 🧩 Notable Implementation Details

### `onCreate()` — Two notification methods
```java
// Toast: brief overlay message
Toast.makeText(getApplicationContext(),
        "Welcome to OnCreate Method",
        Toast.LENGTH_LONG).show();

// Snackbar: anchored to the bottom of the screen, more Material-compliant
Snackbar s = Snackbar.make(
        findViewById(android.R.id.content),
        "Welcome to OnCreate Method !",
        Snackbar.LENGTH_LONG);
s.show();
```

`onCreate()` is the only callback that uses both a `Toast` and a `Snackbar`,
illustrating the difference between the two feedback mechanisms side by side.

| Feature | `Toast` | `Snackbar` |
|---------|---------|-----------|
| Anchor | Floating, no anchor needed | Anchored to a `View` |
| Action button | ❌ Not supported | ✅ Supported |
| Material Design | Legacy | Recommended |
| Dismissal | Auto only | Auto + swipe |

### `onStop()` and `onDestroy()` — Logcat only
```java
@Override
protected void onStop() {
    super.onStop();
    Log.i("onStopTag", "welcome to onStop Method !");
}

@Override
protected void onDestroy() {
    Log.i("onDestroyTag", "Welcome to onDestroy Method !");
    super.onDestroy();
}
```

To observe these callbacks, open the **Logcat** panel in Android Studio and
filter by the tags `onStopTag` and `onDestroyTag`.

---

## 🔬 Observation Scenarios

Run the application and perform each action below. Observe which `Toast`
messages appear on screen and which log entries appear in Logcat.

| Scenario | Callbacks triggered |
|----------|-------------------|
| Launch the app | `onCreate` → `onStart` → `onResume` |
| Press the **Home** button | `onPause` → `onStop` |
| Return to the app | `onStart` → `onResume` |
| Press the **Back** button | `onPause` → `onStop` → `onDestroy` |
| Rotate the device | `onPause` → `onStop` → `onDestroy` → `onCreate` → `onStart` → `onResume` |

---

## 📡 Reading the Logcat Output

1. Run the app on a device or emulator.
2. Open **Logcat** in Android Studio (`View → Tool Windows → Logcat`).
3. Filter by tag to isolate relevant entries:
   - `onStopTag` → triggered when the activity becomes fully hidden
   - `onDestroyTag` → triggered when the activity is being destroyed

---

## Point for Discussion

**1. `Toast` in `onPause()` may not display**

`onPause()` can be followed very quickly by `onStop()`, at which point the
window is detached and the `Toast` may be discarded by the system before it
renders. This is acceptable in a pedagogical context but should be noted.


---

## 🔧 Requirements

- Android Studio (latest stable release recommended)
- Minimum SDK: API 21 (Android 5.0 Lollipop)
- Material Components dependency (for `Snackbar`):
```gradle
  implementation 'com.google.android.material:material:<latest_version>'
```
- Language: Java

---

## 📚 Official References

- [Activity Lifecycle — Android Developers](https://developer.android.com/guide/components/activities/activity-lifecycle)
- [Toast — API Reference](https://developer.android.com/reference/android/widget/Toast)
- [Snackbar — Material Design](https://material.io/components/snackbars)
- [Log — API Reference](https://developer.android.com/reference/android/util/Log)

---

## 👩‍🏫 Author

Android Development Course — Pedagogical Example.
