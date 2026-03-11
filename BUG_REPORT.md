# 🐛 BUG REPORT - Streaming Platform Simulation

**Report Date:** March 11, 2026  
**Project:** Streaming-Platform-Simulation  
**Branch:** New-menu-with-subscription-added

---

## 🔴 CRITICAL BUGS (Program-Breaking)

### **BUG #1: Logout Loop - Program Never Exits**
**Severity:** 🔴 CRITICAL  
**File:** `src/main/Menu.java`  
**Location:** Line 81 (case 6)  
**Issue:** When user selects "Log out" (option 6), the switch statement breaks from the loop but `menuOptions()` is never called again, so the program hangs. The user gets stuck and cannot return to main menu or exit properly.

**Current Code:**
```java
case 6 ->{
    break;
}
```

**Problem:** The `break` statement exits `menuOptions()` loop, but there's no logout logic or return to main menu.

**Impact:** Users cannot log out and exit gracefully. Program is stuck.

**Fix Required:**
```java
case 6 ->{
    System.out.println("Thank you for using STREAM! Goodbye!");
    System.exit(0);  // or return to main menu
}
```

---

### **BUG #2: InputAuthenticator Password Validation is Too Strict**
**Severity:** 🔴 CRITICAL  
**File:** `src/platform/InputAuthenticator.java`  
**Location:** Lines 11-17  
**Issue:** Password regex requires a special character (`[@$!%*?&]`), but most users won't have this in their password. This makes registration nearly impossible.

**Current Regex:**
```
^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$
```

**Requires:**
- ✅ At least one lowercase
- ✅ At least one uppercase  
- ✅ At least one digit
- ❌ **At least one special character** (TOO STRICT)

**Example Failing Passwords:**
- `Password123` → FAILS (no special char)
- `Qwerty1234` → FAILS (no special char)

**Fix Required:** Make special character optional:
```
^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d@$!%*?&]{8,}$
```

---

### **BUG #3: Menu Option Validation Doesn't Handle Invalid Input Properly**
**Severity:** 🔴 CRITICAL  
**File:** `src/main/Menu.java`  
**Location:** Lines 48-60  
**Issue:** When user enters invalid input (like text instead of number), `InputMismatchException` is caught but `scan.nextLine()` is never called to clear the buffer, causing an infinite loop.

**Current Code:**
```java
while(true){
    try{
        System.out.print("Choose an option: ");
        option = scan.nextInt();

        if(option <= 0){
            throw new InputMismatchException("Invalid option. Try again!");
        }
        break;
    }catch(InputMismatchException e){
        System.out.println(e.getMessage());
        // Missing: scan.nextLine(); to clear buffer
    }
}
```

**Steps to Reproduce:**
1. At menu, enter "abc" instead of number
2. Program will loop infinitely without accepting new input

**Fix Required:** Add `scan.nextLine();` to clear buffer:
```java
}catch(InputMismatchException e){
    System.out.println(e.getMessage());
    scan.nextLine();  // Clear the invalid input from buffer
}
```

---

## 🟡 MAJOR BUGS (Functionality Issues)

### **BUG #4: No Persistent User Storage - All Users Lost on Exit**
**Severity:** 🟡 MAJOR  
**File:** `src/platform/Platform.java`  
**Location:** Lines 35-50  
**Issue:** Users are stored in memory only (`List<User> users`). When program closes, all registered users are lost. Users must re-register every time.

**Current Implementation:**
```java
private final List<User> users = new ArrayList<>();  // In-memory only, no file persistence
```

**Impact:** 
- No actual login system (can't retrieve existing users)
- Users can't come back and use their account
- Watch history is never saved

**Expected Behavior:**
- Users should be saved to file on registration
- Users should be loaded from file on startup
- Login should retrieve existing user from persistent storage

**Fix Required:** Add file I/O methods to `Platform.java`

---

### **BUG #5: No Login Screen - Everyone Registers as New User**
**Severity:** 🟡 MAJOR  
**File:** `src/main/Menu.java`  
**Location:** Line 21 (showMenu method)  
**Issue:** The program always shows registration screen. There's no login option for returning users. `Authentication.java` exists but is never used.

**Current Flow:**
```
Program starts → Always shows registration → Only option is to create new account
```

**Expected Flow:**
```
Program starts → Shows login/register choice
              → If login: authenticate existing user
              → If register: create new account
```

**Impact:** No user persistence whatsoever.

**Fix Required:** Add login prompt before registration in `Menu.showMenu()`

---

### **BUG #6: Watch History Not Saved to File**
**Severity:** 🟡 MAJOR  
**File:** `src/admin/WatchHistory.java`  
**Location:** Lines 1-45  
**Issue:** Watch history is stored in memory but never saved/loaded from file. Methods `saveToFile()` and `loadFromFile()` are missing (mentioned in README but not implemented).

**Current Implementation:**
```java
private final List<String> historyList;  // In-memory only
// Missing: saveToFile() and loadFromFile() methods
```

**Impact:** Watch history is lost when program closes.

**Expected:** According to README, should persist to `watchhistory_username.txt`

**Fix Required:** Implement `saveToFile()` and `loadFromFile()` methods

---

### **BUG #7: Recommendation Engine Cannot Work Without Watch History**
**Severity:** 🟡 MAJOR  
**File:** `src/admin/Recommendation.java`  
**Location:** Lines 10-12  
**Issue:** Recommendation requires watch history, but history is never saved/loaded, so it's always empty.

**Current Code:**
```java
if (watched == null || watched.isEmpty()) {
    return recommended;  // Always returns empty list
}
```

**Impact:** Recommendations feature is non-functional.

---

### **BUG #8: No Password Validation at Registration**
**Severity:** 🟡 MAJOR  
**File:** `src/platform/Registration.java`  
**Location:** Lines 32-37  
**Issue:** Password validation uses overly strict regex that includes mandatory special character. Combined with BUG #2, users cannot register.

**Test Case:** Try password `Password123` - will fail registration

---

## 🟠 MODERATE BUGS (Behavior Issues)

### **BUG #9: Logout Doesn't Clear Current User**
**Severity:** 🟠 MODERATE  
**File:** `src/platform/Authentication.java`  
**Location:** Lines 34-37  
**Issue:** The `logout()` method sets `currentUser = null`, but there's no way to log in again after logout because there's no login screen.

**Code:**
```java
public void logout() {
    platform.setCurrentUser(null);  // Clears user but can't log back in
    System.out.println("You have been logged out.");
}
```

**Impact:** Once logged out, no way to resume.

---

### **BUG #10: Recommendation Always Based on Single Genre**
**Severity:** 🟠 MODERATE  
**File:** `src/admin/Recommendation.java`  
**Location:** Lines 22-32  
**Issue:** Algorithm is too simplistic - only recommends based on last watched item's genre.

**Current Logic:**
```java
Media lastWatched = watched.get(watched.size() - 1);
String preferredGenre = lastWatched.getGenre();
// Only recommends same genre
```

**Better Approach:** Should consider multiple watched items, ratings, frequency, etc.

---

### **BUG #11: No Input Validation for Search Results**
**Severity:** 🟠 MODERATE  
**File:** `src/main/Menu.java`  
**Location:** Lines 181-220  
**Issue:** In `displayMovieTitles()` and `displaySeriesTitles()`, if invalid number is entered, the error message doesn't clear the scanner buffer.

**Fix:** Add `scan.nextLine();` after catching InputMismatchException

---

### **BUG #12: Platform Never Stores Created User**
**Severity:** 🟠 MODERATE  
**File:** `src/main/Menu.java`  
**Location:** Line 21-36  
**Issue:** User is created during registration but never added to `Platform.addUser()`. User only exists in local variable.

**Current Code:**
```java
registration.registerNewUser();
registration.setUsername();
this.user = registration.getUser();  // Gets user but doesn't store in Platform
```

**Missing:** `platform.addUser(this.user);`

---

## 🟢 MINOR BUGS (Code Quality)

### **BUG #13: InputAuthenticator Pattern Name is Inconsistent**
**Severity:** 🟢 MINOR  
**File:** `src/platform/InputAuthenticator.java`  
**Location:** Line 11  
**Issue:** Variable named `PASSWORDPattern` but should follow Java naming convention `PASSWORD_PATTERN`

**Current:**
```java
public final Pattern PASSWORDPattern = ...
```

**Should Be:**
```java
public final Pattern PASSWORD_PATTERN = ...
```

---

### **BUG #14: Typo in Method Name**
**Severity:** 🟢 MINOR  
**File:** `src/main/Menu.java`  
**Location:** Line 33  
**Issue:** Method name `setCurrentSubsription()` has typo - should be `setCurrentSubscription()`

**Current:**
```java
platform.setCurrentSubsription(user.getSubscriptionType());
```

**Should Be:**
```java
platform.setCurrentSubscription(user.getSubscriptionType());
```

---

### **BUG #15: Exit Button Breaks Entire Program**
**Severity:** 🟢 MINOR  
**File:** `src/main/Menu.java`  
**Location:** Line 81  
**Issue:** Case 6 just breaks, doesn't gracefully exit or return to menu

---

## 📊 SUMMARY

| Severity | Count | Impact |
|----------|-------|--------|
| 🔴 CRITICAL | 3 | Program unusable |
| 🟡 MAJOR | 5 | Core features broken |
| 🟠 MODERATE | 4 | Unexpected behavior |
| 🟢 MINOR | 3 | Code quality |
| **TOTAL** | **15** | **High Risk** |

---

## 🚀 PRIORITY FIX ORDER

1. **Fix BUG #3** (Menu input buffer) - prevents infinite loop
2. **Fix BUG #2** (Password validation) - allows registration
3. **Fix BUG #1** (Logout) - allows proper exit
4. **Fix BUG #4** (User persistence) - saves users to file
5. **Fix BUG #5** (Login screen) - lets users log back in
6. **Fix BUG #6** (Watch history file I/O) - persists history
7. **Fix BUG #12** (Platform.addUser) - stores created users

---

**Recommended Action:** Fix all CRITICAL bugs before next push to production.
