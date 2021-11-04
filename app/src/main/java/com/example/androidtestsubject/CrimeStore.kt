package com.example.androidtestsubject

import android.util.Log
import java.util.*

object CrimeStore {

    val crimes = mutableListOf<Crime>()

    fun loadCrimes() {
        crimes.clear()
        for (i in 1 until 4) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            crimes += crime
        }
    }

    fun addCrime() : Crime {
        val crime = Crime()
        crimes += crime
        return crime
    }

    fun deleteCrime(id: UUID) : Boolean {
        for ((index, crime) in CrimeStore.crimes.withIndex()) {
            if (crime.id == id) {
                CrimeStore.crimes.removeAt(index)
                return true
            }
        }
        return false
    }

    fun findCrime(id: UUID) : Crime? {
        for (crime in CrimeStore.crimes) {
            if (crime.id == id) {
                return crime
            }
        }
        return null
    }
}