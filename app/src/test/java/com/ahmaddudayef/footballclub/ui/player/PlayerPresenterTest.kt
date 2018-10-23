package com.ahmaddudayef.footballclub.ui.player

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.data.network.model.player.Players
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import com.ahmaddudayef.footballclub.utils.rx.TestSchedulerProvider
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.experimental.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
class PlayerPresenterTest {
    @Mock
    private lateinit var view: PlayerMvpView
    @Mock
    private lateinit var dataManager: DataManager

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var presenter: PlayerPresenter<PlayerMvpView>

    private lateinit var scheduler: SchedulerProvider

    lateinit var players: Flowable<Players>
    private lateinit var playerItem: Players
    private val listPlayers = mutableListOf<Player>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        compositeDisposable = CompositeDisposable()
        scheduler = TestSchedulerProvider()
        playerItem = Players(listPlayers)
        players = Flowable.just(playerItem)
        presenter = PlayerPresenter(dataManager, compositeDisposable, scheduler)
        `when`(dataManager.getAllPlayers("133604")).thenReturn(players)
        presenter.onAttach(view)
    }

    @Test
    fun getPlayerList() {
        presenter.getPlayerList("133604")
        launch { verify(view).showLoading() }
        launch { verify(view).updateListPlayer(playerItem.players) }
        launch { verify(view).hideLoading() }
    }
}