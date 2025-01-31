package com.example.weatherapp.presentation.ui.search

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.weatherapp.presentation.ui.base.BaseViewModel
import com.example.weatherapp.data.entity.CoordEntity
import com.example.weatherapp.domain.usecase.SearchCitiesUseCase
import com.example.weatherapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject internal constructor(
    private val useCase: SearchCitiesUseCase,
    private val pref: SharedPreferences
) : BaseViewModel() {

    private val _searchParams: MutableLiveData<SearchCitiesUseCase.SearchCitiesParams> = MutableLiveData()
    fun getSearchViewState() = searchViewState

    private val searchViewState: LiveData<SearchViewState> = _searchParams.switchMap { params ->
        useCase.execute(params)
    }

    fun setSearchParams(params: SearchCitiesUseCase.SearchCitiesParams) {
        if (_searchParams.value == params) {
            return
        }
        _searchParams.postValue(params)
    }

    fun saveCoordsToSharedPref(coordEntity: CoordEntity): Single<String>? {
        return Single.create<String> {
            pref.edit().putString(Constants.Coords.LAT, coordEntity.lat.toString()).apply()
            pref.edit().putString(Constants.Coords.LON, coordEntity.lon.toString()).apply()
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
