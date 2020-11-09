---
layout: page
title: Alvin Chee Teck Weng's Project Portfolio Page
---

## Project: Addendum for Teaching Assistants (ATAS)

**ATAS** is a desktop application designed to be a handy in-class companion for CS1010S Teaching Assistants. 
It aims to **elevate users' efficiency** and make their experience of handling administrative tasks as **seamless** as possible. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

This project is based on the AddressBook-Level3 (AB3) project created by the [SE-EDU initiative](https://se-education.org).
AB3 has about 10 kLoC, while **ATAS** has about 20 kLoC modified/added on top.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=CodingCookieRookie&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&until=2020-11-09&tabOpen=true&tabType=authorship&tabAuthor=CodingCookieRookie&tabRepo=AY2021S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **New Feature**: Added the ability to enter a session.
  * What it does: allows the user to enter a session to view and toggle the presence and participation status of the students.
  * Justification: This feature limits the user to view and toggle the presence and participation status of each student in a session only if the user has entered that session so that the user would know which session details he or she is editing.
  * Highlights: The implementation was quite tricky as it required changes to existing commands and the usage had to be limited to the current session tab.

* **Enhancements to existing features**:
  * Updated the status bar footer to show current session details (Pull request [\#167](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/167))
  * Enabled session list to be sorted automatically according to the date of each session (Pull request [\#146](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/146))
  * Integrated all the session commands including participation and presence commands into ATAS (Pull request [\#95](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/95))

* **Documentation**:
  * User Guide:
    * Edited quickstart to make it more user-friendly (Pull request[\#157](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/157))<br>
    * Added documentation to exit feature (Pull request[\#157](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/157))
    * Added documentation to all sessions feature including: (Pull request[\#157](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/157))
         * `addses`, `deleteses`, `editses`, `clearses`, `enterses`
  * Developer Guide:
    * Added implementation details of the `enterses` feature. (Pull request [\#128](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/128))
    * Added sequence and activity UML diagrams for the `enterses` feature. (Pull request [\#128](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/128))
    * Edited and added user stories. (Pull request [\#237](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/237))
    * Added instructions on how to add a session for manual testing (Pull request [\#257](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/257))

* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull request[\#81](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/81)), (Pull request[\#99](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/99)), (Pull request[\#154](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/154)), (Pull request[\#147](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/147)), (Pull request[\#254](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/254))
  * Contributed to forum discussions ((Pull request[\#173](https://github.com/nus-cs2103-AY2021S1/forum/issues/173)))
  * Reported 11 bugs and suggestions for another team during practical dry-run ([PED](https://github.com/CodingCookieRookie/ped/issues))
