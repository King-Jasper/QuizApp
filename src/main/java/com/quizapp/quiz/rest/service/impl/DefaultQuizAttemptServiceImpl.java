package com.quizapp.quiz.rest.service.impl;

import com.quizapp.quiz.core.builders.QuizRecordBuilder;
import com.quizapp.quiz.core.exception.NoQuizAttemptsFoundException;
import com.quizapp.quiz.core.utils.Constants;
import com.quizapp.quiz.core.utils.PaginationPayloadUtility;
import com.quizapp.quiz.model.entity.QuizAttempt;
import com.quizapp.quiz.model.payload.base.BaseResponse;
import com.quizapp.quiz.model.payload.pagination.PagedResponse;
import com.quizapp.quiz.model.payload.quiz.QuizAttemptResponse;
import com.quizapp.quiz.model.repository.QuizAttemptRepository;
import com.quizapp.quiz.rest.service.QuizAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DefaultQuizAttemptServiceImpl implements QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;

    @Override
    public BaseResponse<?> fetchUserQuizAttempts(Long userId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
        Page<QuizAttempt> quizAttemptsPage = quizAttemptRepository.findQuizAttemptsByUserId(userId, pageable);
        if (quizAttemptsPage.isEmpty()) {
            throw new NoQuizAttemptsFoundException("No quiz attempts found for user");
        }
        Page<QuizAttemptResponse> quizAttemptResponsePagedResponse = quizAttemptsPage.map(QuizRecordBuilder::resolveQuizAttemptToQuizAttemptResponse);
        PagedResponse<QuizAttemptResponse> pagedResponsePayload = PaginationPayloadUtility.resolvePaginationMetaData(quizAttemptResponsePagedResponse,
                pageNumber, pageSize, Constants.SUCCESS_CODE, HttpStatus.OK.value(), "User quiz attempts data fetched successfully");
        return BaseResponse.builder().responseCode(Constants.SUCCESS_CODE).responseMessage("User quiz attempts data fetched successfully").payload(pagedResponsePayload)
                .timeStamp(LocalDateTime.now()).build();
    }
}
