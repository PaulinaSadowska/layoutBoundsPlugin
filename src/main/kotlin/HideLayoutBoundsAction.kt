import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class HideLayoutBoundsAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        event.project?.let { LayoutBoundsCommand(false).execute(it) }
    }
}