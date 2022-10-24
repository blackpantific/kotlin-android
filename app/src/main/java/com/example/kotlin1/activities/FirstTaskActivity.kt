package com.example.kotlin1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import com.example.kotlin1.CarAdapter
import com.example.kotlin1.R
import com.example.kotlin1.models.Auto
import com.example.kotlin1.models.CarIcon
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial

private const val TAG = "FirstTaskActivity"

class FirstTaskActivity : AppCompatActivity() {

    private lateinit var fabButton: FloatingActionButton
    private lateinit var textOutput: TextView
    private lateinit var textInput: EditText
    private lateinit var listView: ListView
    private lateinit var hideListButton: Button
    private lateinit var showToastButton: Button
    private lateinit var switch: SwitchMaterial
    private lateinit var labelColorChange: TextView

    private var listOfCars: List<Auto> = mutableListOf(
        Auto("Lamborghini", "Automobili Lamborghini S.p.A. is an Italian brand and manufacturer of luxury sports cars and SUVs based in Sant'Agata Bolognese. The company is owned by the Volkswagen Group through its subsidiary Audi.", CarIcon.CAR1),
        Auto("Audi", "Audi AG Aktiengesellschaft, commonly referred to as Audi, is a German automotive manufacturer of luxury vehicles headquartered in Ingolstadt, Bavaria, Germany. As a subsidiary of its parent company, the Volkswagen Group, Audi produces vehicles in nine production facilities worldwide.", CarIcon.CAR2),
        Auto("BMW", "Bayerische Motoren Werke AG, abbreviated as BMW, is a German multinational manufacturer of performance luxury vehicles and motorcycles headquartered in Munich, Bavaria, Germany. ", CarIcon.CAR3),
        Auto("Bugatti", "Automobiles Ettore Bugatti was a German then French manufacturer of high-performance automobiles. The company was founded in 1909 in the then-German city of Molsheim, Alsace, by the Italian-born industrial designer Ettore Bugatti.", CarIcon.CAR4),
        Auto("Mercedes", "Mercedes-Benz,[6][7] commonly referred to as Mercedes and sometimes as Benz, is a German luxury and commercial vehicle automotive brand established in 1926. ", CarIcon.CAR1),
        Auto("Bentley", "Bentley Motors Limited is a British designer, manufacturer and marketer of luxury cars and SUVs. Headquartered in Crewe, England, the company was founded as Bentley Motors Limited by W. O. Bentley (1888–1971) in 1919 in Cricklewood, North London, and became widely known for winning the 24 Hours of Le Mans in 1924, 1927, 1928, 1929 and 1930.", CarIcon.CAR2),
        Auto("Ford", "Ford Motor Company (commonly known as Ford) is an American multinational automobile manufacturer headquartered in Dearborn, Michigan, United States. It was founded by Henry Ford and incorporated on June 16, 1903.", CarIcon.CAR3),
        Auto("Pagani", "Pagani Automobili S.p.A. (commonly referred to as Pagani) is an Italian manufacturer of hypercars and carbon fiber components. The company was founded in 1992 by the Argentinian Horacio Pagani and is based in San Cesario sul Panaro, near Modena, Italy.", CarIcon.CAR4),
        Auto("Ferrari", "Ferrari S.p.A. is an Italian luxury sports car manufacturer based in Maranello, Italy. Founded by Enzo Ferrari (1898–1988) in 1939 from the Alfa Romeo racing division as Auto Avio Costruzioni, the company built its first car in 1940, and produced its first Ferrari-badged car in 1947.", CarIcon.CAR1),
        Auto("Tesla", "Tesla, Inc. is an American multinational automotive and clean energy company headquartered in Austin, Texas.", CarIcon.CAR2),
        Auto("McLaren", "McLaren Automotive (formerly known as McLaren Cars) is a British luxury automotive manufacturer based at the McLaren Technology Centre in Woking, England. The main products of the company are supercars, which are produced in-house in designated production facilities. In July 2017, McLaren Automotive became a wholly owned subsidiary of the wider McLaren Group.", CarIcon.CAR3),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_task)

        supportActionBar!!.setBackgroundDrawable(
            AppCompatResources.getDrawable(this, R.color.teal_200)
        )

        fabButton = findViewById(R.id.fab)
        textOutput = findViewById(R.id.text_output)
        textInput = findViewById(R.id.text_input)
        listView = findViewById(R.id.list_view)
        switch = findViewById(R.id.switch_for_label)
        hideListButton = findViewById(R.id.hide_list_button)
        showToastButton = findViewById(R.id.invoke_toast)
        labelColorChange = findViewById(R.id.label_color_to_change)

        fabButton.setOnClickListener {
            textOutput.text = textInput.text
        }

        switch.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                labelColorChange.setTextColor(resources.getColor(R.color.purple_500, this.theme))
            }else{
                labelColorChange.setTextColor(resources.getColor(R.color.black, this.theme))
            }
        }

        hideListButton.setOnClickListener {
            listView.isVisible = !listView.isVisible
            if (listView.isVisible) {
                Log.d(TAG, "ListView is visible")
            } else {
                Log.d(TAG, "ListView is not visible")
            }
        }

        showToastButton.setOnClickListener {
            Toast.makeText(
                this,
                R.string.toast_text,
                Toast.LENGTH_SHORT
            )
                .show()
            Log.d(TAG, "Toast called")
        }

        listView.adapter =
            CarAdapter(this, android.R.layout.simple_list_item_1, listOfCars)

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id -> // получаем выбранный пункт
                val selectedAuto = parent.getItemAtPosition(position) as Auto
                val intent = FirstTaskDetailsActivity.newIntent(this, selectedAuto)
                startActivity(intent)
            }
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, FirstTaskActivity::class.java)
        }
    }
}