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
        event.project?.getNotEmptyDevices()?.forEach { device ->
            device.showLayoutBounds()
        } ?: run {
            event.project?.showNotification("no device connected")
        }
    }
}

fun Project.getNotEmptyDevices(): Array<IDevice>? =
    AndroidSdkUtils.getDebugBridge(this)?.devices?.takeIf { it.isNotEmpty() }

fun IDevice.showLayoutBounds() {
    executeShellCommand(
        "setprop debug.layout true ; service call activity 1599295570",
        NullOutputReceiver()
    )
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