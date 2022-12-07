package ipca.example.semaphore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonPassar = findViewById<Button>(R.id.buttonPassar)
        var semaphoreView = findViewById<SemaphoreView>(R.id.semaphoreView)

        buttonPassar.setOnClickListener {
            semaphoreView.goToRed()

        }

        semaphoreView.setOnSemaphoreStateChanged { state ->
            when(state){
                SemaphoreView.SemaphoreState.GREEN ->{
                    buttonPassar.isEnabled = true
                }
                SemaphoreView.SemaphoreState.YELLOW,
                SemaphoreView.SemaphoreState.RED -> {
                    buttonPassar.isEnabled = false
                }
            }
        }


    }
}