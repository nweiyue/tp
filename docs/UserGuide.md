---
layout: page
title: User Guide
---

<div class="toc-no-list-style">
  * Table of contents
  {:toc}
</div>

--------------------------------------------------------------------------------------------------------------------

## 1. Overview

[?]

### 1.1. Overview

ATAS is a **desktop app for managing students’ particulars, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type really quickly, ATAS will help you to manage your contacts and students’ particulars faster than traditional GUI apps.

[todo] mention something about the different things we can do for students, sessions, current session, etc.

### 1.2. Preview

[preview]

--------------------------------------------------------------------------------------------------------------------

## 2. About this user guide

### 2.1. Navigation

[to add details about navigation]

### 2.2. Glossary

[glossary]

### 2.3. Symbols and formatting

[symbols and formatting]

--------------------------------------------------------------------------------------------------------------------

## 3. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `atas.jar` from [here](https://github.com/AY2021S1-CS2103T-W16-4/tp/releases). 

3. Copy the file to the folder you want to use as the _home folder_ for your ATAS.

4. Double-click the file to start the app. The GUI similar to the below image should appear in a few seconds. Note how the app contains some sample data.<br>
   ![StartPage](images/StartPage.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`liststu`** : Lists out all the students.

   * **`addstu n/John Cena m/A0123456X e/thechamp@u.nus.edu`** : Adds a student named `John Cena` to the student list.

   * **`deleteses 3`** : Deletes the 3rd session shown in the current session list.
   
   * **`editses 3 s/Tutorial 2`** : Edits the 3rd session name to `Tutorial 2`.

   * **`clearstu`** : Deletes all the students.

   * **`bye`** : Exits out of the application.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 4. Layout of ATAS

[general overview]

### 4.1. Students

[student overview]

### 4.2. Sessions

[sessions overview]

### 4.3 Current session

[current session overview]

### 4.4. Memo

[memo overview]

--------------------------------------------------------------------------------------------------------------------

## 5. Features

### 5.1. Overview

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* The words in `UPPER_CASE` are placeholders to be replaced by the user.<br>
  e.g. in `addstu n/STUDENT_NAME` should be changed to `addstu n/John Cena`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Cena t/foreigner` or simply as `n/John Cena`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/foreigner`, `t/foreigner t/enthusiastic` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME m/MATRICULATION_NUMBER`, `m/MATRICULATION_NUMBER n/NAME` is also acceptable. 

</div>

### 5.2 General

[short overview]

#### 5.2.1. Viewing help : `help`

Shows the list of available commands and a link to access the user guide.

![helpWindow](images/helpWindow.png)

```
help
```

#### 5.2.2. Switching between tabs : `switch`

Switches between tabs.

```
switch TAB_NAME
```

* The tab name is case-insensitive.
* The tab must be an existing tab (students or sessions).
* `Current Session` can only be access using `enterses INDEX`.

Example:
* `switch sessions` switches from the current tab to the sessions tab.

#### 5.2.3. Generating the name of a randomly-selected student : `rng`

Chooses a student at random from the student list.

```
rng
```

#### 5.2.4. Undo-ing a command : `undo`

Undoes a command and essentially returns ATAS to the state prior to performing that command.

```
undo
```

Commands currently supported:
* Adding a student/session: `addstu` / `addses`
* Deleting a student/session: `deletestu` / `deleteses`
* Editing a student/session: `editstu` / `editses`
* Clearing all students/sessions: `clearstu` / `clearses`

#### 5.2.5. Redo-ing a command : `redo`

Redoes a command that was most recently undone and returns ATAS to the state after having performed that command.

Essentially the reverse of undo-ing a command.

```
redo
```

#### 5.2.6. Exiting the program : `bye`

Exits the application.

```
bye
```

### 5.3. Students

[short overview]

#### 5.3.1. Adding a student : `addstu`

Adds a student to the student list.

```
addstu n/NAME m/MATRICULATION_NUMBER e/NUS_EMAIL_ADDRESS [t/TAG]…
```

:bulb: **Tip:**<div markdown="span" class="alert alert-primary">
A student can have any number of tags (including 0) 
</div>

Examples:
* `addstu n/John Cena m/A0123456J e/thechamp@u.nus.edu`
* `addstu n/Alvina Handsome m/A0123456X e/handsome.alvina@u.nus.edu t/new`

#### 5.3.2. Listing all students : `liststu`

Shows a list of all students in the current student list.

```
liststu
```

#### 5.3.3. Locating students by name : `findstu`

Finds students whose names contain any of the given keywords.

```
findstu KEYWORD [MORE_KEYWORDS]
````

* The search is case-insensitive. e.g `john` will match `John`
* The order of the keywords does not matter. e.g. `John Cena` will match `Cena John`
* Only the name is searched.
* Only full words will be matched e.g. `Jon` will not match `John` 
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `John Cena` will return `John Tan`, `Mark Cena`

Examples:
* `findstu John` returns `john` and `John Cena`
* `findstu kent ridge` returns `Kent Tan` and `Mark Ridge`<br>
[Insert image of an example of result of `findstu Cena`]

#### 5.3.4. Editing a student's particulars : `editstu`

The user will be prompted to confirm their decision here.

If yes: edits and updates the particulars of the specified student from the student list.

```
editstu INDEX n/UPDATED_NAME
```

* Edits the student at the specified `INDEX` with the specified updated particular to be updated.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …
* User will then be prompted for a confirmation input **`(yes/no)`**.

Examples:
* `editstu 2 n/John Cena` followed by `yes` edits the 2nd student in the student list with an updated name `John Cena`.
* `editstu 3 t/Joker` followed by `y` edits the 3rd student in the student list with an updated tag `Joker`.

#### 5.3.5. Deleting a student : `deletestu`

The user will be prompted to confirm their decision here.

If yes: deletes the specified student from the student list.

```
deletestu INDEX
```

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …
* User will then be prompted for a confirmation input **`(yes/no)`**.

Examples:
* `liststu` followed by `deletestu 2` followed by `yes` deletes the 2nd student in the student list.
* `findstu Betsy` followed by `deletestu 1` followed by `y` deletes the 1st student in the results of the `findstu` command.

#### 5.3.6. Clearing the student list : `clearstu`

The user will be prompted to confirm their decision here.

If yes: removes all students from the student list.

```
clearstu
```

#### 5.4. Sessions

[short overview]

#### 5.4.1. Adding a session : `addses`

Adds a session to session list.

```
addses s/SESSION_NAME d/SESSION_DATE
```

:bulb: **Tip:**<div markdown="span" class="alert alert-primary">
Two sessions cannot have the same name
</div>

Examples:
* `addses s/tut1 d/12/7/2020`

#### 5.4.2. Deleting a session : `deleteses`

The user will be prompted to confirm their decision here.

If yes: deletes the specified session from the session list.

```
deleteses INDEX
```

* Deletes the session at the specified `INDEX`.
* The index refers to the index number shown in the displayed session list.
* The index **must be a positive integer** 1, 2, 3, …
* User will then be prompted for a confirmation input **`(yes/no)`**.

Examples:
* `deleteses s/tut1 d/12/7/2020` followed by `yes` deletes the 2nd session in the session list.

#### 5.4.3. Clearing the session list : `clearses`

Deletes all the sessions in the session list.

```
clearses
```

#### 5.4.4. Editing a session : `editses`

The user will be prompted to confirm their decision here.

If yes: edits and updates the details of the specified session from the session list.

```
editses INDEX s/UPDATED_NAME d/UPDATED_DATE
```

* Edits the session at the specified `INDEX` with the specified details to be updated.
* The index refers to the index number shown in the displayed session list.
* The index **must be a positive integer** 1, 2, 3, …
* User will then be prompted for a confirmation input **`(yes/no)`**.

Examples:
* `editses 2 s/Tutorial1 d/10/10/2020` followed by `yes` edits the 2nd session in the session list with a new session name `Tutorial1` and a new session date `10/10/2020`.


#### 5.4.5. Entering a session : `enterses`

Enters a session in the session list.

```
enterses INDEX
```

* Enters the session at the specified `INDEX`.
* The index refers to the index number shown in the displayed session list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `enterses 1` enters the first session and allows you to use PARTICIPATE and PRESENCE commands.

### 5.5. Current session

[short overview]

#### 5.5.1. Toggling participation status of students : `participate`

Toggles the participation status of the students in the student list of the session.

```
participate INDEX_RANGE
```

* Toggles the participation status of the students at the specified `INDEX_RANGE`.
* The index range refers to the range of index numbers shown in the displayed student list of the session.
* The index range **must be a positive integer** 1, 2, 3, … OR **a positive index range** 1-4, 2-5, 2-9 … 

Examples:
* `participate 1-4` toggles the participation status of students 1 to 4.

#### 5.5.2. Toggling presence status of students : `presence`

Toggles the presence status of students in the student list of the session.

```
presence INDEX_RANGE
```

* Toggles the presence status of the students at the specified `INDEX_RANGE`.
* The index range refers to the range of index numbers shown in the displayed student list of the session.
* The index range **must be a positive integer** 1, 2, 3, … OR **a positive index range** 1-4, 2-5, 2-9 … 

Examples:
* `presence 1-4` toggles the presence status of students 1 to 4.

### 5.6. Memo

[short overview]

#### 5.6.1. ???

[todo]

### 5.7. User confirmation

Prompts the user to confirm the execution of commands that may permanently remove information.
These commands are: `deletestu`, `editstu`,`clearstu`, `deleteses`, `editses`.

* Confirms the execution at the specified `INDEX`.

Examples:
* `deletestu 3` is followed with `Delete 3? (yes/no)`.
* `editses 3 s/SESSION_NAME` is followed with `Edit 3? (yes/no)`.
* `clearstu` is followed with `Clear list? (yes/no)`.

### 5.8. Saving the data

Students’ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## 6. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ATAS home folder.

--------------------------------------------------------------------------------------------------------------------

## 7. Command summary

### 7.1. General

[general]

### 7.2. Students

[students]

### 7.3. Sessions

[sessions]

### 7.4. Current session

[current session]

### 7.5. Memo

[revamp below]

Action | Format, Examples
--------|------------------
**Help** | `help`
**Switch** | `switch TAB_NAME`<br> e.g., `switch sessions`
**Random Name Generation** | `rng`
**Undo** | `undo`
**Redo** | `redo`
**Exit** | `bye`
**Add Student** | `addstu n/NAME m/MATRICULATION_NUMBER e/NUS_EMAIL_ADDRESS [t/TAG]…​` <br> e.g., `addstu n/Rainer Lam m/A0123456C e/rainerlam@u.nus.edu t/smart`
**List Students** | `liststu`
**Find Students** | `findstu KEYWORD [MORE_KEYWORDS]`<br> e.g., `findstu Justin Bieber`
**Delete Student** | `deletestu INDEX`<br> e.g., `deletestu 3`
**Edit Student** | `editstu INDEX n/UPDATED_NAME`<br> e.g., `editstu 2 n/John Cena`
**Clear Students** | `clearstu`
**Add Session** | `addses s/SESSION_NAME d/SESSION_DATE`<br> e.g., `addses s/TUT1 d/10/10/2020`
**Delete Session** | `deleteses INDEX `<br> e.g., `deleteses 2`
**Edit Session** | `editses INDEX s/UPDATED_NAME d/UPDATED_DATE`<br> e.g., `editses 2 s/TUT2 d/10/10/2020`
**Clear Sessions** | `clearses`
**Enter Session** | `enterses INDEX`<br> e.g., `enterses 1`
**Participate** | `participate INDEX_RANGE `<br> e.g., `participate 2`
**Presence** | `presence INDEX_RANGE `<br> e.g., `presence 2`

