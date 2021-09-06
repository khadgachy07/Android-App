package com.khadga.meromedapp

import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.model.AccountModel
import com.khadga.meromedapp.repository.ProductRepository
import com.khadga.meromedapp.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UnitTesting {
    @Test
    fun loginTestings()
    {
        runBlocking {
            var expectedResult = false
            var user  = UserRepository()
            var response = user.loginUser("rijen","123456")
            var actualResult = response.success
            Assert.assertEquals(expectedResult,actualResult)
        }


    }

    @Test
    fun registerTesting()
    {
        runBlocking {
            var expectedResult = true
            var user = AccountModel(accFN = "test test",accUN = "testTest",accEmail = "test@gmail.com",accPhone = "9801234565",accPwd = "123456",userType = "Customer")
            var repo = UserRepository()
            var actualResult = repo.registerUser(user).success
            Assert.assertEquals(expectedResult,actualResult)

        }
    }

    @Test
    fun addToCartTesting()
    {
        runBlocking {
            var expectedResult = true
            ServiceBuilder.token = "Bearer "+UserRepository().loginUser("rijen","123456").token
            var productRepo = ProductRepository()
            var response = productRepo.addToCart("607c5b5d3e3e5d0ffc8e0824")
            var actualResult = response.success
            Assert.assertEquals(expectedResult,actualResult)
        }
    }

    @Test
    fun updateCartTesting()
    {
        runBlocking {
            var expectedResult = true
            ServiceBuilder.token = "Bearer "+UserRepository().loginUser("rijen","123456").token
            var productRepo = ProductRepository()
            var response = productRepo.updateFromCart("607c8197b36c51414cfb1f7b",2)
            var actualResult = response.success
            Assert.assertEquals(expectedResult,actualResult)
        }
    }

    @Test
    fun deleteTesting()
    {
        runBlocking {
            var expectedResult = true
            ServiceBuilder.token = "Bearer "+UserRepository().loginUser("rijen","123456").token
            var productRepo = ProductRepository()
            var response = productRepo.deleteFromCart("607c8294b36c51414cfb1f7f")
            var actualResult = response.success
            Assert.assertEquals(expectedResult,actualResult)
        }
    }



    @Test
    fun loginTesting()
    {
        runBlocking {
            var expectedResult = true
            var user  = UserRepository()
            var response = user.loginUser("rijen","123456")
            var actualResult = response.success
            Assert.assertEquals(expectedResult,actualResult)
        }


    }

    @Test
    fun registerTestings()
    {
        runBlocking {
            var expectedResult = true
            var user = AccountModel(accFN = "test test",accUN = "testTest",accEmail = "test@gmail.com",accPhone = "9801234565",accPwd = "123456",userType = "Customer")
            var repo = UserRepository()
            var actualResult = repo.registerUser(user).success
            Assert.assertEquals(expectedResult,actualResult)

        }
    }

    @Test
    fun addToCartTestings()
    {
        runBlocking {
            var expectedResult = true
            ServiceBuilder.token = "Bearer "+UserRepository().loginUser("rijen","123456").token
            var productRepo = ProductRepository()
            var response = productRepo.addToCart("607c5b5d3e3e5d0ffc8e0824")
            var actualResult = response.success
            Assert.assertEquals(expectedResult,actualResult)
        }
    }

    @Test
    fun updateCartTestings()
    {
        runBlocking {
            var expectedResult = false
            ServiceBuilder.token = "Bearer "+UserRepository().loginUser("rijen","123456").token
            var productRepo = ProductRepository()
            var response = productRepo.updateFromCart("607c8197b36c51414cfb1f7b",2)
            var actualResult = response.success
            Assert.assertEquals(expectedResult,actualResult)
        }
    }

    @Test
    fun deleteTestings()
    {
        runBlocking {
            var expectedResult = true
            ServiceBuilder.token = "Bearer "+UserRepository().loginUser("rijen","123456").token
            var productRepo = ProductRepository()
            var response = productRepo.deleteFromCart("607c8294b36c51414cfb1f7f")
            var actualResult = response.success
            Assert.assertEquals(expectedResult,actualResult)
        }
    }
}