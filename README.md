# Lines

Reading is so much fun!  Do you want to track the reading progress you have made? Or have 
lists to help you organize your books? The charm of literature often makes people fall in 
love with books. Have you ever meet the lines you extremely loved? **Try *Lines***！Enjoy 
your reading time and record gorgeous lines. That's the time to take a trip to different 
worlds - worlds within books!

## Introduction

*"Lines"* is a collection of reading record, excerpt of lines, and lists of books that 
users can use. Users can record almost everything about reading through *Lines*:
- **Make lists of books based on different function.**
- **Record notes while reading (the feels and thoughts of reader when reading the book. 
Sometimes it can be arguments to a point, or an insight inspired by the passage).**
- **Track their current reading stage.**
- **Record lines they loved.**

Anyone who love reading and want to read books can use it! Whatever the age and grade,
people can use it whenever they need *Lines*. Especially for lovers of reading, *Lines* 
can help them organize their reading stage and record their notes when reading. For those
who love to keep lines they have read as treasure, *Lines* provide a big convenience for
recording.

The creator of *Lines*, me, came up with this idea because I am the person described
above. Usually what I did is putting the lists of books that I finished and want to read 
in my memo. And write down my favourite sentences in my excerpt book. However, sometimes 
it is hard to bring, and I have to spend a lot of time to sort and write them. But with 
*Lines*, I can type as soon as I open it since it is within computers.

## User Stories
- As a user, I want to be able to access my collection of book lists.
- As a user, I want to be able to access my collection of excerpts.
- As a user, I want to be able to make a new book list and name it.
- As a user, I want to be able to access my finished book list.
- As a user, I want to be able to access the book list that I plan to read.
- As a user, I want to be able to access the recommended book list.
- As a user, I want to be able to view all books in a list.
- As a user, I want to be able to select a list(s) and add a book(s) in it.
- As a user, I want to be able to remove a book(s) from the selected list(s).
- As a user, I want to be able to check whether a book is in a list.
- As a user, I want to be able to view a book's information.
- As a user, I want to be able to rename a selected book.
- As a user, I want to be able to select a book and set up a book's reading progress, 
such as: pages and percentage.
- As a user, I want to be able to add a note(s) to a book(s).
- As a user, I want to be able to remove a note(s) from a book(s).
- As a user, I want to be able to finish a book.
- As a user, I want to be able to rename my excerpts' collection.
- As a user, I want to be able to add a excerpt(s) and view my excerpts' collection.
- As a user, I want to be able to choose to save my book lists and excerpts when I select 
the quit option from the application menu. 
- As a user, I want to be able to choose to load my book lists and excerpts when I start 
*Lines*.

# Instructions for Grader

- You can generate the first required event related to adding BookList to mainMenu by enter the name
of the book at the text field.
- You can generate the second required event related to adding books to bookList Y by click the add
button at the bottom down bookList menu.
- You can locate my visual component by access any book in the list which user choose.
- You can save the state of my application when closing the mainMenu.
- You can reload the state of my application when opening the application.

# Phase 4: Task 2

Mon Nov 28 17:17:04 PST 2022
Books Data loaded:


Mon Nov 28 17:17:04 PST 2022
Added book: Harry potter


Mon Nov 28 17:17:04 PST 2022
Added note.


Mon Nov 28 17:17:04 PST 2022
Added note.


Mon Nov 28 17:17:04 PST 2022
Added book: Painful Learning


Mon Nov 28 17:17:04 PST 2022
Added book: 花见花开


Mon Nov 28 17:17:04 PST 2022
Added bookList: booklist


Mon Nov 28 17:17:04 PST 2022
Excerpts Data loaded:


Mon Nov 28 17:17:04 PST 2022
Added excerpt.


Mon Nov 28 17:17:04 PST 2022
Added excerpt.


Mon Nov 28 17:17:31 PST 2022
Added bookList: Boy


Mon Nov 28 17:17:48 PST 2022
Added book: math


Mon Nov 28 17:19:10 PST 2022
Removed book: math


Mon Nov 28 17:19:16 PST 2022
Books Data saved.


Mon Nov 28 17:19:16 PST 2022
Excerpts Data saved.

# Phase 4: Task 3

1. I will refactor all Json classes. I will make two abstract JsonReader and JsonWriter classes to implement
the common methods, such as read, write, open, close, and so on. Then, I can create new JsonReader classes for books 
and excerpts that extend the abstract JsonReader class, and create other two new JsonWriter classes for books and 
excerpts that extend the abstract JsonWriter class.


2. I will refactor the GUI class into multiple classes. The reason is now I wrote all my code about GUI
all in one class. It is very long and contains multiple of repeated codes. I can write a new class that 
construct BookLists panel, a new class to construct Book panel, and one more new class to construct Excerpt 
panel. By doing so, I will figure out those three new classes, let's call them BlPanel, BPanel, and ExPanel, 
will have a lot of common methods, such as LayoutPanel, AddButtonPanel, and so on. Hence, I might consider 
to have an abstract class called Component and let BlPanel, BPanel, and ExPanel extend Component to reduce 
duplicated code. After doing so, I can associate those three classes to GUI by have them as fields and 
instantiate them by methods (or in constructor). So we have a more clear structure of the codes.


3. If I have more time, I will change the display of my BookLists and Books. Now I use button for each 
bookList and a label located in a ScrollPanel to representing Books. If I have more time, I will  change them
all to JList. Then I will add one more button called Access for BookLists, so that when I click each BookList, 
I select them. Then I can push Access to access them - the detailed performance is similar to now: the BookList 
will display in the bottom area. Similarly, I will also add a new Access button for Books. Then, when I choose 
a book in the list, I can select it and access it. Then a new frame will pop up like current implementation.


4. Finally, I will change the refresh method. Currently, every time I do something, such as add a book, note, 
excerpt, and so on, I have to make the old frame un-visible and make a new frame to reflect the changes. After 
the three steps listed above, I think I can rewrite the update method to make the whole application update more 
smoothly.


By refactoring my design as steps stated above, my design can have a clearer, more organized hierarchy. At the same
time, the coupling of my code will reduce since I refactor similar classes to extend one abstract class. Hence, the 
cohesion of my classes will be better since I focus more on one common topic in each class. 
