# Streaming-Platform-Simulation
1. Project Overview
This project implements a Streaming Platform Simulation modeled after real-world platforms like Netflix. The program begins at a registration or login screen — new users register an account and choose a plan, while returning users log in directly. Once authenticated, the platform identifies the account type, loads the subscription, and serves content accordingly.

An admin layer handles content management, a recommendation engine personalizes the experience, and a file handling system ensures watch history persists between sessions. Every class is connected to at least one class from another member, making this a fully integrated system.
2. OOP Concepts Coverage
OOP Concept	Implemented By	How It Applies
Encapsulation	All 5 Members	Passwords, billing info, session tokens, and watch history are hidden behind private fields. Accessible only through controlled methods like login(), calculateBill(), and getWatchHistory()
Inheritance	Members 1, 2, 4, 5	FreeUser, PremiumUser, AdminUser extend abstract User. Movie and Series extend abstract Media. FreeSubscription and PremiumSubscription extend abstract Subscription
Polymorphism	Members 2, 4, 5	play() on Movie plays a full film; on Series plays the next episode. calculateBill() returns $0 for Free and applies pricing logic for Premium. getAccessLevel() differs per user type
Abstraction	Members 1, 2, 4	Abstract base classes User, Media, and Subscription define what must exist without defining how. All subclasses are forced to implement abstract methods

3. Member Class Assignments
Each member is responsible for designing, implementing, committing, and documenting their 3 assigned classes. All classes must connect to at least one other member's class.

OLIVEROS — User Management
Class Name	Class Type	Responsibility
User	Abstract Base Class	Defines shared attributes (username, email, password) and abstract method getAccessLevel()
FreeUser	Concrete — extends User	Restricted access: ads enabled, limited content, no HD streaming, no downloads
PremiumUser	Concrete — extends User	Full access: ad-free, HD streaming, offline downloads enabled

DOLLISON — Content Library
Class Name	Class Type	Responsibility
Media	Abstract Base Class	Defines shared attributes (title, genre, rating) and abstract methods play() and getDetails()
Movie	Concrete — extends Media	Implements play() as full-length film playback; includes duration and cast fields
Series	Concrete — extends Media	Implements play() as episode-by-episode playback; tracks seasons and current episode progress

DELOS REYES — Platform & Gateway
Class Name	Class Type	Responsibility
Registration	Service Class	First entry point for new users. Handles register(), validates input, creates a User object, assigns a Subscription, and saves the account to Platform
Authentication	Service Class	Entry point for returning users. Handles login(), logout(), session tokens, and password validation. No user accesses the platform without this class
Platform	Main System Class	Central hub — stores all user accounts, loads the correct user and subscription after login, and serves the content library

DACUMOS — Billing & Subscriptions
Class Name	Class Type	Responsibility
Subscription	Abstract Base Class	Defines billing cycle, plan name, and abstract method calculateBill() enforced on all subtypes
FreeSubscription	Concrete — extends Subscription	calculateBill() returns $0; enforces content and quality restrictions on the user
PremiumSubscription	Concrete — extends Subscription	calculateBill() applies monthly/annual pricing logic with discounts and renewal tracking

CREENCIA — Admin, Recommendations & Watch History
Class Name	Class Type	Responsibility
AdminUser	Concrete — extends User	Elevated user with content moderation — can add, remove, and update Media entries on the Platform
Recommendation	Service / Logic Class	Analyzes WatchHistory data and user preferences to suggest relevant Movie and Series objects
WatchHistory	Tracker Class + File Handling	Records every Media watched, tracks progress, feeds data to Recommendation, and persists data using saveToFile() and loadFromFile()

ALL MEMBERS
5. File Handling — WatchHistory Persistence
Without file handling, watch history is wiped every time the program closes. The WatchHistory class solves this by saving data to a .txt file per user on logout and reloading it on login.

Method	Trigger	Description
addRecord(title, date, progress)	On play()	Adds a new entry to the in-memory history list
saveToFile(username)	On logout	Writes all records to watchhistory_username.txt
loadFromFile(username)	On login	Reads the file and restores the user's history list
getHistory()	By Recommendation	Returns the full list of watched media for analysis
getLastWatched()	By Platform	Returns the most recently watched media item
