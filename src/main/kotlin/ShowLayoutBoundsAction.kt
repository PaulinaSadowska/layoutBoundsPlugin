import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.android.ddmlib.IDevice
import com.android.ddmlib.NullOutputReceiver
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils


class ShowLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val devices = event.project?.let { AndroidSdkUtils.getDebugBridge(it)?.devices }

        if (devices.isNullOrEmpty()) {
            event.project?.showNotification("no device connected")
        } else {
            event.project?.showNotification("${devices.size} device(s) connected")
        }
    }
}

fun Project.showNotification(message: String) {
    NotificationGroup("layout bounds", NotificationDisplayType.BALLOON, true)
        .createNotification(
            "Layout bounds plugin says:",
            message,
            NotificationType.WARNING,
            null
        ).notify(this)
}