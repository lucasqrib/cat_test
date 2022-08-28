package com.lucasqrib.cats.data.repository

import com.lucasqrib.cats.data.datasource.CatDatasource
import com.lucasqrib.cats.data.dto.CatImageNetworkDTO
import com.lucasqrib.cats.data.dto.CatNetworkDTO
import com.lucasqrib.cats.data.dto.WeightNetworkDTO
import com.lucasqrib.cats.domain.model.Cat
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class CatsRepositoryImplTest : TestCase() {

    private val testCoroutineScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testCoroutineScheduler)

    private val testScope = TestScope(testDispatcher)

    @Test
    fun testSuccessCall() = testScope.runTest {
        val mockDataSource = mock<CatDatasource>()
        val repository = CatsRepositoryImpl(mockDataSource, testDispatcher)
        whenever(
            mockDataSource.getCatList(
                Mockito.anyInt(),
                Mockito.anyInt(),
                eq("ASC")
            )
        ).thenReturn(
            createSuccessResponse()
        )
        val result = repository.getCatList(0)
        assertTrue("Result is success", result.isSuccess)
        assertTrue("There's one item on list", result.getOrNull()?.size == 1)
        assertTrue(
            "The mapper did not changed any property",
            validateMapper(getCompleteDTO(), result.getOrNull()?.first())
        )
    }


    @Test
    fun testErrorCall() = testScope.runTest {
        val mockDataSource = mock<CatDatasource>()
        val repository = CatsRepositoryImpl(mockDataSource, testDispatcher)
        whenever(
            mockDataSource.getCatList(
                Mockito.anyInt(),
                Mockito.anyInt(),
                eq("ASC")
            )
        ).thenReturn(
            createErrorResponse()
        )
        val result = repository.getCatList(0)
        assertTrue("Result is error", result.isFailure)
    }


    private fun verifyIntAsBoolean(int: Int?, bool: Boolean?): Boolean {
        if (bool == null) return int == null
        return int == if (bool) 1 else 0
    }


    private fun validateMapper(dto: CatNetworkDTO?, cat: Cat?): Boolean {
        if (cat == null) return dto == null
        return dto?.run {
            adaptability == cat.adaptability &&
                    wikipediaUrl == cat.wikipediaUrl &&
                    weight.imperial == cat.weight.imperial &&
                    weight.metric == cat.weight.metric &&
                    vocalisation == cat.vocalisation &&
                    temperament == cat.temperament &&
                    strangerFriendly == cat.strangerFriendly &&
                    socialNeeds == cat.socialNeeds &&
                    sheddingLevel == cat.sheddingLevel &&
                    verifyIntAsBoolean(dto.rex, cat.rex) &&
                    verifyIntAsBoolean(dto.rare, cat.rare) &&
                    origin == cat.origin &&
                    verifyIntAsBoolean(dto.natural, cat.natural) &&
                    name == cat.name &&
                    lifeSpan == cat.lifeSpan &&
                    verifyIntAsBoolean(dto.lap, cat.lap) &&
                    intelligence == cat.intelligence &&
                    verifyIntAsBoolean(indoor, cat.indoor) &&
                    id == cat.id &&
                    image?.id == cat.image?.id &&
                    image?.height == cat.image?.height &&
                    image?.width == cat.image?.width &&
                    image?.url == cat.image?.url &&
                    verifyIntAsBoolean(hypoallergenic, cat.hypoallergenic) &&
                    healthIssues == cat.healthIssues &&
                    verifyIntAsBoolean(hairless, cat.hairless) &&
                    grooming == cat.grooming &&
                    verifyIntAsBoolean(experimental, cat.experimental) &&
                    energyLevel == cat.energyLevel &&
                    dogFriendly == cat.dogFriendly &&
                    description == cat.description &&
                    countryCodes == cat.countryCodes &&
                    countryCode == cat.countryCode &&
                    childFriendly == cat.childFriendly &&
                    cfaUrl == cat.cfaUrl &&
                    catFriendly == cat.catFriendly &&
                    bidability == cat.bidability &&
                    affectionLevel == cat.affectionLevel &&
                    referenceImageId == cat.imageId &&
                    verifyIntAsBoolean(shortLegs, cat.shortLegs) &&
                    verifyIntAsBoolean(suppressedTail, cat.suppressedTail) &&
                    vcahospitalsUrl == cat.vcaHospitals &&
                    vetstreetUrl == cat.vetStreetUrl
        } ?: false

    }


    private fun getCompleteDTO() = CatNetworkDTO(
        adaptability = 1,
        wikipediaUrl = "a",
        weight = WeightNetworkDTO("a", "a"),
        vocalisation = 1,
        temperament = "a",
        strangerFriendly = 1,
        socialNeeds = 1,
        sheddingLevel = 1,
        rex = 1,
        rare = 1,
        origin = "a",
        natural = 1,
        name = "a",
        lifeSpan = "a",
        lap = 1,
        intelligence = 1,
        indoor = 1,
        id = "a",
        image = CatImageNetworkDTO("a", 1, 1, "a"),
        hypoallergenic = 1,
        healthIssues = 1,
        hairless = 1,
        grooming = 1,
        experimental = 1,
        energyLevel = 1,
        dogFriendly = 1,
        description = "a",
        countryCodes = "a",
        countryCode = "a",
        childFriendly = 1,
        cfaUrl = "a",
        catFriendly = 1,
        bidability = 1,
        affectionLevel = 1,
        referenceImageId = "a",
        shortLegs = 1,
        suppressedTail = 1,
        vcahospitalsUrl = "a",
        vetstreetUrl = "a"
    )

    private fun createSuccessResponse(): Response<List<CatNetworkDTO>> {
        return Response.success(
            listOf(
                getCompleteDTO()
            )
        )
    }

    private fun createErrorResponse(): Response<List<CatNetworkDTO>> {
        return Response.error(500, EMPTY_RESPONSE)
    }
}