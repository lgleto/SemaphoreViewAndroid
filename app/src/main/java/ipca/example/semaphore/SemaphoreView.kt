package ipca.example.semaphore

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SemaphoreView : View {

    enum class SemaphoreState{
        RED, YELLOW, GREEN
    }

    var state = SemaphoreState.GREEN

    fun goToRed(){
        GlobalScope.launch {
            state = SemaphoreState.YELLOW
            GlobalScope.launch (Dispatchers.Main){
                onSemaphoreStateChanged?.invoke(state)
            }
            invalidate()
            delay(1000)
            state = SemaphoreState.RED
            GlobalScope.launch (Dispatchers.Main){
                onSemaphoreStateChanged?.invoke(state)
            }
            invalidate()
            delay(5000)
            state = SemaphoreState.GREEN
            GlobalScope.launch (Dispatchers.Main){
                onSemaphoreStateChanged?.invoke(state)
            }
            invalidate()
        }
    }

    private var onSemaphoreStateChanged : ((SemaphoreState) ->Unit)? = null
    fun setOnSemaphoreStateChanged(onSemaphoreStateChanged : (SemaphoreState) ->Unit) {
        this.onSemaphoreStateChanged = onSemaphoreStateChanged
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 40f
        paint.style = Style.STROKE

        canvas?.drawRect(Rect(0,0,width,height),paint)

        paint.color = Color.YELLOW
        paint.style = Style.STROKE

        canvas?.drawCircle(width/2f, height/2f,width * 0.2f,paint )

        paint.color = Color.RED
        paint.style = Style.STROKE

        canvas?.drawCircle(width/2f, height*0.20f,width * 0.2f,paint )

        paint.color = Color.GREEN
        paint.style = Style.STROKE

        canvas?.drawCircle(width/2f, height*0.80f,width * 0.2f,paint )

        when(state){
            SemaphoreState.GREEN -> {
                paint.color = Color.GREEN
                paint.style = Style.FILL
                canvas?.drawCircle(width/2f, height*0.80f,width * 0.2f,paint )
            }
            SemaphoreState.YELLOW -> {
                paint.color = Color.YELLOW
                paint.style = Style.FILL
                canvas?.drawCircle(width/2f, height/2f,width * 0.2f,paint )
            }
            SemaphoreState.RED-> {
                paint.color = Color.RED
                paint.style = Style.FILL
                canvas?.drawCircle(width/2f, height*0.20f,width * 0.2f,paint )
            }
        }

    }
}