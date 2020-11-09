---
layout: page
title: Ngoh Wei Yue's Project Portfolio Page
---

## Project: Addendum for Teaching Assistants (ATAS)

**ATAS** is a desktop application designed to be a handy in-class companion for CS1010S Teaching Assistants. 
It aims to **elevate users' efficiency** and make their experience of handling administrative tasks as **seamless** as possible. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

This project is based on the AddressBook-Level3 (AB3) project created by the [SE-EDU initiative](https://se-education.org).
AB3 has about 10 kLoC, while **ATAS** has about 20 kLoC modified/added on top.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link to code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nweiyue&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added a **tab layout**, and the ability to **switch between individual tabs** (Pull request [\#75](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/75))
  * What it does: Allows **ATAS** to have multiple layouts which serves different functions.
  * Justification: This feature improves the product significantly because we can now display more features without overcrowding the GUI. It also enhances the UX since there is a dedicated tab for each aspect of the main features of **ATAS**.
  * Highlights: This enhancement allows commands like `enterses` to work properly. It required an in-depth analysis of both the front-end and the back-end of the application. The implementation too was challenging as it required integration between the front-end and back-end code.

* **New Feature**: Added a **memo** feature (Pull request [\#122](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/122))
  * What it does: Allows users to take down notes in a text box which serves as a memo. Users can type directly into the memo or use `addnote` to add notes to the memo. The memo also auto-saves when users make any changes to it.
  * Justification: This feature improves the product because **ATAS** focuses on in-class teaching experience. Taking down notes during a lesson and referring to them afterwards is very common, and **ATAS** should allow users to do so without having to switch to other applications.
  * Highlights: This enhancement required the implementation of all 4 components (`Ui`, `Logic`, `Model` and `Storage`) of memo from scratch. It required an in-depth analysis of the architecture and design alternatives. The implementation too was challenging as it required major addition to many components of the application.
  <div style="page-break-after: always;"></div>
  
* **Project management**:
  * Designed the initial UI mockup (Pull request [\#56](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/56))
  * Updated README.md (Pull requests [\#56](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/56), [\#218](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/218))
  * Updated Index.md (Pull request [\#214](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/214))

* **Enhancements to existing features**:
  * Updated `findstu` command (Pull request [\#66](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/66)) 
  * Devised and updated the GUI color scheme (Pull request [\#75](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/75))
  * Refactored most command words. Examples: `add` -> `addstu`, `deletesession` -> `deleteses` (Pull request [\#99](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/99))
  * Updated `deleteses` and `editses` to be referenced by index number instead of session name (Pull request [\#99](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/99))
  * Updated help window to display command tables (Pull requests [\#101](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/101), [\#210](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/210))
  * Updated UI to show specialised messages for empty state. Examples: user has no students, user has not entered session (Pull requests [\#210](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/210), [\#230](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/230))
  * Fixed various bugs (Pull requests [\#83](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/83), [\#84](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/84), [\#133](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/133), [\#138](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/138), [\#217](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/217))

* **Documentation**:
  * User Guide: (Pull requests [\#158](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/158), [\#239](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/239), [\#250](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/250))
    * Added documentation and UI diagrams for the layout of **ATAS**
    * Added documentation for the overview of features, `help`, `switch`, `addnote`, Saving the memo, Saving the data and FAQ
    * Added command summary for features related to memo
    
  * Developer Guide:
    * Added use cases, NFRs and updated glossary (Pull requests [\#63](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/63), [\#240](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/240))
    * Updated documentation and UML diagram for the `Ui` component of **ATAS** (Pull request [\#126](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/126))
    * Added documentation and UML diagrams for the implementation of `switching between tabs` and `adding a note` (Pull request [\#126](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/126), [\#240](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/240))
    * Updated Documentation.md and section on documentation (Pull request [\#240](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/240))
    * Added documentation on most instructions for manual testing (Pull request [\#259](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/259))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#125](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/125), [\#162](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/162), [\#167](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/167), [\#223](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/223)
  * Contributed to forum discussions (Issue [\#237](https://github.com/nus-cs2103-AY2021S1/forum/issues/237))
  * Reported 9 bugs and suggestions for another team during practical dry-run ([PED](https://github.com/nweiyue/ped/issues))
