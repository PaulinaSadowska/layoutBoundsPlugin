import com.android.ddmlib.NullOutputReceiver
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils

class ShowLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val devices = event.project?.let { project ->
            AndroidSdkUtils.getDebugBridge(project)?.devices
        }

        devices?.takeIf { it.isNotEmpty() }?.forEach { device ->
            device.executeShellCommand(
                "setprop debug.layout true ; service call activity 1599295570",
                NullOutputReceiver()
            )
        } ?: run {
            event.project?.showNotification("no device connected")
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