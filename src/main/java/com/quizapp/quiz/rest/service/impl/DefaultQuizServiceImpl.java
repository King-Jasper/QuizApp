package com.quizapp.quiz.rest.service.impl;

import com.quizapp.quiz.core.builders.QuizRecordBuilder;
import com.quizapp.quiz.core.builders.UserRecordBuilder;
import com.quizapp.quiz.core.exception.InvalidRequestException;
import com.quizapp.quiz.core.exception.NoQuizAttemptsFoundException;
import com.quizapp.quiz.core.exception.QuizNotFoundException;
import com.quizapp.quiz.core.exception.UserNotFoundException;
import com.quizapp.quiz.core.utils.Constants;
import com.quizapp.quiz.core.utils.PaginationPayloadUtility;
import com.quizapp.quiz.model.entity.AppUser;
import com.quizapp.quiz.model.entity.Question;
import com.quizapp.quiz.model.entity.Quiz;
import com.quizapp.quiz.model.entity.QuizAttempt;
import com.quizapp.quiz.model.payload.base.BaseResponse;
import com.quizapp.quiz.model.payload.pagination.PagedResponse;
import com.quizapp.quiz.model.payload.quiz.*;
import com.quizapp.quiz.model.payload.user.UserDTO;
import com.quizapp.quiz.model.repository.AppUserRepository;
import com.quizapp.quiz.model.repository.QuestionRepository;
import com.quizapp.quiz.model.repository.QuizAttemptRepository;
import com.quizapp.quiz.model.repository.QuizRepository;
import com.quizapp.quiz.rest.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultQuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    private final AppUserRepository userRepository;

    private final QuestionRepository questionRepository;

    private final QuizAttemptRepository quizAttemptRepository;

    @Override
    @Transactional
    public BaseResponse<?> handleQuizSubmission(QuizSubmissionRequest quizSubmissionRequest, Principal principal) {
        AppUser loggedInUser = userRepository.findAppUserByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Quiz quiz = QuizRecordBuilder.quizSubmissionToQuizRecord(quizSubmissionRequest, loggedInUser);
        if (quizSubmissionRequest.getQuestionMetaData() != null) {
            List<Question> questions = quizSubmissionRequest.getQuestionMetaData().stream()
                    .map(QuizRecordBuilder::resolveQuestionMetaDataToQuestion)
                    .peek(question -> question.setQuiz(quiz)).collect(Collectors.toList());
            questionRepository.saveAll(questions);
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            return BaseResponse.builder().responseCode(Constants.SUCCESS_CODE).responseMessage("Quiz saved successfully").timeStamp(LocalDateTime.now()).build();
        } else {
            throw new InvalidRequestException("Bad request metadata");
        }
    }

    @Override
    @Transactional
    public BaseResponse<?> submitQuizAttempt(Long userId, Long quizId, List<QuestionAnswerRequest> submittedAnswers) {
        AppUser user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(QuizNotFoundException::new);
        int quizScore = calculateScore(quiz, submittedAnswers);

        QuizAttempt quizAttempt = new QuizAttempt(user, quiz, submittedAnswers.toString(), quizScore);
        quizAttemptRepository.save(quizAttempt);
        UserDTO userDTO = UserRecordBuilder.mapAppUserToUserDTO(user);
        QuizScoreResponse quizScoreResponse = QuizRecordBuilder.resolveQuizScoreResponse(quizScore, quiz.getTitle(), userDTO);
        return BaseResponse.builder().responseCode(Constants.SUCCESS_CODE)
                .responseMessage("quiz attempt saved successfully").payload(quizScoreResponse).timeStamp(LocalDateTime.now()).build();
    }

    @Override
    public BaseResponse<?> fetchQuizzes(int pageNumber, int pageSize, Principal principal) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
        Page<Quiz> quizPages = quizRepository.findAll(pageable);
        if (quizPages.isEmpty()) {
            throw new NoQuizAttemptsFoundException("No quiz attempts found for user");
        }
        Page<FetchAvailableQuizResponse> fetchAvailableQuizPagedResponse = quizPages.map(QuizRecordBuilder::resolveQuizToFetchAvailableQuizResponse);
        PagedResponse<FetchAvailableQuizResponse> pagedResponsePayload = PaginationPayloadUtility.resolvePaginationMetaData(fetchAvailableQuizPagedResponse,
                pageNumber, pageSize, Constants.SUCCESS_CODE, HttpStatus.OK.value(), "Quiz data fetched successfully");
        return BaseResponse.builder().responseCode(Constants.SUCCESS_CODE).responseMessage("Quiz data fetched successfully").payload(pagedResponsePayload)
                .timeStamp(LocalDateTime.now()).build();
    }

    private int calculateScore(Quiz quiz, List<QuestionAnswerRequest> submittedAnswers) {
        int score = 0;
        for (Question question : quiz.getQuestions()) {
            Long questionId = question.getId();
            String correctAnswer = question.getCorrectAnswer();
            int markPerQuestion = quiz.getMarkPerQuestion();

            Optional<String> userAnswer = submittedAnswers.stream()
                    .filter(submittedAnswer -> submittedAnswer.getQuestionId().equals(questionId))
                    .map(QuestionAnswerRequest::getSubmittedAnswer).findFirst();

            if (userAnswer.isPresent() && correctAnswer != null && correctAnswer.equals(userAnswer.get())) {
                score += markPerQuestion;
            }
        }
        return score;
    }
}
