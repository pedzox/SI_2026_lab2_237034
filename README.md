# Втора лабораториска вежба по Софтверско инженерство

###  Control Flow Graph

### searchBookByTitle

<img width="1667" height="936" alt="image" src="https://github.com/user-attachments/assets/b2ecd619-368d-4a24-9444-50dab1a2cb40" />


### borrowBook

<img width="1438" height="951" alt="image" src="https://github.com/user-attachments/assets/c5b76f0b-aac6-422c-83ff-6d2643a76a83" />


## Цикломатска комплексност

Цикломатската комплексност за функцијата searchBookByTitle ја пресметав преку формулата CC = P + 1, каде што P е бројот на предикатни јазли во Control Flow Graph-от. Предикатни јазли се сите јазли каде што постои разгранување во текот на извршување на функцијата. Во оваа функција постојат 4 предикатни јазли: if (title.isEmpty()), for (Book book : books), if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()) и if (results.isEmpty()). Со примена на формулата: CC = 4 + 1 = 5.

Цикломатската комплексност за функцијата borrowBook ја пресметав преку формулата CC = P + 1, каде што P е бројот на предикатни јазли во Control Flow Graph-от. Во оваа функција постојат 4 предикатни јазли: if (title.isEmpty() || author.isEmpty()), for (Book book : books), if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) и if (!book.isBorrowed()). Со примена на формулата: CC = 4 + 1 = 5.

## Тест случаи според критериумот Every Statement

### searchBookByTitle

| | test 1 | test 2 | test 3 |
|---|---|---|---|
| if (title.isEmpty()) | * | * | * |
| throw IllegalArgumentException | * | | |
| results = new ArrayList() | | * | * |
| for (Book book : books) | | * | * |
| if (title.equalsIgnoreCase && !borrowed) | | * | * |
| results.add(book) | | * | |
| if (results.isEmpty()) | | | * |
| return null | | | * |
| return results | | * | |

Минимален број на тест случаи за Every Statement критериумот е 3.

- Test 1: searchBookByTitle("") -  IllegalArgumentException
- Test 2: searchBookByTitle("Clean Code") -  листа (knigata postoi i ne iznajmena)
- Test 3: searchBookByTitle("Harry Potter") -  null (knigata ne postoi)

## Тест случаи според критериумот Every Branch

### borrowBook

| | test 1 | test 2 | test 3 | test 4 |
|---|---|---|---|---|
| if (title/author isEmpty) → true | * | | | |
| if (title/author isEmpty) → false | | * | * | * |
| for loop → has next | | * | * | * |
| for loop → no more books | | | | * |
| if (title && author match) → true | | * | * | |
| if (title && author match) → false | | | | * |
| if (!book.isBorrowed()) → true | | * | | |
| if (!book.isBorrowed()) → false | | | * | |

Минимален број на тест случаи за Every Branch критериумот е 4.

- Test 1: borrowBook("", "Author") - IllegalArgumentException
- Test 2: borrowBook("The Hobbit", "J.R.R. Tolkien") - uspesno iznajmuvanje
- Test 3: borrowBook("The Hobbit", "J.R.R. Tolkien") - фрла RuntimeException (vekje e iznajmena)
- Test 4: borrowBook("Nonexistent", "Nobody") - фрла RuntimeException (knigata ne postoi)

## Тест случаи според критериумот Multiple Condition

### searchBookByTitle
book.getTitle().equalsIgnoreCase(title) , !book.isBorrowed()

| Комбинација | title match | !isBorrowed | Резултат | Тест |
|---|---|---|---|---|
| TT | true | true | book се додава | searchBookByTitle("Clean Code") |
| TF | true | false | book се прескока | book изнајмена, searchBookByTitle("Clean Code") |
| FT | false | true | book се прескока | searchBookByTitle("Harry Potter") |
| FF | false | false | book се прескока | опфатено со FT (кратко спојување) |

Минимален број на тест случаи е 3.

### borrowBook
title.isEmpty() , author.isEmpty()

| Комбинација | title.isEmpty | author.isEmpty | Резултат | Тест |
|---|---|---|---|---|
| TT | true | true | exception | borrowBook("", "") |
| TF | true | false | exception | borrowBook("", "Martin") |
| FT | false | true | exception | borrowBook("Clean Code", "") |
| FF | false | false | продолжува | borrowBook("Clean Code", "Martin") |

Минимален број на тест случаи е 4.
