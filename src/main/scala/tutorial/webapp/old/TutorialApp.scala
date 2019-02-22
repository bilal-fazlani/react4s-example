//package tutorial.webapp
//
//import com.github.ahnfelt.react4s._
//
//object TutorialApp extends App {
//  val component = Component(MainComponent)
//  ReactBridge.renderToDomById(component,"main")
//}
//
//object MyCss extends CssClass (
//  S.padding("10px"),
//  S.backgroundColor("pink"),
//  Css.hover(
//    S.backgroundColor("yellow")
//  )
//)
//
//case class MainComponent() extends Component[NoEmit]{
//  override def render(get: Get): Node = {
//    E.div(MyCss, Text("hello react4s"))
//  }
//}