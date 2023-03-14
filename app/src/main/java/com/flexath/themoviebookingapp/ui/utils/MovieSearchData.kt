package com.flexath.themoviebookingapp.ui.utils

import com.flexath.themoviebookingapp.data.vos.movie.MovieVO

data class MovieSearchData (
    var movieList:List<MovieVO>
) : java.io.Serializable