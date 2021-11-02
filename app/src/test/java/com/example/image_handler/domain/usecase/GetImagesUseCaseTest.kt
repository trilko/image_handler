package com.example.image_handler.domain.usecase

import com.example.image_handler.domain.model.ImageDomain
import com.example.image_handler.domain.model.SortedOrder.*
import com.example.image_handler.domain.repository.Repository
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetImagesUseCaseTest {

    @Mock
    private lateinit var repository: Repository
    private lateinit var getImagesUseCase: GetImagesUseCase

    companion object {
        private val RIGHT_ORDER_NO_SORTED = listOf("1", "2", "3", "4", "5")
        private val RIGHT_ORDER_SORTED_BY_TAKEN_DATE = listOf("2", "1", "3", "4", "5")
        private val RIGHT_ORDER_SORTED_BY_PUBLISHED_DATE = listOf("5", "4", "3", "2", "1")
    }

    private val testData = listOf(
        ImageDomain(title = "1", content = "", dateTaken = "2021-11-10T11:54:47-08:00", datePublished = "2021-11-10T12:55:48-08:00"),
        ImageDomain(title = "2", content = "", dateTaken = "2021-10-10T11:54:47-08:00", datePublished = "2021-11-10T12:55:47-08:00"),
        ImageDomain(title = "3", content = "", dateTaken = "2021-11-10T12:54:47-08:00", datePublished = "2021-11-10T12:54:47-08:00"),
        ImageDomain(title = "4", content = "", dateTaken = "2021-11-10T12:55:47-08:00", datePublished = "2021-11-10T11:54:47-08:00"),
        ImageDomain(title = "5", content = "", dateTaken = "2021-11-10T12:55:48-08:00", datePublished = "2021-10-10T11:54:47-08:00")
    )

    @Before
    fun tune() {
        repository = Mockito.mock(Repository::class.java)
        Mockito.`when`(repository.getImages()).thenReturn(Single.just(testData))
        getImagesUseCase = GetImagesUseCase(repository)
    }

    @Test
    fun getNoSortedImagesTest() {
        val expected = RIGHT_ORDER_NO_SORTED
        val actual: List<String> = getImagesUseCase.execute(NO_SORTED).blockingGet().map { it.title }

        assertEquals(expected, actual)
    }

    @Test
    fun getSortedByPublishedDate() {
        val expected = RIGHT_ORDER_SORTED_BY_PUBLISHED_DATE
        val actual: List<String> = getImagesUseCase.execute(PUBLISH_DATE).blockingGet().map { it.title }

        assertEquals(expected, actual)
    }

    @Test
    fun getSortedByTakenDate() {
        val expected = RIGHT_ORDER_SORTED_BY_TAKEN_DATE
        val actual: List<String> = getImagesUseCase.execute(TAKEN_DATE).blockingGet().map { it.title }

        assertEquals(expected, actual)
    }


}