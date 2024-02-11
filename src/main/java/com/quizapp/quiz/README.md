#QuizApp API

## Table of contents
- [Introduction](...)
- [Requirements](...)
- [Setup](#setup)
- [Prerequisites](...)
- [Installation](...)
- [Database Setup](...)
- [Usage](#usage)
- [API Endpoints](...)


## Introduction

- 
This Quiz API entails developing back-end logic to score quizzes, requiring endpoints for quiz submission, data storage, scoring mechanism implementation, score calculation, and retrieval/display, quiz scores to users.
- Java 17
## Setup
### Prerequisites
-Ensure you have the following software installed:


### Payload Structure
TODO: add payload structure

#### Signup User
- Endpoint: `http://localhost:8080/api/v1/users/signup`
- Method: `POST`
- Consumes: `application/json`
- Produces: `application/json`

- request:
{
"first_name": "IFESCO",
"last_name": "JASPER",
"phone_number": "08124214585",
"email_address": "ifescojasper1@gmail.com",
"password": "StrongPassword1234@"
}
- response:
{
"responseCode": "00",
"responseMessage": "User registered successfully",
"time_stamp": "2024-02-10 12:30:15"
}
#### Login User
- Endpoint: `http://localhost:8080/api/v1/users/login`
- Method: `POST`
- Consumes: `application/json`
- Produces: `application/json`
  {
  "responseCode": "00",
  "responseMessage": "User login successful",
  "payload": {
  "access_token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpZmVubmFud2Fmb3JAZ21haWwuY29tIiwiaXNzIjoieHByZXNzcGF5bWVudHMiLCJpYXQiOjE3MDc2MDcxNTAsImV4cCI6MTc5NDAwNzE1MH0.zj3CKH2bNRMnDgxzxNi48HfRPrx2tmW9FPsMAHWUZOQYeuSZVN7zfZ956i7vP83NWkBmlHO7Qw4Y120M8kLSAg",
  "refresh_token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpZmVubmFud2Fmb3JAZ21haWwuY29tIiwiaXNzIjoieHByZXNzcGF5bWVudHMiLCJpYXQiOjE3MDc2MDcxNTAsImV4cCI6MTc5NDAwNzE1MH0.zj3CKH2bNRMnDgxzxNi48HfRPrx2tmW9FPsMAHWUZOQYeuSZVN7zfZ956i7vP83NWkBmlHO7Qw4Y120M8kLSAg"
  }
  }



#### Quiz Submit
- Endpoint: `http://localhost:8080/api/v1/quiz/submit`
- Method: `POST`
- Consumes: `application/json`
- Produces: `application/json`

- request: {
- {
  "quiz_title": "Sample Quiz 1",
  "quiz_description": "This is a sample quiz for demonstration purposes",
  "score_per_answer": 10,
  "quiz_type": "MEDIUM",
  "questionMetaData": [
  {
  "question_text": "What is the capital of USA?",
  "correct_answer": "Paris",
  "answer_options": "Paris,London,Berlin,Madrid",
  "subject_category": "Geography"
  },
  {
  "question_text": "Which programming language is known for its simplicity?",
  "correct_answer": "Python",
  "answer_options": "Java,Python,C++,JavaScript",
  "subject_category": "Programming"
  },
  {
  "question_text": "Who wrote 'Romeo and Juliet'?",
  "correct_answer": "William Shakespeare",
  "answer_options": "Charles Dickens,Emily Bronte,William Shakespeare,Jane Austen",
  "subject_category": "Literature"
  }
  ]
  }
  - response
  - {
    "responseCode": "00",
    "responseMessage": "Quiz saved successfully",
    "time_stamp": "2024-02-11 00:29:23"
    }

#### Quiz attempts Submit
- Endpoint: `http://localhost:8080/api/v1/quiz-attempts/submit?userId=1&quizId=1`
- Method: `POST`
- Consumes: `application/json`
- Produces: `application/json`

request{

{
"question_id": 1,
"submitted_answer": "Paris"
},
{
"question_id": 2,
"submitted_answer": "Python"
},
{
"question_id": 3,
"submitted_answer": "William Shakespeare"
}
]

response{
{
"responseCode": "00",
"responseMessage": "quiz attempt saved successfully",
"payload": {
"quiz_title": "Sample Quiz 1",
"quiz_score": 30,
"quiz_player": {}
},
"time_stamp": "2024-02-10 13:12:53"
}

