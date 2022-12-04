package com.example.kotlin1.activities.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin1.R
import java.util.concurrent.atomic.AtomicBoolean

class SecondTaskFragment : Fragment() {

    private var flag1: Int = 0
    private var flag2: Int = 0
    private lateinit var firstThreadTextOutput: TextView
    private lateinit var secondThreadTextOutput: TextView
    private lateinit var runButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var handlerFirstThread: Handler
    private lateinit var handlerSecondThread: Handler
    private lateinit var increaseFirstThreadSpeed: ImageButton
    private lateinit var decreaseFirstThreadSpeed: ImageButton
    private lateinit var increaseSecondThreadSpeed: ImageButton
    private lateinit var decreaseSecondThreadSpeed: ImageButton

    private var firstNumber: Int = 0
    private var secondNumber: Int = 0

    private lateinit var firstThread: Thread
    private lateinit var secondThread: Thread

    private lateinit var controlThreads: AtomicBoolean
    private var threadsLaunched: Boolean = false

    @Volatile private var defaultFirstTSpeed: Long = 1000
    @Volatile private var defaultSecondTSpeed: Long = 5000

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second_task, container, false)

        firstThreadTextOutput = view.findViewById(R.id.thread1TextView)
        secondThreadTextOutput = view.findViewById(R.id.thread2TextView)
        runButton = view.findViewById(R.id.run_button)
        stopButton = view.findViewById(R.id.stop_button)
        resetButton = view.findViewById(R.id.reset_button)
        increaseFirstThreadSpeed = view.findViewById(R.id.increaseFirstThreadSpeed)
        decreaseFirstThreadSpeed = view.findViewById(R.id.decreaseFirstThreadSpeed)
        increaseSecondThreadSpeed = view.findViewById(R.id.increaseSecondThreadSpeed)
        decreaseSecondThreadSpeed = view.findViewById(R.id.decreaseSecondThreadSpeed)

        //restore previous value
        firstThreadTextOutput.text = firstNumber.toString()
        secondThreadTextOutput.text = secondNumber.toString()

        //init atomic boolean
        controlThreads = AtomicBoolean()

        handlerFirstThread = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                if (controlThreads.get()) {
                    firstThreadTextOutput.text = firstNumber.toString()
                }
            }
        }

        handlerSecondThread = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                if (controlThreads.get()) {
                    secondThreadTextOutput.text = secondNumber.toString()
                }
            }
        }

        runButton.setOnClickListener {
            if(flag1 == 0 || flag1 == -1){
                controlThreads.set(true)
                flag1 = -2
                flag2 = -2
                if (!threadsLaunched) {
                    launchThreads()
                    threadsLaunched = true
                }
            }
        }

        stopButton.setOnClickListener {
            if(threadsLaunched){
                controlThreads.set(false)
                flag1 = -1
                flag2 = -1
            }
        }

        resetButton.setOnClickListener {
            if(threadsLaunched){
                controlThreads.set(false)

                flag1 = -1
                flag2 = -1
                firstNumber = 0
                secondNumber = 0
                firstThreadTextOutput.text = firstNumber.toString()
                secondThreadTextOutput.text = secondNumber.toString()
            }
        }

        increaseFirstThreadSpeed.setOnClickListener {
            var temp = defaultFirstTSpeed
            temp += 5000
            defaultFirstTSpeed = temp
        }

        decreaseFirstThreadSpeed.setOnClickListener {
            var temp = defaultFirstTSpeed
            if((temp - 5000) > 0){
                temp -= 5000
            }
            defaultFirstTSpeed = temp
        }

        increaseSecondThreadSpeed.setOnClickListener {
            var temp = defaultFirstTSpeed
            temp += 5000
            defaultFirstTSpeed = temp
        }

        decreaseSecondThreadSpeed.setOnClickListener {
            var temp = defaultFirstTSpeed
            if((temp - 5000) > 0){
                temp -= 5000
            }
            defaultFirstTSpeed = temp
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    private fun launchThreads() {
        firstThread = Thread(this::firstThreadIncrementation)
        firstThread.start()

        secondThread = Thread(this::secondThreadIncrementation)
        secondThread.start()
    }

    private fun firstThreadIncrementation() {
        while (true) {
            if (flag1 == -2 || flag1 == 0)
                flag1 = 1
            if (controlThreads.get() && flag1 == 1) {
                try {
                    Thread.sleep(defaultFirstTSpeed)
                } catch (e: InterruptedException) {
                    println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
                    break;
                }
                //println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
                if (controlThreads.get() && flag1 == 1) {
                    handlerFirstThread.sendEmptyMessage(1)
                    firstNumber++
                }
            }
        }
    }

    private fun secondThreadIncrementation() {
        while (true) {
            if (flag2 == -2 || flag2 == 0)
                flag2 = 1
            if (controlThreads.get() && flag2 == 1) {
                try {
                    Thread.sleep(defaultSecondTSpeed)
                } catch (e: InterruptedException) {
                    println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
                    break;
                }
                //println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
                if (controlThreads.get() && flag2 == 1) {
                    handlerSecondThread.sendEmptyMessage(1)
                    secondNumber++
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()

        controlThreads.set(false)
        flag1 = -1
        flag2 = -1

        firstThread.interrupt()
        secondThread.interrupt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}