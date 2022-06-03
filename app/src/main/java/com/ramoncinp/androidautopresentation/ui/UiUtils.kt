package com.ramoncinp.androidautopresentation.ui

import androidx.navigation.NavController

fun NavController.navigateAndReplaceStartRoute(newHomeRoute: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(newHomeRoute)
    navigate(newHomeRoute)
}
