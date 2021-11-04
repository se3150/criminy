package com.example.androidtestsubject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import java.util.*

private const val TAG = "CrimeActivity"

class CrimeActivity : AppCompatActivity() {

    private lateinit var crime: Crime

    private lateinit var titleField: EditText
    private lateinit var dateTextView: TextView
    private lateinit var solvedCheckBox: CheckBox
    private lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)

        val crimeId = intent.getSerializableExtra("crimeId") as UUID
        crime = CrimeStore.findCrime(crimeId) ?: Crime()
        Log.d(TAG, "Hello: ${crime.title}")

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int ){
                // This space intentionally left blank
            }
            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int ){
                crime.title = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {
                // This one too
            }
        }

        titleField = findViewById(R.id.crime_title) as EditText
        titleField.addTextChangedListener(titleWatcher)

        dateTextView = findViewById(R.id.crime_date) as TextView
        dateTextView.text = crime.date.toString()

        solvedCheckBox = findViewById(R.id.crime_solved) as CheckBox
        solvedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }

        deleteButton = findViewById(R.id.delete_crime) as Button
        deleteButton.setOnClickListener {
            CrimeStore.deleteCrime(crime.id)
            finish()
        }

        updateUI()
    }

    private fun updateUI() {
        titleField.setText(crime.title)
        dateTextView.text = crime.date.toString()
        solvedCheckBox.isChecked = crime.isSolved
    }
}