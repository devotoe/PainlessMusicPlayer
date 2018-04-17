package com.doctoror.fuckoffmusicplayer.presentation.home

import android.content.res.Resources
import com.doctoror.commons.reactivex.SchedulersProvider
import com.doctoror.fuckoffmusicplayer.di.scopes.FragmentScope
import com.doctoror.fuckoffmusicplayer.domain.albums.AlbumsProvider
import com.doctoror.fuckoffmusicplayer.domain.queue.QueueProviderAlbums
import com.doctoror.fuckoffmusicplayer.presentation.library.LibraryPermissionsProvider
import com.doctoror.fuckoffmusicplayer.presentation.rxpermissions.RxPermissionsProvider
import dagger.Module
import dagger.Provides

@Module
class RecentActivityFragmentModule {

    @Provides
    @FragmentScope
    fun provideRecentActivityPresenter(
            albumsProvider: AlbumsProvider,
            fragment: RecentActivityFragment,
            libraryPermissionProvider: LibraryPermissionsProvider,
            queueProvider: QueueProviderAlbums,
            resources: Resources,
            schedulersProvider: SchedulersProvider,
            viewModel: RecentActivityViewModel
    ) = RecentActivityPresenter(
            albumsProvider,
            fragment,
            libraryPermissionProvider,
            queueProvider,
            resources,
            schedulersProvider,
            viewModel)

    @Provides
    @FragmentScope
    fun provideRecentActivityViewModel() = RecentActivityViewModel()

    @Provides
    @FragmentScope
    fun provideRxPermissionsProvider(fragment: RecentActivityFragment) =
            RxPermissionsProvider(fragment.requireActivity())

    @Provides
    @FragmentScope
    fun provideLibraryPermissionsProvider(
            fragment: RecentActivityFragment,
            rxPermissionsProvider: RxPermissionsProvider) =
            LibraryPermissionsProvider(fragment.requireContext(), rxPermissionsProvider)
}