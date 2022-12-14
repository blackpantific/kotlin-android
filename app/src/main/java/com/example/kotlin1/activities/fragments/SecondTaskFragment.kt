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

enum class ThreadCondition {
    CREATED,
    STARTED,
    SUSPENDED,
    PERFORMED
}

class SecondTaskFragment : Fragment() {

    private var FirstThreadCondition: ThreadCondition = ThreadCondition.CREATED
    private var SecondThreadCondition: ThreadCondition = ThreadCondition.CREATED
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

    @Volatile
    private var defaultFirstTSpeed: Long = 1000

    @Volatile
    private var defaultSecondTSpeed: Long = 5000

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView")
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
            if (FirstThreadCondition == ThreadCondition.CREATED || FirstThreadCondition == ThreadCondition.SUSPENDED) {
                controlThreads.set(true)
                FirstThreadCondition = ThreadCondition.STARTED
                SecondThreadCondition = ThreadCondition.STARTED

                if (!threadsLaunched) {
                    launchThreads()
                    threadsLaunched = true
                }
            }
        }

        stopButton.setOnClickListener {
            if (threadsLaunched) {

                suspendThreads()
            }
        }

        resetButton.setOnClickListener {
            if (threadsLaunched) {
                controlThreads.set(false)

                FirstThreadCondition = ThreadCondition.SUSPENDED
                SecondThreadCondition = ThreadCondition.SUSPENDED
                firstNumber = 0
                secondNumber = 0
                firstThreadTextOutput.text = firstNumber.toString()
                secondThreadTextOutput.text = secondNumber.toString()
            }
        }

        increaseFirstThreadSpeed.setOnClickListener {
            var temp = defaultFirstTSpeed
            temp += 1000
            defaultFirstTSpeed = temp
        }

        decreaseFirstThreadSpeed.setOnClickListener {
            var temp = defaultFirstTSpeed
            if ((temp - 1000) > 0) {
                temp -= 1000
            }
            defaultFirstTSpeed = temp
        }

        increaseSecondThreadSpeed.setOnClickListener {
            var temp = defaultSecondTSpeed
            temp += 1000
            defaultSecondTSpeed = temp
        }

        decreaseSecondThreadSpeed.setOnClickListener {
            var temp = defaultSecondTSpeed
            if ((temp - 1000) > 0) {
                temp -= 1000
            }
            defaultSecondTSpeed = temp
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        println("onStart")
    }

    private fun launchThreads() {
        firstThread = Thread(this::firstThreadIncrementation)
        firstThread.start()

        secondThread = Thread(this::secondThreadIncrementation)
        secondThread.start()
    }

    private fun firstThreadIncrementation() {
        while (true) {
            if (FirstThreadCondition == ThreadCondition.STARTED || FirstThreadCondition == ThreadCondition.CREATED)
                FirstThreadCondition = ThreadCondition.PERFORMED
            if (controlThreads.get() && FirstThreadCondition == ThreadCondition.PERFORMED) {
                try {
                    Thread.sleep(defaultFirstTSpeed)
                    println("CURRENT THREAD NAME IS " + Thread.currentThread().id)
                } catch (e: InterruptedException) {
                    //println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
                    break;
                }
                if (controlThreads.get() && FirstThreadCondition == ThreadCondition.PERFORMED) {
                    handlerFirstThread.sendEmptyMessage(1)
                    firstNumber++
                }
            }
        }
    }

    private fun secondThreadIncrementation() {
        while (true) {
            if (SecondThreadCondition == ThreadCondition.STARTED || SecondThreadCondition == ThreadCondition.CREATED)
                SecondThreadCondition = ThreadCondition.PERFORMED
            if (controlThreads.get() && SecondThreadCondition == ThreadCondition.PERFORMED) {
                try {
                    Thread.sleep(defaultSecondTSpeed)
                    println("CURRENT THREAD NAME IS " + Thread.currentThread().id)
                } catch (e: InterruptedException) {
                    //println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
                    break;
                }
                if (controlThreads.get() && SecondThreadCondition == ThreadCondition.PERFORMED) {
                    handlerSecondThread.sendEmptyMessage(1)
                    secondNumber++
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onStop() {
        super.onStop()
        println("onStop")

        suspendThreads()
    }

    private fun suspendThreads() {
        controlThreads.set(false)
        FirstThreadCondition = ThreadCondition.SUSPENDED
        SecondThreadCondition = ThreadCondition.SUSPENDED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("onDestroyView")

        if (::firstThread.isInitialized)
            firstThread.interrupt()
        if (::secondThread.isInitialized)
            secondThread.interrupt()
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        println("onDetach")
    }
}