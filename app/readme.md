# 🏅 SportsApp — Android Beginner Learning Journal

> **How to use this README:** Every section has placeholder prompts in `[ ]` brackets.
> Replace them with your own notes as you build and learn. This is your personal write-and-learn document — the more detail you add, the more you'll retain.

---

## 📖 Project Overview

**What does this app do?**

This app displays multiple sports names utilizing the feature of CardView. Upon click, the app displays 
a toast message.

**Why did I build this?**

On a broader perspective, I want to build an end to end functional chat app tweaking in features which are not in current market chat apps.
Hence, the attempt to eventually get there by building smaller apps in Java and Kotlin.

**Current status:**

- [ ] Planning
- [ ] In progress
- [✅] Completed

---

## 🛠️ Tech Stack & Tools

| Tool / Technology | Version             | Why I used it                                                                                                                                                                                                                                                                                                      |
|---|---------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Language | Java                | Although Kotlin is the preferred choice for building Android apps because of it's readability, Null safety features by preventing common runtime crashes by catching null pointer exceptions at complile time, extension functions, lambda expressions and coroutines, I opted Java purely for the learning curve. |
| IDE | Android Studio      | Android Studio Panda 3                                                                                                                                                                                                                                                                                             |
| Build System | Gradle (Kotlin DSL) | Gradle is a build automation tool. Takes raw ingredients - source code, images, libraries, and resources and turn them into a finished app (like an Android APK) or program.                                                                                                                                       |
| Min SDK | API 24              | Minimum Android version an app requires to run. This project covers from Android 7                                                                                                                                                                                                                                 |
| Target SDK | API 36              | Controls how your app behaves when running on newer Android versions                                                                                                                                                                                                                                               |
---

## 📁 Project Structure

```
SportsApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/          ← Where I wrote my code
│   │   │   ├── res/           ← Where I wrote my XML layouts
│   │   │   └── AndroidManifest.xml  ← Where I registered my activities
│   │   └── test/              ← Unit tests that run on JVM.
├── build.gradle.kts            ← Applies config to all modules/ subprojects at root level.
├── settings.gradle.kts         ← Defines project structure, which modules to fetch plugins/ dependencies from.
└── gradle.properties           ← Key-Value config file for tuning gradle's behavior and passing flags to the build.
```

**My own explanation of the folder structure: (but actually prompted from claude)**

app/ - The main application module. Large projects can have multiple modules; app is the
primary one that produces the final APK.

src/ - Contains all source code and resources, split into source sets:
main/ - production code,
test/ - unit tests,
androidTest/ - UI tests

main/ - The core of the app. Everything here goes to the final build.

java/ - All Kotlin/ Java source files, organized by package (e.g. com.example.app). Contains activities,
ViewModels, Repositories, etc. despite being called "java", Kotlin files go here too.

res/ - All non-code resources: layout/ - XML UI layouts, drawable/ - images and icons,
values/ - strings, colors, dimensions, themes, mipmap - app launcher icons

AndroidManifest.xml - App's configuration file. Declares components (Activities, Services, Receivers),
permissions (camera, internet), the app entry point, and metadata. Android reads this to understand
how the app is structured.

test/ - Local JVM unit tests. Fast, no device needed. Tests pure logic like ViewModels
and utility functions

build.gradle.kts - Build config written in Kotlin DSL. Exists at two levels:
project level - global config, plugin versions, App level - dependencies, SDK versions(minSDK, targetSDK),
appID, build types (debug/ release)

settings.gradle.kts - Defines which modules are included in the project. Also configures repository
sources like Google and Maven Central. The entry point Gradle reads first.

gradle.properties - Key-Value config for the Gradle build system itself. Common settings include JVM
heap size, enabling AndroidX, and turning on Kotlin/ Android-specific flags like android.useAndroidX=true

---

## 🧠 Core Android Concepts I Learned

> This is the heart of your learning journal. Fill this in as you go.

### Activities

**What is an Activity?**

An Activity is like one screen of the app. It has a lifecycle — it can be created, paused,
stopped, or destroyed...

Unlike other programming languages, which are launched with main method, the Android system initiates code
in an activity instance by invoking specific callback methods that correspond to specific stages of it's lifecycle

In the mobile-app experience, the user journey often begins non-deterministically and the Activity class is designed to
facilitate this paradigm. When one app invokes another, the calling app invokes an activity in the other app,
rather than the app as an atomic whole. The activity serves as the entry point for an app's interaction with the user.


**Activities in this project:**


| Activity Name | What it does                                                                                       |
|---|----------------------------------------------------------------------------------------------------|
| `MainActivity.java` | Entry point, Sets up the UI, Handles user interactions, manages lifecycle, connects logic to views |

---

### The Activity Lifecycle

An activity lifecycle is essential for transitioning between states. We use a series of callbacks to handle
transitions between states.

onCreate - When the system creates the activity to create views and bind data to lists.

onStart - Activity becomes visible to the user. Final preparations for coming to the foreground and becoming
interactive

onResume - Invokes this callback just before the activity starts interacting with the user. Activity is at the
top of the activity stack, and captures all user input. Most of an app's core functionality is implemented in the
onResume method.

onPause - Activity loses focus and enters a paused state. Occurs when user taps the back or recents button.
Still partially visible, but most often is an indication that the user is leaving the activity and will soon
enter Stopped or Resumed state.

onStop - Activity is no longer visible to the user. Activity either destroyed, new activity starting up,
or returning to resumed state and covering the stopped activity. Either onRestart or onDestroy.

onRestart - Activity is being restarted. Restores the state of the activity from the time it was stopped and always
followed by onStart.

onDestroy - Activity is being destroyed. Implemented to ensure that all resources are released and avoid leaks.


```
onCreate() → onStart() → onResume() → [App Running]
                                            ↓
                                       onPause() → onStop() → onDestroy()
```

**When do I use each callback?**

`onCreate()`: Used this callback in MainActivity.java to initialize the activity.

Once the MainActivity class is loaded by the system and memory is allocated, the `onCreate()(Bundle savedInstanceState)`
callback is invoked. The system passes any previously saved state (e.g., after a rotation) inside the `Bundle`.
In this simple app, no state is saved, so `savedInstanceState` is null.

`super.onCreate(savedInstanceState)` - ensures the parent class (`AppCompactActivity`) performs initial setup,
susch as creating the internal decor view, initializing the theme, and restoring any framework-managed state.

`setContentView(R.layout.activity_main)` - The layout file `activity_main.xml` is parsed (XML is inflated to
actual view objects) and becomes the visible content of the activity.

`findViewById(R.id.gridView)` - The inflated `GridView` is located and stored in the `gridView` variable.

Create the data source - An `ArrayList<Shape>` is created and populated with four shapes, each containing
an image resource and a label.

Create and set the adapter - Custom adapter is instantiated with the shape list and the application context.
Adapter's job is to convert each `Shape` object into a `View` (typically an `ImageView` + `TextView` inside a grid cell).
Tells the GridView to ask the adapter for the number of items and for each item's view.

`gridView.setNumColumns(2)` - forces the grid to show two columns regardless of screen width.

---

### XML Layouts

**What is an XML layout file?**

Defines structure for User Interface and are built using a hierarchy of View and ViewGroup objects.

View usually draws something the user can see and interact with.

ViewGroup is an invisible container that defines the layout structure for View and other ViewGroup objects.

View objects are often called widgets and can be one of many subclasses, such as Button or TextView.

ViewGroup objects are called layouts and can be LinearLayout or ConstraintLayout


**Layout types I used:**


| Layout | When I used it                                     | Why I chose it                                                                           |
|---|----------------------------------------------------|------------------------------------------------------------------------------------------|
| `ConstraintLayout` | To create a grid view of various shapes in the app | Helps create large, complex layouts with a <br/> flat hierarchy - no nested view groups. |


**A layout snippet I'm proud of (Utilization of CardView in ConstraintLayout):**


```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
xmlns:app="http://schemas.android.com/apk/res-auto"
```
The root element is a `CardView`, which provides a card surface with rounded corners and a drop shadow. 
Namespaces are declared here since this is the root element.

```xml
android:layout_width="match_parent"
android:layout_height="220dp"
```
Each card streches to the full width of the RecyclerView and has a fixed height of 220dp.

```xml
app:cardCornerRadius="40dp"
```
Rounds the corners of the card with a 40dp radius 

```xml
app:cardElevation="20dp"
```
20dp drop shadow is added to the card

```xml
<androidx.constraintlayout.widget.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
```
`ConstraintLayout` fills the interior of the card so child views can be precisely positioned with constraints.

```xml
<ImageView
    android:id="@+id/imageviewCard"
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:src="@drawable/volley"
```
The sport image. Width and height are both 0dp. `android:src` is a design-time placeholder; the real image
is set in `onBindViewHolder()`

```xml
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```
All four sides constrained to the parent, so the image fills the entire card interior.

```xml
        <TextView
            android:id="@+id/textView"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
```
The sport name label. Width fills it's horizontal constraints; height wraps to fit the text content.

```xml
            android:layout_marginStart="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginBottom="76dp"
```
8dp margin on the sides to avoid touching the card edges. 76dp bottom margin lifts the text up from the very bottom
of the card.

```xml
android:text="Title"
```
Placeholder text visible at design time only, overwritten in `onBindViewHolder`.

```xml
    app:layout_constraintBottom_toBottomOf="@+id/imageviewCard"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintHorizontal_bias="0.0"
    app:layout_constraintStart_toStartOf="parent" />
```
Constraints the `TextView` to the bottom of the image (which fills the card) and to both horizontal edges.
`horizontal_bias="0.0` aligns it to the left side.

---

### Views & View Binding

**Common Views I used:**

| View           | What it does                          | Example in my app                                                                                               |
|----------------|---------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| `TextView`     | Displays text                         | Pretty much anywhere i wanted to display text on the screen                                                     |
| `ImageView`    | Displays images                       | Used to display images in the grid_item_layout.xml before replicating it in the activity_main.xml as a GridView |
| `RecyclerView` | Scrollable list                       | Usage in the activity_main.xml where multiple images view are arranged in a grid strictly with 2 columns        |
| `CardView`     | Arranges multiple ImageViews as cards | Used as a root element, which provides a card surface with rounded corners and a drop shadow                                                                                        |


---

## ⌨️ Code Walkthrough

### Sport.java

```java
public class Sport {
```
Defines a plain data class called `Sport`. Holds information about a single sport.

```java
String sportName;
int sportImg;
```
Two instance fields: `sportName` stores the sport's display name and text, and `sportImg` stores the image resource.

```java
public Sport(String sportName, int sportImg) {
    this.sportName = sportName;
    this.sportImg = sportImg;
}
```
Constructor for the `Sport` class. Accepts a name and an image resource ID and assigns them to the fields above.
`this.` disambiguates between the field and the parameter of the same name

```java
public String getSportName() { return sportName; }
public int getSportImg() { return sportImg; }
```
Getter methods for the fields. Other classes call these to read the field values without accessing the fields 
directly (encapsulation).

```java
public void setSportName(String sportName) { this.sportName = sportName; }
public void setSportImg(int sportImg) { this.sportImg = sportImg; }
```
Setter methods for the fields. Other classes call these to modify the field values without accessing the fields 
directly (encapsulation).

---

### ItemClickListener.java

```java
import android.view.View;
```
Imports Android's `View` class so it can be used as a parameter type below.

```java
public interface ItemClickListener {
```
Declares a Java Interface - a contract that any implementing class must fulfill. Contains no logic,
only method signatures.

```java
void onItemClick(View view, int position);
```
Single method every implementing class must provide. Receives the `View` that was tapped and the `position`
(index) of that item in the list. 

---

### CustomAdapter.java

```java
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.SportsViewHolder> {
```
`CustomAdapter` is an adapter - the bridge between the data (sportList) and the `RecyclerView`
that displays it. The generic type parameter `<CustomAdapter.SportsViewHolder>` tells RecyclerView which 
ViewHolder class this adapter uses.

```java
private List<Sport> sportList;
```
Holds the list of `Sport` objects that the adapter will display.

```java
public static ItemClickListener clickListener;
```
Static reference to an `ItemClickListener` object. Static means it's shared across all instances of 
`CustomAdapter`. It is set externally so `MainActivity` can be notified when a card is tapped.

```java
public void setClickListener(ItemClickListener myListener) {
        this.clickListener = myListener;
    }
```
Setter that lets `MainActivity` register itself as the click handler. 
Once called, any tap on a card will route through this listener.

```java
public CustomAdapter(List<Sport> sportList) {
        this.sportList = sportList;
    }
```
Constructor. Receives the data list and stores it so the adapter can use it when binding views.

```java
@NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
```
RecyclerView calls this when it needs a new card view. `@Override` means we're
implementing a method required by `RecyclerView.Adapter`. `@NonNull` asserts the return value will never be null.

```java
View itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.card_item_layout, parent, false);
```
Inflates `card_item_layout.xml` — turns the XML file into an actual `View` object in memory.
`parent.getContext()` provides the Android context needed for inflation. `false` means don't attach
it to the parent yet (RecyclerView handles that).

```java
return new SportsViewHolder(itemView);
```
Wraps the inflated view in a `SportsViewHolder` and returns it.

```java
@Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {
```
RecyclerView calls this to fill a card with data. `holder` is the recycled card view;
`position` is which item in the list it should show.

```java
Sport sport = sportList.get(position);
```
Retrieves the `Sport` object at this position.

```java
holder.textView.setText(sport.getSportName());
holder.imageView.setImageResource(sport.getSportImg());
```
Populates the card's `TextView` with the sport's name and its `ImageView` with the sport's image.

```java
@Override
    public int getItemCount() {
        return sportList.size();
    }
```
Tells RecyclerView how many items exist. RecyclerView uses this to know when to stop creating cards.

```java
public static class SportsViewHolder extends RecyclerView.ViewHolder
                                         implements View.OnClickListener {
```
A static inner class that holds references to the views inside one card.
`static` means it doesn't hold a reference to the outer CustomAdapter. 
It also implements `View.OnClickListener` so it can respond to taps.

```java
TextView textView;
ImageView imageView;
```
References to the two widgets inside each card — the sport name label and the sport image.

```java
public SportsViewHolder(@NonNull View itemView) {
            super(itemView);
```
Constructor. Calls the parent `RecyclerView.ViewHolder` constructor,
which stores `itemView` as the root view of this card.

```java
textView  = itemView.findViewById(R.id.textView);
imageView = itemView.findViewById(R.id.imageviewCard);
```
Finds the `TextView` and `ImageView` inside the card layout by their XML IDs,
so we don't have to search for them every time `onBindViewHolder` runs.

```java
itemView.setOnClickListener(this);
```
Registers this `SportsViewHolder` as the click listener for the entire card. 
When tapped, `onClick` below is called.

```java
@Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(v, getAdapterPosition());
            }
        }
```
Called when a card is tapped. `getAdapterPosition()` returns the index of this card.
The null check prevents a crash if no listener has been set. Delegates to `MainActivity.onItemClick`.

---

### MainActivity.java

```java
public class MainActivity extends AppCompatActivity implements ItemClickListener {
```
`MainActivity` is the app's entry point screen. It extends `AppCompatActivity` (provides modern Android features)
and implements `ItemClickListener` so it can receive card-tap events from the adapter.

```java
private RecyclerView recyclerView;
private CustomAdapter myAdapter;
private List<Sport> sportList;
```
Three instance-level fields: the scrollable list view, the adapter bridging data to that view,
and the data itself.

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
```
`onCreate` is the first lifecycle method called when the activity starts. `super.onCreate` runs the parent
class's setup (required). `savedInstanceState` holds any previously saved state (e.g. after rotation).

```java
setContentView(R.layout.activity_main);
```
Tells Android to use `activity_main.xml` as the screen layout for this activity.

```java
recyclerView = findViewById(R.id.recyclerView);
```
Finds the `RecyclerView` declared in the XML by its ID and stores it in the field.

```java
sportList = new ArrayList<>();
```
Creates an empty list that will hold all `Sport` objects.

```java
Sport s1 = new Sport("Football",   R.drawable.football);
Sport s2 = new Sport("Basketball", R.drawable.basketball);
Sport s3 = new Sport("VolleyBall", R.drawable.volley);
Sport s4 = new Sport("Tennis",     R.drawable.tennis);
Sport s5 = new Sport("Ping Pong",  R.drawable.ping);
```
Creates five `Sport` objects, each with a name and a reference to a drawable resource in `res/drawable/`.

```java
sportList.add(s1);
sportList.add(s2);
sportList.add(s3);
sportList.add(s4);
sportList.add(s5);
```
Adds all five sports to the list. The order here determines the display order in the RecyclerView.

```java
myAdapter = new CustomAdapter(sportList);
```
Creates the adapter, passing in the populated list.

```java
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(myAdapter);
```
Attaches a `LinearLayoutManager`, which lays cards out in a vertical scrolling column.
`this` is the context (the activity itself).
Connects the adapter to the RecyclerView, causing it to start displaying cards.

```java
myAdapter.setClickListener(this);
```
Registers `MainActivity` as the click listener. `this` works here because `MainActivity` 
implements `ItemClickListener`.

```java
public void onItemClick(View view, int position) {
        Toast.makeText(this,
            "You chose: " + sportList.get(position).getSportName(),
            Toast.LENGTH_SHORT).show();
    }
```
Called whenever a card is tapped (routed here from `SportsViewHolder.onClick`).
`Toast.makeText` shows a brief pop-up message at the bottom of the screen naming the chosen sport.
`Toast.LENGTH_SHORT` keeps it visible for ~2 seconds.

---

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
```
Standard XML declaration — specifies the XML version and character encoding.

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
```
The root layout is a `ConstraintLayout`, which positions children using constraint rules. 
The three `xmlns:` declarations define the XML namespaces: `android:` for standard attributes, `app:` 
for library/constraint attributes, and `tools:` for design-time-only hints.

```xml
    android:layout_width="match_parent"
    android:layout_height="match_parent"
```
The `ConstraintLayout` fills the entire screen in both dimensions.

```xml
    tools:context=".MainActivity">
```
A design-time hint telling Android Studio to render this layout in the context of `MainActivity`.
Has no effect at runtime.

```xml
    <ImageView
        android:id="@+id/imageView"
```
Declares an `ImageView` widget and assigns it the ID `imageView` so it can be referenced from code or
other constraint rules.

```xml
        android:layout_width="0dp"
        android:layout_height="230dp"
```
`0dp` width with horizontal constraints (below) means "fill the space between those constraints".
Height is fixed at 230dp.

```xml
        android:src="@drawable/header"
```
Sets the displayed image to the `header` drawable resource.

```xml
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```
Pins the `ImageView` to the left edge, right edge, and top edge of the parent — making it a full-width
banner at the very top of the screen.

```xml
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
```
Declares the `RecyclerView` and assigns it the ID `recyclerView`, which `MainActivity` uses in `findViewById`.

```xml
        android:layout_width="0dp"
        android:layout_height="0dp"
```
Both set to `0dp`, meaning the view will fill all space defined by its constraints.

```xml
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView" />
```
Constrains the `RecyclerView` so its top sits directly below the header `ImageView` and its other three
edges reach the screen edges — filling all remaining space beneath the header.

---

## 🚀 How to Run This Project

1. Clone the repo: `git clone https://github.com/vineetganti/SportsApp.git`
2. Open in Android Studio
3. Let Gradle sync finish
4. [ Any other steps — API keys, emulator setup, etc. ]
5. Hit ▶ Run

**Min Android version required:** [ e.g., Android 7.0 (API 24) ]

---

*Last updated: May 16th 2026 · Written by Vineet Ganti*