---
layout: page
title: Marcus Tan Wei's Project Portfolio Page
---

## Project: Addendum for Teaching Assistants (ATAS)

**ATAS** is a desktop application designed to be a handy in-class companion for CS1010S Teaching Assistants. 
It aims to **elevate users' efficiency** and make their experience of handling administrative tasks as **seamless** as possible. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

This project is based on the AddressBook-Level3 (AB3) project created by the [SE-EDU initiative](https://se-education.org).
AB3 has about 10 kLoC, while **ATAS** has about 20 kLoC modified/added on top.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link to code](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=MarcusTw&tabRepo=AY2021S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **New Feature**: Added a user confirmation prompt feature. (Pull request [\#74](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/74), [\#213](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/213))
  * What it does: Prompts the user to accept or reject a dangerous command which will overwrite the local data stored.
  * Justification: Just like a typical command prompt which has confirmation prompts where you have to type `yes` or `no` to accept or reject a command, we too, implemented this feature in our **ATAS** to prevent errors made by users.
  * Highlights: This enhancement requires the addition of classes into `Logic` component and its parser to suit the application.
  This enhancement is also particularly tricky because unlike other normal commands, the execution of a `DangerousCommand` will return a user confirmation rather executing the command right away.
  The implementation was also challenging due to the improvement where **ATAS** checks whether the command is valid and executable before the execution phase, for example, checking if the INDEX given in `deletestu INDEX` exists before the actual deletion occurs.

* **Project management**:
  * Managed release `v1.3.1` on GitHub
  * Completed Demo showcase for `v1.3`
  * Update Ui image [\#154](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/154)
  
* **Enhancements to existing features**:
  * Refactored most class names. Examples: `Person` -> `Student`, `AddressBook` -> `Atas` or `StudentList` (Pull request [\#98](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/98))
  * Changed the size 'File' and 'Help' button. [\#136](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/136)<br>
  * Updated `Email` to show capability of using '_' in email. [\#211](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/211)
  * Updated `Matriculation` to allow user entering lower case letter for first and last letter of a matriculation number, for example, `a0149980n` is allowed and converted to `A0149980N` by `Matriculation`.
  [\#211](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/211)
  * Helped to fix various bugs that exist (both frontend and backend):
    [\#106](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/106),
    [\#117](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/117),
    [\#140](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/140),
    [\#165](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/165),
    [\#213](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/213),
    [\#216](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/216)

* **Documentation**:
  * User Guide: (Pull request [\#154](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/154))
    * Added documentation and images for UI components under 'Preview' section
    * Added glossary table to define key terms
    * Added documentation regarding symbols and formatting
    * Added documentation and screenshots of expected outcomes for the following features
        * Student
            * `deletestu`, `editstu`, `clearstu`
        * User confirmation
    * Added command summary for features related to Sessions
  * Developer Guide: (Pull request [\#129](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/129))
    * Updated Architecture Sequence Diagram
    * Added documentation and UML diagrams on User Confirmation Prompt
    * Updated UML diagram for the `Logic` component of the application
    * Added documentation on manual testing
    * Added documentation on on a proposed feature (encrypting data) for future versions

* **Community**:
  * PRs reviewed (with non-trivial review comments):
     [\#75](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/75), 
     [\#99](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/99),
     [\#152](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/152),
     [\#157](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/157),
     [\#237](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/237)
  * Contributed to forum discussions (Issue [\#196](https://github.com/nus-cs2103-AY2021S1/forum/issues/196), [\#213](https://github.com/nus-cs2103-AY2021S1/forum/issues/213), [\#278](https://github.com/nus-cs2103-AY2021S1/forum/issues/278))
  * Reported 6 bugs and suggestions for another team during practical dry-run ([PED](https://github.com/MarcusTw/ped/issues))
