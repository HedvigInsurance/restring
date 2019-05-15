package com.ice.restring

import com.ice.restring.shadow.MyShadowAsyncTask
import com.ice.restring.util.javaClass

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

import java.util.Arrays
import java.util.HashMap

import org.junit.Assert.assertEquals
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


@RunWith(RobolectricTestRunner::class)
@Config(shadows = [MyShadowAsyncTask::class])
class StringsLoaderTaskTest {

    @Before
    fun setUp() {
    }

    @Test
    fun shouldLoadStringsAndSaveInRepository() {
        val langs = Arrays.asList("en", "fa")
        val enStrings = HashMap<String, String?>()
        enStrings["string1"] = "value1"
        enStrings["string2"] = "value2"
        val deStrings = HashMap<String, String?>()
        deStrings["string3"] = "value3"
        deStrings["string4"] = "value4"

        val loader = Mockito.mock(Restring.StringsLoader::class.java)
        `when`(loader.languages).thenReturn(langs)
        `when`(loader.getStrings("en")).thenReturn(enStrings)
        `when`(loader.getStrings("fa")).thenReturn(deStrings)

        val repository = Mockito.mock(StringRepository::class.java)

        val task = StringsLoaderTask(loader, repository)
        task.run()

        Robolectric.flushBackgroundThreadScheduler()
        Robolectric.flushForegroundThreadScheduler()

        val enCaptor: ArgumentCaptor<MutableMap<String, String?>> = ArgumentCaptor.forClass(javaClass<MutableMap<String, String?>>())
        verify(repository).setStrings(eq("en"), enCaptor.capture())
        assertEquals(enStrings, enCaptor.getValue())

        val deCaptor = ArgumentCaptor.forClass(javaClass<MutableMap<String, String?>>())
        verify(repository).setStrings(eq("fa"), deCaptor.capture())
        assertEquals(deStrings, deCaptor.value)
    }
}