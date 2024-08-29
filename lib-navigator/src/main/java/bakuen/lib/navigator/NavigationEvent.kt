package bakuen.lib.navigator

sealed class NavigationEvent {
    data object Backward : NavigationEvent()
    class Forward(val next: ContentNode) : NavigationEvent()
    class Replace(val target: ContentNode) : NavigationEvent()
}