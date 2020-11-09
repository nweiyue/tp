---
layout: page
title: Eris Jacey Masagca's Project Portfolio Page
---

## Project: Addendum for Teaching Assistants (ATAS)

**ATAS** is a desktop application designed to be a handy in-class companion for CS1010S Teaching Assistants. 
It aims to **elevate users' efficiency** and make their experience of handling administrative tasks as **seamless** as possible. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

This project is based on the AddressBook-Level3 (AB3) project created by the [SE-EDU initiative](https://se-education.org).
AB3 has about 10 kLoC, while **ATAS** has about 20 kLoC modified/added on top.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=totalCommits%20dsc&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=erisjacey&tabRepo=AY2021S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)
* **New feature**: Added the model component for `sessions`. (pull request [#73](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/73))
   * What it does: essentially the backend component for the above entity that will change depending on the logic component.
   * Justification: This feature was included early in iteration v1.2 in order to facilitate a smoother working process and to ensure more of the team is involved with implementation (they can work on the same feature by implementing the other components).
   * Highlights: This enhancement allowed for easy extensibility and was implemented with good design principles in mind.
* **New feature**: Added a "random name generator" command that allows the user to receive the name of a randomly-selected student in the student list. (pull request [#112](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/112))
* **New feature**: Added the ability to undo/redo previous commands. (pull request [#131](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/131))
   * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
   * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
     This is especially so when attempting to type fast (on the CLI) is prone to typographical errors, etc.
   * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives.
     The implementation too was challenging as it required to existing commands.
* **Project management**:
   * Set up the GitHub team org/repo and managed the GitHub settings for the team
   * Set up and maintained the issue tracker and added relevant tags for issues
   * Managed releases `v1.1` - `v1.4` (all 4 releases) on GitHub
* **Enhancements to existing features**:
   * Updated the existing `list` command (pull request [#65](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/65))
   * Updated existing AB3 legacy package names into **ATAS**-relevant names (pull request [#96](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/96), [#120](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/120))
   * Updated the GUI color scheme (pull requests [#114](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/114), [#207](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/207))
   * Added a new tab (ATAS) to the UI (pull request [#207](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/207))
   * Fixed various bugs (pull requests 
   [#162](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/162), 
   [#163](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/163),
   [#232](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/232))
* **Documentation**:
   * User Guide:
      * Updated the AB3 legacy UG skeleton to make it more relevant for **ATAS** (pull requests [#148](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/148), [#149](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/149))
      * Added / updated existing documentation for the following parts: (pull request [#153](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/153))
         * `1. Introduction`, `1.1. Elevate your efficiency`, `2. About this user guide`, `2.1. Navigation`,
           `5.2.3. Generating the name of a randomly-selected student`, `5.2.4. Undo`, `5.2.5. Redo`, 
           `7. Command summary`, `7.1. General`
   * Developer Guide:
      * Updated the AB3 legacy DG skeleton to make it more relevant for **ATAS** (pull request [#225](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/225))
      * Added details to the following parts: (pull request [#51](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/51))
         * Target user profile, Value proposition, User stories
      * Added / updated details to the following parts: (pull request [#234](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/234), [#255](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/255))
         * DevOps, Appendix: requirements -- product scope,
           Appendix: instructions for manual testing -- launch and shutdown (added pictures), Appendix: effort   
      * Added architecture details of the `model` component (pull request [#127](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/127))    
      * Added implementation details of the `rng` feature (pull request [#127](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/127))
      * Added implementation details of the `undo/redo` feautre (pull request [#234](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/234))      
* **Community**:
   * PRs reviewed (with non-trivial review comments): 
   [#68](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/68),
   [#74](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/74),
   [#75](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/75),
   [#81](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/81),
   [#84](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/84),
   [#94](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/94),
   [#98](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/98),
   [#99](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/99),
   [#103](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/103),
   [#105](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/105),
   [#117](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/117),
   [#124](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/124),
   [#152](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/152),
   [#155](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/155),
   [#158](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/158),
   [#165](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/165),
   [#235](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/235),
   [#236](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/236),
   [#240](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/240),
   [#249](https://github.com/AY2021S1-CS2103T-W16-4/tp/pull/249)
   * Contributed to forum discussions - partook in general discussions 
   (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/24#issuecomment-675483348),
              [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/66),
              [3](https://github.com/nus-cs2103-AY2021S1/forum/issues/136),
              [4](https://github.com/nus-cs2103-AY2021S1/forum/issues/147),
              [5](https://github.com/nus-cs2103-AY2021S1/forum/issues/156#issuecomment-684881309))
   * Contributed to forum discussions - gave suggestions to fix various problems for other students in the class 
   (examples: [1](https://github.com/nus-cs2103-AY2021S1/forum/issues/4#issuecomment-674324666),
              [2](https://github.com/nus-cs2103-AY2021S1/forum/issues/6#issuecomment-674372429),
              [3](https://github.com/nus-cs2103-AY2021S1/forum/issues/7#issuecomment-674409588),
              [4](https://github.com/nus-cs2103-AY2021S1/forum/issues/8#issuecomment-674464491),
              [5](https://github.com/nus-cs2103-AY2021S1/forum/issues/6#issuecomment-675202126),
              [6](https://github.com/nus-cs2103-AY2021S1/forum/issues/49#issuecomment-678733643),
              [7](https://github.com/nus-cs2103-AY2021S1/forum/issues/101#issuecomment-683231413),
              [8](https://github.com/nus-cs2103-AY2021S1/forum/issues/134#issuecomment-683781950),
              [9](https://github.com/nus-cs2103-AY2021S1/forum/issues/128#issuecomment-684471615),
              [10](https://github.com/nus-cs2103-AY2021S1/forum/issues/160#issuecomment-685331533))
   * Reported 10 bugs and suggestions for another team during a dry-run of the practical exam ([link here](https://github.com/erisjacey/ped/issues)) 
   * Some parts of my CSS stylesheet for a similar school project was adopted by several other classmates
   ([1](https://github.com/nweiyue/ip/blob/master/src/main/resources/view/style.css))           

