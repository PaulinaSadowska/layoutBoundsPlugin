import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class HideLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        event.project?.getNotEmptyDevices()?.forEach { device ->
            device.setLayoutBounds(false)
        } ?: run {
            event.project?.showNotification("no device connected")
        }
    }
}