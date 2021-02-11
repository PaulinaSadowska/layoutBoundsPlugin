import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.jetbrains.android.sdk.AndroidSdkUtils

class EnableLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val devices = event.project?.let { project ->
            AndroidSdkUtils.getDebugBridge(project)?.devices
        }

        if (devices.isNullOrEmpty()) {
            event.showNotification("no device connected")
        } else {
            event.showNotification("${devices.size} device(s) connected")
        }
    }

    private fun AnActionEvent.showNotification(message: String) {
        NotificationGroup("layout bounds", NotificationDisplayType.BALLOON, true)
            .createNotification(
                "Layout bounds plugin says:",
                message,
                NotificationType.WARNING,
                null
            ).notify(project)
    }
}