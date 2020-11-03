---
layout: page
title: Ngoh Wei Yue's Project Portfolio Page
---

## Project: Addendum for Teaching Assitants (ATAS)

**ATAS** is a desktop application designed to be a handy in-class companion for CS1010S Teaching Assistants. It aims to **elevate users' efficiency** and make their experience of handling administrative tasks as **seamless** as possible. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 16 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a **memo** feature where user can take down any notes he/she wants (Pull request [\#122](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/122))
  * What it does: Allows user to take down notes in a text box which serves as a memo. User can also use `addnote` to add notes to the memo using the CLI. The memo also auto-saves when users make any changes to it.
  * Justification: This feature improves the product because our application focuses on in-class teaching experience. Taking down notes during a lesson and referring to them afterwards is very common, and our application should allow the user to do so without switching to other applications.
  * Highlights: This enhancement required the addition of classes to all 4 components (`Ui`, `Logic`, `Model` and `Storage`) of the application. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to the application's architecture.

* **New Feature**: Added a **tab layout**, and the ability to **switch between individual tabs** (Pull request [\#75](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/75))
  * What it does: Allows the application to have multiple UIs which serves different functions. User can use `switch TAB_NAME` to switch between tabs. 
  * Justification: This feature improves the product significantly because we can now display more features without overcrowding the GUI. The `switch TAB_NAME` command helps optimise the product for fast-typists.
  * Highlights: This enhancement allows commands like `enterses` to work properly. It requires modifications to both the front-end and the back-end of the application.
  
* **Code contributed**: [RepoSense link to code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nweiyue&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Designed the initial UI mockup (Pull request [\#56](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/56))
  * Updated README.md (Pull requests [\#56](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/56), [\#218](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/218))
  * Updated Index.md (Pull request [\#214](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/214))

* **Enhancements to existing features**:
  * Updated `findstu` command (Pull request [\#66](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/66)) 
  * Updated the GUI color scheme (Pull request [\#75](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/75))
  * Refactored most command words. Examples: `add` -> `addstu`, `edit` -> `editstu`, `deletesession` -> `deleteses` (Pull request [\#99](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/99))
  * Updated `deleteses` and `editses` to be referenced by index number instead of session name (Pull request [\#99](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/99))
  * Updated help window (Pull requests [\#101](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/101), [\#210](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/210))

* **Documentation**:
  * User Guide: (Pull request [\#158](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/158))
    * Added documentation and UI diagrams for the layout of the application
    * Added documentation for the overview of the features of the application and FAQ
    * Added documentation for the following features
        * General:
            * `help`, `switch`
        * Memo:
            * `addnote`, Save memo
        * Saving the data
    * Added command summary for features related to memo
  * Developer Guide:
    * Added use cases, NFRs and update glossary with [erisjacey](https://github.com/erisjacey) (Pull request [\#63](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/63))
    * Updated documentation and UML diagram for the `Ui` component of the application (Pull request [\#126](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/126))
    * Added documentation and UML diagrams for the implementation of switching between tabs (Pull request [\#126](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/126))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#125](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/125), [\#162](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/162), [\#167](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/167)
  * Contributed to forum discussions (Issue [\#237](https://github.com/nus-cs2103-AY2021S1/forum/issues/237))
  * Reported 9 bugs and suggestions for another team during practical dry-run ([PED](https://github.com/nweiyue/ped/issues))
