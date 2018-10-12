package com.ahmaddudayef.footballclub.ui.nextmatch

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.utils.rx.TestSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import kotlinx.coroutines.experimental.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Ahmad Dudayef on 10/12/2018.
 */
class NextPresenterTest {
    @Mock
    private lateinit var view: NextMvpView
    @Mock
    private lateinit var dataManager: DataManager

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var presenter: NextPresenter<NextMvpView>

    private lateinit var testScheduler: TestScheduler

    private lateinit var testSchedulerProvider: TestSchedulerProvider
    @Mock
    private lateinit var eventItem: Events

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        testSchedulerProvider = TestSchedulerProvider(testScheduler)
        presenter = NextPresenter(dataManager, compositeDisposable, testSchedulerProvider)
        presenter.onAttach(view)
    }

    @Test
    fun getNextScheduleList() {
        launch { verify(view).showLoading() }
        presenter.getNextScheduleList("4328")
        launch { verify(view)?.updateList(eventItem.events!!) }
        launch { verify(view)?.hideLoading() }
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.onDetach()
    }
}