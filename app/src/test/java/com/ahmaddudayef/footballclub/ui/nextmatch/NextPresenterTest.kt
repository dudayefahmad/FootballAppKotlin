package com.ahmaddudayef.footballclub.ui.nextmatch

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.utils.rx.TestSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
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
@RunWith(MockitoJUnitRunner::class)
class NextPresenterTest {
    @Mock
    private lateinit var view: NextMvpView
    @Mock
    private lateinit var dataManager: DataManager
    @Mock
    private lateinit var compositeDisposable: CompositeDisposable
    @Mock
    private lateinit var presenter: NextPresenter<NextMvpView>
    @Mock
    private lateinit var testScheduler: TestScheduler
    @Mock
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
        verify(view)?.showLoading()
        presenter.getNextScheduleList("4328")

        verify(view)?.updateList(eventItem.events!!)
        verify(view)?.hideLoading()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.onDetach()
    }
}