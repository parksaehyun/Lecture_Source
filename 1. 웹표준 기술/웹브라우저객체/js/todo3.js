

window. addEventListener("DOMContentLoaded", function() {
    todo.init();

    frmRegist.addEventListner("submit", function(e) {
        e.preventDefault();

        todo.add();
    })
})