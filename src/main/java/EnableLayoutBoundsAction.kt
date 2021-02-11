import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class EnableLayoutBoundsAction : AnAction() {

    override fun actionPerformed(actionEvent: AnActionEvent) {
        NotificationGroup("layout bounds", NotificationDisplayType.BALLOON, true)
            .createNotification(
                "Hello from the plugin!",
                "Message body",
                NotificationType.WARNING,
                null
            ).notify(actionEvent.project)
    }

}