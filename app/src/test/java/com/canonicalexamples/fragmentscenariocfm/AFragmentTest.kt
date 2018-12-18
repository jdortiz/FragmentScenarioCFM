package com.canonicalexamples.fragmentscenariocfm

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.launchInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * 20181218. Initial version created by jorge
 * for a Canonical Examples training.
 *
 *
 * Copyright 2018 Jorge D. Ortiz Fuentes
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class)//, manifest=Config.NONE)
class AFragmentTest {
    // <editor-fold desc="Test variables">
    private lateinit var sut: FragmentScenario<AFragment>
    private lateinit var presenter: APresenter
    // </editor-fold>

    // <editor-fold desc="Set up & Tear down">
    @Before
    fun setUp() {
        presenter = mock()
        val app = ApplicationProvider.getApplicationContext() as TestApplication
        app.presenter = presenter
        sut = launchInContainer(AFragment::class.java)
    }
    // </editor-fold>

    @Test
    fun presenterSurvivesConfigurationChanges() {
        sut.recreate()
        sut.onFragment {
            assertSame(this@AFragmentTest.presenter, it.presenter)
        }
    }
}
