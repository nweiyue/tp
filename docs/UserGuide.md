---
layout: page
title: User Guide
---
ATAS is a **desktop app for managing students’ particulars, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type really quickly, ATAS will help you to manage your contacts and students’ particulars faster than traditional GUI apps.
<hr>

## Table of Contents

* [Quick start](#qs)
* [Features](#features)
  * [Viewing help](#help) (coming soon)
  * [Adding a students](#add)
  * [Listing all students](#list)
  * [Locating students by name](#find)
  * [Deleting a student](#delete)
  * [Editing a student's particulars](#edit)
  * [Clearing all entries](#clear)
  * [Adding a session](#addses)
  * [Deleting a session](#deleteses)
  * [Editing a session details](#editses)
  * [Entering a session](#enterses)
  * [Switching between tabs](#switch)
  * [Exiting the program](#exit)
  * [User confirmation prompt](#ucp) (coming soon)
  * [Saving data](#sd)
* [FAQ](#faq)
* [Command summary](#cs)

--------------------------------------------------------------------------------------------------------------------

## <a name="qs"></a>Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `atas.jar` from [here](https://github.com/AY2021S1-CS2103T-W16-4/tp/releases). 

3. Copy the file to the folder you want to use as the _home folder_ for your ATAS.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![StartPage](images/StartPage.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists out all the students.

   * **`add n/John Cena e/thechamp@u.nus.edu`** : Adds a student named `John Cena` to the class list.

   * **`delete 3`** : Deletes the 3rd student shown in the current list.
   
   * **`edit 3 n/John Cena`** : Edits the 3rd student's name to `John Cena`.

   * **`clear`** : Deletes all the students.

   * **`bye`** : Exits out of the application.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## <a name="features"></a>Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* The words in `UPPER_CASE` are placeholders to be replaced by the user.<br>
  e.g. in `add n/STUDENT_NAME` should be changed to `add n/John Cena`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Cena t/foreigner` or simply as `n/John Cena`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/foreigner`, `t/foreigner t/enthusiastic` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME m/MATRICULATION_NUMBER`, `m/MATRICULATION_NUMBER n/NAME` is also acceptable.

</div>

### <a name="help"></a>Viewing help : `help` (feature coming soon!)

Shows a message explaining how to access the help page.

[insert help message image]

```
help
```

### <a name="add"></a>Adding a student : `add`

Adds a student to class list.

```
add n/NAME m/MATRICULATION_NUMBER e/NUS_EMAIL_ADDRESS [t/TAG]…
```

:bulb: **Tip:**<div markdown="span" class="alert alert-primary">
A student can have any number of tags (including 0)
</div>

Examples:
* `add n/John Cena m/A0123456J e/thechamp@u.nus.edu`
* `add n/Alvina Handsome m/A0123456X e/handsome.alvina@u.nus.edu t/new`

### <a name="list"></a>Listing all students : `list`

Shows a list of all students in the current class list.

```
list
```

### <a name="find"></a>Locating students by name: `find`

Finds students whose names contain any of the given keywords.

```
find KEYWORD [MORE_KEYWORDS]
````

* The search is case-insensitive. e.g `john` will match `John`
* The order of the keywords does not matter. e.g. `John Cena` will match `Cena John`
* Only the name is searched.
* Only full words will be matched e.g. `Jon` will not match `John` 
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `John Cena` will return `John Tan`, `Mark Cena`

Examples:
* `find John` returns `john` and `John Cena`
* `find kent ridge` returns `Kent Tan` and `Mark Ridge`<br>
[Insert image of an example of result of `find Cena`]

### <a name="delete"></a>Deleting a student : `delete`

The user will be prompted to confirm their decision here.

If yes: deletes the specified student from the class list.

```
delete INDEX
```

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed class list.
* The index **must be a positive integer** 1, 2, 3, …
* User will then be prompted for a confirmation input **`(yes/no)`**.

Examples:
* `list` followed by `delete 2` followed by `yes` deletes the 2nd person in the class list.
* `find Betsy` followed by `delete 1` followed by `y` deletes the 1st person in the results of the `find` command.

### <a name="edit"></a>Edit a student's particulars: `edit`

The user will be prompted to confirm their decision here.

If yes: edits and updates the particulars of the specified student from the class list.

```
edit INDEX n/UPDATED_NAME
```

* Edits the student at the specified `INDEX` with the specified particular to be updated.
* The index refers to the index number shown in the displayed class list.
* The index **must be a positive integer** 1, 2, 3, …
* User will then be prompted for a confirmation input **`(yes/no)`**.

Examples:
* `edit 2 n/John Cena` followed by `yes` edits the 2nd person in the class list with an updated name `John Cena`.
* `edit 3 t/Joker` followed by `y` edits the 3rd person in the class list with an updated tag `Joker`.

###  <a name="clear"></a>Clearing all entries : `clear`

The user will be prompted to confirm their decision here.

If yes: removes all students from the class list.

```
clear
```

### <a name="addses"></a>Adding a session : `addses`

Adds a session to session list.

```
addses s/SESSION_NAME d/SESSION_DATE
```

:bulb: **Tip:**<div markdown="span" class="alert alert-primary">
Two sessions cannot have the same name
</div>

Examples:
* `addses s/tut1 d/12/7/2020`

### <a name="deleteses"></a>Deleting a session : `deleteses`

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

### <a name="editses"></a>Editing a session : `editses`

The user will be prompted to confirm their decision here.

If yes: edits and updates the details of the specified session from the session list.

```
editses INDEX s/NEW_NAME d/NEW_DATE
```

* Edits the session at the specified `INDEX` with the specified details to be updated.
* The index refers to the index number shown in the displayed session list.
* The index **must be a positive integer** 1, 2, 3, …
* User will then be prompted for a confirmation input **`(yes/no)`**.

Examples:
* `editsession 2 s/Tutorial1 d/10/10/2020` followed by `yes` edits the 2nd session in the session list with a new session name `Tutorial1` and a new session date `10/10/2020`.

### <a name="enterses"></a>Entering a session : `enterses`

Enters a session in the session list.

```
enter INDEX
```

* Enters the session at the specified `INDEX`.
* The index refers to the index number shown in the displayed session list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `enterses 1` enters the first session and allows you to use PARTICIPATE and PRESENCE commands.

### <a name="participate"></a>Toggling participation status of student : `participate`

Toggles the participation status of a student in the student list of the session.

```
PARTICIPATE INDEX_RANGE
```

* Toggles the participation status of the student at the specified `INDEX_RANGE`.
* The index range refers to the range of index numbers shown in the displayed class list of the session.
* The index range **must be a positive integer** 1, 2, 3, … OR **a positive index range** 1-4, 2-5, 2-9 … 

Examples:
* `participate 1-4` toggles the participation status of students 1 to 4.

### <a name="presence"></a>Toggling presence status of student : `presence`

Toggles the presence status of a student in the student list of the session.

```
PARTICIPATE INDEX_RANGE
```

* Toggles the presence status of the student at the specified `INDEX_RANGE`.
* The index range refers to the range of index numbers shown in the displayed class list of the session.
* The index range **must be a positive integer** 1, 2, 3, … OR **a positive index range** 1-4, 2-5, 2-9 … 

Examples:
* `presence 1-4` toggles the presence status of students 1 to 4.

### <a name="switch"></a>Switching between tabs : `switch`

Switches between tabs.

```
switch TAB_NAME
```

* The tab name is case-insensitive.
* The tab must be an existing tab (classes or attendance).

Example:
* `switch attendance` switches from the current tab to the attendance tab.

###  <a name="exit"></a>Exiting the program : `bye`

Exits the application.

```
bye
```

### <a name="ucp"></a>User confirmation prompt

Prompts the user to confirm the execution of commands that may permanently remove information.
These commands are: `delete`, `edit` and `clear`.

* Confirms the execution at the specified `INDEX`.

Examples:
* `delete 3` is followed with `Delete 3? (yes/no)`.
* `edit 3 t/TAG_NAME` is followed with `Edit 3? (yes/no)`.
* `clear` is followed with `Clear list? (yes/no)`.

###  <a name="sd"></a>Saving the data

Students’ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## <a name="faq"></a>FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ATAS home folder.

--------------------------------------------------------------------------------------------------------------------

## <a name="cs"></a>Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add n/NAME m/MATRICULATION_NUMBER e/NUS_EMAIL_ADDRESS [t/TAG]…​` <br> e.g., `add n/Rainer Lam m/A0123456C e/rainerlam@u.nus.edu t/smart`
**List** | `list`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Justin Bieber`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX n/UPDATED_NAME`<br> e.g., `edit 2 n/John Cena`
**Addses** | `addses s/SESSION_NAME d/SESSION_DATE`<br> e.g., `addses s/TUT1 d/10/10/2020`
**Deleteses** | `deleteses INDEX `<br> e.g., `deleteses 2`
**Editses** | `editses INDEX s/UPDATED_NAME d/UPDATED_DATE`<br> e.g., `editses 2 s/TUT2 d/10/10/2020`
**Participate** | `participate INDEX `<br> e.g., `participate 2`
**Presence** | `presence INDEX `<br> e.g., `presence 2`
**Clear** | `clear`
**Switch** | `switch TAB_NAME`<br> e.g., `switch attendance`
**Exit** | `bye`
