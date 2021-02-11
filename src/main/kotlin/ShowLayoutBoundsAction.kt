import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ShowLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        event.project?.getNotEmptyDevices()?.forEach { device ->
            device.setLayoutBounds(true)
        } ?: run {
            event.project?.showNotification("no device connected")
        }
    }
}
