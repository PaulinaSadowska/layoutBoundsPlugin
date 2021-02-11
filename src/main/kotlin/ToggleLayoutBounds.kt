import com.android.ddmlib.IDevice
import com.android.ddmlib.MultiLineReceiver
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ToggleLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        event.project?.getNotEmptyDevices()?.forEach { device ->
            device.toggleLayoutBounds()
        } ?: run {
            event.project?.showNotification("no device connected")
        }
    }
}

private fun IDevice.toggleLayoutBounds() {
    executeShellCommand("getprop debug.layout", SingleLineReceiver {
        setLayoutBounds(it.toBoolean().not())
    })
}

class SingleLineReceiver(private val processFirstLine: (response: String) -> Unit) : MultiLineReceiver() {

    private var closed = false

    override fun processNewLines(lines: Array<out String>?) {
        lines?.getOrNull(0)?.let { firstLine ->
            processFirstLine(firstLine)
            closed = true
        }
    }

    override fun isCancelled() = closed
}
