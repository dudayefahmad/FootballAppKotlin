package com.ahmaddudayef.footballclub.ui.nextmatch

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.utils.rx.AppSchedulerProvider
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import com.ahmaddudayef.footballclub.utils.rx.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import kotlinx.coroutines.experimental.launch
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
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

    private lateinit var scheduler: SchedulerProvider

    lateinit var match: Observable<Events>

    private lateinit var eventItem: Events

    private val event = mutableListOf<EventsItem>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        compositeDisposable = CompositeDisposable()
        scheduler = TestSchedulerProvider()
        eventItem = Events(event)
        match = Observable.just(eventItem)
        presenter = NextPresenter(dataManager, compositeDisposable, scheduler)
        `when`(dataManager.getNextSchedule("4328")).thenReturn(match)
//        presenter.onAttach(view)
    }

    @Test
    fun getNextScheduleList() {
        presenter.getNextScheduleList("4328")
        launch { verify(view).showLoading() }
        launch { verify(view).updateList(eventItem.events!!) }
        launch { verify(view).hideLoading() }
    }

//    @After
//    @Throws(Exception::class)
//    fun tearDown() {
//        presenter.onDetach()
//    }
}