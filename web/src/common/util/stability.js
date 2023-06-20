export function antiZoomFromUser_PC(){
    document.addEventListener('mousewheel', function (e) {
        e = e || window.event;
        if ((e.wheelDelta && event.ctrlKey) || e.detail) {
            event.preventDefault();
        }
    }, {
        capture: false,
        passive: false
    });
}
export function antiZoomFromUser_Mobile(){
    window.onload = function() {
        document.addEventListener('touchstart', function(event) {
            if (event.touches.length > 1) {
                event.preventDefault()
            }
        })
        document.addEventListener('gesturestart', function(event) {
            event.preventDefault()
        })
    }
}