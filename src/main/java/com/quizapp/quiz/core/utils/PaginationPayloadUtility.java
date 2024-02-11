package com.quizapp.quiz.core.utils;

import com.quizapp.quiz.model.payload.pagination.PagedResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class PaginationPayloadUtility {

    /**
     * Resolves pagination metadata for a paginated response.
     *
     * @param page         The Page object containing the data.
     * @param pageNumber   The current page number.
     * @param pageSize     The number of elements on each page.
     * @param status       The status of the response.
     * @param statusCode   The HTTP status code of the response.
     * @param message      A descriptive message for the response.
     * @param <T>          The type of data in the Page.
     * @return             A PagedResponse with metadata and data.
     */
    public static <T> PagedResponse<T> resolvePaginationMetaData(Page<T> page, int pageNumber, int pageSize, String status, int statusCode, String message) {
        PagedResponse.PagedData<T> pagedData = new PagedResponse.PagedData<>(page.getContent(),
                new PagedResponse.PageMetadata(pageNumber, pageSize, page.getTotalPages(), page.getTotalElements()));
        return new PagedResponse<>(status, statusCode, message, LocalDateTime.now(), pagedData);
    }
}
