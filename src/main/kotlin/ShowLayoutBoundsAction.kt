import com.intellij.notification.NotificationDisplayType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType


class ShowLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        NotificationGroup("layout bounds", NotificationDisplayType.BALLOON, true)
            .createNotification(
                "Layout bounds plugin says:",
                "Hello World!",
                NotificationType.WARNING,
                null
            ).notify(event.project)
    }
}