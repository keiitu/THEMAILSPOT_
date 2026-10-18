# The Mail Spot

A Java console application for user registration and identity management.

Part of the **THE SPOT** ecosystem—a South African art and mental health platform. While THE SPOT connects people to expression and resources, The Mail Spot handles the first step: **identity**.

## Features

- Username validation (underscore required, ≤ 5 characters)
- Password complexity (≥ 8 chars, capital, number, special character)
- South African cell phone validation (+27 format)
- Registration and login flows
- Unit tested with JUnit

## Project Context

THE SPOT is a conceptual non-profit art platform showcasing original artwork as evidence of inner experience. The Mail Spot is the backend companion—a system for managing who enters the space.

Both projects share a South African context and a focus on access.

## Tech Stack

- Java
- Apache Ant (build)
- JUnit 4 (testing)
- NetBeans (IDE)



## Part of a Larger Vision

This is Part 1 of a multi-part project. Future parts will include:
- File handling (persistent user data)
- Multiple user support
- Advanced validation and security

# The Mail Spot

A Java console application for user registration, identity management, and secure messaging.

Part of the **THE SPOT** ecosystem—a South African art and mental health platform. While THE SPOT connects people to expression and resources, The Mail Spot handles the backend logic: starting with **identity**, and expanding into **communication**.

## Features

- Username validation (underscore required, ≤ 5 characters)
- Password complexity (≥ 8 chars, capital, number, special character)
- South African cell phone validation (+27 format)
- Registration and login flows
- **QuickChat Messaging Menu (Send, Store, Discard, Quit)**
- **Message length validation (max 250 characters)**
- **Automated Message Hash generation (e.g., `00:0:HITONIGHT`)**
- **JSON File Storage for saved messages (using Google Gson)**
- Unit tested with JUnit 4 and automated via GitHub Actions

## Project Context

THE SPOT is a conceptual non-profit art platform showcasing original artwork as evidence of inner experience. The Mail Spot is the backend companion—a system for managing who enters the space, and how they communicate once inside.

Both projects share a South African context and a focus on access.

## Tech Stack

- Java (JDK 25)
- Apache Ant (build)
- JUnit 4 & Hamcrest (testing)
- Google Gson (JSON parsing)
- GitHub Actions (CI/CD automated testing)
- NetBeans (IDE)

### Testing
To run the automated tests locally via Ant, use the following command in your terminal:
```bash
ant test

## Author

[Keitumetse Chief Mamakoko]

IT Business Systems Major | Systems thinker at the intersection of code, business & creative production
