// Выводит на экран окно подтверждения удаления записи
function confirmDelete(itemName, deleteLink) {
    if (true === confirm('Действительно удалить запись "' + itemName + '"?')) {
        window.location.href = deleteLink;
    }
}

$(document).ready(function () {
    $('table.dataTable tbody tr').hover(
        function () {
            $(this).find('td div.rowActions').fadeIn(200)
        },
        function () {
            $(this).find('td div.rowActions').fadeOut(200)
        });
});
