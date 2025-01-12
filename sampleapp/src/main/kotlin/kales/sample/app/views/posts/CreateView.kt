package kales.sample.app.views.posts

import kales.actionview.ActionView
import kales.sample.app.views.shared.PostPartialView
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h2

class CreateView(
    bindings: PostViewModel? = PostViewModel()
) : ActionView<PostViewModel>(bindings) {
  override fun FlowContent.render() {
    div("container") {
      div("row") {
        div("col-sm-12") {
          h2 {
            +"New Post created"
          }
        }
      }
    }
    renderPartial(PostPartialView(bindings))
  }
}