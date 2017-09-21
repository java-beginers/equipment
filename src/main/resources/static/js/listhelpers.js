// Выводит на экран окно подтверждения удаления записи
function confirmDelete(itemName, deleteLink) {
    if (true === confirm('Действительно удалить запись "' + itemName + '"?')) {
        window.location.href = deleteLink;
    }
}

$(document).ready(function () {
    $('div.item').hover(
        function () {
            $(this).find('div.itemMenu').fadeIn(200)
        },
        function () {
            $(this).find('div.itemMenu').fadeOut(200)
        }).click(
        function () {
            $(this).find('div.itemBody').fadeToggle(200)
        }
    )
});
