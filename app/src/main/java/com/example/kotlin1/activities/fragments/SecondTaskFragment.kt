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
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin1.R
import java.util.concurrent.atomic.AtomicBoolean

class SecondTaskFragment : Fragment() {

    private lateinit var firstThreadTextOutput: TextView
    private lateinit var secondThreadTextOutput: TextView
    private lateinit var runButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var handlerFirstThread: Handler
    private lateinit var handlerSecondThread: Handler

    private var firstNumber: Int = 0
    private var secondNumber: Int = 0

    private lateinit var firstThread: Thread
    private lateinit var secondThread: Thread

    private lateinit var controlThreads: AtomicBoolean
    private var threadsLaunched: Boolean = false

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

        //restore previous value
        firstThreadTextOutput.text = firstNumber.toString()
        secondThreadTextOutput.text = secondNumber.toString()

        //init atomic boolean
        controlThreads = AtomicBoolean()

        handlerFirstThread = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                firstThreadTextOutput.text = firstNumber.toString()
            }
        }

        handlerSecondThread = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                secondThreadTextOutput.text = secondNumber.toString()
            }
        }

        runButton.setOnClickListener {
            controlThreads.set(true)
            if(!threadsLaunched){
                launchThreads()
                threadsLaunched = true
            }
        }

        stopButton.setOnClickListener {
            controlThreads.set(false)
        }

        resetButton.setOnClickListener {
            controlThreads.set(false)

            firstNumber = 0
            secondNumber = 0
            firstThreadTextOutput.text = firstNumber.toString()
            secondThreadTextOutput.text = secondNumber.toString()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    private fun launchThreads(){
        firstThread = Thread(this::firstThreadIncrementation)
        firstThread.start()

        secondThread = Thread(this::secondThreadIncrementation)
        secondThread.start()
    }

    private fun firstThreadIncrementation() {

        while (true) {
            if(controlThreads.get()) {
                Thread.sleep(1000)
                //println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
                handlerFirstThread.sendEmptyMessage(1)
                firstNumber++
            }else{
                Thread.sleep(5000)
                println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
            }
        }
    }

    private fun secondThreadIncrementation() {
        while (true) {
            if(controlThreads.get()) {
                Thread.sleep(5000)
                //println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
                handlerSecondThread.sendEmptyMessage(1)
                secondNumber++
            }else{
                Thread.sleep(5000)
                println("CURRENT THREAD NAME IS " + Thread.currentThread().name)
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